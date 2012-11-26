<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Commendans</title>
</head>
<body>

	<p>Welcome to commendans, an REST recommendation service.<p>
	
	<p>You may register your client app <a href='<c:url value="/app/new" />'>here</a></p>
	
	<p>Some routes implemented:</p>
	<ul>
		<li><pre>GET <c:url value="/recommend/item/" />{appItemId}</pre>
			<ul>
				<li><strong>Returns a sorted array of recommendations given an item of your app (identified with id <code>appItemId</code>)</strong></li>
				<li>Parameters:</li>
				<ul>
					<li><pre>accessKey: {your-access-key}</pre></li>
				</ul>
			</ul>
		</li>
		<li><pre>GET <c:url value="/recommend/items/" /></pre>
			<ul>
				<li><strong>Returns a sorted array of recommendations given an array of items of your app (identified with ids array <code>items</code>)</strong></li>
				<li>Parameters:</li>
				<ul>
					<li><pre>accessKey: {your-access-key}</pre></li>
					<li><pre>items[0]: {some-item-id}</pre></li>
					<li><pre>items[1]: {some-item-id}</pre></li>
					<li><pre>items[...]: ...</pre></li>
				</ul>
			</ul>
		</li>
		<li><pre>POST <c:url value="/app/addSale" /></pre>
			<ul>
				<li><strong>Register a event of your app, given a user id and a list of items bought (or liked, or visited, whatever)</strong></li>
				<li>Parameters:</li>
				<ul>
					<li><pre>accessKey: {your-access-key}</pre></li>
					<li><pre>clientAppUserId: {some-user-id}</pre></li>
					<li><pre>items[0]: {some-item-id}</pre></li>
					<li><pre>items[1]: {some-item-id}</pre></li>
					<li><pre>items[...]: ...</pre></li>
				</ul>
			</ul>
		</li>
	</ul>

</body>
</html>