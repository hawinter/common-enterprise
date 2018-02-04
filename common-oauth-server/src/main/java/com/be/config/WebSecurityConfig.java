package com.be.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
//	auth.inMemoryAuthentication()
//	  .withUser("john").password("123").roles("USER").and()
//	  .withUser("tom").password("111").roles("ADMIN").and()
//	  .withUser("user1").password("pass").roles("USER").and()
//	  .withUser("admin").password("nimda").roles("ADMIN");
    	
    	auth.jdbcAuthentication().dataSource(dataSource).rolePrefix("ROLE_")
			.usersByUsernameQuery("select user_name as username, password, enabled from user where user_name=?")
			.authoritiesByUsernameQuery("select user_name as username, name as authority from user inner join user_role on user.id = user_role.user_id inner join role on role.id = user_role.role_id where user_name=?");

    }// @formatter:on

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
		http.authorizeRequests().antMatchers("/login").permitAll()
		.antMatchers("/oauth/token/revokeById/**").permitAll()
		.antMatchers("/tokens/**").permitAll()
		//.anyRequest().authenticated()
		.anyRequest().permitAll()
		.and().formLogin().permitAll()
		.and().csrf().disable();
		// @formatter:on
    }

}
