package com.saransh.sprinbootapi.SpringbootCrudApiH2.studentRepository;

import com.saransh.sprinbootapi.SpringbootCrudApiH2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByStudentDepartmentAndStudentCity(String studentDepartment, String studentCity);

    List<Student> findByStudentDepartment(String studentDepartment);

    List<Student> findByStudentCity(String studentCity);
}
