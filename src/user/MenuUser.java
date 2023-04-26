package user;

import controller.ManagerStaff;

import java.util.Scanner;

public class MenuUser extends Thread{
    static ManagerStaff managerStaff = new ManagerStaff();
    @Override
    public void run() {
        super.run();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("""
                        *--------------------------------*
                        *   Bạn muốn xem thông tin nào   *
                        *--------------------------------*
                        |    1. Thông tin cơ bản.        |
                        |    2. Thông tin lương.         |   
                        |    3. Thông tin tài khoản      |
                        | --------- 0. Thoát ----------- |
                """);
                byte choice = scanner.nextByte();
                switch (choice){
                    case 1:
                        System.out.println("Thông tin cơ bản");
                        break;
                    case 2:
                        System.out.println("Thông tin lương");
                        break;
                    case 3:
                        System.out.println("Thông tin tài khoản");
                        break;
                    case 0:
                        System.out.println("Thoát");
                        return;
                    default:
                        System.out.println("lựa chọn không hợp lệ");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Lựa chọn không đúng");
            }

        }

    }
}
