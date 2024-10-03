public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Student student = new Student("Pesho");
        System.out.println(student.name);
        Teacher teacher = new Teacher("Prof. Ivanov");
        System.out.println(teacher.name);
        Course javaCourse = new Course("Java Basics");
        
    }
}

