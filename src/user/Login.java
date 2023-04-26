package user;

import storage.ReadFile;

import java.util.*;

public class Login extends Thread {
    private String filePatch = "./src/storage/User.bin";
    private Map<String, String> userMap;
    private ReadFile<String> readFile;
    private final String MENU_COLOR = "\u001b[33m";
    private final String ERROR_COLOR = "\u001b[31m";
    private final String ACCESS_COLOR = "\u001b[34m";
    private final String RESET_COLOR = "\u001B[0m";
    private final String ADMIN = "admin";
    private final String PASSWORD = "admin";
    private String user;
    private String password;

    public Login() {
        userMap = new HashMap<>();
        readFile = new ReadFile<>(filePatch);
        userMap = readFile.read2();
    }

    public void login(String user, String password) {

        if ((user.equals(this.ADMIN))&&(password.equals(PASSWORD))) {
            System.out.println(ACCESS_COLOR+"Đăng nhập thành công"+RESET_COLOR);
            MenuManager menuManager = new MenuManager();
            menuManager.start();
            try {
                menuManager.join();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            boolean check = false;
            for (Map.Entry<String, String> u : userMap.entrySet()) {
                if (u.getKey().equals(user) && u.getValue().equals(password)) {
                    check = true;
                    break;
                } else {
                    System.out.println(ERROR_COLOR+"Sai tên hoặc mật khẩu"+ERROR_COLOR);
                }
            }
            if (check) {
                System.out.println(ACCESS_COLOR+"Đăng nhập thành công"+RESET_COLOR);
                MenuUser menuUser = new MenuUser();
                menuUser.start();
                try {
                    menuUser.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println(MENU_COLOR + """
                             *-------------------------*        
                            |||      1. Đăng Nhập     |||    
                            |||       0. Thoát        |||  
                             *-------------------------*
                             |     Mời bạn lựa chọn    |
                             *-------------------------*
                    """ + RESET_COLOR);
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
                        System.out.println(ACCESS_COLOR + "Thoát chương trình" + RESET_COLOR);
                        return;
                    default:
                        System.out.println(ERROR_COLOR + "Lựa chọn không hợp lệ" + RESET_COLOR);
                        break;
                }
            } catch (Exception e) {
                System.out.println(ERROR_COLOR + "Lựa chọn không hợp lệ" + RESET_COLOR);
                scanner.nextLine();
            }
        }
    }
}
