package com.saransh.sprinbootapi.SpringbootCrudApiH2.studentService;

import com.saransh.sprinbootapi.SpringbootCrudApiH2.model.Student;
import com.saransh.sprinbootapi.SpringbootCrudApiH2.studentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student createStudent(Student student) {
        try {
            return studentRepository.save(new Student(student.getStudentName(), student.getDateOfBirth(), student.getStudentCity(), student.getStudentDepartment()));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String updateStudent(long id, Student student){
        try{
            if(studentRepository.findById(id).isPresent()){
                Student studentExist = studentRepository.findById(id).get();
                studentExist.setStudentName(student.getStudentName());
                studentExist.setDateOfBirth(student.getDateOfBirth());
                studentExist.setStudentCity(student.getStudentCity());
                studentExist.setStudentDepartment(student.getStudentDepartment());
                studentRepository.save(studentExist);
                return "Student updated successfully for: "+id;
            }
            else{
                return "Student does not exist";
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String deleteStudent(long id) {
        try {
            if(studentRepository.findById(id).isPresent()){
                studentRepository.deleteById(id);
                return "Student deleted successfully";
            }
            else{
                return "Student does not exist";
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Student> getStudentById(long id) {
        try {
            return studentRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Student> getAllStudent() {
        try {
            return studentRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Student> getStudentByDepartmentOrCity(String studentDepartment, String studentCity) {
        try {
            if(studentDepartment!=null && studentCity==null){
                return studentRepository.findByStudentDepartment(studentDepartment);
            }
            else if(studentDepartment==null && studentCity!=null){
                return studentRepository.findByStudentCity(studentCity);
            }
            else if(studentDepartment!=null && studentCity!=null){
                return studentRepository.findByStudentDepartmentAndStudentCity(studentDepartment, studentCity);
            }
            else{
                return List.of();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
