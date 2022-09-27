<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.example.demo.vo.MemberVO" %>
<%
	/* MemberVO mVO = (MemberVO)session.getAttribute("mVO");
	String s_id = null;
	String s_name = null;
	if(mVO != null){
		s_id = mVO.getMem_id();
		s_name = mVO.getMem_name();
	} */
	String smem_id = (String)session.getAttribute("smem_id");
	String smem_name = (String)session.getAttribute("smem_name");
	int s_cnt = 0;
	if(session.getAttribute("s_cnt")!=null){
		s_cnt = (Integer)session.getAttribute("s_cnt");
	}
	out.print(smem_id+", "+smem_name);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증처리 - 쿠키와 세션</title>
<%@ include file="../common/easyui_common.jsp"%>
<style type="text/css">
a {
	text-decoration: none;
}
</style>
<script type="text/javascript">
		let gm_no; //메모 받아오는 것..pk값 가져오기
		let to_id; //받는 사람 아이디 - 사용자가 입력하는 것이 아니라 쪽지쓰기 로우에서 자동으로 담기
		let to_name; // 받는 사람 이름
    	// 함수 선언은 head태그 안에서 한다
    	// easyui_common.jsp열어봐서 왜 head태그 안에서 처리하는지
    	function memberUpdate() {
    		//alert("수정");
    		
    	}
    	function memberDelete() {
    		//alert("삭제");
    		
    	}
    	function login() {
    		const mem_id = $("#mem_id").val();
    		const mem_pw = $("#mem_pw").val();
    		location.href = "/member/login?mem_id="+mem_id+"&mem_pw="+mem_pw;
    	}
    	function logout() {
    		location.href="./logout.jsp";
    	}
    	// 받은 쪽지함 - 번호, 보낸 사람 이름, 개봉여부, 내용보기 버튼(알리아스명은 버튼임 왜냐면 코드 재사용할거라서)
    	function receiveMemoList(){
    		$("#dg_receiveMemoList").datagrid({
				method:"get"
				,url:"/memo/receiveMemoList?to_id=<%=smem_id%>"
				,onSelect: function(index, row){
					gm_no = row.NO; // pk값 알아서 read_yn의 값 업데이트해주려고(N -> Y)
				}
			});
			/* 위치 주의 */
    		$("#d_member").hide();
    		// after
			$("#d_memberInsert").hide();
    		$("#d_receiveMemoList").show();
    		$("#d_sendMemoList").hide();
    	}
    	// 보낸 쪽지함
    	function sendMemoList(){
    		$("#dg_sendMemoList").datagrid({
				method:"get"
				,url:"/memo/sendMemoList?from_id=<%=smem_id%>" // 응답페이지는 JSON포맷의 파일이어야함(html이아니라)
				,onSelect: function(index, row){
					gm_no = row.NO; // pk값 알아서 read_yn의 값 업데이트해주려고(N -> Y)
				}
			});
			/* 위치 주의 */
    		$("#d_member").hide();
    		// after
			$("#d_memberInsert").hide();
    		$("#d_receiveMemoList").hide();
    		$("#d_sendMemoList").show();
    	}
    	// 순서지향적인, 절차지향적인 코딩 -> 모듈화 -> 비동기처럼 처리 하기(연습-await, async)
    	function memberList(){
    		let type = null;
    		let keyword = null;
    		if($("#_easyui_textbox_input4").val() != null && $("#_easyui_textbox_input4").val().length > 0){
    			type = "mem_id";
    			keyword = $("#_easyui_textbox_input4").val();
    		}
    		else if($("#_easyui_textbox_input5").val() != null && $("#_easyui_textbox_input5").val().length > 0){
    			type = "mem_name";
    			keyword = $("#_easyui_textbox_input5").val();
    		}
			// 시점 -> 이게 왜 이자리로 오는지 알지?
    		// 아래 코드는 클라이언트 측에 같이 다운로드가 완료된 상태에서 처리가 된다 - 결정이 되었다
			/* 위치 주의 */
			// jeasyUI datagrid에서도 get방식과 post방식 지원함
			// url속성에 XXX.jsp가 오면 표준 서블릿이 HttpServlet이 관여하는 것이고
			// XXX.pj로 요청하면 ActionSupport가 관여하는 것이다.
			$("#dg_member").datagrid({
				// 오라클 서버에서 요청한 결과를 myBatis를 사용하면 자동으로 컬럼명이 대문자
				// 단 List<XXVO>형태라면 그땐 소문자가 맞다
				method:"get"
				/* json포맷으로 멤버리스트 data를 전달받아야함 */
				,url:"/member/jsonMemberList?type="+type+"&keyword="+keyword // 응답페이지는 JSON포맷의 파일이어야함(html이아니라)
				,onSelect: function(index, row){
					to_id = row.MEM_ID; //데이터그리드 선택시 해당 로우의 아이디 담기
					to_name = row.MEM_NAME; // 데이터그리드 선택시 해당 로우의 이름 담기
					console.log(to_id+", "+to_name);
				}
				,onDblClickCell: function(index,field,value){
					//console.log(index+", "+field+", "+value);
					if("BUTTON" == field){
						alert("쪽지쓰기");
					}
				}
			});				
			/* 위치 주의 */
    		$("#d_member").show();
    		// after
    		$("#d_memberInsert").hide();
    		$("#d_receiveMemoList").hide();
    		$("#d_sendMemoList").hide();
    	}
    	function memberInsert(){
    		alert("회원등록 호출 성공");
    		$("#d_member").hide();
    		$("#d_memberInsert").show();
    	}
    	function memoForm(){
    		console.log("memoForm 호출");
    		$("#dlg_memo").dialog({
    			title: "쪽지쓰기",
    			href:"/memo/memoForm.jsp?to_id="+to_id+"&to_name="+to_name,
    			modal: true,
    			closed: true
    		});
    		$("#dlg_memo").dialog('open');
    	}
    	function memoSend(){
    		console.log("쪽지보내기");
    		$("#f_memo").submit();
    	}
    	function memoFormClose(){
    		$("#dlg_memo").dialog('close');
    	}
    	function memoContent(){
    		console.log("쪽지내용 보기");
    		$("#dlg_memoContent").dialog({
    			title: "쪽지내용확인",
    			// 쪽지내용을 DB에서 꼭 가져와야 하나요?
    			// 목록을 가져올 떄 가지고 있다가 출력해도 되지 않을까? (오라클 경유를 안해도 됨)
    			// 너라면 1번, 2번중 몇번을 선택할거니?
    			// 기준: 쪽지내용 확인 후 read_yn을 Y로 업데이트 해야하므로 1번 선택
    			// 한 건을 조회한 후에 그 자료구조가 null이 아닐 때만 업데이트 해야 됨
    			// 만일 500번이 일어나면 진행이 안돼야 함.
    			// 이 기능을 구현하는데 있어서 컨트롤계층의 메소드부터 호출이 되어야 하는 건지? 아님
    			// 로직클래스에 메소드 호출만으로도 충분한지를 고민해 보기 -> 객체주입관계와 메소드 선언을 결정해야함
    			// 설계자는 기능 담당자가 구현해야하는 페이지이름과 메소드, 그리고 파라미터와 리턴타입 모두를 
    			// 다 정해놓고 담당자는 메소드안에 기능구현만 하도록 할것.
    			href:"/memo/memoContent?no="+gm_no,
    			modal: true,
    			closed: true
    		});
    		$("#dlg_memoContent").dialog('open');
    	}
    	// 하나를 바꾸면 다른 관련된 코드들도 모두 찾아서 변경해야 한다.
    	// 대체로 반복되는 코드이고 어렵지는 않은데 꽤 귀찮게 하는 코드이고, 또한 에러가 발생할 수 있다.
    	// 유지보수 업무를 담당하고 있다... 가정
    	// 가능하다면 최소한의 코드만 수정하고 유지보수가 이루어질 수 있도록 코딩하기
    	// S급, A급, C급 코드
    	function memoContentClose(){
    		//receiveMemoList();
    		// SPA는 과연 옳은 선택이었을까?
    		location.href="/auth/index.jsp"; // URL이 변경 -> 기존에 요청이 끊어지고 새로운 요청 - 세션이나 쿠키가 바뀐다.
    		$("#dlg_memoContent").dialog('close');
    	}
    	
    </script>
