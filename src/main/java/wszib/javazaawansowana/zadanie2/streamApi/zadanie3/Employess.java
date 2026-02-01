package wszib.javazaawansowana.zadanie2.streamApi.zadanie3;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Employess {
    static void main() {

        class Employee {
            private String name;
            private String department;
            private double salary;

            // konstruktor, gettery, settery
            public Employee(String name, String department, double salary) {
                this.name = name;
                this.department = department;
                this.salary = salary;
            }

            public String getName() {
                return name;
            }

            public String getDepartment() {
                return department;
            }

            public double getSalary() {
                return salary;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setDepartment(String department) {
                this.department = department;
            }

            public void setSalary(double salary) {
                this.salary = salary;
            }
        }

        List<Employee> employees = Arrays.asList(
                new Employee("Anna Kowalska", "IT", 8000.00),
                new Employee("Jan Nowak", "IT", 7500.00),
                new Employee("Maria Wiśniewska", "HR", 4500.00),
                new Employee("Piotr Zieliński", "IT", 9000.00),
                new Employee("Katarzyna Lewandowska", "HR", 4800.00),
                new Employee("Tomasz Kamiński", "Sprzedaż", 5500.00),
                new Employee("Agnieszka Wójcik", "Sprzedaż", 6000.00),
                new Employee("Michał Kowalczyk", "IT", 8500.00),
                new Employee("Ewa Szymańska", "HR", 5000.00),
                new Employee("Paweł Dąbrowski", "Sprzedaż", 5800.00),
                new Employee("Magdalena Król", "Marketing", 6500.00),
                new Employee("Krzysztof Piotrowski", "Marketing", 7000.00),
                new Employee("Joanna Grabowska", "HR", 4200.00),
                new Employee("Adam Pawlak", "Sprzedaż", 6200.00)
        );
        Map<String, Double> results = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                        ))
                .entrySet().stream()
                .filter(e -> e.getValue() > 5000.0)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> BigDecimal.valueOf(e.getValue())
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue()
                ));

        System.out.println(results);
    }
}
