package edu.pwr.zpi.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.incrementer.AbstractSequenceMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;

import edu.pwr.zpi.data.dao.UserDao;
import edu.pwr.zpi.data.dao.mappers.UserAccountMapper;
import edu.pwr.zpi.data.entities.User;

@Repository
public class UserJdbcDaoImpl implements UserDao {

	private static org.slf4j.Logger logger = LoggerFactory
			.getLogger(UserJdbcDaoImpl.class);
	private NamedParameterJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;
	private AbstractSequenceMaxValueIncrementer sequence;

	private static Map<String, Long> ROLE_MAP;

	static {
		ROLE_MAP = new HashMap<String, Long>();
		ROLE_MAP.put("ADMIN", 1L);
		ROLE_MAP.put("DATA_BROWSER", 2L);
		ROLE_MAP.put("DEVICE_MANAGER", 3L);
		ROLE_MAP.put("WATCHER", 4L);

	}

	@Autowired
	@Qualifier("dataSourcePooled")
	public void setDataSource(DataSource ds) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(ds);
		insert = new SimpleJdbcInsert(ds).withTableName("USERACCOUNT");
		sequence = new PostgreSQLSequenceMaxValueIncrementer(ds,
				"useraccount_seq");
	}

	@Override
	public List<User> getAllUsers() {
		String sql = "select  u.id, u.login, u.password, u.first_name, u.last_name, u.disabled, r.role_name "
				+ " from useraccount u join useraccount_role ur on u.id = ur.useraccount_id "
				+ " join role r on ur.role_id = r.id";

		List<User> result = jdbcTemplate.query(sql, new UserAccountMapper());
		return result;
	}

	@Override
	public User find(long id) {
		// String sql =
		// "select id, login, password, first_name, last_name, disabled from useraccount where id = :userId";
		String sql = "select  u.id, u.login, u.password, u.first_name, u.last_name, u.disabled, r.role_name "
				+ " from useraccount u join useraccount_role ur on u.id = ur.useraccount_id "
				+ " join role r on ur.role_id = r.id where u.id = :userId";
		SqlParameterSource namedParameters = new MapSqlParameterSource(
				"userId", id);

		List<User> result = jdbcTemplate.query(sql, namedParameters,
				new UserAccountMapper());
		if (result != null && result.size() == 1) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public User save(User u) {
		User result = null;
		if (u.getId() == 0) {
			result = inserUser(u);
			addRole(result.getId(), result.getRole());
		} else {
			result = updateUser(u);
			removeUserRoles(result.getId());
			addRole(result.getId(), result.getRole());
		}
		return u;
	}

	private User updateUser(User u) {
		String sql = "update useraccount set login=:login, password=:password, "
				+ "first_name=:first_name, last_name=:last_name, disabled=:disabled "
				+ "where id=:id";

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", u.getId());
		parameters.put("login", u.getLogin());
		parameters.put("password", u.getPassword());
		parameters.put("first_name", u.getFirstName());
		parameters.put("last_name", u.getLastName());
		parameters.put("disabled", u.isDisabled() ? 1 : 0);
		jdbcTemplate.update(sql, parameters);

		return u;
	}

	private User inserUser(User u) {
		long id = sequence.nextLongValue();
		u.setId(id);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", u.getId());
		parameters.put("login", u.getLogin());
		parameters.put("password", u.getPassword());
		parameters.put("first_name", u.getFirstName());
		parameters.put("last_name", u.getLastName());
		parameters.put("disabled", u.isDisabled() ? 1 : 0);

		insert.execute(parameters);
		return u;
	}

	private void addRole(long userId, String role) {
		String sql = "insert into USERACCOUNT_ROLE(useraccount_id, role_id) values(:userId,:roleId)";

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("userId", userId);
		params.put("roleId", ROLE_MAP.get(role));
	

		int result = jdbcTemplate.update(sql, params);

		logger.debug("Added {} user role", result);
	}

	@Override
	public void remove(long id) {
		String sql = "delete from useraccount where id = :userId";
		SqlParameterSource namedParameters = new MapSqlParameterSource(
				"userId", id);

		int result = jdbcTemplate.update(sql, namedParameters);

		logger.debug("Removed {} user", result);
	}

	@Override
	public void removeUserRoles(long userId) {
		String sql = "delete from USERACCOUNT_ROLE where useraccount_id = :userId";
		SqlParameterSource namedParameters = new MapSqlParameterSource(
				"userId", userId);

		int result = jdbcTemplate.update(sql, namedParameters);

		logger.debug("Removed {} user role", result);
	}

	@Override
	public VerificationResult verify(String login, String password) {

		// String sql =
		// "select id, login, password, first_name, last_name, disabled from useraccount where login = :login and password = :passwd";
		String sql = "select  u.id, u.login, u.password, u.first_name, u.last_name, u.disabled, r.role_name "
				+ " from useraccount u join useraccount_role ur on u.id = ur.useraccount_id "
				+ " join role r on ur.role_id = r.id where u.login = :login and u.password = :passwd";
		Map<String, String> params = new HashMap<String, String>();

		params.put("login", login);
		params.put("passwd", password);

		List<User> result = jdbcTemplate.query(sql, params,
				new UserAccountMapper());
		if (result != null && result.size() == 1) {
			User u = result.get(0);
			VerificationResult vr =  new VerificationResult(true, u.getRole());
			vr.firstName = u.getFirstName();
			vr.lastName = u.getLastName();
			return vr;
		}
		return new VerificationResult(false);
	}

}
