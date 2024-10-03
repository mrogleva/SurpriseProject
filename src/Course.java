import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Course {
    public final String courseName;
    private List<Student> enrolledStudents;
    private List<Teacher> teachers;

    public Course(String courseName) {
        this.courseName = courseName;
        this.enrolledStudents = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    public void enroll(Student student) {
        /*
         * Add the student to the list of enrolled students
         * 
         * Here we can do some check if the student is already enrolled,
         * or if they satisfy some criteria for enrollment
         */
        if (enrolledStudents.contains(student)) {
            return;
        }
        enrolledStudents.add(student);
        student.enroll(this);
    }

    public void removeStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            return;
        }
        enrolledStudents.remove(student);
        student.drop(this);
    }

    public void addTeacher(Teacher teacher) {
        /*
         * todo: checks, ...
         */
        teachers.add(teacher);
        teacher.addCourse(this);
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(enrolledStudents);
    }

    public List<Teacher> getTeachers() {
        return Collections.unmodifiableList(teachers);
    }
}