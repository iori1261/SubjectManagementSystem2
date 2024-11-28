package model;

import java.util.List;

import dao.SubjectDAO;

public class SubjectList {
    private List<SubjectData> subjects;
    private List<String> teacherNames;

    public SubjectList() {
        try {
            SubjectDAO subjectDao = new SubjectDAO();
            this.subjects = subjectDao.getAllSubjects();
            this.teacherNames = subjectDao.getTeacherNames();
        } catch (Exception e) {
            System.err.println("SubjectList 初期化エラー: " + e.getMessage());
            this.subjects = List.of();
            this.teacherNames = List.of();
        }
    }

    public List<SubjectData> getSubjects() {
        return subjects != null ? subjects : List.of();
    }

    public List<String> getTeacherNames() {
        return teacherNames != null ? teacherNames : List.of();
    }

    public SubjectData getSubjectById(int id) {
        if (subjects == null || subjects.isEmpty()) return null;
        for (SubjectData subject : subjects) {
            if (subject.getId() == id) return subject;
        }
        return null;
    }
}
