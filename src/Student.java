import java.util.ArrayList;
import java.util.List;

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
        enrolledCourses.add(course);
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
}
