package com.school.project.enrollstudent.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByClassName(String className);

    Student findById(int id);
}
