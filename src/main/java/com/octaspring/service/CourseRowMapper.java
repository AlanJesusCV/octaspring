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
		return null;
	}

	

}
