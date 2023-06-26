<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2 style="color:yellow;">Upload file</h2>
<br><br>
<form action="/upload-file" method="post" enctype="multipart/form-data">
    Name : <input type="text" name="name">  <br> <br>
    IMG : <input type="file" name="fileIMG" accept="image/png,.jpg">
    <br> <br>
<button type="submit">VIEW</button>
</form>
</body>
</html>
