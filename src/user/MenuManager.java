package user;

import controller.ManagerStaff;

import java.util.Scanner;

public class MenuManager extends Thread {
    static ManagerStaff managerStaff = new ManagerStaff();

    @Override
    public void run() {
        super.run();
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("""
                                |||---------------------------------------------------------|||        
                                 |        Chào mừng bạn đến với quản lí nhân viên            |
                                |||---------------------------------------------------------||| 
                                 ||       1. Thêm Nhân viên.                                ||
                                 ||       2. Tìm Nhân Viên.                                 ||
                                 ||       3. Sửa đổi thông tin nhân viên.                   ||
                                 ||       4. Xóa nhân viên.                                 ||
                                 ||       5. Sắp xếp nhân viên theo tên.                    ||
                                 ||       6. Hiển thị danh sách nhân viên.                  ||
                                 ||       7. Thống kê theo lương nhân viên.                 ||                   
                                 ||       0. Thoát.                                         ||
                                 ------------------------------------------------------------  
                                 ||             Mời bạn nhập lựa chọn 0 -> 7                ||
                                 ------------------------------------------------------------        
                        """);
                byte choice;
                choice = sc.nextByte();
                switch (choice) {
                    case 1:
                        System.out.println("Thêm nhân viên: ");
                        add();
                        break;
                    case 2:
                        System.out.println("Tìm kiếm nhân viên: ");
                        find();
                        break;
                    case 3:
                        System.out.println("Sửa đổi thông tin nhân viên: ");
                        update();
                        break;
                    case 4:
                        System.out.println("Xóa nhân viên: ");
                        remove();
                        break;
                    case 5:
                        System.out.println("Sắp xếp nhân viên");
                        sort();
                        break;
                    case 6:
                        System.out.println("Hiển thị dánh sách nhân viên: ");
                        show();
                        break;
                    case 7:
                        System.out.println("Thống kế theo lương nhân viên");
                        payRoll();
                        break;
                    case 0:
                        System.out.println("Thoát chương trình.");
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ vui lòng chọn lại.");
                }
            } catch (Exception e) {
                System.out.println("lựa chọn không hợp lệ vui lòng chọn lại.");
                sc.nextLine();
            }


        }

    }

    public static void add() {
        managerStaff.addStaff(managerStaff.newStaff());
    }

    public static void find() {
        System.out.println("Nhập tên nhân viên cần tìm kiếm: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        managerStaff.displayNameStaff(name);
    }

    public static void update() {
        System.out.println("Nhập ID của nhân viên: ");
        String id = managerStaff.inputId();
        managerStaff.updateStaff(id);
    }

    public static void remove() {
        System.out.println("Nhập ID nhân viên bạn muốn xóa");
        String id = managerStaff.inputId();
        managerStaff.removeStaff(id);
    }

    public static void sort() {
        managerStaff.arrangement();
    }

    public static void show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("""
                                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^    
                                ^    1. Hiển thị toàn bộ nhân viên.         ^ 
                                ^    2. Hiển thị nhân viên chính thức.      ^ 
                                ^    3. Hiển thị nhân viên bán thời gian.   ^ 
                                ^    0. Quay lại.                           ^
                                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                        """);
                byte choice = scanner.nextByte();
                switch (choice) {
                    case 1:
                        System.out.println("Hiển thị toàn bộ nhân viên");
                        managerStaff.displayStaff();
                        break;
                    case 2:
                        System.out.println("Hiển thị nhân viên chính thức");
                        managerStaff.showFullTime();
                        break;
                    case 3:
                        System.out.println("Hiển thị nhân viên bán thời gian");
                        managerStaff.showPartTime();
                        break;
                    case 0:
                        System.out.println("Quay lại");
                        return;
                    default:
                        System.out.println("lựa chọn không hợp lệ vui lòng chọn lại");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Lựa chọn không hợp lệ vui lòng chọn lại");
                scanner.nextLine();
            }

        }

    }
    public static void payRoll() {
        System.out.println("Tổng lương phải trả cho nhân viên: "+ managerStaff.sum());
        System.out.println("Lương trung bình toàn nhân viên: "+ managerStaff.averageSalary());
        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                System.out.println("""
                             *--------------------------------------------------------*   
                             *   1. Thống kê lương phải trả cho nhân viên chính thức. *  
                             *   2. Lương phải trả cho nhân viên bán thời gian.       *  
                             *   0. Quay lại.                                         *  
                             *--------------------------------------------------------*   
                    """);
                byte choice = sc.nextByte();
                switch (choice) {
                    case 1:
                        managerStaff.showPayRollFullTime();
                        break;
                    case 2:
                        managerStaff.showPayRollPartTime();
                        break;
                    case 0:
                        System.out.println("Quay lại");
                        return;
                    default:
                        System.out.println("Lựa chọn không đúng vui lòng chọn lại");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Lựa chọn không đúng vui lòng chọn lại");
                sc.nextLine();
            }

        }
    }
}
