import java.util.List;
import java.util.ArrayList;
import Exceptions.EnrollmentException;

public class Course {
    private String courseName;
    private List<Student> enrolledStudents;
    private List<Teacher> teachers;

    public Course(String courseName) {
        this.courseName = courseName;
        this.enrolledStudents = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    public boolean enroll(Student student) throws EnrollmentException {
        /* Add the student to the list of enrolled students
         * 
         * Here we can do some check if the student is already enrolled, 
         * or if they satisfy some criteria for enrollment
         */
        if (enrolledStudents.contains(student)) {
            throw new EnrollmentException("Student is already enrolled.");
        }
        enrolledStudents.add(student);
        student.enroll(this);
        return true;
    }

    public void addTeacher(Teacher teacher) {
        /*
         * todo: checks, ... 
         */
        teachers.add(teacher);
    }

    public List<Student> getStudents() {
        return enrolledStudents;
    }
}