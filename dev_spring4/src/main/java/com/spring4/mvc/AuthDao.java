package com.spring4.mvc;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;

import com.vo.MemberVO;
// DAO뒤에서는 오라클서버와 연동하기
// myBatis 레이어에 대한 설정 필요함.
public class AuthDao {
	Logger logger = Logger.getLogger(AuthLogic.class);
	// SqlSessionTemplate는 mybatis-spring.jar에서 제공하는 클래스로
	// SqlSession의 역할임
	/*
	 * 
	 <bean id="auth-dao" class="com.spring4.mvc.AuthDao">
	 	<!-- name에는 인스턴스변수 들어가야함 -->
	 	<property name="sqlSessionTemplate" ref="sqlSessionTemplate" />
	 </bean>
	 * 
	 */
	private SqlSessionTemplate sqlSessionTemplate = null;
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public MemberVO login() {
		logger.info("login 호출 성공");
		MemberVO memVO = null;
		Map<String,Object> pMap = new HashMap<>();
		pMap.put("mem_id","tomato");
		pMap.put("mem_pw","123");
		// selectOne(One은 Object이다.-오직 한건만)은 조회건수가 반드시 한건이어야 한다
		// Too Many...
		memVO = sqlSessionTemplate.selectOne("login",pMap); // selectOne은 리턴타입이 Object임
		if(memVO!=null) {
			logger.info(memVO.getMem_name());
		}
		return memVO;
	}
	
}
