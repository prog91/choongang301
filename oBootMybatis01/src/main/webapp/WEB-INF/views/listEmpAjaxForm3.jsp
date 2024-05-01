<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
	function getEmpListUpdateTest() {
		alert("getEmpListUpdateTest Run...")
		// Group 번호 가져오기
		var arr = new Array();
		var item;

		<c:forEach items="${listEmp}" var = "item">
		arr.push({
			empno : "${item.empno}",
			ename : "${item.ename}",
			deptno : "${item.deptno}"
		});
		</c:forEach>

		for (var i = 0; i < arr.length;) {
			alert("arr.empno->" + i + " : " + arr[i].empno + "arr.ename->"
					+ " : " + arr[i].ename);
			i++;
			if (i > 3)
				return;
		}
	}

	//  JavaScript 에서  empList를 수정한 Data를 JSON 데이터로 전환  empController로  보내기 예제
	function getEmpListUpdate() {
		alert("getEmpListUpdate Run...")
		let empList = [];
		const inputs = document.querySelectorAll('input[name="empno"], input[name="ename"], input[name="deptno"]');
		for (let i = 0; i < inputs.length; i += 3) {
			const empno = inputs[i+1].value;
			const ename = inputs[i+2].value;
			const deptno = inputs[i+3].value;
			//  불러온 값들을 JSON 객체 형태로 만든다
			const empItem = {
					"empno":empno,
					"ename":ename,
					"deptno":deptno
			};
			// alert("ename -> " + ename);
			// JSON 객체를 배열 안에 넣어둔다
			empList.push(empItem);
			if (i > 5) break;
		}
		// 완성된 dataList를 확인한다
		// console.log(empList);
		// dataList를 문자열로 바꾼 결과를 확인한다
		console.log(JSON.stringify(empList));
		alert ("JSON.stringify(empList) -> " + JSON.stringify(empList));
		
		if (empList.length > 0) {
			
			$.ajax( {
					url: 'empListUpdate',
					contentType: 'application/json',
					data: JSON.stringify(empList), // JSON 객체를 불러와서 stringify() 함수 안에 배열	
					method: 'POST',
					dataType: 'text',
					success: function(result) {
						console.log(result);
					}
			});
			alert("Ajax empListUpdate 수행...");
		}

	}
</script>
</head>
<body>
	<h2>회원 정보3</h2>
	<table id="empList">
		<tr>
			<th>번호</th>
			<th>사번</th>
			<th>이름</th>
			<th>업무</th>
			<th>부서</th>
		</tr>
		<c:forEach var="emp" items="${listEmp}" varStatus="status">
			<tr id="empListRow">
				<td>emp${status.index}</td>

				<td><input type="hidden" class="deptno" id="deptno"
					name="deptno" value="${emp.deptno }"> <input type="text"
					class="empno" id="empno" name="empno" value="${emp.empno }">${emp.empno }</td>
				<td><input type="text" class="ename" id="ename" name="ename"
					value="${emp.ename }">${emp.ename }</td>
				<td>${emp.job }</td>
				<td>${emp.deptno }</td>
			</tr>

		</c:forEach>


	</table>

	RestController LISTVO3:
	<input type="button" id="btn_Dept3" value="empLISTTest1 전송 "
		onclick="getEmpListUpdateTest()">
	<p>
		RestController LISTVO3: <input type="button" id="btn_Dept3"
			value="empLIST2 전송 " onclick="getEmpListUpdate()">
	<p>
	<h1>The End</h1>
</body>
</html>