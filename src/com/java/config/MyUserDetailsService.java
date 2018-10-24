package com.java.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	NamedParameterJdbcTemplate template;

	@Override
	public UserDetails loadUserByUsername(String username) {
		System.out.println("in query!!"+ username);
		try {
		User user=template.query(
				"select u.username , u.password, a.authority from users u, authorities a where u.username= a.username and u.username = '"+ username+ "'",
				new ResultSetExtractor<User>() {

					@Override
					public User extractData(ResultSet rs) throws SQLException, DataAccessException {
						System.out.println("In result set");
						String password="";
						List<GrantedAuthority> authorities = new ArrayList<>();
						while (rs.next()) {
							password= rs.getString("password");
							authorities.add(new SimpleGrantedAuthority(rs.getString("authority").substring(5)));
							System.out.println(rs.getString("authority").substring(5));
						}
						if (authorities.size() > 0) {
							System.out.println("Successfully validated userame, password!");
							return new User(username, password, authorities);
						} else {
							throw new RuntimeException("Invalid credentials!");
						}
					}

				});
		System.out.println(user);
		return user;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
