package kr.ac.hansung.cse;

import kr.ac.hansung.cse.dao.CourseDao;
import kr.ac.hansung.cse.dao.InstructorDao;
import kr.ac.hansung.cse.dao.StudentDao;
import kr.ac.hansung.cse.entity.Course;
import kr.ac.hansung.cse.entity.Instructor;
import kr.ac.hansung.cse.entity.InstructorDetail;
import kr.ac.hansung.cse.entity.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

// One-to-Many Uni-directional
//public class Main {
//    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context =
//                new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        InstructorDao instructorDao = context.getBean(InstructorDao.class);
//        CourseDao courseDao = context.getBean(CourseDao.class);
//
//        Instructor instructor1 = new Instructor("Namyun Kim", "nykim@hansung.ac.kr");
//        Course course1 = new Course("웹프레임워크");
//        Course course2 = new Course("오픈소스소프트웨어");
//
//        Instructor instructor2 = new Instructor("Jaemon Lee", "jmlee@hansung.ac.kr");
//        Course course3 = new Course("iOS 프로그래밍");
//        Course course4 = new Course("안드로이드 프로그래밍");
//
//        instructorDao.save(instructor1);
//        instructorDao.save(instructor2);
//
//        // Instructor 객체를 먼저 저장한 후, Course 객체를 저장해야 합니다.
//        // Course 객체 내부에 Instructor 참조가 있기 때문에, Instructor가 먼저 영속화되어야 합니다.
//        course1.setInstructor(instructor1);
//        course2.setInstructor(instructor1);
//        course3.setInstructor(instructor2);
//        course4.setInstructor(instructor2);
//
//        courseDao.save(course1);
//        courseDao.save(course2);
//        courseDao.save(course3);
//        courseDao.save(course4);
//
//        // 저장된 데이터 조회
//        List<Instructor> instructors = instructorDao.findAll();
//        List<Course> courses = courseDao.findAll();
//
//        System.out.println("Instructors:");
//        for (Instructor Instructor : instructors) {
//            System.out.println(Instructor.getFullName());
//        }
//
//        System.out.println("\nCourses:");
//        for (Course course : courses) {
//            System.out.println( "Instructor: " + course.getInstructor().getFullName() +
//                    ", Course: " + course.getTitle());
//        }
//    }
//}

// One-to-Many bi-directional

//public class Main {
//    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context =
//                new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        InstructorDao instructorDao = context.getBean(InstructorDao.class);
//
//        Instructor instructor1 = new Instructor("Namyun Kim", "nykim@hansung.ac.kr");
//        Course course1 = new Course("웹프레임워크");
//        Course course2 = new Course("오픈소스소프트웨어");
//
//        instructor1.addCourse(course1);
//        instructor1.addCourse(course2);
//
//        instructorDao.save(instructor1);
//
//        // 저장된 Instructor 조회 및 결과 확인
//        //Instructor retrievedInstructor = instructorDao.findById(instructor1.getId());
//        Instructor retrievedInstructor = instructorDao.findByIdWithCourses(instructor1.getId());
//        boolean isManaged = instructorDao.isManaged(retrievedInstructor);
//        System.out.println("Is entity managed? " + isManaged);
//
//        System.out.println("Instructor: " + retrievedInstructor.getFullName());
//        for (Course Course : retrievedInstructor.getCourses()) {
//            System.out.println("Course: " + Course.getTitle());
//        }
//    }
//}

//One-to-One uni-directional
//public class Main {
//    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context =
//                new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        InstructorDao instructorDao = context.getBean(InstructorDao.class);
//
//        // InstructorDetail 객체 생성
//        InstructorDetail detail =
//                new InstructorDetail("youtube.com/@HansungUvi", "교육");
//
//        // Instructor 객체 생성 및 InstructorDetail 설정
//        Instructor instructor = new Instructor("Namyun Kim", "nykim@hansung.ac.kr");
//        instructor.setInstructorDetail(detail);
//
//        instructorDao.save(instructor);
//
//        // 저장된 객체 조회
//        Instructor storedInstructor = instructorDao.findById( instructor.getId());
//        System.out.println("Retrieved Instructor: " + storedInstructor.getFullName());
//
//        // 연결된 InstructorDetail 정보 출력
//        InstructorDetail storedDetail = storedInstructor.getInstructorDetail();
//        System.out.println("Instructor Detail: ");
//        System.out.println("YouTube Channel: " + storedDetail.getYoutubeChannel());
//        System.out.println("Hobby: " + storedDetail.getHobby());
//
//    }
//}

//Many-to-Many Unidirectional
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        InstructorDao instructorDao = context.getBean(InstructorDao.class);
        CourseDao courseDao = context.getBean(CourseDao.class);
        StudentDao studentDao = context.getBean(StudentDao.class);

        // create a course
        Course course1 = new Course("웹프레임워크");
        Course course2 = new Course("오픈소스소프트웨어");
        Course course3 = new Course("정보보안");
        Course course4 = new Course("웹서버프로그래밍");
        Course course5 = new Course("클라우드컴퓨팅");

//        courseDao.save(course1);
//        courseDao.save(course2);
//        courseDao.save(course3);
//        courseDao.save(course4);
//        courseDao.save(course5);

//        Arrays.asList(course1, course2, course3, course4, course5).forEach(
//                course -> courseDao.save(course));
        Arrays.asList(course1, course2, course3, course4, course5).forEach(
                courseDao::save);


        // create the students
        Student student1 = new Student("Alice", "alice@hansung.ac.kr");
        Student student2 = new Student("bob", "bob@hansung.ac.kr");
        Student student3 = new Student("charlie", "charlie@hansung.ac.kr");

        student1.setCourses(Arrays.asList(course1, course2));
        student2.setCourses(Arrays.asList(course2, course3, course4));
        student3.setCourses(Arrays.asList(course3, course4, course5));

        Arrays.asList(student1, student2, student3).forEach(
                studentDao::save);
//        Arrays.asList(student1, student2, student3).forEach(
//                student -> studentDao.save(student));
//        studentDao.save(student1);
//        studentDao.save(student2);
//        studentDao.save(student3);

        // 저장된 학생 및 코스 정보 조회 및 출력
        Student storedStudent = studentDao.findByIdWithCourses(student1.getId());
        System.out.println("Retrieved Student: " + storedStudent.getFullName());
        storedStudent.getCourses().forEach(
                course -> System.out.println("Enrolled in Course: " + course.getTitle())
        );
    }
}