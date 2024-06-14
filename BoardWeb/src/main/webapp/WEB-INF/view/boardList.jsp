<%@page import="co.yedam.common.PageDTO"%>
<%@page import="co.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- "boardList"에 담긴 값을 읽어서 반복처리. -->
<%@include file="../public/header.jsp" %>
<style>
  .center {
    text-align: center;
  }

  .pagination {
    display: inline-block;
  }

  .pagination a {
    color: black;
    float: left;
    padding: 8px 16px;
    text-decoration: none;
    transition: background-color .3s;
    border: 1px solid #ddd;
    margin: 0 4px;
  }

  .pagination a.active {
    background-color: #4CAF50;
    color: white;
    border: 1px solid #4CAF50;
  }

  .pagination a:hover:not(.active) {
    background-color: #ddd;
  }
</style>

<h3>게시글 목록(boardList.jsp)</h3>

<div class="center">
  <form action="boardList.do">
    <div class="row">
      <div class="col-sm-4">
        <select name="searchCondition" class="form-control">
          <option value="">선택하세요</option>
          <option value="T">제목</option>
          <option value="W">작성자</option>
          <option value="TW">제목&작성자</option>
        </select>
      </div>
      <div class="col-sm-6">
        <input type="text" name="keyword" class="form-control">
      </div>
      <div class="col-sm-2">
        <input type="submit" value="찾기" class="btn btn-primary">
      </div>
    </div>
  </form>
</div>
<p>sc: ${searchCondition }, kw: ${keyword }</p>
<table class="table">
  <thead>
    <tr>
      <th>글번호</th>
      <th>제목</th>
      <th>작성자</th>
      <th>조회수</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="vo" items="${boardList }">
      <tr>
        <td>${vo.boardNo }</td>
        <td><a href="getBoard.do?bno=${vo.boardNo }&page=${paging.page}&searchCondition=${searchCondition}&keyword=${keyword}">
            <c:out value="${vo.title }" /></a></td>
        <td>
          <c:out value="${vo.writer }" />
        </td>
        <td>
          <c:out value="${vo.clickCnt }" />
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<!-- paging -->
<div class="center">
  <div class="pagination">

    <c:if test="${paging.prev }">
      <a href="boardList.do?page=${paging.startPage-1 }&searchCondition=${searchCondition}&keyword=${keyword}">&laquo;</a>
    </c:if>

    <c:forEach var="p" begin="${paging.startPage }" end="${paging.endPage }">
        <a href="boardList.do?page=${p }&searchCondition=${searchCondition}&keyword=${keyword}" class=${p == paging.page ? 'active' : '' }>
            <c:out value="${p }" /></a>
    </c:forEach>

    <c:if test="${paging.next }">
      <a href="boardList.do?page=${paging.endPage+1 }&searchCondition=${searchCondition}&keyword=${keyword}">&raquo;</a>
    </c:if>

  </div>
</div>

<%@include file="../public/footer.jsp" %>