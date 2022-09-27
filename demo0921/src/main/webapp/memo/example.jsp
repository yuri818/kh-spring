<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 자바영역 - 톰캣서버에서 처리된 결과가 내보내진다.
	// 이미 값들이 결정된 상태이다.
	String mem_name = "홍길동";
%>
<!-- html영역 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/easyui_common.jsp" %>
<script type="text/javascript">
	let mem_name = "이순신";
	let name = "<%=mem_name%>"; //합법이지만 유동적이지 않다 - 브라우저에서 소스보기
	
	//name = mem_name;
</script>
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function(){
			console.log(name);
		});
	</script>		
	<%
		out.print("<b>"+mem_name+"</b>님 고맙습니다.");
	%>
</body>
</html>