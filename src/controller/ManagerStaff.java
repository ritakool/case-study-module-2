package controller;

import model.Address;
import model.Staff;
import view.DataEntryKeyboard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class ManagerStaff {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ManagerStaff.class));
   List<Staff> staffs = new ArrayList<>();
   Scanner scanner = new Scanner(System.in);
   DataEntryKeyboard input = new DataEntryKeyboard();

    public ManagerStaff() {
    }
    public void displayStaff() {
        LOGGER.info("Danh sách nhân viên là: ");
        staffs.forEach(System.out::println);
    }

    public void displayNameStaff(String name) {
        boolean checkName = false;
        int i = 0;
        while (i < staffs.size()) {
            String[] arrName = staffs.get(i).getName().split("\\s");
            if (arrName[arrName.length - 1].equalsIgnoreCase(name)) {
                checkName = true;
                System.out.println(staffs.get(i));
            }
            i++;
        }
        if (!checkName) {
            LOGGER.warning("không tồn tại tên này.");
        }
    }
    public void updateStaff(String id) {
        if (!checkStaffId(id)) {
            LOGGER.info("Không tồn tại nhân viên này trong hệ thống.");
        } else {
            LOGGER.info("Bạn có chắc muốn sửa đổi thông tin (Y/N)?");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                String choice;
                do {

                    System.out.println("""
                                        Bạn muốn sửa thông tin nào: 
                                        1. Tên
                                        2. Ngày tháng năm sinh
                                        4. Số điện thoại
                                        3. Địa chỉ
                                        0. Không thay đổi nữa                            
                                                                    """);
                    choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            System.out.println("Sửa tên: ");
                            String name = scanner.nextLine();
                            while (!input.checkName(name)) {
                            LOGGER.info("Nhập đúng định dạng tên...");
                            name = scanner.nextLine();
                            }
                            staffs.stream().filter(staff -> staff.getId().equals(id)).findFirst().ifPresent(staff -> staff.setName(name));
                            LOGGER.info("Đã cập nhập tên thành công.");
                            break;
                        case "2":
                            System.out.println("Sửa ngày tháng năm sinh: ");
                            LOGGER.warning("Nhập đúng định dạng (yyyy-mm-dd)");
                            LocalDate birthDay = LocalDate.parse(scanner.nextLine());
                            staffs.stream().filter(staff -> staff.getId().equals(id)).findFirst().ifPresent(staff -> staff.setBirthDay(birthDay));
                            LOGGER.info("Cập nhật thành công ngày sinh.");
                            break;
                        case "3":
                            String conscious;
                            String district;
                            String commune;
                            String apartmentNumber;
//                            staffs.stream().filter(staff -> staff.getId().equals(id)).findFirst().ifPresent(staff -> staff.setAddress(new Address()));
                            break;
                        case "0":
                            LOGGER.info("Không thay đổi nữa...");
                            break;
                    }


                }while (!choice.equals("0"));
            } else {
                LOGGER.info("Không thay đổi gì cả.");
            }
        }
    }

    public void addStaff(Staff staff) {

        if (!checkStaffId(staff.getId())) {
            staffs.add(staff);
            LOGGER.info("Đã thêm nhân viên thành công.");
        } else {
            LOGGER.info("Đã có nhân viên này, bạn có muốn cập nhật nhân viên này không. (Y/N) ?");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                LOGGER.info("Đã cập nhập thành công");
                //TODO: chưa hoàn thành.
            } else {
                LOGGER.info("không cập nhập.");
            }
        }
    }
    public boolean checkStaffId(String id) {
        boolean checkId = staffs.stream().anyMatch(staff -> staff.getId().equals(id));
        return checkId;
    }
//    public void
}
