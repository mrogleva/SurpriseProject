import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("*** Welcome to susi ver. 0.1! ***");
        System.out.println("");
        System.out.println("*** We can have students: ");
        List<Student> suStudents = List.of(
            new Student("Maria"),
            new Student("Sasho"),
            new Student("Georgi")
            );
        for (Student student : suStudents) {
            System.out.print(student.name + " ");
        }
        System.out.println(suStudents);
            
        System.out.println("");
        System.out.println("*** We can have courses: ");
        List<Course> suCourses = List.of(
            new Course("Java Basics"),
            new Course("Java OOP"),
            new Course("Java Advanced")
            );
        for (Course course : suCourses) {
            System.out.print(course.courseName + " ");
        }
        System.out.println(suCourses);

        System.out.println("");
        System.out.println("*** Students can enroll in courses: ");
        suStudents.get(0).enroll(suCourses.get(0));
        suStudents.get(0).enroll(suCourses.get(1));
        suStudents.get(1).enroll(suCourses.get(1));
        System.out.println(suStudents.get(0).name +" enrolled in "+ suStudents.get(0).getEnrolledCourses());
        System.out.println(suStudents.get(1).name +" enrolled in "+ suStudents.get(1).getEnrolledCourses());
    
        System.out.println("");
        System.out.println("*** To be continued bye for now: ");

    }
}

