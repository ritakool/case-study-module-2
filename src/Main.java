import controller.ManagerStaff;
import model.Address;
import model.FullTimeStaff;
import model.PartTimeStaff;
import model.Staff;

import java.time.LocalDate;
import java.util.Scanner;

import view.Regex;

public class Main {
    public static void main(String[] args) {
        ManagerStaff managerStaff = new ManagerStaff();
        Regex regex = new Regex();

//        Staff staff1 = new FullTimeStaff("#1","Nguyễn Văn Tèo", LocalDate.parse("1994-07-09"),"(+84)-(123456789)",new Address("Hà Nội","Ba Đình","34","425"));
//        Staff staff2 = new PartTimeStaff("#2","Mộng Hà", LocalDate.parse("2005-07-09"),"(+84)-(123456789)",new Address("Hà Nội","Ba Đình","34","425"));
//        Staff staff3 = new FullTimeStaff("#3","Nguyễn Văn Hà", LocalDate.parse("2000-07-31"),"(+84)-(123456789)",new Address("Hà Nội","Ba Đình","34","425"));
//        Staff staff4 = new FullTimeStaff("#5","Nguyễn Văn Hà", LocalDate.parse("2000-08-31"),"(+84)-(123456709)",new Address("Hà Nội","Ba Đình","34","425"));
//        managerStaff.addStaff(staff4);
//        managerStaff.addStaff(staff1);
//        managerStaff.addStaff(staff3);
//        managerStaff.addStaff(staff2);
//        managerStaff.displayStaff();
//        managerStaff.displayNameStaff("Hà");
//        managerStaff.addStaff(staff1);
//        managerStaff.updateStaff("#1");
//        System.out.println("sắp xếp");
//        managerStaff.arrangement();
//        managerStaff.removeStaff("#2");
//        Employee employee = new Employee("hà",19,1994,"mama");
//        System.out.println(employee.toString(true));
//        System.out.println(employee);

        Scanner scanner = new Scanner(System.in);
        FullTimeStaff fullTimeStaff = new FullTimeStaff();
        // Nhập thông tin của nhân viên
        System.out.print("Nhập tên nhân viên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập số ngày đi muộn: ");
        int lateDays = scanner.nextInt();

        // Tính tiền thưởng/phạt
        int totalRewardPunishment = 0;
        if (lateDays > 0) {
            int amount = FullTimeStaff.RewardPunishment.getAmountByName("LATE_ATTENDANCE");
            totalRewardPunishment -= lateDays * amount;
            System.out.println("Phạt " + (-totalRewardPunishment) + " đồng vì đi muộn " + lateDays + " ngày");
        } else {
            System.out.println("Không bị phạt vì không đi muộn");
        }

        // In tổng tiền thưởng/phạt của nhân viên
        System.out.println("Tổng tiền thưởng/phạt của nhân viên " + name + " là " + totalRewardPunishment + " đồng");
    }
}