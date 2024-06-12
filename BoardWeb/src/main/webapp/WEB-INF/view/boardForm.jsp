<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../public/header.jsp"%>
<h3>게시글 등록</h3>
<%
	String msg = (String) request.getAttribute("message");
	if (msg != null) {
		out.print("<p>" + msg + "</p>");
	}
%>
<form action="addBoard.do">
    <table class="table">
        <tr>
            <th>제목</th>
            <td><input class="form-control" type="text" name="title"></td>
        </tr>
        <tr>
            <th>작성자</th>
            <td><input class="form-control" type="text" name="writer"></td>
        </tr>
        <tr>
            <th>내용</th>
            <td><textarea class="form-control" name="content"></textarea></td>
        </tr>
        <tr align="center">
            <td colspan="2"><input type="submit" value="등록" class="btn btn-primary"></td>
        </tr>
    </table>
</form>
<%@include file="../public/footer.jsp"%>