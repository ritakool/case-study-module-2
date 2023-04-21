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
        Staff staff4 = new FullTimeStaff("#5","Nguyễn Văn Hà", LocalDate.parse("2000-08-31"),"(+84)-(123456709)",new Address("Hà Nội","Ba Đình","34","425"));
        managerStaff.addStaff(staff4);
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

    }
}