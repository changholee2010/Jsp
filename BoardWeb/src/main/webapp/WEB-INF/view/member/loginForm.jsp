<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="cust" %>   

<h3>로그인화면(loginForm.jsp)</h3>

<cust:line/>

<c:set var="name" value="hong"></c:set>
<c:out value="${name == 'hong' ? '맞다' : '다르다'}"></c:out>
 
<c:forEach var="i" begin="1" end="10" step="2">
  <p>${i }</p>
</c:forEach>

<!-- JSP standard tag library -->
<form action="login.do">
<table class="table">
  <tr>
    <th>아이디</th><td><input type="text" name="id"></td>
  </tr>
  <tr>
    <th>비밀번호</th><td><input type="password" name="pw"></td>
  </tr>
  <tr>
    <td colspan="2"><input type="submit" value="로그인">
    </td>
  </tr>
</table>
</form>
