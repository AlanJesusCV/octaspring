package com.octaspring.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.octaspring.dao.LangInterface;
import com.octaspring.entity.Category;
import com.octaspring.entity.Lang;

public class LangService implements LangInterface {
//Atributo JDBC para realizar la manupulacion de datos a traves
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	public LangService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void save(Lang lang) {
		// TODO Auto-generated method stub
		sql = "insert into lang (lang) value(?)";
		jdbcTemplate.update(sql, lang.getLang());
	}

	public void update(Lang lang) {
		// TODO Auto-generated method stub
		sql = "update lang set lang=? where id = ?";
		jdbcTemplate.update(sql, lang.getLang(),lang.getId());
	}

	public void delete(long id) {
		// TODO Auto-generated method stub
		sql = "delete  from lang where id = ?";
		jdbcTemplate.update(sql, id);
	}

	public List<Lang> findByAll() {
		// TODO Auto-generated method stub
		// System.out.println("Find by all");
		sql = "SELECT * FROM lang";
		return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Lang.class));
	}

	
	public Lang findById(long id) {
		sql="select * from lang where id = ?";
		
		// TODO Auto-generated method stub
		return this.jdbcTemplate.queryForObject(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(Lang.class));
	}

	
}
