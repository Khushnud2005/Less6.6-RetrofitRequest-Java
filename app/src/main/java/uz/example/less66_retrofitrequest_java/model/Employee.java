package uz.example.less66_retrofitrequest_java.model;

public class Employee {
    private int id;
    private String employee_name;
    private int employee_salary;
    private int employee_age;

    public Employee(String name, int salary, int age) {
        this.employee_name = name;
        this.employee_salary = salary;
        this.employee_age = age;
    }

    public Employee(int id, String name, int salary, int age) {
        this.id = id;
        this.employee_name = name;
        this.employee_salary = salary;
        this.employee_age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return employee_name;
    }

    public int getSalary() {
        return employee_salary;
    }

    public int getAge() {
        return employee_age;
    }
}