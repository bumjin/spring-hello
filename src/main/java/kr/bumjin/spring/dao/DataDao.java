package kr.bumjin.spring.dao;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class DataDao extends JdbcDaoSupport{
	
	public List getNames() {
		return getJdbcTemplate().queryForList("select name from usr_global");
	}
}
