public class Employee {
    private String name;
    private int age;
    private int yearOfBirth;
    private String employeeType;

    public Employee(String name, int age, int yearOfBirth, String employeeType) {
        this.name = name;
        this.age = age;
        this.yearOfBirth = yearOfBirth;
        this.employeeType = employeeType;
    }

    @Override
    public String toString() {
        return toString(false);
    }

    public String toString(boolean detailed) {
        if (detailed) {
            return String.format("Name: %s, Age: %d, Year of Birth: %d, Employee Type: %s",
                    name, age, yearOfBirth, employeeType);
        } else {
            return String.format("Name: %s, Age: %d, Year of Birth: %d", name, age, yearOfBirth);
        }
    }
}
