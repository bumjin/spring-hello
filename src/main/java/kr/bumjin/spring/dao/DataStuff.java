package kr.bumjin.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class DataStuff {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public DataSource getDataSource() {
		return this.dataSource;
	}
	
	public List getNames() {
		return jdbcTemplate.queryForList("select name from usr_global");
	}
}
