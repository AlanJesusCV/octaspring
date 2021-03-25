package com.octaspring.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.octaspring.dao.PurchaseInterface;
import com.octaspring.dao.UserPersonInterface;
import com.octaspring.entity.Course;
import com.octaspring.entity.Purchase;

@Service
public class PurchaseService  implements  PurchaseInterface {
	private JdbcTemplate jdbcTemplate;
	String sql;
	@Autowired
	private UserPersonInterface userpersonInterface;
	
	public PurchaseService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	double total = 0.0;
	@Override
	public void save(Set<Course> student_cart2) {
		// TODO Auto-generated method stub
		Date fecha = new Date();
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    String email = userDetail.getUsername();
	    userpersonInterface.findByEmail(email).getId();
	    
	    for (Course course : student_cart2) {
	    	total += course.getPrice();
	    }
		GeneratedKeyHolder generateKeyHolder = new GeneratedKeyHolder();
	    sql="insert into purchase(user_person,registered,total) values (?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@SuppressWarnings("deprecation")
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement stm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				stm.setLong(1, userpersonInterface.findByEmail(email).getId());
				stm.setDate(2,new java.sql.Date (fecha.getDate()));
				stm.setDouble(3, total);
				return stm;
			}
		},generateKeyHolder);
		
		
	    for (Course course : student_cart2) {
	    	sql = "insert into purchase_course(price,course,purchase) values (?,?,?)";
			jdbcTemplate.update(sql,course.getPrice(),course.getId(),generateKeyHolder.getKey().longValue());
	    }
	}

	@Override
	public void update(Purchase purchase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Purchase> findByAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Purchase> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