</head>
<body>
	<script>
	// DOM트리가 다 그려졌다면 -> function을 실행해주세요
	// 만약에 ready를 쓰지 않겠다면 input태그의 id="tb"뒤에 스크립트 적어주면 위치문제 해결
	// DOM트리가 그려졌을 때(준비되었을 때) - ready
	$(document).ready(function(){
		$("#d_member").hide();
		$("#d_memberInsert").hide();
		$("#d_sendMemoList").hide();
		$("#d_receiveMemoList").hide();
		$("mem_id").textbox('setValue','apple');
		$("mem_pw").textbox('setValue','123');
	});
</script>
	<div style="margin: 20px 0;"></div>
	<div class="easyui-layout" style="width: 1000px; height: 500px;">
		<div data-options="region:'south',split:true" style="height: 50px;"></div>
		<div data-options="region:'west',split:true" title="KH정보교육원"
			style="width: 200px;">
			<!-- 여백 줄거임 -->
			<div style="margin: 10px 0;"></div>
<%
	if(smem_name == null) {
%>
<!--####################### 로그인  영역 시작 #######################-->
			<div id="d_login" align="center">
				<div style="margin: 3px 0;"></div>
				<input id="mem_id" name="mem_id" class="easyui-textbox" />
				<script type="text/javascript">
					$("#mem_id").textbox({
						iconCls:'icon-man',
						iconAlign: 'right',
						prompt: '아이디'
					});
				</script>
				<div style="margin: 3px 0;"></div>
				<input id="mem_pw" name="mem_pw" class="easyui-passwordbox" />
				<script type="text/javascript">
					$("#mem_pw").passwordbox({
						iconAlign: 'right',
						prompt: '비밀번호',
				        showEye: true
					});
				</script>
				<!-- 로그인 버튼과 회원가입 버튼 추가 -->
				<div style="margin: 3px 0;"></div>
				<a id="btn_login" href="javascript:login()"
					class="easyui-linkbutton" data-options="iconCls:'icon-man'">
					로그인 </a> <a id="btn_member" href="javascript:memberShip()"
					class="easyui-linkbutton" data-options="iconCls:'icon-add'">
					회원가입 </a>
			</div>
<!--####################### 로그인  영역 끝 #######################-->
<%
	} else {
%>
<!--####################### 로그아웃 영역 시작 #######################-->
			<div id="d_logout" align="center">
				<div id="d_ok">
					<%= smem_name %>님 환영합니다.
					<br>
					읽지않은 쪽지 수: <%=s_cnt %>통
				</div>
				<div style="margin: 3px 0;"></div>
				<a id="btn_logout" href="javascript:logout()"
					class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">
					로그아웃 </a> <a id="btn_member" href="javascript:memberUpdate()"
					class="easyui-linkbutton" data-options="iconCls:'icon-edit'">
					정보수정 </a>
			</div>
<!--####################### 로그아웃 영역  끝 #######################-->
<%
	} ///////////////////////end of 로그아웃
