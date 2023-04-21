package model;

import java.time.LocalDate;

public class FullTimeStaff extends Staff{
    private final double BASIC_SALARY = 7000;
    private int numberOfMistake;
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
        public static double getAmountByName(String name) {
            for (mistake rp : mistake.values()) {
                if (rp.name.equals(name)) {
                    return rp.amount;
                }
            }
            return 0;
        }
    }
    private enum reward {
        GOOD_QUALITY("Hoàn thành tốt nhiệm vụ được giao", 0.1),
        CONTRIBUTE_TO_TEAM("Đóng góp tích cực trong nhóm", 0.05),
        SUGGEST_SOLUTION("Ý tưởng mới", 0.03);
    }
    private int[] errorCount = new int[mistake.values().length];

    public FullTimeStaff() {
    }
    public FullTimeStaff(String id, String name, LocalDate birthDay, String tel, Address address) {
        super(id, name, birthDay, tel, address);
    }

    public double bonus() {
        double bonus = 0;
        return bonus;
    }
    pu
    public double penalty() {
        double penalty;
        if (numberOfMistake > 0) {
            double amount = mistake.getAmountByName(nameMistake);
            penalty = amount * numberOfMistake;
        } else {
            penalty = 0;
        }
        return penalty;
    }

    public int getNumberOfMistake() {
        return numberOfMistake;
    }

    public void setNumberOfMistake(int numberOfMistake) {
        this.numberOfMistake = numberOfMistake;
    }

    public String getNameMistake() {
        return nameMistake;
    }

    public void setNameMistake(String nameMistake) {
        this.nameMistake = nameMistake;
    }

    @Override
    public double payroll() {
        return BASIC_SALARY + bonus() - penalty();
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Nhân viên chính thức: " + '\n' +
                "Lương cơ bản = " + BASIC_SALARY + '\t' +
                "Thưởng: " + bonus() +'\t' +
                "Phạt: " + penalty() + '\t' +
                "Tổng lương: " + payroll() + "\n" +
                "---------------";
    }
}
