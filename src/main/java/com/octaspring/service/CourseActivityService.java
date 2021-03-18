package com.octaspring.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.octaspring.dao.CourseActivityInterface;
import com.octaspring.entity.Category;
import com.octaspring.entity.Course;
import com.octaspring.entity.CourseActivity;

public class CourseActivityService implements CourseActivityInterface {
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	
	public CourseActivityService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Override
	public void save(CourseActivity courseActivity) {
		sql = "INSERT INTO course_activity (title, description, duration,file,video,course) VALUES (?,?,?,?,?,?)";
		jdbcTemplate.update(sql, courseActivity.getTitle(),courseActivity.getDescription(),courseActivity.getDuration(),courseActivity.getFile(),
				courseActivity.getVideo(),courseActivity.getCourse().getId());		
		
	}


	@Override
	public void update(CourseActivity courseActivity) {
		sql = "UPDATE course_activity SET title=?, description=?, duration=?, file=?, video=?, course =? where id= ? ";
		
		jdbcTemplate.update(sql, courseActivity.getTitle(), courseActivity.getDescription(),courseActivity.getDuration(),
				courseActivity.getFile(),courseActivity.getVideo(),courseActivity.getCourse().getId(),courseActivity.getId());	
		
	}


	@Override
	public void delete(long id) {
		sql = "DELETE FROM course_activity where id = ?";
		jdbcTemplate.update(sql,id);
		
	}


	@Override
	public List<CourseActivity> findByAll(long id) {
		sql = "SELECT * FROM course_activity ca INNER JOIN course co ON co.id = ca.course where ca.course = course";
		List<CourseActivity> listaCourseActivity = this.jdbcTemplate.query(sql, new RowMapper<CourseActivity>() {
			@Override
			public CourseActivity mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseActivity ca = new CourseActivity();
				ca.setId(new Long(rs.getInt("ca.id")));
				ca.setTitle(rs.getString("ca.title"));
				ca.setDescription(rs.getString("ca.description"));
				ca.setDuration(rs.getString("ca.duration"));
				ca.setFile(rs.getString("ca.file"));
				ca.setVideo(rs.getString("ca.video"));
				
				Course co = new Course();
				co.setId(new Long(rs.getInt("co.id")));
				co.setTitle(rs.getString("co.title"));
				co.setSubtitle(rs.getString("co.subtitle"));
				co.setDescription(rs.getString("co.description"));
				co.setPrice(rs.getDouble("co.price"));
				co.setStatus(rs.getInt("co.status"));
				co.setPublished(rs.getDate("co.published"));
				ca.setCourse(co);

				return ca;
			}
		});
		return listaCourseActivity;
	}


	@Override
	public CourseActivity findById(long id) {
		// TODO Auto-generated method stub
		sql = "select * from  course_activity ca inner join course c on c.id = ca.course where ca.id = ?;";
		CourseActivity ca = this.jdbcTemplate.queryForObject(sql, new Object[] {id}, new RowMapper<CourseActivity>() {
			@Override
			public CourseActivity mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseActivity ca = new CourseActivity();
				ca.setId(new Long(rs.getInt("ca.id")));
				ca.setTitle(rs.getString("ca.title"));
				ca.setDescription(rs.getString("ca.description"));
				ca.setDuration(rs.getString("ca.duration"));
				ca.setFile(rs.getString("ca.file"));
				ca.setVideo(rs.getString("ca.video"));
				
				Course c = new Course();
				c.setId((long) rs.getInt("c.id"));
				ca.setCourse(c);
				
				return ca;
			}
		});
		return ca;
	}


	@Override
	public List<CourseActivity> findByc(int id) {
		sql = "select * from  course_activity ca inner join course c on c.id = ca.course where ca.course = ?";
		
		List<CourseActivity> listCourse = this.jdbcTemplate.query(sql, new Object[] {id}, new RowMapper<CourseActivity>() {

			@Override
			public CourseActivity mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				CourseActivity ca = new CourseActivity();
				ca.setId(new Long(rs.getInt("ca.id")));
				ca.setTitle(rs.getString("ca.title"));
				ca.setDescription(rs.getString("ca.description"));
				ca.setDuration(rs.getString("ca.duration"));
				ca.setFile(rs.getString("ca.file"));
				ca.setVideo(rs.getString("ca.video"));
				
				Course c = new Course();
				c.setId((long) rs.getInt("c.id"));
				ca.setCourse(c);
				
				return ca;
			}
		});
		return listCourse;
	}
	
}
