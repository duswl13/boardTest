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
</head>
<body>


	<div class="container">
	
	
			
	<form action="boardSave" method="post" enctype="multipart/form-data">
		<table class="table table-hover">
		
			<tbody>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="author" required="required"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" required="required"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="5" cols="100" name="content" required="required"></textarea></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td>
					<div class="custom-file">
							<input type="file" name="uploadfile" class="custom-file-input" id="inputGroupFile01">
							<label class="custom-file-label" for="inputGroupFile01">${item.fileNameOrigin }</label>
					</div>
				</td>
			</tr>
				
			</tbody>

		</table>

		<button class="btn btn-primary" type="submit" style="margin-top: 3vh;">작성</button>	
	
	</form>
			<button class="btn btn-secondary" onclick="location.href='main';" style="margin-top: 3vh;">돌아가기</button>	
		
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
	<script>
	var boardWrite = {
			init : function() {
				var _this = this;

				
				
				$('input[type="file"]').change(function(){
					
					 var inputfile = $(this).val().split('\\');
					 $('.custom-file-label').text(inputfile[inputfile.length - 1]);
				});

			}

		};

	boardWrite.init();
	</script>
</body>
</html>