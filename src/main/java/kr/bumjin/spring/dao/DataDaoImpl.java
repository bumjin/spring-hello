package kr.bumjin.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kr.bumjin.spring.model.Emp;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class DataDaoImpl extends JdbcDaoSupport implements DataDao{
	/*
	 * (non-Javadoc)
	 * 
	 * create table emp (id varchar(10), name varchar(20));
	 * insert into emp values ('111', 'abc');
	 * 
	 * 
	 * 
	 * 
	 * @see kr.bumjin.spring.dao.DataDao#getEmps()
	 */
	public void create(Emp emp) {
		// TODO Auto-generated method stub
		getJdbcTemplate().update("insert into employee values (?, ?) ", new Object[]{new Integer(emp.getId()), emp.getName()});
	}
	
	public List getEmps() {
		//return getJdbcTemplate().queryForList("select id, name from employee");
		return getJdbcTemplate().query("select id, name from employee", new RowMapper() {
			
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Emp emp = new Emp();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				return emp;
			}
		});
	}

	public Emp getEmpById(int id) {
		// TODO Auto-generated method stub
		return (Emp) getJdbcTemplate().queryForObject("select id, name from employee where id = ? ", new Object[]{new Integer(id)}, new RowMapper() {
			
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Emp emp = new Emp();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				return emp;
			}
		});
	}

}
