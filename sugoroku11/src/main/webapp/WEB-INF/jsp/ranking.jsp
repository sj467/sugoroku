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
<h1>ランキング</h1>
<table class="block" border="1">
	<tr>
		<th></th>
		<th>ユーザー名</th>
		<th>ポイント</th>
	</tr>
	<tr>
		<td>1</td>
		<td>${rankingList[0].name}</td>
		<td>${rankingList[0].point}</td>
	</tr>
	<tr>
		<td>2</td>
		<td>${rankingList[1].name}</td>
		<td>${rankingList[1].point}</td>
	</tr>
	<tr>
		<td>3</td>
		<td>${rankingList[2].name}</td>
		<td>${rankingList[2].point}</td>
	</tr>
	<tr>
		<td>4</td>
		<td>${rankingList[3].name}</td>
		<td>${rankingList[3].point}</td>
	</tr>
	<tr>
		<td>5</td>
		<td>${rankingList[4].name}</td>
		<td>${rankingList[4].point}</td>
	</tr>
</table>
<p><a href="/sugoroku11/GameController">戻る</a></p>
</body>
</html>