package com.octaspring.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.octaspring.dao.CourseInterface;
import com.octaspring.entity.Category;
import com.octaspring.entity.Course;
import com.octaspring.entity.Lang;
import com.octaspring.entity.Level;
import com.octaspring.entity.Subcategory;
import com.octaspring.entity.UserPerson;

public class CourseService implements CourseInterface {
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	public CourseService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(Course course) {
		sql = "INSERT INTO course (title, subtitle, description, price, status,published,lang,level,owner,subcategory,category) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		course.setStatus(1);
		course.setPublished(new Date().toString());
		jdbcTemplate.update(sql, course.getTitle(),course.getSubtitle(), course.getDescription(),course.getPrice(), course.getStatus(),course.getPublished(),course.getLang().getId(),
				course.getLevel().getId(),course.getOwner().getId(),course.getSubcategory().getId(), course.getCategory().getId());		
	}

	@Override
	public void update(Course course) {
		// TODO Auto-generated method stub
		sql = "UPDATE course SET title=?, subtitle=?, description=?, price=?,  lang=?, level=?, owner=?, subcategory=?, category=? where id = ? ";
		
		jdbcTemplate.update(sql, course.getTitle(),course.getSubtitle(), course.getDescription(),course.getPrice(),course.getLang().getId(),
				course.getLevel().getId(),course.getOwner().getId(),course.getSubcategory().getId(), course.getCategory().getId(),course.getId());	
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		sql = "DELETE FROM course where id = ?";
		jdbcTemplate.update(sql,id);
	}

	@Override
	public List<Course> findByAll() {
		// TODO Auto-generated method stub
		sql ="select * from course c inner join lang la on la.id = c.lang inner join level le on le.id = c.level inner join user_person ow on ow.id = c.owner inner join subcategory sc on sc.id = c.subcategory inner join category ca on ca.id = c.category";
		List<Course> listCourse = this.jdbcTemplate.query(sql, new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Course c = new Course();
				c.setId(new Long(rs.getInt("c.id")));
				c.setTitle(rs.getString("c.title"));
				c.setSubtitle(rs.getString("c.subtitle"));
				c.setDescription(rs.getString("c.description"));
				c.setPrice(rs.getDouble("c.price"));
				c.setStatus(rs.getInt("c.status"));
				c.setPublished(rs.getString("c.published"));
				
				Lang la = new Lang();
				la.setId((long) rs.getInt("la.id"));
				la.setLang(rs.getString("la.lang"));
				c.setLang(la);
				
				Level le = new Level();
				le.setId((long) rs.getInt("le.id"));
				le.setLevel(rs.getString("le.level"));
				c.setLevel(le);
				
				UserPerson ow = new UserPerson();
				ow.setEmail(rs.getString("ow.email"));
				ow.setId((long) rs.getInt("ow.id"));
				c.setOwner(ow);
				
				Subcategory sc = new Subcategory();
				sc.setName(rs.getString("sc.name"));
				sc.setId((long) rs.getInt("sc.id"));
				c.setSubcategory(sc);
				
				Category ca = new Category();
				ca.setName(rs.getString("ca.name"));
				ca.setId((long) rs.getInt("ca.id"));
				c.setCategory(ca);
				
				return c;
			}
			
		});
		return listCourse;
	}

	@Override
	public Course findById(long id) {
		// TODO Auto-generated method stub
		sql ="select * from course c inner join lang la on la.id = c.lang inner join level le on le.id = c.level inner join user_person ow on ow.id = c.owner inner join subcategory sc on sc.id = c.subcategory inner join category ca on ca.id = c.category where c.id = ?";
		Course c = jdbcTemplate.queryForObject(sql, new Object[] {id}, new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Course c = new Course();
				c.setId(new Long(rs.getInt("c.id")));
				c.setTitle(rs.getString("c.title"));
				c.setSubtitle(rs.getString("c.subtitle"));
				c.setDescription(rs.getString("c.description"));
				c.setPrice(rs.getDouble("c.price"));
				c.setStatus(rs.getInt("c.status"));
				c.setPublished(rs.getString("c.published"));
				
				Lang la = new Lang();
				la.setId((long) rs.getInt("la.id"));
				la.setLang(rs.getString("la.lang"));
				c.setLang(la);
				
				Level le = new Level();
				le.setId((long) rs.getInt("le.id"));
				le.setLevel(rs.getString("le.level"));
				c.setLevel(le);
				
				UserPerson ow = new UserPerson();
				ow.setEmail(rs.getString("ow.email"));
				ow.setId((long) rs.getInt("ow.id"));
				c.setOwner(ow);
				
				Subcategory sc = new Subcategory();
				sc.setName(rs.getString("sc.name"));
				sc.setId((long) rs.getInt("sc.id"));
				c.setSubcategory(sc);
				
				Category ca = new Category();
				ca.setName(rs.getString("ca.name"));
				ca.setId((long) rs.getInt("ca.id"));
				c.setCategory(ca);
				
				return c;
			}
			
		});
		return c;
	}
	
	@Override
	public void updateStatus(Course course) {
		// TODO Auto-generated method stub
		
	}
	
}
