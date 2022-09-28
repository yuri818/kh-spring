<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/easyui_common.jsp" %>
<style type="text/css">
	#d_detail {
		position: absolute;
	}
</style>
<script type="text/javascript">
	function startMethod(td){
		$.ajax({
			  method: "GET", //POST로 해야한다. 
			  url: "./pictureInfo.jsp?p_no=2",
			  success:function(result){ // result는 searchResult.jsp에 html태그들을 말함
				  console.log(result);
			  }////end of success
			  ,error:function(e){
				  $("#d_search").text(e.responseText); //에러메세지 출력 - 힌트 - 디버깅
			  }///end of error
		});////end of ajax
	}
	function clearMethod(){
		document.getElemtentById("d_detail").innerHTML="";
		//$("#d_detail").html("");
	}
</script>
</head>
<body>
	<table border="1">
		<thead>
			<th colspan="2">그림목록</th>
		</thead>
		<tbody>
			<tr>
				<td align="center"><img src="../images/picture1.jpg" width="50" heigth="50"></td>
				<td id="1" onmouseover="startMethod(this)" onmouseout="clearMethod()">추상화1</td>
			</tr>
			<tr>
				<td align="center"><img src="../images/picture2.jpg" width="50" heigth="50"></td>
				<td id="2" onmouseover="startMethod(this)" onmouseout="clearMethod()">추상화2</td>
			</tr>
			<tr>
				<td align="center"><img src="../images/picture3.jpg" width="50" heigth="50"></td>
				<td id="3" onmouseover="startMethod(this)" onmouseout="clearMethod()">추상화3</td>
			</tr>
			<tr>
				<td align="center"><img src="../images/picture4.jpg" width="50" heigth="50"></td>
				<td id="4" onmouseover="startMethod(this)" onmouseout="clearMethod()">추상화4</td>
			</tr>
		</tbody>
	</table>
	<div id="d_detail"></div>
</body>
</html>