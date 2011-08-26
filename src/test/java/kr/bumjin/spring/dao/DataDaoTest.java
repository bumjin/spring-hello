package kr.bumjin.spring.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import kr.bumjin.spring.model.Emp;

import org.dbunit.database.AmbiguousTableNameException;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class DataDaoTest extends AbstractDependencyInjectionSpringContextTests {

	private DataDao dataDao;

	private JdbcTemplate jdbcTemplate;

	public void setDataDao(DataDao dataDao) {
		this.dataDao = dataDao;
	}

	public void setDataSource(@Qualifier("dataSource") final DataSource dataSource) {
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
		
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File(
				"./src/test/resources/dataset.xml"
			));
			DatabaseOperation.CLEAN_INSERT.execute(getConnection(), dataSet);
	}

	private IDatabaseConnection getConnection() throws SQLException {
		IDatabaseConnection dbConn = new DatabaseDataSourceConnection(jdbcTemplate.getDataSource());
		return dbConn;
	}
	
	protected void onTearDown() throws Exception {
		assertNotNull(jdbcTemplate);
		jdbcTemplate.execute("drop table employee");
	}

	public void testCreate() throws SQLException, DataSetException, IOException {
		int initialCount = jdbcTemplate.queryForInt("select count(1) from employee");
		Emp emp = new Emp(7, "G");
		dataDao.create(emp);
		assertEquals(initialCount+1,jdbcTemplate.queryForInt("select count(1) from employee"));
		
		// Fetch database data after executing the code  
        QueryDataSet databaseSet = new QueryDataSet(getConnection());  
        // filter data  
        databaseSet.addTable("employee", "select id, name from employee where id = 7");  
        ITable actualTable = databaseSet.getTables()[0];  

        // Load expected data from an XML dataset  
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/resources/expectedDataSet.xml"));  
        ITable expectedTable = expectedDataSet.getTable("employee");  

        // filter unnecessary columns of current data by xml definition  
        actualTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());  

        // Assert actual database table match expected table
        assertEquals(1, actualTable.getRowCount());
        assertEquals(1,expectedTable.getRowCount());  
        assertEquals(expectedTable.getRowCount(), actualTable.getRowCount());  
        assertEquals(expectedTable.getValue(0, "name"), actualTable.getValue(0, "name"));  

	}

	public void testGetEmps() {
		List<Map> names = dataDao.getEmps();
		assertEquals(6, names.size());
		/*Map map = names.get(0);
		assertEquals(1, map.get("ID"));
		assertEquals("A", map.get("NAME"));
		map = names.get(5);
		assertEquals(6, map.get("ID"));
		assertEquals("F", map.get("NAME"));*/
	}

	
	protected String[] getConfigLocations() {
		return new String[] { "classpath:application-context.xml",
				"classpath:dataAccessContext.xml" };
	}
}