%>
<!--####################### 메뉴 영역 시작 #######################-->
			<div style="margin: 20px 0;"></div>
<%
	if(smem_id != null){
%>
			<ul id="tre_gym" class="easyui-tree" style="margin: 0 6px;">
				<li data-options="state:'closed'"><span>회원관리</span>
					<ul class="member">
						<li><a href="javascript:memberList()">회원목록</a></li>
						<li><a href="javascript:memberInsert()">회원등록</a></li>
						<li><a href="javascript:memberDelete()">회원삭제</a></li>
					</ul></li>
				<li data-options="state:'closed'"><span>쪽지관리</span>
					<ul>
						<li><a href="javascript:receiveMemoList()">받은쪽지함</a></li>
						<li><a href="javascript:sendMemoList()">보낸쪽지함</a></li>
					</ul></li>
				<li data-options="state:'closed'"><span>게시판</span>
					<ul>
						<li><span>1:1</span></li>
						<li><span>공지사항</span></li>
						<li><span>Q&A</span></li>
					</ul></li>
			</ul>
<%
	}
%>
		</div>
<!--####################### 메뉴 영역  끝 #######################-->

		<div data-options="region:'center',title:'TerrGYM System',iconCls:'icon-ok'">
			<!-- [[ 회원관리{회원목록, 회원등록, 회원삭제} ]] -->
			<div id="d_member">
				<div style="margin: 5px 0;"></div>
				HOME > 회원관리 > 회원목록
				<hr>
				<div style="margin: 20px 0;"></div>
				<!-- datagrid 붙는 부분 -->
				<!-- [[ 조건검색 화면 ]] -->
				<div>
					아이디 : <input id="mem_id" name="mem_id" class="easyui-textbox"
						style="width: 110px"> &nbsp;&nbsp;&nbsp; 이 름 : <input
						id="mem_name" name="mem_name" class="easyui-textbox"
						style="width: 110px">
				</div>
				<div style="margin: 20px 0;"></div>
				<!-- [[ 조회|입력|수정|삭제 버튼 시작 ]] -->
				<div style="margin: 5px 0;"></div>
				<div>
					<a id="btn" href="javascript:memberList()"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'">조회</a>
					<a id="btn" href="javascript:memberInsert()"
						class="easyui-linkbutton" data-options="iconCls:'icon-edit'">입력</a>
					<a id="btn" href="javascript:memberUpdate()"
						class="easyui-linkbutton" data-options="iconCls:'icon-reload'">수정</a>
					<a id="btn" href="javascript:memberDelete()"
						class="easyui-linkbutton" data-options="iconCls:'icon-cut'">삭제</a>
				</div>
				<div style="margin: 10px 0;"></div>
				<!-- [[ 조회|입력|수정|삭제 버튼 끝 ]] -->
				<!-- [[ 회원 목록 출력 ]] -->
				<table id="dg_member" class="easyui-datagrid" style="width: 700px; height: 250px"
					data-options="singleSelect:true,collapsible:true,method:'get'">
					<thead>
						<tr>
							<th data-options="field:'MEM_ID',width:80">아이디</th>
							<th data-options="field:'MEM_NAME',width:100">이름</th>
							<th data-options="field:'MEM_ADDRESS',width:300,align:'left'">주소</th>
							<th data-options="field:'BUTTON',width:100,align:'center'">버튼</th>
						</tr>
					</thead>
				</table>
			</div>
			<div id="d_memberInsert">
				<div style="margin: 5px 0;"></div>
				HOME > 회원관리 > 회원목록
				<hr>
				<div style="margin: 20px 0;"></div>
				<div>회원등록화면 보여주기</div>
			</div>
			
