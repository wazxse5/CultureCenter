package server;

public class Employee extends Client {
    private String department;
    private float salary;

    public Employee(int id, String name, String surname, String login, String department, float salary) {
        super(id, name, surname, "mail", login);
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public float getSalary() {
        return salary;
    }
}
