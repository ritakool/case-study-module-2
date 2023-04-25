package model;

import java.lang.ref.SoftReference;
import java.time.LocalDate;

public class FullTimeStaff extends Staff{
    private final double BASIC_SALARY = 7000;
    private mistake[] mistakes;
    private reward[] rewards;

    public reward[] getRewards() {
        return rewards;
    }

    public void setRewards(reward[] rewards) {
        this.rewards = rewards;
    }

    public int[] getCountReward() {
        return countReward;
    }

    public void setCountReward(int[] countReward) {
        this.countReward = countReward;
    }

    private int[] errorCount;
    private int[] countReward;


    public FullTimeStaff() {
    }

    public FullTimeStaff(String id, String name, LocalDate birthDay, String tel, Address address) {
        super(id, name, birthDay, tel, address);
    }

    public FullTimeStaff(String id, String name, LocalDate birthDay, String tel, Address address, reward[] rewards ,int[] countReward, mistake[] mistakes, int[] errorCount) {
        super(id, name, birthDay, tel, address);
        this.rewards = rewards;
        this.mistakes = mistakes;
        this.errorCount = errorCount;
        this.countReward = countReward;
    }

    public void setMistakes(mistake[] mistakes) {
        this.mistakes = mistakes;
    }

    public int[] getErrorCount() {
        return errorCount;
    }


    public void setErrorCount(int[] errorCount) {
        this.errorCount = errorCount;
    }

    private double penalty() {
        double penalty = 0;
        for (int i = 0; i < mistakes.length; i++ ) {
            double amount = mistakes[i].getAmountMistake();
            penalty += amount * errorCount[i];
        }
        return penalty;
    }
    private double bonus() {
        double bonus = 0;
        for (int i = 0; i < rewards.length; i++) {
            double amount = rewards[i].getAmountReward();
            bonus += amount * BASIC_SALARY * countReward[i];
        }
        return bonus;
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
    public enum mistake {
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
        private double getAmountMistake() {
            return amount;
        }
    }
    public enum reward {
        COMPLETE_ON_SCHEDULE("Hoàn thành tốt nhiệm vụ được giao",0.1),
        CONTRIBUTE_IN_TEAM("Đóng góp tích cực trong nhóm",0.05),
        CREATIVE_IDEAS("Có ý tưởng sáng tạo",0.03);
        private final double amount;
        private final String name;
        reward (String name, double amount) {
            this.name = name;
            this.amount = amount;
        }
        public String getNameReward() {
            return name;
        }
        private double getAmountReward() {
            return amount;
        }
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
                "Thưởng: " + bonus() + '\t' +
                "Phạt: " + penalty() + '\t' +
                "Tổng lương: " + payroll() + "\t" + getMistakes() + '\n' +
                "---------------";
    }
}
