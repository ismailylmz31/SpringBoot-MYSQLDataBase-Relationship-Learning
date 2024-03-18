package com.y1forcodee.crudddemo;

import com.y1forcodee.crudddemo.dao.AppDAO;
import com.y1forcodee.crudddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner ->{
			//createCourseAndStudents(appDAO);
			//findCourseAndSrudents(appDAO);
			//findStudentAndCourses(appDAO);
			//addMoreCoursesForStudent(appDAO);
			//deleteCourseById(appDAO);
			deleteStudent(appDAO);


		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId=1;
		System.out.println("Deleting Student id: "+theId);
		appDAO.deleteStudentById(theId);
		System.out.println("OGRENCI silindi");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId=2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);
		// create more course
		Course tempCourse1 = new Course("KILIC ALI FILM MUZIKLERI");
		Course tempCourse2 = new Course("MAD MAX FILM MUZIKLERI");
		Course tempCourse3 = new Course("USMAN AGA FILM MUZIKLERI");

		// add courses the student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);
		tempStudent.addCourse(tempCourse3);
		System.out.println("saving student: "+tempStudent );
		System.out.println("associated courses : "+ tempStudent.getCourses());
		appDAO.update(tempStudent);

		System.out.println("everthing is up to date USMAN AGAAAA");

	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int theId=2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);
		System.out.println("Loaded Student: "+tempStudent);
		System.out.println("Courses : " +tempStudent.getCourses());
		System.out.println("MANY TO MANY ISLEMI BASARILI");
	}

	private void findCourseAndSrudents(AppDAO appDAO) {
		int theId=10;
		Course tempCourse =appDAO.findCourseAndStudentsByCourseId(theId);
		System.out.println("KURSUNUZ : "+tempCourse);
		System.out.println("ogrenciler : "+ tempCourse.getStudents());
		System.out.println("basarili");
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("INEK SABAN FILM MUZIKLERI");
		//ADD SOME REVIEW
		Student tempStudent1=new Student("Suleyman","cakir","kv@gmail.com");
		Student tempStudent2=new Student("ZIYA","YILMAZ","SILAH@gmail.com");
		Student tempStudent3=new Student("LAPSEKILI","TAYFUR","DARBUKA@gmail.com");

		// SAVE THE COURSE and leverage cascade all
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);
		tempCourse.addStudent(tempStudent3);

		System.out.println("saving the course: "+tempCourse);
		System.out.println("associated student: "+tempCourse.getStudents());
		appDAO.save(tempCourse);


		System.out.println("everything is allright");
	}

	private void deleteCourseAndRevievs(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id: "+theId);
		appDAO.deleteCourseById(theId);
		System.out.println("silindi");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		// get the course and reviews
		int theId=10;
		Course tempCourse= appDAO.findCourseAndReviewsByCourseId(theId);
		// print the course
		System.out.println(tempCourse);
		//print the reviews
		System.out.println(tempCourse.getReviews());
		System.out.println("SORUN YOK");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("ALTARIN OGLU TARKAN OLMA KURSU");
		//ADD SOME REVIEW
		tempCourse.addReview(new Review("GUMUS EGERI TARKAN HAK ETTI O YUZDEN LAYKLADIM"));
		tempCourse.addReview(new Review("Her;ey iyi de cunet arkinin yerini tutmaz"));
		tempCourse.addReview(new Review("kesin ip var orda"));
		// SAVE THE COURSE and leverage cascade all
		System.out.println("SSaving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());
		appDAO.save(tempCourse);

		System.out.println("everything is allright");


	}

	private void deleteCourseById(AppDAO appDAO) {
		int theId=10;
		System.out.println("Deleting course id: "+theId);
		appDAO.deleteCourseById(theId);
		System.out.println("kurs silindi");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId=10;
		System.out.println("Finding course id: "+theId);
		Course tempCourse=appDAO.findCourseById(theId);

		//update course
		System.out.println("updating course id: "+ theId);
		tempCourse.setTitle("kurs degisti");
		appDAO.update(tempCourse);
		System.out.println("kurs degistirildi");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor= appDAO.findInstructorById(theId);

		//update instructor
		System.out.println("updating instructor id: "+ theId);
		tempInstructor.setFirstName("ZIYA");
		tempInstructor.setLastName("YILMAZ");
		tempInstructor.setEmail("dostumolmazhasmimyasamaz@gmail.com");
		appDAO.update(tempInstructor);
		System.out.println("instructor updated");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId=1;
		//find the instructor
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associated courses: "+ tempInstructor.getCourses());
		System.out.println("DONEEE");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: "+tempInstructor);
		//find courses for instructor
		System.out.println("Finding courses for Instructor id: "+ theId);

		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// associated objects
		tempInstructor.setCourses(courses);
		System.out.println("the associated courses: "+ tempInstructor.getCourses());
		System.out.println("done courses coming by instructor");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: "+tempInstructor);

		System.out.println("the associated courses: "+ tempInstructor.getCourses());
		System.out.println("done!!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// create instructor

		Instructor tempInstructor = new Instructor("ramiz","karaeski","ezel2024@gmail.com");

		InstructorDetail tempInstrcutorDetail = new InstructorDetail("http://www.DAYI.com","Racon");



		Instructor tempInstructor2 = new Instructor("suleyman","cakir","34tk054@gmail.com");

		InstructorDetail tempInstrcutorDetail2 = new InstructorDetail("http://www.cakir.com","ALTIPATLAR");

		// associate the object
		tempInstructor.setInstructorDetail(tempInstrcutorDetail);

		//create some course
		Course tempCourse1 = new Course("spring boot Java one to many relationship");
		Course tempCourse2 = new Course("DAYIDAN DERSLER");

		// ADD courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor

		System.out.println("saving instructor: "+tempInstructor);
		System.out.println("The Courses: "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("everything is alright /n RELAX NOW DONE!");


	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId=3;
		System.out.println("deleting instructor detail id: "+theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("gorev basarili patron");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		// get the instructor detail object
int theId=2;
InstructorDetail tempInstructorDetail =appDAO.findInstructorDetailById(theId);
		//print the instructor detail
		System.out.println("instructor detail: "+ tempInstructorDetail);

		//print the associated instructor
		System.out.println("associated instructor: "+tempInstructorDetail.getInstructor());
		System.out.println("instructor detailden giderek instructor bulundu.");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("deleting instructor id: "+ theId);

		appDAO.deleteInstructorById(theId);
		System.out.println("done! id: "+theId +" DELETED");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding Instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: "+ tempInstructor);
		System.out.println("the associated instructorDetail only:  " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {

		// create instructor

		Instructor tempInstructor = new Instructor("iso","y1","ismail732yilmaz@gmail.com");

		InstructorDetail tempInstrcutorDetail = new InstructorDetail("http://www.Y1forcode.com","development");



		Instructor tempInstructor2 = new Instructor("suleyman","cakir","34tk054@gmail.com");

		InstructorDetail tempInstrcutorDetail2 = new InstructorDetail("http://www.cakir.com","ALTIPATLAR");

		// associate the object
		tempInstructor.setInstructorDetail(tempInstrcutorDetail);

		//save instructor
		//
		//Note: this will ALso save details object
		//because of CascadeType.ALL
		//
		System.out.println("saving Instructor"+ tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("DONE!");
	}

}
