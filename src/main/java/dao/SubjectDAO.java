package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SubjectData;

public class SubjectDAO {
    private final DBConnector dbConnector = new DBConnector();

    // 全科目データを取得
    public List<SubjectData> getAllSubjects() {
        List<SubjectData> subjects = new ArrayList<>();
        String sql = "SELECT s.id, s.name, t.name AS teacher_name " +
                     "FROM subject s " +
                     "LEFT JOIN teachers t ON s.teacher_id = t.id";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                subjects.add(new SubjectData(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("teacher_name")
                ));
            }
        } catch (SQLException e) {
            System.err.println("getAllSubjects エラー: " + e.getMessage());
        }

        return subjects;
    }

    // 講師一覧を取得
    public List<String> getTeacherNames() {
        List<String> teacherNames = new ArrayList<>();
        String sql = "SELECT name FROM teachers";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                teacherNames.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("getTeacherNames エラー: " + e.getMessage());
        }

        return teacherNames;
    }

    // 科目の担当講師を更新
    public void updateSubjectTeacher(int subjectId, String teacherName) {
        String sql = "UPDATE subject SET teacher_id = (SELECT id FROM teachers WHERE name = ?) WHERE id = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, teacherName);
            pstmt.setInt(2, subjectId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updateSubjectTeacher エラー: " + e.getMessage());
        }
    }

    // 新しい科目を登録
    public void registerSubject(SubjectData subject) {
        String sql = "INSERT INTO subject (name, teacher_id) VALUES (?, (SELECT id FROM teachers WHERE name = ?))";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subject.getName());
            pstmt.setString(2, subject.getTeacherName());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("registerSubject エラー: " + e.getMessage());
        }
    }

    // 科目情報を更新
    public void updateSubject(SubjectData subject) {
        String sql = "UPDATE subject SET name = ?, teacher_id = (SELECT id FROM teachers WHERE name = ?) WHERE id = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subject.getName());
            pstmt.setString(2, subject.getTeacherName());
            pstmt.setInt(3, subject.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updateSubject エラー: " + e.getMessage());
        }
    }

    // 科目を削除
    public void deleteSubject(int id) {
        String sql = "DELETE FROM subject WHERE id = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("deleteSubject エラー: " + e.getMessage());
        }
    }
    // 科目IDに基づいて科目データを取得
    public SubjectData getSubjectById(int id) {
        SubjectData subject = null;
        String sql = "SELECT s.id, s.name, t.name AS teacher_name " +
                     "FROM subject s " +
                     "LEFT JOIN teachers t ON s.teacher_id = t.id " +
                     "WHERE s.id = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    subject = new SubjectData(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("teacher_name")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("getSubjectById エラー: " + e.getMessage());
        }

        return subject;
    }

}
