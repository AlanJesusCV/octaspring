package com.octaspring.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.octaspring.dao.LevelInterface;
import com.octaspring.entity.Lang;
import com.octaspring.entity.Level;

public class LevelService implements LevelInterface {
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	public LevelService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void save(Level level) {
		// TODO Auto-generated method stub
		sql = "insert into level (level) value(?)";
		jdbcTemplate.update(sql, level.getLevel());
	}
	
	@Override
	public void update(Level level) {
		// TODO Auto-generated method stub
		sql = "update level set level=? where id = ?";
		jdbcTemplate.update(sql, level.getLevel(),level.getId());
	}
	
	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		sql = "delete  from level where id = ?";
		jdbcTemplate.update(sql, id);
	}
	
	@Override
	public List<Level> findByAll() {
		// TODO Auto-generated method stub
		// System.out.println("Find by all");
		sql = "SELECT * FROM level";
		return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Level.class));
	}

	@Override
	public Level findById(long id) {
		sql="select * from level where id = ?";
		
		// TODO Auto-generated method stub
		return this.jdbcTemplate.queryForObject(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(Level.class));
	}

}
