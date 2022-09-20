<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹서버에서 쿠키 생성하기</title>
</head>
<body>
<%
	Cookie cook1 = new Cookie("sneakers","조던");
	cook1.setMaxAge(60); // 1분
	// 생성자는 메소드 오버로딩 - 같은 이름의 생성자 여러개 가능
	//Cookie cook2 = new Cookie("hp","아이폰");
	Cookie cook2 = new Cookie("age",String.valueOf(30));
	List<String> names = new ArrayList<>();	
	//cook2 = new Cookie("names",names);
	session.setAttribute("s_age", 30);
	session.setAttribute("s_names", names);
	cook2.setMaxAge(60); // 1분
	cook2.setPath("/"); // 하위 경로도 공유 가능 //디폴트는 현재 바라보는 경로
	Cookie cook3 = new Cookie("coffee","아샷추");
	cook3.setMaxAge(60); // 1분
	response.addCookie(cook1); // response객체에 쿠키객체를 추가함
	response.addCookie(cook2);
	response.addCookie(cook3);
%>
</body>
</html>