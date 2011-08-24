package kr.bumjin.spring.dao;

import java.util.List;

public interface DataDao {
	public List getEmps() ;

	public int deleteTable(String string);

	public int insertSample();
}
