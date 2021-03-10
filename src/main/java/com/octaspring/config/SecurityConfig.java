package com.octaspring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends  WebSecurityConfigurerAdapter{
		@Autowired
		PasswordEncoder passwordEncoder;
	
		@Autowired
		DataSource dataSource;
		
		@Bean
		public DataSource getDataSource() {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/cursos_en?serverTimezone=UTC");
			dataSource.setUsername("root");
			dataSource.setPassword("root");
			return dataSource;
		}
		@Bean
		public PasswordEncoder passwordEncoder () {
			return new BCryptPasswordEncoder();
		}
		public void configure(AuthenticationManagerBuilder auth) throws Exception{
			//permitir la autentificacion
			//jdbc si hay una tabla con usuarios, roles etcs
			/*
			 * auth.jdbcAuthentication().dataSource(this.getDataSource()).passwordEncoder(
			 * passwordEncoder)
			 * .usersByUsernameQuery("select email as username, password, 'true' as enabled"
			 * + " from user_person " + "where email=?")
			 * .authoritiesByUsernameQuery("select r.name " + "form user_role ur " +
			 * "inner join role r on r.id = ur.role where ur.user_person = ? ");
			 */
			///in memory cuando esta fija sin base de datos 
			
			auth.jdbcAuthentication().dataSource(this.getDataSource())
		    .passwordEncoder(this.passwordEncoder())
		    .usersByUsernameQuery(
		      "SELECT email, password, 'true' as enabled "
		      + "FROM user_person "
		      + "WHERE email = ?")
		    .authoritiesByUsernameQuery(""
		      + "SELECT up.email, r.name "
		      + "FROM user_person up "
		      + "INNER JOIN user_role ur ON up.id = ur.user_person "
		      + "INNER JOIN role r ON r.id = ur.role "
		      + "WHERE email = ?");
			 
		}
		
		
		protected void configure(HttpSecurity http) throws Exception{
			// Controllar el acceso a las rutas (acctiones)
			http.authorizeRequests()
			.antMatchers("/","/login", "/register","/user-create")
			.permitAll().antMatchers("/**")
			.hasAnyRole("ADMIN","TEACHER")
			
			
			.and()
			.formLogin()
			.defaultSuccessUrl("/")
			.loginPage("/login")
				.permitAll()
			.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login")
				.permitAll()
			.and()
				.csrf()
				.disable();
		}
}
