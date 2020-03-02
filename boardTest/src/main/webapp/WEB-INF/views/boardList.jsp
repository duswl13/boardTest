<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC BoardTest</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<style>
tbody td {
	cursor: pointer;
}
</style>
</head>
<body>


	<div class="container">



		<table class="table table-hover">
			<thead>
				<tr>
					<td>글 번호</td>
					<td>글 제목</td>
					<td>작성자</td>
					<td>작성 일자</td>
					<td>조회수</td>
				</tr>
			</thead>
			<tbody>

				<c:if test="${empty list }">
					<tr>
						<td colspan="5">게시글이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach items="${list }" var="item">
					<tr onclick="location.href='boardDetail?boardKey=${item.boardKey}'">

						<td>${item.boardKey }</td>
						<td>${item.title }<c:if test="${!empty item.fileNameOrigin }">
								<i class="fas fa-file-image"></i>
							</c:if>
						</td>
						<td>${item.author }</td>
						<td>${item.writeDate }</td>
						<td>${item.readCount }</td>

					</tr>
				</c:forEach>

			</tbody>

		</table>


		<div class="input-group">


			<input type="text" class="form-control"
				placeholder="작성자/제목/내용을 검색하세요.">
			<div class="input-group-append">
				<button class="btn btn-secondary" type="button">
					<i class="fa fa-search"></i>
				</button>
			</div>
		</div>
		<button class="btn btn-primary" onclick="location.href='boardWrite'"
			style="margin-top: 3vh;">글쓰기</button>

	</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

</body>
</html>