<!-- 
==============================================================================================
	[ 받은 쪽지함 ] - 읽지않은 쪽지 수, to_id == smem_id
==============================================================================================
 -->
	<div id="d_receiveMemoList">
		<div style="margin: 5px 0;"></div>
		HOME > 쪽지관리 > 받은쪽지함
		<hr>
		<!-- [[ 회원 목록 출력 ]] -->
		<table id="dg_receiveMemoList" class="easyui-datagrid" style="width: 700px; height: 250px"
			data-options="singleSelect:true,collapsible:true,method:'get'">
			<thead>
				<tr>
					<th data-options="field:'NO',width:80">번호</th>
					<th data-options="field:'FROM_ID',width:100">보낸사람ID</th>
					<th data-options="field:'MEM_NAME',width:100">보낸사람이름</th>
					<th data-options="field:'READ_YN',width:300,align:'left'">개봉여부</th>
					<th data-options="field:'BUTTON',width:100,align:'center'">버튼</th>
				</tr>
			</thead>
		</table>
		<!-- [[ 쪽지 내용보기 시작 ]] -->
		<div id="dlg_memoContent" footer="#btn_memo" class="easyui-dialog"
			 title="쪽지쓰기" data-options="modal:true,closed:true"
			 style="width: 600px; height: 400px; padding: 10px">
			<div id="btn_memoContent" align="right">
				<a href="javascript:memoContentClose()" class="easyui-linkbutton" iconCls="icon-clear">닫기</a>
			</div>
		</div>
		<!-- [[ 쪽지 내용보기  끝 ]] -->
		
	</div>
<!-- 
==============================================================================================
	[ 보낸 쪽지함 ]
==============================================================================================
 -->
	<div id="d_sendMemoList">
		<div style="margin: 5px 0;"></div>
		HOME > 쪽지관리 > 보낸쪽지함
		<hr>
	</div>

<!-- 
==============================================================================================
	[ 쪽지함 끝!! ]
==============================================================================================
 -->
			<!-- [[ 쪽지관리{받은쪽지함, 보낸쪽지함} ]] -->
<!-- [[쪽지쓰기 다이얼로그 화면 시작]] -->
	<div id="dlg_memo" footer="#btn_memo" class="easyui-dialog"
		 title="쪽지쓰기" data-options="modal:true,closed:true"
		 style="width: 600px; height: 400px; padding: 10px">
		<div id="btn_memo" align="right">
			<a href="javascript:memoFormClose()" class="easyui-linkbutton" iconCls="icon-clear">닫기</a>
		</div>
	</div>
<!-- [[쪽지쓰기 다이얼로그 화면  끝]] -->
		</div>
	</div>

</body>
</html>