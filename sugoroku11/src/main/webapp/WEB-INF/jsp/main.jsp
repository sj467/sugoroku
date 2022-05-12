<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>すごろくゲーム</title>
<link rel="stylesheet" href="/sugoroku11/css/style.css">
</head>
<body>
<div id="rule">ルール<br>
片方がゴールした時点で終了。ポイントの多いほうが勝ち。先にゴールした方に+10000ポイント。<br>
●:ユーザーの位置　▲:CPUの位置　◆:ユーザーとCPUが同じ位置にいる時　＋:ポイントプラス　ー:ポイントマイナス　★:もう一度サイコロを振る</div>
<table class="block">
	<tr>
		<td id="start">スタート</td>
	</tr>
	<tr>
		<td id="stage">${stage}</td>
	</tr>
	<tr>
		<td id="goal">ゴール</td>
	</tr>
</table>
<c:choose>
	<c:when test="${turnPlayer.name == \"CPU\"}">
		${notTurnPlayer.name}:${notTurnPlayer.point}　${turnPlayer.name}:${turnPlayer.point}
	</c:when>
	<c:otherwise>
		${turnPlayer.name}:${turnPlayer.point}　${notTurnPlayer.name}:${notTurnPlayer.point}
	</c:otherwise>
</c:choose>
<br>
<br>
<c:choose>
	<c:when test="${not empty endmsg}">
		${endmsg}<br>
		<c:choose>
			<c:when test="${turnPlayer.point > notTurnPlayer.point}">
				${turnPlayer.point}ポイントで${turnPlayer.name}の勝ちです。
			</c:when>
			<c:when test="${turnPlayer.point < notTurnPlayer.point}">
				${notTurnPlayer.point}ポイントで${notTurnPlayer.name}の勝ちです。
			</c:when>
			<c:otherwise>
				${turnPlayer.point}ポイントで引き分けです。
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test="${not empty msg}">
		${msg}<br>
		${turnPlayer.name}はもう一度サイコロを振ります。<br>
		<form action="/sugoroku11/GameController" method="post">
		<input type="hidden" name="moveAgain" value="0">
		<input type="submit" value="進める">
		</form>
	</c:when>
	<c:when test="${not empty branchStr}">
		${notTurnPlayer.name}のサイコロの目は${notTurnPlayer.diceNumber}でした。<br>
		進む方向を選んでください。
		<form action="/sugoroku11/GameController" method="post">
		<input type="hidden" name="branch" value="0">
		<input type="submit" value="${branchStr[0]}">
		</form>
		<form action="/sugoroku11/GameController" method="post">
		<input type="hidden" name="branch" value="1">
		<input type="submit" value="${branchStr[1]}">
		</form>
	</c:when>
	<c:otherwise>
		<c:if test="${turnPlayer.position != 0}">
			${turnPlayer.name}のサイコロの目は${turnPlayer.diceNumber}でした。<br>
			${turnPlayer.diceNumber}進みました。<br>
		</c:if>
		<c:if test="${not empty point}">
			<c:choose>
				<c:when test="${point > 0}">
					<br>
					＋に止まりました。<br>
					${point}ポイント加算されました。<br>
				</c:when>
				<c:when test="${point < 0}">
					<br>
					ーに止まりました。<br>
					${point}ポイント減算されました。<br>
				</c:when>
			</c:choose>
		</c:if>
		<br>
		${notTurnPlayer.name}のターンです。
		<form action="/sugoroku11/GameController" method="post">
		<input type="submit" value="${buttonText}">
		</form>
	</c:otherwise>
</c:choose>
<br>
<p id="gameEnd"><a href="/sugoroku11/GameController">ゲームを終了する</a><br>
途中のデータは保存されません。</p>
</body>
</html>