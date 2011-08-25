package kr.bumjin.spring.dao;

import java.util.List;

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
	public List getEmps() {
		return getJdbcTemplate().queryForList("select id, name from employee");
	}

}
