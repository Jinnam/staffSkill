<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action='<c:url value="/StaffAddController"></c:url>' method="post">
		<table border="1"  width="60%">
			<tr>
				<td>�̸�</td>
				<td><input type="text" name="name"/></td>
				<td>�ֹι�ȣ</td>
				<td><input type="text" maxlength="6" name="snOne">-
					<input type="text" maxlength="7" name="snTwo"></td>
				<td>����</td>
				<td><select name="religion">
					<c:forEach var="r" items="${ReligionList}">
						<option value="${r.no}">${r.name}</option>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>�з�</td>
				<td><div>
					<c:forEach var="s" items="${SchoolList }">
						<input type="radio" name="school" value="${s.no}"/>${s.graduate}
					</c:forEach>
				</div></td>
				<td>���</td>
				<td colspan="3"><div>
					<c:forEach var="sk" items="${SkillList }">
						<input type="checkbox" name="skill" value="${sk.no }"/>${sk.name }
					</c:forEach>
				</div></td>
			</tr>
			<tr colspan="5">
				<td>������</td>
				<td><input type="date" name="graduateDay"/>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="right"><input type="submit" value="���"/></td>
				<td colspan="3"><input type="reset" vaule="�ʱ�ȭ"/>
				
			</tr>
		</table>
	</form>
</body>
</html>