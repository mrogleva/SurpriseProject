import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Student extends Person {
    private String facultyNumber;
    private List<Course> enrolledCourses;

    public Student(String name) {
        super(name);
        this.enrolledCourses = new ArrayList<>();
    }

    // The same person can have different FNs over time (bachelor, master, PhD)
    // FN is issued from the university and does not come with the person
    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public void enroll(Course course) {
        if (enrolledCourses.contains(course)) {
            return;
        }
        enrolledCourses.add(course);
        course.enroll(this);
    }

    public void drop(Course course) {
        if (!enrolledCourses.contains(course)) {
            return;
        }
        enrolledCourses.remove(course);
        course.removeStudent(this);
    }

    public List<Course> getEnrolledCourses() {
        return Collections.unmodifiableList(enrolledCourses);
    }
}
