package kr.bumjin.spring.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class DataDaoTest extends AbstractDependencyInjectionSpringContextTests {

	private DataDao dataDao;

	private JdbcTemplate jdbcTemplate;

	public void setDataDao(DataDao dataDao) {
		this.dataDao = dataDao;
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void onSetUp() throws Exception {
		super.onSetUp();
		assertNotNull(jdbcTemplate);
		jdbcTemplate.execute("create table employee (id int, name varchar(30))");
		jdbcTemplate.execute("insert into employee (id, name) values (1, 'A')");
		jdbcTemplate.execute("insert into employee (id, name) values (2, 'B')");
		jdbcTemplate.execute("insert into employee (id, name) values (3, 'C')");
		jdbcTemplate.execute("insert into employee (id, name) values (4, 'D')");
		jdbcTemplate.execute("insert into employee (id, name) values (5, 'E')");
		jdbcTemplate.execute("insert into employee (id, name) values (6, 'F')");
	}
	
	protected void onTearDown() throws Exception {
		assertNotNull(jdbcTemplate);
		jdbcTemplate.execute("drop table employee");
	}


	public void testGetEmps() {
		List<Map> names = dataDao.getEmps();
		Map map = names.get(0);
		assertEquals(1, map.get("ID"));
		assertEquals("A", map.get("NAME"));
		map = names.get(5);
		assertEquals(6, map.get("ID"));
		assertEquals("F", map.get("NAME"));
	}

	
	protected String[] getConfigLocations() {
		return new String[] { "classpath:application-context.xml",
				"classpath:dataAccessContext.xml" };
	}
}
