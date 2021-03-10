package com.octaspring.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.octaspring.dao.UserPersonInterface;
import com.octaspring.entity.Role;
import com.octaspring.entity.UserPerson;

public class UserPersonService implements UserPersonInterface{
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Bean
	public PasswordEncoder passwordEncoder1 () {
		return new BCryptPasswordEncoder();
	}
	
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	public UserPersonService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public void save(UserPerson userPerson,int role) {
		// TODO Auto-generated method stub
		sql = "insert into user_person(name,last_name,email,password,gender,status,registered,photo) values (?,?,?,?,?,?,?,?)";
		userPerson.setRegistered(new Date());
		userPerson.setPhoto("default.png");
		userPerson.setStatus(1);	
		
		GeneratedKeyHolder generateKeyHolder = new GeneratedKeyHolder();
		
		
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement stm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				stm.setString(1, userPerson.getName());
				stm.setString(2, userPerson.getLastname());
				stm.setString(3, userPerson.getEmail());
				stm.setString(4, passwordEncoder.encode(userPerson.getPassword()));
				stm.setInt(5, userPerson.getGender());
				stm.setInt(6, userPerson.getStatus());
				stm.setDate(7, new java.sql.Date(userPerson.getRegistered().getTime()) );
				stm.setString(8, userPerson.getPhoto());


				return stm;
			}
		
		}, generateKeyHolder);
		//jdbcTemplate.update(sql, userPerson.getName(),userPerson.getLastname(),userPerson.getEmail(),userPerson.getPassword(),userPerson.getGender(),userPerson.getStatus(),userPerson.getRegistered(),userPerson.getPhoto());

		// Relacion
		sql = "insert into user_role (role, user_person) values (?,?) ";
		jdbcTemplate.update(sql,role,generateKeyHolder.getKey().longValue());
	}

	public void update(UserPerson userPerson) {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	
	public List<UserPerson> findByAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<UserPerson> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}


	@Override
	public UserPerson findByEmail(String email) {
		sql ="select id from user_person where email=?";
		return this.jdbcTemplate.queryForObject(sql, new Object[] {email}, BeanPropertyRowMapper.newInstance(UserPerson.class));
	}

}
