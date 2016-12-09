<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  page import ="dao.MiniDao" %>
<%@ page import = "dto.StaffAll" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<table>
	<thead>
		<tr>
			<td>이름</td><td>성별</td><td>졸업일</td><td>최종학력</td><td>종교</td><td>보유기술</td>
		</tr>
	</thead>
	<tbody>
	
		<tr>
			<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
		</tr>
	</tbody>
</table>


<table border="1">
	<tr>
		<td>getGender</td>
		<td>getGraduateday</td>
		<td>getName</td>
		<td>getNo</td>
		<td>getReligion</td>
		<td>getSchool</td>
		<td>getSkill</td>
	</tr>
	<%
		MiniDao dao = new MiniDao();
		List<StaffAll> list = dao.mSelectAll();
		
	for(int i=0;i<list.size();i++ ){
		StaffAll all = list.get(i);
	%>
	<tr>
		<td><%= all.getGender() %></td>
		<td><%= all.getGraduateday() %></td>
		<td><%= all.getName() %></td>
		<td><%= all.getNo() %></td>
		<td><%= all.getReligion() %></td>
		<td><%= all.getSchool() %></td>
		<td><%for(int i=0;i<list.size();i++){
		%><%= all.getSkill() %></td>
	</tr>
	<%}

	%>
	</table>
</body>
</html>