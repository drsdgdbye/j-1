import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        for (int i = 0; i < 5; i++) {
            employees[i] = new Employee("Vasya" + i,
                    "manager" + i,
                    "vasya" + i + "@" + "mail.ru",
                    "+0123456789" + i,
                    Integer.parseInt("1" + new Random().nextInt(10 * i + 1) + "000"),
                    20 * i);
        }

        Arrays.stream(employees)
                .filter(employee -> employee.getAge() > 40)
                .forEach(Employee::printInfo);
    }
}
