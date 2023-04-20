import controller.ManagerStaff;
import model.Address;
import model.FullTimeStaff;
import model.PartTimeStaff;
import model.Staff;

import java.time.LocalDate;
import java.util.Scanner;
import view.DataEntryKeyboard;

public class Main {
    public static void main(String[] args) {
        ManagerStaff managerStaff = new ManagerStaff();
        DataEntryKeyboard dataEntryKeyboard = new DataEntryKeyboard();

        Staff staff1 = new FullTimeStaff("#1","Nguyễn Văn Tèo", LocalDate.parse("1994-07-09"),7777777,new Address("Hà Nội","Ba Đình","34","425"));
        Staff staff2 = new PartTimeStaff("#2","Đặng Hà", LocalDate.parse("2005-07-09"),7777777,new Address("Hà Nội","Ba Đình","34","425"));
        Staff staff3 = new FullTimeStaff("#3","Nguyễn Văn Hà", LocalDate.parse("2000-07-31"),7777777,new Address("Hà Nội","Ba Đình","34","425"));
        managerStaff.addStaff(staff1);
        managerStaff.addStaff(staff3);
        managerStaff.addStaff(staff2);
        managerStaff.displayStaff();
        managerStaff.displayNameStaff("Nga");
        managerStaff.updateStaff("#3");
//        Scanner scanner = new Scanner(System.in);
//        String name = scanner.nextLine();
//        System.out.println(dataEntryKeyboard.checkName(name));
//        String id = scanner.nextLine();
//        System.out.println(dataEntryKeyboard.checkID(id));
//        String phone = scanner.nextLine();
//        System.out.println(dataEntryKeyboard.checkPhoneNumber(phone));
//        System.out.println(dataEntryKeyboard.checkDate(phone));


    }
}