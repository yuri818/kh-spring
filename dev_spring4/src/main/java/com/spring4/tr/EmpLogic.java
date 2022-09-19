package com.spring4.tr;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

public class EmpLogic {
	Logger logger = Logger.getLogger(EmpLogic.class);
	private DeptDao deptDao = null;
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}
	private EmpDao empDao = null;
	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}
	//@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor= {DataAccessException.class})
//	public int doEmp() { // do 면한번에 커밋 하시요
	public int cudEmp() { // go 면 건건이 커밋해라
		logger.info("doEmp 호출");
		Map<String,Object> eMap = new HashMap<>();
		eMap.put("empno", 9005);
		eMap.put("ename", "이유리");
		eMap.put("deptno", 89);
		try {
			Map<String,Object> dMap = new HashMap<>();
			dMap.put("deptno",89);
			dMap.put("dname", "개발부");
			dMap.put("loc", "부산");
			deptDao.deptInsert(dMap);
			empDao.empInsert(eMap); // 여기에 에러 던져둠
		} catch (DataAccessException de) {
			throw de;
		}
		return 0;
	}

}
