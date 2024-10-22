<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="/WEB-INF/fragment/navbar.jsp"/>
<h2>${board.id}번 게시글 보기</h2>

<div>
    <input type="text" value="${board.title}" readonly>
</div>
<div>
    <textarea id="" cols="30" rows="10" readonly>${board.content}</textarea>
</div>
<div>
    작성자
    <input type="text" name="writer" value="${board.writer}" readonly>
</div>
<div>
    작성일자
    <input type="text" value="${board.inserted}" readonly>
</div>
</body>
</html>
