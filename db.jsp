
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>DataBase</title>
</head>
<body>
<h1>
    Work for DB

</h1>
<h3>
    <%=request.getAttribute("dbMessage") %>

</h3>
<p>
    <a href="?query=create" style="display: inline-block;background: blueviolet; color:greenyellow;border-radius: 5px; padding: 5px 10px;
    text-decoration: none">
        Create a user
    </a>

</p>
<p>
    <a href="?query=deleted" style="display: inline-block;background: blue; color:greenyellow;border-radius: 5px; padding: 5px 10px;
    text-decoration: none">
        delete a user
    </a>
</p>
</body>
</html>
