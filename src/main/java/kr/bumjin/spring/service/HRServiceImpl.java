package kr.bumjin.spring.service;

import kr.bumjin.spring.dao.DataDao;
import kr.bumjin.spring.model.Emp;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class HRServiceImpl implements HRService {

	DataDao dao = null;
	TransactionTemplate transactionTemplate;
	
	public void setDao(DataDao dao) {
		this.dao = dao;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public void add() throws Exception{
		Object result = transactionTemplate.execute(new TransactionCallback() {
			
			public Object doInTransaction(TransactionStatus status) {
				// TODO Auto-generated method stub
				try{
					Emp emp = new Emp(11,"aa");
					dao.create(emp);
					Emp emp2 = new Emp(22,"bb");
					dao.create(emp2);
					String s = null;
					System.out.println(s.length());
					Emp emp3 = new Emp(33,"cc");
					dao.create(emp3);
				}catch(Exception e) {
					status.setRollbackOnly();
					return e;
				}
				return null;
			}
		});
		// TODO Auto-generated method stub
		if(result instanceof Throwable) {
			Throwable throwable = (Throwable)result;
			throw new Exception(throwable);
		}else {
			
		}
	}

}
