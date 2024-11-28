<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.SubjectDAO" %>
<%@ page import="model.SubjectData" %>
<%
    // ID パラメータの取得と検証
    String idParam = request.getParameter("id");
    if (idParam == null || idParam.isEmpty()) {
        throw new IllegalArgumentException("科目IDが指定されていません。");
    }
    int id = Integer.parseInt(idParam);

    // 科目データの取得
    SubjectDAO subjectDao = new SubjectDAO();
    SubjectData subject = subjectDao.getSubjectById(id);
    if (subject == null) {
        throw new IllegalArgumentException("指定されたIDに対応する科目が見つかりません。");
    }

    // 講師リストの取得
    List<String> teacherNames = subjectDao.getTeacherNames();
    if (teacherNames == null || teacherNames.isEmpty()) {
        teacherNames = List.of("未設定"); // 空リストの場合のデフォルト値
    }
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>科目更新</title>
</head>
<body>
    <h2>科目更新</h2>
    <form action="ProcessServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= subject.getId() %>">
        <label for="name">科目名:</label>
        <input type="text" id="name" name="name" value="<%= subject.getName() %>" required>
        <br>
        <label for="teacherName">担当講師:</label>
        <select id="teacherName" name="teacherName">
            <% for (String teacher : teacherNames) { %>
                <option value="<%= teacher %>" <%= teacher.equals(subject.getTeacherName()) ? "selected" : "" %>>
                    <%= teacher %>
                </option>
            <% } %>
        </select>
        <br>
        <button type="submit">更新</button>
    </form>
    <a href="subjectList.jsp">戻る</a>
</body>
</html>
