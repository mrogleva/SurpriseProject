import java.util.List;
import java.util.ArrayList;

public class Teacher extends Person {

    private List<Course> teachedCourses;

    public Teacher(String name) {
        super(name);
        this.teachedCourses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        teachedCourses.add(course);
    }

    public void removeCourse(Course course) {
        teachedCourses.remove(course);
    }

    public List<Course> getTeachedCourses() {
        return teachedCourses;
    }

}
