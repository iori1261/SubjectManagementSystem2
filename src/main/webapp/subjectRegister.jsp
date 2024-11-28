<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.SubjectDAO" %>
<%
    SubjectDAO subjectDao = new SubjectDAO();
    List<String> teacherNames = subjectDao.getTeacherNames(); // 講師リストを取得
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>科目登録</title>
</head>
<body>
    <h2>科目登録</h2>
    <form action="ProcessServlet" method="post">
        <input type="hidden" name="action" value="register">
        <label for="name">科目名:</label>
        <input type="text" id="name" name="name" required>
        <br>
        <label for="teacherName">担当講師:</label>
        <select id="teacherName" name="teacherName">
            <% for (String teacher : teacherNames) { %>
                <option value="<%= teacher %>"><%= teacher %></option>
            <% } %>
        </select>
        <br>
        <button type="submit">登録</button>
    </form>
    <a href="subjectList.jsp">戻る</a>
</body>
</html>
