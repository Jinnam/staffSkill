<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action='<c:url value="/StaffSearchController"></c:url>' method="post">
		<table border="1" color="##00##" width="60%">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"/></td>
				<td>성별</td>
				<td><input type="checkbox" name="gender" value="1">남
					<input type="checkbox" name="gender" value="2">여</td>
				<td>종교</td>
				<td><select name="religion">
					<option value="0">종교선택</option>
					<c:forEach var="r" items="${ReligionList}">
						<option value="${r.no}">${r.name}</option>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>학력</td>
				<td><div>
					<c:forEach var="s" items="${SchoolList }">
						<input type="checkbox" name="school" value="${s.no}"/>${s.graduate}
					</c:forEach>
				</div></td>
				<td>기술</td>
				<td colspan="3"><div>
					<c:forEach var="sk" items="${SkillList }">
						<input type="checkbox" name="skill" value="${sk.no }"/>${sk.name }
					</c:forEach>
				</div></td>
			</tr>
			<tr colspan="5">
				<td>졸업일</td>
				<td><input type="date" name="graduateDayOne"/>&nbsp;~
					<input type="date" name="graduateDayTwo"/>
				</td>
			</tr>
			<tr>
				<td colspan="6" align="center"><input type="submit" value="검색"/></td>
			</tr>
		</table>
	</form>
</body>
</html>