package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        studentMap.put(student.getName(),student);
    }

    public void saveTeacher(Teacher teacher){
        teacherMap.put(teacher.getName(),teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            if(!teacherStudentMapping.containsKey(teacher)){
                teacherStudentMapping.put(teacher,new ArrayList<>());
            }
            teacherStudentMapping.get(teacher).add(student);
            Teacher t = teacherMap.get(teacher);
            t.setNumberOfStudents(t.getNumberOfStudents()+1);
        }
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
        teacherMap.remove(teacher);
        teacherStudentMapping.remove(teacher);
    }

    public void deleteAllTeachers(){
        teacherMap.clear();
    }
}