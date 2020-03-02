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
.custom-file {
	display: none;
}
</style>

</head>
<body>


	<div class="container">



		<form action="boardUpdate" method="post" enctype="multipart/form-data">
			<input type="hidden" name="boardKey" value="${item.boardKey }">
			<table class="table table-hover">

				<tbody>
					<tr>
						<td>작성자</td>
						<td><input type="text" value="${item.author }" name="author"
							readonly="readonly"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" value="${item.title }" name="title"
							readonly="readonly"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea rows="5" cols="100" name="content"
								readonly="readonly">
						${item.content}
						</textarea></td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td>
							
							<input type="hidden" name="originFile" value="${item.fileName }">
							<div class="custom-file">
								<input type="file" name="uploadfile" class="custom-file-input"
									id="inputGroupFile01"> <label class="custom-file-label"
									for="inputGroupFile01">${item.fileNameOrigin }</label>
							</div> <c:choose>
								<c:when test="${!empty item.fileNameOrigin }">
									<a id="downloadFile"
										href="BoardFileDown?fileName=${item.fileName}&fileNameOrigin=${item.fileNameOrigin}">
										<i class="fas fa-file-image"></i> ${item.fileNameOrigin }
									</a>
								</c:when>
								<c:otherwise>
									<a id="downloadFile"> <i class="fas fa-file-image"></i>첨부된
										파일이 없습니다.
									</a>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>

				</tbody>

			</table>

			<button type="button" class="btn btn-primary btn_update"
				style="margin-top: 3vh;">수정</button>
			<button type="button" class="btn btn-danger"
				onclick="boardDetail.deletee();" style="margin-top: 3vh;">삭제</button>

		</form>

		
		
		<table class="table table-hover" style="margin-top: 10vh;">
			<thead>
				<tr>
					<td>총 00개의 댓글</td>
				</tr>
				<tr>
					<td>댓글 번호</td>
					<td>작성자</td>
					<td>내용</td>
					<td>작성일자</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty comment }">
					<tr>
						<td colspan="4">작성된 댓글이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${!empty comment }">
					<tr>
						<td>댓글 번호</td>
						<td>작성자</td>
						<td>내용</td>
						<td>작성일자</td>
					</tr>
				</c:if>
			</tbody>

			<tfoot>
				<tr>
					
					<td colspan="3"><input type="text" name="comment"
						style="width: 100%;"></td>
					<td>
						<button class="btn btn-secondary" type="submit">댓글 추가</button>
					</td>
				</tr>
			</tfoot>
		</table>



		<button class="btn btn-secondary" onclick="location.href='main';"
			style="margin-top: 3vh;">돌아가기</button>

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
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-latest.js"></script>
	<script>
		var boardDetail = {
			init : function() {
				var _this = this;

				$(".btn_update").on('click', function() {

					if ($('.btn_update').text() == '수정')
						_this.update();
					else
						_this.updateSave();
				});

				$('input[type="file"]').change(
						function() {

							var inputfile = $(this).val().split('\\');
							$('.custom-file-label').text(
									inputfile[inputfile.length - 1]);
						});

			},
			update : function() {
				//글을 수정할 수 있게 readOnly 속성 해제
				$('input[name="title"]').prop('readonly', false);
				$('input[type="file"]').prop('readonly', false);
				$('textarea').prop('readonly', false);
				var inputfile = $('td:last a').text();

				$('.custom-file').css("display", "block");
				$('#downloadFile').css("display", "none");
				//	$('#filevalue').text(inputfile[inputfile.length - 1]);
				$('.btn_update').text('수정 완료');
			},
			updateSave : function() {

				$('form').submit();

			},
			deletee : function() {
				//글 삭제여부 확인
				check = confirm('정말 삭제하시겠습니까?');

				if (check) {
					//ajax 처리
					$.ajax({
						url : "deleteBoard",
						method : "post",
						data : {
							"boardKey" : $('input[type="hidden"]').val()
						},
						success : function(result) {

							if (result == 1) {
								alert("삭제되었습니다.");
								location.href = "main";
							}
						}
					});
				}

			}

		};

		boardDetail.init();
	</script>
</body>
</html>