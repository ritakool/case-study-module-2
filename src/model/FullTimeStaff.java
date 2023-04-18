package model;

import java.time.LocalDate;

public class FullTimeStaff extends Staff{
    private final double BASIC_SALARY = 5000;
    private double bonus;
    private double fine;
    public FullTimeStaff() {
    }
    public FullTimeStaff(String id, String name, LocalDate birthDate, int tel, Address address) {
        super(id, name, birthDate, tel, address);
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    @Override
    public double payroll() {
        return BASIC_SALARY + bonus - fine;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Nhân viên chính thức: " + '\n' +
                "Lương cơ bản = " + BASIC_SALARY + '\n' +
                "Thưởng: " + bonus +'\n' +
                "Phạt: " + fine + '\n' +
                "Tổng lương: " + payroll();
    }
}
