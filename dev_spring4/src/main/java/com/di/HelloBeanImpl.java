package com.di;
// 통일감 - 팀장마다 설계가 다르다
// HelloBean의 구현체 클래스
public class HelloBeanImpl implements HelloBean {
	private String msg = null;

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String getGreeting(String msg) {
		return "Spring!!!" + this.msg;
	}

}
