<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.SubjectDAO" %>
<%@ page import="model.SubjectData" %>
<%
    SubjectDAO subjectDao = new SubjectDAO();
    List<SubjectData> subjects = subjectDao.getAllSubjects(); // 科目一覧を取得
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>科目一覧</title>
</head>
<body>
    <h2>科目一覧</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>科目名</th>
            <th>担当講師</th>
            <th>操作</th>
        </tr>
        <%
            for (SubjectData subject : subjects) {
        %>
        <tr>
            <td><%= subject.getId() %></td>
            <td><%= subject.getName() %></td>
            <td><%= subject.getTeacherName() %></td>
            <td>
                <a href="subjectUpdate.jsp?id=<%= subject.getId() %>">編集</a>
                <!-- 修正した削除部分 -->
                <form action="ProcessServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<%= subject.getId() %>">
                    <button type="submit" onclick="return confirm('本当に削除しますか？');">削除</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="subjectRegister.jsp">新規登録</a>
</body>
</html>
