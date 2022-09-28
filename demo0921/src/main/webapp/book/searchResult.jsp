<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%
// div안에 들어갈거라 데이터 셋만 가져오기
	List<Map<String,Object>> bookList = 
		(List<Map<String,Object>>)request.getAttribute("bookList");
%>

<table>
<%
	if(bookList == null){ //조회(검색)결과가 없을 때
%>
	<tr>
		<td>조회 결과가 없습니다.</td>
	</tr>
<%
	} else{ //조회 결과가 있을 때
		for(int i=0;i<bookList.size();i++){
			Map<String,Object> rmap = bookList.get(i);
%>
	<tr>
		<td><%=rmap.get("BK_TITLE") %></td>
	</tr>
<%
		}///////////end of for
	}///////////////end of if-else
%>
</table>