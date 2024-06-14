<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>게시글 등록(boardForm.jsp)</h3>
<form action="addBoard.do">
    <input type="hidden" name="writer" value="${logId }">
    <table class="table">
        <tr>
            <th>제목</th>
            <td><input class="form-control" type="text" name="title"></td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>${logId }</td>
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
