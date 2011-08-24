package kr.bumjin.spring.dao;

import java.util.List;
import java.util.Map;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class DataDaoTest extends AbstractDependencyInjectionSpringContextTests  {

	private DataDao dataDao = null;
	
	public void setDataDao(DataDao dataDao) {
		this.dataDao = dataDao;
	}
	
	public void onSetUp() throws Exception {
		 deleteFromTables(new String[] {"emp"});
		 dataDao.insertSample();
	}
	
	private void deleteFromTables(String[] tables) {
		// TODO Auto-generated method stub
		for(int i=0;i<tables.length;i++) {
			dataDao.deleteTable(tables[i]);
		}
	}

	public void testGetEmps() {
		List<Map> names = dataDao.getEmps();
		Map map = names.get(0);
		assertEquals("111", map.get("ID"));
		assertEquals("abc", map.get("NAME"));
	}
	
	protected String[] getConfigLocations() {
        return new String[] { "classpath:application-context.xml", "classpath:dataAccessContext.xml" };
    }
}
