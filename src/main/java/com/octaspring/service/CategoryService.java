package com.octaspring.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.octaspring.dao.CategoryInterface;
import com.octaspring.entity.Category;

public class CategoryService implements CategoryInterface {
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	public CategoryService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public void save(Category category) {
		// TODO Auto-generated method stub
		sql = "insert into Category (name,description,status,image) values (?,?,?,?)";
		category.setStatus(1);
		jdbcTemplate.update(sql, category.getName(),category.getDescription(),category.getStatus(),category.getImage());
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		sql="update category set name=?,description=?,image=? where id = ?";
		jdbcTemplate.update(sql, category.getName(),category.getDescription(),category.getImage(),category.getId());

	}

	@Override
	public void delete(long id) {
		sql = "delete from category where id = ? ";
		jdbcTemplate.update(sql, id);

	}
	@Override
	public void deleteLogical(long id,int status) {
		sql = "update  category set status=? where id = ? ";
		jdbcTemplate.update(sql, status,id);

	}

	@Override
	public List<Category> findByAll() {
	
		sql ="select * from Category";
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Category.class));
	}

	@Override
	public Category findById(long id) {
		sql="select * from category where id = ?";
		
		// TODO Auto-generated method stub
		return this.jdbcTemplate.queryForObject(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(Category.class));
	}

}
