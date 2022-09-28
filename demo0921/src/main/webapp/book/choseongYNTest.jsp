<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>초성유무 체크하기</title>
</head>
<body>
<script type="text/javascript">
	let code;
	const cho = ["ㄱ","ㄲ","ㄴ","ㄷ","ㄸ",
		         "ㄹ","ㅁ","ㅂ","ㅃ","ㅅ",
		         "ㅆ","ㅇ","ㅈ","ㅉ","ㅊ",
		         "ㅋ","ㅌ","ㅍ","ㅎ"];
	// charCodeAt(0)은 첫자리를 꺼내는 것
	code = "남산".charCodeAt(0);
	console.log(code); //45224
	code = "남산".charCodeAt(0) - 44032;
	console.log(code); //1192
	code = Math.floor(code/588);
	console.log(code); //2
	console.log(cho[code]); //ㄴ
</script>
</body>
</html>