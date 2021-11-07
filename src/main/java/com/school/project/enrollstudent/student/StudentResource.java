package com.school.project.enrollstudent.student;

import com.school.project.enrollstudent.exception.StudentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentResource {

    private static final Logger logger = LoggerFactory.getLogger(StudentResource.class);

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(path = "/fetchStudents")
    public List<Student> fetchAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping(path = "/fetchStudents/class/{className}")
    public List<Student> fetchClassStudentsDetails(@PathVariable String className) {
        List<Student> students = studentRepository.findByClassName(className);
        if (students.size() == 0) {
            throw new StudentNotFoundException("no student record found for class - " + className);
        }
        return students;
    }

    @GetMapping(path = "/fetchStudents/id/{id}")
    public Student fetchStudentDetail(@PathVariable int id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new StudentNotFoundException("no student record found with Id - " + id);
        }
        return student;
    }

    @PostMapping(path = "/students")
    public void enrollStudent(@Valid @RequestBody Student student) {
        studentRepository.save(student);
    }

    @PutMapping(path = "/students")
    public void updateStudent(@Valid @RequestBody Student student) {
        int id = student.getId();
        Student studentToUpdate = studentRepository.findById(id);
        if (studentToUpdate == null) {
            throw new StudentNotFoundException("student does not exist with Id - " + id);
        } else if (student.getFirstName() != null) {
            studentToUpdate.setFirstName(student.getFirstName());
        } else if (student.getLastName() != null) {
            studentToUpdate.setLastName(student.getLastName());
        } else if (student.getClassName() != null) {
            studentToUpdate.setClassName(student.getClassName());
        } else if (student.getNationality() != null) {
            studentToUpdate.setNationality(student.getNationality());
        }
        studentRepository.save(studentToUpdate);
    }

    @DeleteMapping(path = "/students")
    public void deleteStudent(@Valid @RequestBody Student student) {
        int id = student.getId();
        Student studentToDelete = studentRepository.findById(id);
        if (studentToDelete == null) {
            throw new StudentNotFoundException("student does not exist with Id - " + id);
        } else {
            studentRepository.deleteById(id);
            logger.info("deleted student record with Id - " + id);
        }
    }

}
