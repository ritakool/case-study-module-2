import controller.ManagerStaff;
import model.Address;
import model.FullTimeStaff;
import model.PartTimeStaff;
import model.Staff;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import view.Regex;

public class Main {
    public static void main(String[] args) {
        ManagerStaff managerStaff = new ManagerStaff();
        Regex regex = new Regex();
        Scanner scanner = new Scanner(System.in);
//        Staff staff1 = new FullTimeStaff("#1","Nguyễn Văn Tèo", LocalDate.parse("1994-07-09"),"(+84)-(123456789)",new Address("Hà Nội","Ba Đình","34","425"));
        Staff staff2 = new PartTimeStaff("#2","Mộng Hà", LocalDate.parse("2005-07-09"),"(+84)-(123456789)",new Address("Hà Nội","Ba Đình","34","425"),1.2);
//        Staff staff3 = new FullTimeStaff("#3","Nguyễn Văn Hà", LocalDate.parse("2000-07-31"),"(+84)-(123456789)",new Address("Hà Nội","Ba Đình","34","425"));
        FullTimeStaff.reward[] rewards = new FullTimeStaff.reward[] {FullTimeStaff.reward.CONTRIBUTE_IN_TEAM, FullTimeStaff.reward.CREATIVE_IDEAS};
        FullTimeStaff.mistake[] mistakes = new FullTimeStaff.mistake[] {FullTimeStaff.mistake.ABSENCE, FullTimeStaff.mistake.FORGOT_REPORT};
        int[] countError = {2,3};
        Staff staff4 = new FullTimeStaff("#5","Nguyễn Văn Hà", LocalDate.parse("2000-08-31"),"(+84)-(123456709)",new Address("Hà Nội","Ba Đình","34","425"),rewards, mistakes,countError);
        managerStaff.addStaff(staff2);
//        managerStaff.addStaff(staff1);
//        managerStaff.createNewStaff();
//        managerStaff.addStaff(staff3);
        managerStaff.addStaff(staff4);
        managerStaff.displayStaff();
        managerStaff.updateStaff("#2");
//        System.out.println("Nhập thông tin nhân viên:");
//        System.out.print("Mã nhân viên: ");
//        String id = scanner.nextLine();
//        System.out.print("Họ và tên: ");
//        String name = scanner.nextLine();
//        System.out.print("Ngày sinh (yyyy-MM-dd): ");
//        LocalDate birthDay = LocalDate.parse(scanner.nextLine());
//        System.out.print("Số điện thoại: ");
//        String tel = scanner.nextLine();
//        System.out.println("Địa chỉ:");
//        System.out.print("Số nhà: ");
//        String streetNumber = scanner.nextLine();
//        System.out.print("Tên đường: ");
//        String streetName = scanner.nextLine();
//        System.out.print("Quận/huyện: ");
//        String district = scanner.nextLine();
//        System.out.print("Tỉnh/thành phố: ");
//        String city = scanner.nextLine();
//        Address address = new Address(streetNumber, streetName, district, city);


//        System.out.println("Nhập thông tin lỗi và số lần vi phạm:");
//        FullTimeStaff.mistake[] mistakes = FullTimeStaff.mistake.values();
//        int[] errorCount = new int[mistakes.length];
//        for (int i = 0; i < mistakes.length; i++) {
//            System.out.print("Số lần vi phạm " + mistakes[i].getName() + ": ");
//            errorCount[i] = scanner.nextInt();
//            scanner.nextLine();
//        }

//        FullTimeStaff staff = new FullTimeStaff(id, name, birthDay, tel, address, mistakes, errorCount);
//        System.out.println("Thông tin nhân viên:");
//        System.out.println(staff);

    }
}