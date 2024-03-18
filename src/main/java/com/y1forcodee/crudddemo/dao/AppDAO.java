package com.y1forcodee.crudddemo.dao;

import com.y1forcodee.crudddemo.entity.Course;
import com.y1forcodee.crudddemo.entity.Instructor;
import com.y1forcodee.crudddemo.entity.InstructorDetail;
import com.y1forcodee.crudddemo.entity.Student;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update(Instructor tempInstructor);
    void update(Course tempCourse);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);


    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);

    Course findCourseAndStudentsByCourseId(int theId);

    Student findStudentAndCoursesByStudentId(int theId);


    void update(Student tempStudent);

    void deleteStudentById(int theId);
}
