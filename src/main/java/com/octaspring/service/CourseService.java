package com.octaspring.service;

import java.awt.print.Pageable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.octaspring.dao.CourseInterface;
import com.octaspring.dao.UserPersonInterface;
import com.octaspring.entity.Category;
import com.octaspring.entity.Course;
import com.octaspring.entity.Lang;
import com.octaspring.entity.Level;
import com.octaspring.entity.Subcategory;
import com.octaspring.entity.UserHasCourse;
import com.octaspring.entity.UserPerson;

public class CourseService implements CourseInterface {
	private JdbcTemplate jdbcTemplate;
	String sql;
	@Autowired
	private UserPersonInterface userpersonInterface;
	
	public CourseService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(Course course) {
		sql = "INSERT INTO course (title, subtitle, description, price, status,image,published,lang,level,owner,subcategory,category) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		course.setStatus(1);
		course.setPublished((java.sql.Date) new Date());
		jdbcTemplate.update(sql, course.getTitle(),course.getSubtitle(), course.getDescription(),course.getPrice(), course.getStatus(),course.getImage(),course.getPublished(),course.getLang().getId(),
				course.getLevel().getId(),course.getOwner().getId(),course.getSubcategory().getId(), course.getCategory().getId());		
	}
	
	@Override
	public void adduserHasCourse(long idCourse) {
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    String email = userDetail.getUsername();
		sql = "INSERT INTO user_has_course (user_person,course) VALUES (?,?)";
		jdbcTemplate.update(sql, userpersonInterface.findByEmail(email).getId(), idCourse);		
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
	public List<Course> findAllByOwnwer(int owner){
sql ="select * from course c inner join lang la on la.id = c.lang inner join level le on le.id = c.level inner join user_person ow on ow.id = c.owner inner join subcategory sc on sc.id = c.subcategory inner join category ca on ca.id = c.category where c.owner = ?";
		
		List<Course> listCourse = this.jdbcTemplate.query(sql, new Object[] {owner}, new RowMapper<Course>() {

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
			
		});
		return listCourse;
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
				//c.setPublished(rs.getDate("c.published"));
				
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
				//c.setPublished(rs.getDate("c.published"));
				
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
	public boolean verifyUserHasCourse(long idCourse) {
		sql = "select * from user_has_course where course = ? and user_person = ?";
		
		boolean flag;
		UserHasCourse uhc = null;
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		UserPerson student = userpersonInterface.findByEmail(username);
		
		try {
			uhc = jdbcTemplate.queryForObject(sql, new Object[] {idCourse, student.getId()}, new RowMapper<UserHasCourse>() {
				@Override
				public UserHasCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserHasCourse uhc = new UserHasCourse();
					uhc.setId((long) rs.getInt("id"));
					return uhc;
				}
			});
			flag = true;
		} catch (EmptyResultDataAccessException e) {
			flag = false;
		}
		
		return flag;
	}
	
	@Override
	public void updateStatus(int status,int id) {
		sql = "update course set status = ? where id = ?";
		jdbcTemplate.update(sql, new Object[] {status, id});
	}
	
	@Override
	public List<Course> findCoursesByStatus(int status) {
		sql ="select * from course c inner join lang la on la.id = c.lang inner join level le on le.id = c.level inner join user_person ow on ow.id = c.owner inner join subcategory sc on sc.id = c.subcategory inner join category ca on ca.id = c.category where c.status = ?";
		
		List<Course> listCourse = this.jdbcTemplate.query(sql, new Object[] {status}, new RowMapper<Course>() {

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
			
		});
		return listCourse;
	}
	
	@Override
	public List<Course> findCoursesByCategory(int category) {
		sql ="select * from course c inner join lang la on la.id = c.lang inner join level le on le.id = c.level inner join user_person ow on ow.id = c.owner inner join subcategory sc on sc.id = c.subcategory inner join category ca on ca.id = c.category where c.category = ? and c.status = 2";
		
		List<Course> listCourse = this.jdbcTemplate.query(sql, new Object[] {category}, new RowMapper<Course>() {

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
			
		});
		return listCourse;
	}

	@Override
	public List<Course> findAllBySearch(String searchword) {
		
		//recuperar solo status 2
		sql="select * from course c inner join lang la on la.id = c.lang"
				+ " inner join level le on le.id = c.level "
				+ " inner join user_person ow on ow.id = c.owner "
				+ " inner join subcategory sc on sc.id = c.subcategory "
				+ " inner join category ca on ca.id = c.category "
				+ " where c.title LIKE ('%' ? '%')"
				+ "or c.description like ('%'?'%')";
		
		//this.jdbcTemplate.query(psc, rse);
		return this.jdbcTemplate.query(sql, new Object[] {searchword, searchword},  new RowMapper<Course>() {

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
			
		});
	}

	@Override
	public Page<Course> findByAllPagination(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*
	 * @Override public Page<Course> findByAllPagination(Pageable pageable) { //
	 * TODO Auto-generated method stub sql
	 * ="select * from course c inner join lang la on la.id = c.lang inner join level le on le.id = c.level inner join user_person ow on ow.id = c.owner inner join subcategory sc on sc.id = c.subcategory inner join category ca on ca.id = c.category"
	 * ; List<Course> listCourse = this.jdbcTemplate.query(sql, new
	 * RowMapper<Course>() {
	 * 
	 * @Override public Course mapRow(ResultSet rs, int rowNum) throws SQLException
	 * { // TODO Auto-generated method stub Course c = new Course(); c.setId(new
	 * Long(rs.getInt("c.id"))); c.setTitle(rs.getString("c.title"));
	 * c.setSubtitle(rs.getString("c.subtitle"));
	 * c.setDescription(rs.getString("c.description"));
	 * c.setPrice(rs.getDouble("c.price")); c.setStatus(rs.getInt("c.status"));
	 * //c.setPublished(rs.getDate("c.published"));
	 * 
	 * Lang la = new Lang(); la.setId((long) rs.getInt("la.id"));
	 * la.setLang(rs.getString("la.lang")); c.setLang(la);
	 * 
	 * Level le = new Level(); le.setId((long) rs.getInt("le.id"));
	 * le.setLevel(rs.getString("le.level")); c.setLevel(le);
	 * 
	 * UserPerson ow = new UserPerson(); ow.setEmail(rs.getString("ow.email"));
	 * ow.setId((long) rs.getInt("ow.id")); c.setOwner(ow);
	 * 
	 * Subcategory sc = new Subcategory(); sc.setName(rs.getString("sc.name"));
	 * sc.setId((long) rs.getInt("sc.id")); c.setSubcategory(sc);
	 * 
	 * Category ca = new Category(); ca.setName(rs.getString("ca.name"));
	 * ca.setId((long) rs.getInt("ca.id")); c.setCategory(ca);
	 * 
	 * return c; }
	 * 
	 * }); return listCourse; }
	 */
}
