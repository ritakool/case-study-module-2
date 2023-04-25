package user;

import java.util.Scanner;

public class Login extends Thread{
    private final String MENU_COLOR = "\u001b[33m";
    private final String ERROR_COLOR = "\u001b[31m";
    private final String ACCESS_COLOR = "\u001b[34m";
    private final String RESET_COLOR = "\u001B[0m";
    private final String ADMIN = "1";
    private final String PASSWORD ="1";
    private String user;
    private String password;
    public Login() {
    }

    public Login(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void login(String user, String password) {
        if ((user.equals(this.ADMIN))&&(password.equals(PASSWORD))) {
            System.out.println(ACCESS_COLOR+"Đăng nhập thành công"+RESET_COLOR);
        }else {
            System.out.println(ERROR_COLOR+"Sai tên hoặc mật khẩu"+RESET_COLOR);
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println(MENU_COLOR+"""
                     *-------------------------*        
                    |||      1. Đăng Nhập     |||    
                    |||       0. Thoát        |||  
                     *-------------------------*
                     |     Mời bạn lựa chọn    |
                     *-------------------------*
            """+RESET_COLOR);
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Đăng Nhập");
                        System.out.println("Nhập username: ");
                        String name = scanner.next();
                        System.out.println("Nhập mật khẩu: ");
                        String pass = scanner.next();
                        login(name, pass);
                        break;
                    case 0:
                        System.out.println(ACCESS_COLOR+"Thoát chương trình"+RESET_COLOR);
                        return;
                    default:
                        System.out.println(ERROR_COLOR+"Lựa chọn không hợp lệ"+RESET_COLOR);
                        break;
                }
            } catch (Exception e) {
                System.out.println(ERROR_COLOR+"Lựa chọn không hợp lệ"+RESET_COLOR);
                scanner.nextLine();
            }
        }
    }
}
