package com.octaspring.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.octaspring.dao.RoleInterface;
import com.octaspring.entity.Category;
import com.octaspring.entity.Role;

public class RoleService implements RoleInterface{
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	public RoleService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		sql = "insert into role (name,description,status) values (?,?,?)";
		role.setStatus(1);
		jdbcTemplate.update(sql, role.getName(),role.getDescription(),role.getStatus());
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		sql="update role set name=?,description=? where id = ?";
		jdbcTemplate.update(sql, role.getName(),role.getDescription(),role.getId());

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		sql = "delete from role where id = ? ";
		jdbcTemplate.update(sql, id);
	}
	@Override
	public void deleteLogical(long id,int status) {
		sql = "update  role set status=? where id = ? ";
		jdbcTemplate.update(sql, status,id);

	}

	@Override
	public List<Role> findByAll() {
		// TODO Auto-generated method stub
		
		sql ="select * from role";
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Role.class));
	}
	@Override
	public List<Role> findRolesNotAdmin() {
		// TODO Auto-generated method stub
		
		sql ="select * from role where name != 'ROLE_ADMIN'";
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Role.class));
	}

	@Override
	public Role findById(long id) {
		// TODO Auto-generated method stub
		sql="select * from role where id = ?";
		
		// TODO Auto-generated method stub
		return this.jdbcTemplate.queryForObject(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(Role.class));
	}

}
