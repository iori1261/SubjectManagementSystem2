package servlet;

import java.io.IOException;
import java.util.Enumeration;

import dao.SubjectDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SubjectData;

@WebServlet("/ProcessServlet")
public class ProcessServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        SubjectDAO subjectDao = new SubjectDAO();

        try {
            if ("updateAll".equals(action)) {
                // 一括更新処理
                Enumeration<String> paramNames = request.getParameterNames();
                while (paramNames.hasMoreElements()) {
                    String paramName = paramNames.nextElement();
                    if (paramName.startsWith("teacher_")) {
                        int subjectId = Integer.parseInt(paramName.substring(8)); // teacher_ の後ろを取得して subjectId に変換
                        String teacherName = request.getParameter(paramName); // 選択された講師名
                        subjectDao.updateSubjectTeacher(subjectId, teacherName); // 更新メソッド呼び出し
                    }
                }
                response.sendRedirect("subjectList.jsp"); // 更新後にリダイレクト
            } else if ("register".equals(action)) {
                // 新規登録処理
                String name = request.getParameter("name");
                String teacherName = request.getParameter("teacherName");
                if (teacherName == null || teacherName.isEmpty()) {
                    teacherName = "未設定"; // 未設定時のデフォルト値
                }
                subjectDao.registerSubject(new SubjectData(0, name, teacherName));
                response.sendRedirect("subjectList.jsp");
            } else if ("update".equals(action)) {
                // 更新処理
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String teacherName = request.getParameter("teacherName");
                if (teacherName == null || teacherName.isEmpty()) {
                    teacherName = "未設定"; // 未設定時のデフォルト値
                }
                subjectDao.updateSubject(new SubjectData(id, name, teacherName));
                response.sendRedirect("subjectList.jsp");
            } else if ("delete".equals(action)) {
                // 削除処理
                int id = Integer.parseInt(request.getParameter("id"));
                subjectDao.deleteSubject(id);
                response.sendRedirect("subjectList.jsp");
            } else {
                // 不正なアクション
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "不正なアクションが指定されました。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "処理中にエラーが発生しました: " + e.getMessage());
        }
    }
}
