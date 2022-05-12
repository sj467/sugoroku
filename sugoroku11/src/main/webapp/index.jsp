<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>すごろくゲーム</title>
<link rel="stylesheet" href="/sugoroku11/css/style.css">
</head>
<body>
<h1>すごろくゲーム</h1>
<p>ユーザー名を半角英数字8文字以内で入力してください。</p>
<form action="/sugoroku11/NameCheckServlet" method="post">
<input type="text" name="name"><br>
<br>
<input type="submit" value="ゲームを始める">
</form>
<p class="errorMsg">${errorMsg}<p><br>
<br>
<a href="/sugoroku11/RankingServlet">ランキングを見る</a>
</body>
</html>