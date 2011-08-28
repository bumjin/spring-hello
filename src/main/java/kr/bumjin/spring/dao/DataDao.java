package kr.bumjin.spring.dao;

import java.util.List;

import kr.bumjin.spring.model.Emp;

public interface DataDao {
	public List getEmps() ;

	public void create(Emp emp) ;

	public Emp getEmpById(int id);
}
