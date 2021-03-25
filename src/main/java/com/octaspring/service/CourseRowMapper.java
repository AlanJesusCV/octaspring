package com.octaspring.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.octaspring.entity.Category;
import com.octaspring.entity.Course;
import com.octaspring.entity.Lang;
import com.octaspring.entity.Level;
import com.octaspring.entity.Subcategory;
import com.octaspring.entity.UserPerson;

public class CourseRowMapper implements org.springframework.jdbc.core.RowMapper<Course> {

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
		//c.setPublished(rs.getDate("c.published"));
		c.setImage(rs.getString("c.image"));
		
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
		ow.setName(rs.getString("ow.name"));
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

	

}
