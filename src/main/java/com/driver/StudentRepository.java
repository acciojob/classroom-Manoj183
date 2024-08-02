package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap = new HashMap<>();
    private HashMap<String, Teacher> teacherMap = new HashMap<>();
    private HashMap<String, List<String>> teacherStudentMapping = new HashMap<>();


    public void saveStudent(Student student){
        studentMap.put(student.getName(),student);
    }

    public void saveTeacher(Teacher teacher){
        teacherMap.put(teacher.getName(),teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
            if(!teacherStudentMapping.containsKey(teacher)){
                teacherStudentMapping.put(teacher,new ArrayList<>());
            }
            teacherStudentMapping.get(teacher).add(student);
    }

    public Student findStudent(String student){
        return studentMap.get(student);
    }

    public Teacher findTeacher(String teacher){
        return teacherMap.get(teacher);
    }

    public List<String> findStudentsFromTeacher(String teacher){
        return teacherStudentMapping.get(teacher);
    }

    public List<String> findAllStudents(){
        List<String> students = new ArrayList<>();
        for(String s: studentMap.keySet()){
            students.add(s);
        }
        return students;
    }

    public void deleteTeacher(String teacher){
        for(String str : teacherStudentMapping.get(teacher)){
            studentMap.remove(str);
        }
        teacherStudentMapping.remove(teacher);
        teacherMap.remove(teacher);
    }

    public void deleteAllTeachers(){
        for(String teacher: teacherMap.keySet()){
            deleteTeacher(teacher);
        }
    }
}