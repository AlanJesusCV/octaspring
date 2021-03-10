package com.octaspring.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.octaspring.dao.SubcategoryInterface;
import com.octaspring.entity.Category;
import com.octaspring.entity.Subcategory;

public class SubcategoryService implements SubcategoryInterface{

	private JdbcTemplate jdbcTemplate;
	
	String sql;
	
	public SubcategoryService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(Subcategory subCategory) {
		// TODO Auto-generated method stub
		sql = "INSERT INTO subcategory (name, description, status, category) VALUE (?,?,?,?)";
		subCategory.setStatus(1);
		jdbcTemplate.update(sql, subCategory.getName(), subCategory.getDescription(), subCategory.getStatus(), subCategory.getCategory().getId());
		
	}

	@Override
	public void update(Subcategory subCategory) {
		// TODO Auto-generated method stub
				sql = "UPDATE subcategory SET name=?, description=?, status=?, category=? WHERE id = ?";
				//subCategory.setStatus(1);
				jdbcTemplate.update(sql, subCategory.getName(), subCategory.getDescription(), subCategory.getStatus(), subCategory.getCategory().getId(), subCategory.getId());
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
				sql = "DELETE FROM Subcategory WHERE id = ?";
				jdbcTemplate.update(sql, id);
		
	}

	@Override
	public List<Subcategory> findByAll() {
		// TODO Auto-generated method stub
		sql = "SELECT * FROM subcategory sc INNER JOIN  category c ON c.id = sc.category";
		List<Subcategory> listSubcategory = this.jdbcTemplate.query(sql, new RowMapper<Subcategory>() {
			@Override
			public Subcategory mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Subcategory sc = new Subcategory();
				sc.setId(new Long(rs.getInt("sc.id")));
				sc.setName(rs.getString("sc.name"));
				sc.setDescription(rs.getString("sc.description"));
				sc.setStatus(rs.getInt("sc.status"));
				
				Category c = new Category();
				c.setName(rs.getString("c.name"));
				c.setId((long) rs.getInt("c.id"));
				sc.setCategory(c);
			
				return sc;
			}
		});
		return listSubcategory;
	}

	@Override
	public Subcategory findById(long id) {
		// TODO Auto-generated method stub
				sql = "SELECT * FROM subcategory sc INNER JOIN  category c ON c.id = sc.category WHERE sc.id = ?";
				Subcategory sc = jdbcTemplate.queryForObject(sql, new Object[] {id}, new RowMapper<Subcategory>() {
					@Override
					public Subcategory mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						Subcategory sc = new Subcategory();
						sc.setId(new Long(rs.getInt("sc.id")));
						sc.setName(rs.getString("sc.name"));
						sc.setDescription(rs.getString("sc.description"));
						sc.setStatus(rs.getInt("sc.status"));
						
						Category c = new Category();
						c.setName(rs.getString("c.name"));
						c.setId((long) rs.getInt("c.id"));
						sc.setCategory(c);	
						return sc;
					}
				});
				return sc;
			}

	@Override
	public void updateStatus(Subcategory subcategory) {
		// TODO Auto-generated method stub
		
	}
}