import java.math.BigDecimal;

public class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Employee(String fullName, String position, String email, String phone, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void printInfo() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "fullName: " + fullName + "\n" +
                "position: " + position + "\n" +
                "email: " + email + "\n" +
                "phone: " + phone + "\n" +
                "salary: " + salary + "\n" +
                "age: " + age + "\n";
    }
}
