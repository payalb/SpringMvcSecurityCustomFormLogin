package com.java.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource ds;
	@Autowired
	MyUserDetailsService service;

	@Bean
	public PasswordEncoder getEncoder() {
		Map<String, PasswordEncoder> map = new HashMap<>();
		map.put("bcrypt", new BCryptPasswordEncoder(12));
		return new DelegatingPasswordEncoder("bcrypt", map);

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(getEncoder());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated();

		// spring automatically redirects to page automatically
		// jsp page should be login.jsp only
		http.formLogin().loginPage("/login").usernameParameter("staticEmail2").passwordParameter("inputPassword2")
				.permitAll();

	//	http.csrf().disable();

	}

}
