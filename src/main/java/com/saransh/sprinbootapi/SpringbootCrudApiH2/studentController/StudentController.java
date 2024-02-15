package com.saransh.sprinbootapi.SpringbootCrudApiH2.studentController;

import com.saransh.sprinbootapi.SpringbootCrudApiH2.model.Student;
import com.saransh.sprinbootapi.SpringbootCrudApiH2.studentService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable long id, @RequestBody Student student){
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id){
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id){
        Optional<Student> student = studentService.getStudentById(id);
        if(student.isPresent()){
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> student = studentService.getAllStudent();
        if(!student.isEmpty()) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getStudentByDepartmentOrCity(@RequestParam(required = false) String studentDepartment, @RequestParam(required = false) String studentCity){
        List<Student> student = studentService.getStudentByDepartmentOrCity(studentDepartment,studentCity);
        if(!student.isEmpty()) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
