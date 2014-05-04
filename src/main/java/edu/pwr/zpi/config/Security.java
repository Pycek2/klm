package edu.pwr.zpi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import edu.pwr.zpi.web.security.AjaxAuthenticationFailureHandler;
import edu.pwr.zpi.web.security.AjaxAuthenticationSuccessHandler;
import edu.pwr.zpi.web.security.Http401UnauthorizedEntryPoint;

//@Configuration
//@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
	private static Logger logger = LoggerFactory.getLogger(Security.class);

	@Autowired
	private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

	@Autowired
	private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

	// @Autowired
	// private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

	@Autowired
	private Http401UnauthorizedEntryPoint authenticationEntryPoint;

	public Security() {
		super();
		logger.info("Security configuration created");
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN");
    }
    
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/index.html").antMatchers("/ang/**")
				.antMatchers("/images/**").antMatchers("/img/**")
				.antMatchers("/api/data/public")
			.and()
				.debug(true);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.and()
				.formLogin()
				.loginProcessingUrl("/api/authentication")
				.successHandler(ajaxAuthenticationSuccessHandler)
				.failureHandler(ajaxAuthenticationFailureHandler)
				.usernameParameter("j_username")
				.passwordParameter("j_password")
				.permitAll()
				.and()
				// .logout()
				// .logoutUrl("/api/logout")
				// .logoutSuccessHandler(ajaxLogoutSuccessHandler)
				// .deleteCookies("JSESSIONID")
				// .permitAll()
				// .and()
				.csrf().disable().headers().frameOptions().disable()
				.authorizeRequests()

				// .antMatchers("/app/rest/authenticate").permitAll()
				.antMatchers("/admin**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/**").authenticated();
		// .antMatchers("/websocket/tracker").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/websocket/**").permitAll()
		// .antMatchers("/metrics*").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/metrics/**").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/health*").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/health/**").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/trace*").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/trace/**").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/dump*").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/dump/**").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/shutdown*").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/shutdown/**").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/beans*").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/beans/**").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/info*").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/info/**").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/autoconfig*").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/autoconfig/**").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/env*").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/env/**").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/trace*").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/trace/**").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/swagger-ui/**").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/api-docs/**").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/protected/**").authenticated();

	}

	@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
	private static class GlobalSecurityConfiguration extends
			GlobalMethodSecurityConfiguration {
	}

}
