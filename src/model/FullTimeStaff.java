package model;

import java.time.LocalDate;

public class FullTimeStaff extends Staff{
    private final double BASIC_SALARY = 7000;
    private String nameMistake;
    private enum mistake {
        FORGOT_REPORT("Quên làm báo cáo tuần", 150),
        ABSENCE("Vắng mặt không lý do", 100),
        LATE_ATTENDANCE("Đi muộn",50);
        private final String name;
        private final double amount;
        mistake(String name, double amount) {
            this.name = name;
            this.amount = amount;
        }
        public String getName() {
            return name;
        }
        public double getAmountMistake() {
            return amount;
        }
    }

    public FullTimeStaff() {
    }

    public FullTimeStaff(String id, String name, LocalDate birthDay, String tel, Address address) {
        super(id, name, birthDay, tel, address);
    }
    private int[] errorCount = new int[mistake.values().length];
    public void addMistake(mistake mistake) {
        errorCount[mistake.ordinal()]++;
    }
    public double penalty() {
        double penalty = 0;
        for (int i = 0; i < errorCount.length; i++ ) {
            double amount = mistake.values()[i].getAmountMistake();
            penalty = amount * errorCount[i];
        }
        return penalty;
    }
    public String getMistakes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Danh sách lỗi: \n");
        for (int i = 0; i < errorCount.length; i++ ) {
            if (errorCount[i] > 0) {
                sb.append(mistake.values()[i].getName())
                        .append(": ")
                        .append(errorCount[i])
                        .append("\n");
            }
        }
        return sb.toString();
    }


    @Override
    public double payroll() {
        return BASIC_SALARY  - penalty();
    }
    @Override
    public String toString() {
        return  super.toString() +
                "Nhân viên chính thức: " + '\n' +
                "Lương cơ bản = " + BASIC_SALARY + '\t' +
                "Thưởng: " + '\t' +
                "Phạt: " + penalty() + '\t' +
                "Tổng lương: " + payroll() + "\t" + getMistakes() + '\n' +
                "---------------";
    }
}
