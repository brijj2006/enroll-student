package com.school.project.enrollstudent.student;

import com.school.project.enrollstudent.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentResource {

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

    /*@GetMapping(path = "/fetchStudents")
    public List<Student> fetchStudent(@RequestParam String className) {
        return studentRepository.findByClassName(className);
    }*/

    /*@GetMapping(path = "/fetchStudents")
    public Student fetchStudent(@RequestParam int id) {
        return studentRepository.findById(id);
    }*/

    @PostMapping(path = "/students")
    public void enrollStudent(@Valid @RequestBody Student student) {
        studentRepository.save(student);

    }

    @PutMapping(path = "/students")
    public void updateStudent(@Valid @RequestBody Student student) {
        Student studentToUpdate = studentRepository.findById(student.getId());
        if (studentToUpdate != null) {
            if (student.getFirstName() != null) {
                studentToUpdate.setFirstName(student.getFirstName());
            }
            if (student.getLastName() != null) {
                studentToUpdate.setLastName(student.getLastName());
            }
            if (student.getClassName() != null) {
                studentToUpdate.setClassName(student.getClassName());
            }
            if (student.getNationality() != null) {
                studentToUpdate.setNationality(student.getNationality());
            }
            studentRepository.save(studentToUpdate);
        }
    }

    @DeleteMapping(path = "/students")
    public void deleteStudent(@Valid @RequestBody Student student) {
        int id = student.getId();
        studentRepository.deleteById(id);
    }

}
