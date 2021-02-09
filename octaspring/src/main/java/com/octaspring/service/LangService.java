package com.octaspring.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.octaspring.dao.LangInterface;
import com.octaspring.entity.Lang;

public class LangService implements LangInterface {
//Atributo JDBC para realizar la manupulacion de datos a traves
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	public LangService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void save(Lang lang) {
		// TODO Auto-generated method stub
		sql = "insert into lang (lang) value(?)";
		jdbcTemplate.update(sql, lang.getLang());
	}

	@Override
	public void update(Lang lang) {
		// TODO Auto-generated method stub
		sql = "update lang set lang=? where id = ?";
		jdbcTemplate.update(sql, lang.getLang(),lang.getId());
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		sql = "delete  from lang where id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Lang> findByAll() {
		// TODO Auto-generated method stub
		// System.out.println("Find by all");
		sql = "SELECT * FROM lang";
		return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Lang.class));
	}

	@Override
	public List<Lang> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
