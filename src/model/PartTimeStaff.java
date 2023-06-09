package model;

import java.io.Serializable;
import java.time.LocalDate;

public class PartTimeStaff extends Staff implements Serializable {
    private final double HOURLY_WAGE = 100;
    private double timeWork;
    public PartTimeStaff() {
    }
    public PartTimeStaff(String id, String name, LocalDate birthDay, String tel, Address address) {
        super(id, name, birthDay, tel, address);
    }

    public PartTimeStaff(String id, String name, LocalDate birthDay, String tel, Address address, double timeWork) {
        super(id, name, birthDay, tel, address);
        this.timeWork = timeWork;
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
        return super.toString()+
                "Nhân viên bán thời gian:" + '\n' +
                "Thời gian làm việc: " + timeWork + '\t' +
                "Lương: " + payroll() + '\n' +
                "----------";
    }
}
