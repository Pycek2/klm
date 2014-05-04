package edu.pwr.zpi.data.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.pwr.zpi.data.entities.User;

public class UserAccountMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int index) throws SQLException {
		User user = new User();

		user.setId(rs.getLong("id"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		int disabled = rs.getInt("disabled");
		user.setDisabled(disabled > 0);
		user.setRole(rs.getString("role_name"));
		return user;
	}

}
