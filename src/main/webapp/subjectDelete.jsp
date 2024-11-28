<%@ page import="dao.SubjectDAO" %>
<%@ page import="model.SubjectData" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    SubjectDAO subjectDao = new SubjectDAO();
    SubjectData subject = subjectDao.getSubjectById(id);
%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <title>科目削除</title>
</head>
<body>
    <h2>科目削除</h2>
    <p>本当に「<%= subject.getName() %>」を削除しますか？</p>
    <form action="ProcessServlet" method="post">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="id" value="<%= subject.getId() %>">
        <button type="submit">削除</button>
    </form>
    <a href="subjectList.jsp">戻る</a>
</body>
</html>
