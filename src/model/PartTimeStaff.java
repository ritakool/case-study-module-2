package model;

import java.time.LocalDate;

public class PartTimeStaff extends Staff{
    private final double HOURLY_WAGE = 100;
    private double timeWork;
    public PartTimeStaff() {
    }
    public PartTimeStaff(String id, String name, LocalDate birthDate, int tel, Address address) {
        super(id, name, birthDate, tel, address);
    }

    public double getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(double timeWork) {
        this.timeWork = timeWork;
    }

    @Override
    public double payroll() {
        return timeWork * HOURLY_WAGE;
    }

    @Override
    public String toString() {
        return super.toString()+ '\n' +
                "Nhân viên bán thời gian" + '\n' +
                "Thời gian làm việc: " + timeWork + '\n' +
                "Lương: " + payroll();
    }
}
