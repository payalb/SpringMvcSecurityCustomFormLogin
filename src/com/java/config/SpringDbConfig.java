package com.java.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class SpringDbConfig {

	@Autowired DataSource ds;
	
	@Bean
	public NamedParameterJdbcTemplate getTemplate() {
		NamedParameterJdbcTemplate  template= new NamedParameterJdbcTemplate(ds);
		return template;
	}
	
	@Profile("dev")
	@Bean("pr")
	public static PropertySourcesPlaceholderConfigurer getPropDev() {
		PropertySourcesPlaceholderConfigurer pc= new PropertySourcesPlaceholderConfigurer();
		pc.setLocation(new ClassPathResource("database.properties"));
		return pc;
	}
	
	@Profile("test")
	@Bean("pr")
	public static PropertySourcesPlaceholderConfigurer getPropTest() {
		PropertySourcesPlaceholderConfigurer pc= new PropertySourcesPlaceholderConfigurer();
		pc.setLocation(new ClassPathResource("database-test.properties"));
		return pc;
	}
	
	@Profile("prod")
	@Bean("pr")
	public static PropertySourcesPlaceholderConfigurer getPropProd() {
		PropertySourcesPlaceholderConfigurer pc= new PropertySourcesPlaceholderConfigurer();
		pc.setLocation(new ClassPathResource("database-prod.properties"));
		return pc;
	}
	
	/*@Bean(initMethod="migrate", value="flyway")
	public Flyway flyway() {
		Flyway flyway= new Flyway();
		flyway.setLocations("classpath:/migration");
		flyway.setBaselineOnMigrate(true);
		flyway.setDataSource(ds);
		return flyway;
	}*/
}
