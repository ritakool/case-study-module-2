package controller;

import model.Address;
import model.FullTimeStaff;
import model.PartTimeStaff;
import model.Staff;
import view.Regex;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

public class ManagerStaff {
    private final String ERROR_COLOR = "\u001B[31m";
    private final String ACCESS_COLOR = "\u001B[34m";
    private final String RESET_COLOR = "\u001B[0m";
    List<Staff> staffs = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Regex regex = new Regex();

    public ManagerStaff() {
    }

    public void displayStaff() {
        System.out.println(ACCESS_COLOR+"Danh sách toàn bộ nhân viên là: "+RESET_COLOR);
        staffs.forEach(System.out::println);
    }

    public double averageSalary() {
        double sum = 0;
        int count = 0;
        for (Staff staff : staffs) {
            sum += staff.payroll();
            count++;
        }
        return sum / count;
    }

    public void displayNameStaff(String name) {
        boolean checkName = false;
        int i = 0;
        while (i < staffs.size()) {
            if (staffs.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(staffs.get(i));
                checkName = true;
            }
            i++;
        }
        if (!checkName) {
            System.out.println(ERROR_COLOR + "Không tồn tại nhân viên này trong hệ thống." + RESET_COLOR);
        }

    }

    public void addStaff(Staff staff) {

        if (!checkStaffId(staff.getId())) {
            staffs.add(staff);
            System.out.println(ACCESS_COLOR+"Đã thêm nhân viên thành công."+RESET_COLOR);
        } else {
            System.out.println( ERROR_COLOR+"Đã có nhân viên này, bạn có muốn cập nhật nhân viên này không. (Y/N) ?"+RESET_COLOR);
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                updateStaff(staff.getId());
            } else {
                System.out.println("Không cập nhật nữa.");
            }
        }
    }

    public void updateStaff(String id) {
        if (!checkStaffId(id)) {
            System.out.println(ERROR_COLOR+"Không tồn tại nhân viên này trong hệ thống."+RESET_COLOR);
        } else {
            System.out.println(ERROR_COLOR+"Bạn có chắc muốn sửa đổi thông tin (Y/N)?"+RESET_COLOR);
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                while (true) {
                    System.out.println("""
                                        Bạn muốn sửa thông tin nào: 
                                        1. Sửa tên nhân viên
                                        2. Ngày tháng năm sinh
                                        3. Số điện thoại
                                        4. Địa chỉ
                                        5. Thông tin khác.
                                        6. Chuyển đổi nhân viên
                                        0. Không thay đổi nữa                            
                            """);
                    try {
                        byte choice = scanner.nextByte();
                        switch (choice) {
                            case 1:
                                System.out.println("Sửa tên: ");
                                staffs.stream().filter(staff -> staff.getId().equals(id)).findFirst().ifPresent(staff -> staff.setName(inputName()));
                                System.out.println(ACCESS_COLOR +"Đã cập nhập tên thành công."+RESET_COLOR);
                                break;
                            case 2:
                                System.out.println("Sửa ngày tháng năm sinh: ");
                                staffs.stream().filter(staff -> staff.getId().equals(id)).findFirst().ifPresent(staff -> staff.setBirthDay(inputBirthDay()));
                                System.out.println(ACCESS_COLOR+"Cập nhật thành công ngày sinh."+RESET_COLOR);
                                break;
                            case 3:
                                System.out.println("Sửa số điện thoại: ");
                                staffs.stream().filter(staff -> staff.getId().equals(id)).findFirst().ifPresent(staff -> staff.setTel(inputPhoneNumber()));
                                System.out.println(ACCESS_COLOR+"Cập nhật thành công số điện thoại"+RESET_COLOR);
                                break;
                            case 4:
                                System.out.println("sửa địa chỉ: ");
                                staffs.stream().filter(staff -> staff.getId().equals(id)).findFirst().ifPresent(staff -> staff.setAddress(inputAddress()));
                                System.out.println(ACCESS_COLOR+"Cập nhật thành công địa chỉ mới"+RESET_COLOR);
                                break;
                            case 5:
                                for (int i = 0; i < staffs.size(); i++) {
                                    if (staffs.get(i).getId().equals(id)) {
                                        if (staffs.get(i) instanceof PartTimeStaff) {
                                            System.out.println(ERROR_COLOR+"""
                                                    Bạn có muốn thay đổi thời gian làm việc của nhân viên ?
                                                    Y. Đồng ý
                                                    Ấn phím bất kì để kết thúc.
                                            """+RESET_COLOR);
                                            scanner.nextLine();
                                            String Confirm = scanner.nextLine();
                                            if (Confirm.equalsIgnoreCase("y")) {
                                                changeTime(id);
                                            } else {
                                                System.out.println("Thay đổi ý định");
                                            }

                                        }
                                        if (staffs.get(i) instanceof FullTimeStaff) {
                                            System.out.println(ERROR_COLOR+"""
                                                    Bạn có muốn thay đổi xét thưởng,lỗi vi phạm của nhân viên ?
                                                    Y. Đồng ý
                                                    Ấn phím bất kì để kết thúc.
                                            """+RESET_COLOR);
                                            scanner.nextLine();
                                            String Confirm = scanner.nextLine();
                                            if (Confirm.equalsIgnoreCase("y")) {
                                                changeRewardsMistakes(id);
                                            } else {
                                                System.out.println("Thay đổi ý định");
                                            }
                                        }
                                    }
                                }
                                break;
                            case 6:
                                byte choice2 = -1;
                                do {
                                    System.out.println("""
                                                       Chuyển đổi nhân viên:
                                                       1. FullTime -> PartTime.
                                                       2. PartTime -> FullTime.
                                                       0. Không chuyển đổi nữa.
                                            """);
                                    try {
                                        choice2 = scanner.nextByte();
                                        switch (choice2) {
                                            case 1:
                                                System.out.println("FullTime -> PartTime");
                                                convertToPartTime(id);
                                                break;
                                            case 2:
                                                System.out.println("PartTime -> FullTime");
                                                convertToFullTime(id);
                                                break;
                                            case 0:
                                                System.out.println("Không chuyển đổi nữa");
                                                break;
                                            default:
                                                System.out.println("Nhập không đúng");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Nhập không đúng");
                                        scanner.next();
                                    }
                                } while (choice2 != 0);
                                break;
                            case 0:
                                System.out.println(ACCESS_COLOR+"Không thay đổi nữa"+RESET_COLOR);
                                System.out.println("Nhân viên bạn vừa sửa đổi là: ");
                                staffs.stream().filter(staff -> staff.getId().equals(id)).findFirst().ifPresent(System.out::println);
                                return;
                            default:
                                System.out.println(ERROR_COLOR+"lựa chọn không hợp lệ"+RESET_COLOR);
                        }
                    } catch (Exception e) {
                        System.out.println(ERROR_COLOR+"Lựa chọn không hợp lệ"+RESET_COLOR);
                        scanner.next();
                    }
                }
            } else {
                System.out.println("không thay đổi gì cả.");
            }
        }
    }

    public void removeStaff(String id) {
        if (!checkStaffId(id)) {
            System.out.println(ERROR_COLOR+"Không tồn tại nhân viên này trong hệ thống"+RESET_COLOR);
        } else {
            System.out.println(ERROR_COLOR+"Bạn có chắc muốn xóa nhân viên này (Y/N)?"+RESET_COLOR);
            staffs.stream().filter(staff -> staff.getId().equals(id)).findFirst().ifPresent(System.out::println);
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                staffs.removeIf(staff -> staff.getId().equals(id));
                System.out.println(ACCESS_COLOR+"Đã xóa thành công..."+RESET_COLOR);
            } else {
                System.out.println(ACCESS_COLOR+"không xóa nữa"+RESET_COLOR);
            }
        }
    }

    public void arrangement() {
        if (staffs.isEmpty()) {
            System.out.println(ACCESS_COLOR+"Danh sách nhân viên trống"+RESET_COLOR);
        } else {
            Collections.sort(staffs, new Comparator<Staff>() {
                @Override
                public int compare(Staff o1, Staff o2) {
                    String nameObj1 = o1.getName().substring(o1.getName().lastIndexOf(" ") + 1);
                    String nameObj2 = o2.getName().substring(o2.getName().lastIndexOf(" ") + 1);
                    int nameObj = nameObj1.compareTo(nameObj2);
                    if (nameObj != 0) {
                        return nameObj;
                    } else {
                        String objName1 = o1.getName().substring(0, o1.getName().indexOf(" "));
                        String objName2 = o2.getName().substring(0, o2.getName().indexOf(" "));
                        return objName1.compareTo(objName2);
                    }
                }
            });
            displayStaff();
        }
    }

    public boolean checkStaffId(String id) {
        boolean checkId;
        checkId = staffs.stream().anyMatch(staff -> staff.getId().equals(id));
        return checkId;
    }


    public String inputName() {
        String name;
        name = scanner.nextLine();
        while (!regex.checkName(name)) {
            System.out.println(ERROR_COLOR+"Nhập đúng định dạng tên"+RESET_COLOR);
            name = scanner.nextLine();
        }
        return name;
    }

    public LocalDate inputBirthDay() {
        boolean checkBirthDay = false;
        LocalDate birthDay = null;
        while (!checkBirthDay) {
            try {
                System.out.println(ERROR_COLOR+"Nhập đúng định dạng (yyyy-mm-dd)"+RESET_COLOR);
                scanner.nextLine();
                birthDay = LocalDate.parse(scanner.nextLine());
                checkBirthDay = true;
            } catch (DateTimeException e) {
                System.out.println(ERROR_COLOR+"Không đúng định dạng (yyyy-mm-dd)"+ERROR_COLOR);
            }
        }
        return birthDay;
    }

    public String inputPhoneNumber() {
        String phoneNumberInput;
        do {
            System.out.println(ERROR_COLOR+"Nhập đúng định dạng (+84)-(xxxxxxxxx) "+RESET_COLOR);
            phoneNumberInput = scanner.nextLine();
        } while (!regex.checkPhoneNumber(phoneNumberInput));
        return phoneNumberInput;
    }

    public Address inputAddress() {
        // Nhập tỉnh
        System.out.println("Nhập tỉnh: ");
        String conscious;
        conscious = scanner.nextLine();
        while (regex.checkAddress(conscious)) {
            System.out.println(ERROR_COLOR+"Nhập đúng định dạng"+RESET_COLOR);
            conscious = scanner.nextLine();
        }
        // Nhập QUận/Huyện
        System.out.println("Nhập Quận/Huyện: ");
        String district;
        district = scanner.nextLine();
        while (regex.checkAddress(district)) {
            System.out.println(ERROR_COLOR+"Nhập đúng định dạng"+RESET_COLOR);
            district = scanner.nextLine();
        }
        // Nhập phường xã
        System.out.println("Nhập phường/xã: ");
        String commune = scanner.nextLine();
        while (regex.checkAddress(commune)) {
            System.out.println(ERROR_COLOR+"Nhập đúng định dạng"+RESET_COLOR);
            commune = scanner.nextLine();
        }
        System.out.println("Nhập Số nhà: ");
        String apartmentNumber = scanner.nextLine();
        return new Address(conscious, district, commune, apartmentNumber);
    }

    public String inputId() {
        System.out.println("nhập id: ");
        String id;
        id = scanner.nextLine();
        while (!regex.checkID(id)) {
            System.out.println(ERROR_COLOR+"nhập đúng quy định (#xxx)"+RESET_COLOR);
            id = scanner.nextLine();
        }
        return id;
    }

    public void convertToPartTime(String id) {
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getId().equals(id)) {
                Staff staff = staffs.get(i);
                if (staff instanceof FullTimeStaff) {
                    FullTimeStaff fullTimeStaff = (FullTimeStaff) staff;
                    PartTimeStaff partTimeStaff = new PartTimeStaff(fullTimeStaff.getId(), fullTimeStaff.getName(), fullTimeStaff.getBirthDay(), fullTimeStaff.getTel(), fullTimeStaff.getAddress(), workingHours());
                    staffs.remove(i);
                    staffs.add(i, partTimeStaff);
                    System.out.println(ACCESS_COLOR+"Đã chuyển đổi thành công"+RESET_COLOR);
                    return;
                } else {
                    System.out.println(ERROR_COLOR+"Đang là nhân viên bán thời gian rồi"+RESET_COLOR);
                    return;
                }
            }
        }
    }

    public void convertToFullTime(String id) {
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getId().equals(id)) {
                Staff staff = staffs.get(i);
                if (staff instanceof PartTimeStaff) {
                    System.out.println("Nhập thông tin lỗi và số lần vi phạm:");
                    FullTimeStaff.mistake[] mistakes = FullTimeStaff.mistake.values();
                    int[] errorCount = new int[mistakes.length];
                    for (int j = 0; j < mistakes.length; j++) {
                        boolean validInput = false;
                        while (!validInput) {
                            System.out.print("Số lần vi phạm " + mistakes[j].getName() + ": ");
                            try {
                                int intput = scanner.nextInt();
                                if (intput < 0) {
                                    System.out.println(ERROR_COLOR+"Số lần vi phạm phải là số dương"+RESET_COLOR);
                                } else {
                                    errorCount[j] = intput;
                                    validInput = true;
                                }

                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                System.out.println(ERROR_COLOR+"Vui lòng nhập số nguyên"+RESET_COLOR);
                            } catch (IllegalArgumentException e) {
                                scanner.nextLine();
                                System.out.println(ERROR_COLOR+"Sai định dạng"+RESET_COLOR);
                            }
                        }
                    }

                    System.out.println("Nhập thông tin mà nhân viên đuơc thưởng");
                    FullTimeStaff.reward[] rewards = FullTimeStaff.reward.values();
                    int[] countReward = new int[rewards.length];
                    for (int j = 0; j < rewards.length; j++) {
                        boolean validInput = false;
                        while (!validInput) {
                            System.out.println("loại thưởng: " + rewards[j].getNameReward());
                            try {
                                byte input = scanner.nextByte();
                                if (input < 0 || input > 1) {
                                    System.out.println(ERROR_COLOR+"Nhập 0 hoặc 1"+RESET_COLOR);
                                } else {
                                    countReward[j] = input;
                                    validInput = true;
                                }
                            } catch (Exception e) {
                                scanner.nextLine();
                                System.out.println(ERROR_COLOR+"Nhập 0 hoặc 1"+RESET_COLOR);
                            }
                        }
                    }
                    PartTimeStaff partTimeStaff = (PartTimeStaff) staff;
                    FullTimeStaff fullTimeStaff = new FullTimeStaff(partTimeStaff.getId(), partTimeStaff.getName(), partTimeStaff.getBirthDay(), partTimeStaff.getTel(), partTimeStaff.getAddress(), rewards, countReward, mistakes, errorCount);
                    staffs.remove(i);
                    staffs.add(i, fullTimeStaff);
                    System.out.println(ACCESS_COLOR+"Đã chuyển đổi thành công"+RESET_COLOR);
                    return;
                } else {
                    System.out.println(ERROR_COLOR+"Đang là nhân viên chính thức rồi"+RESET_COLOR);
                    return;
                }
            }
        }
    }

    public void changeTime(String id) {
        System.out.println("Sửa giờ làm ");
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getId().equals(id)) {
                Staff staff = staffs.get(i);
                if (staff instanceof PartTimeStaff) {
                    System.out.println("Nhập giờ làm: ");
                    double workingHours = 0;
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            workingHours = scanner.nextDouble();
                            validInput = true;
                            scanner.nextLine();
                            if (workingHours < 0) {
                                System.out.println(ERROR_COLOR+"Có giờ âm hả ????"+RESET_COLOR);
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println(ERROR_COLOR+"Vui lòng nhập cho đúng định dạng"+RESET_COLOR);
                            scanner.nextLine();
                        }
                    }
                    ((PartTimeStaff) staff).setTimeWork(workingHours);
                    System.out.println(ACCESS_COLOR+"Sửa giờ làm việc thành công"+RESET_COLOR);
                }
            }
        }
    }

    public void changeRewardsMistakes(String id) {
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getId().equals(id)) {
                Staff staff = staffs.get(i);
                if (staff instanceof FullTimeStaff) {
                    int choice = -1;
                    do {
                        System.out.println("""
                                    Bạn muốn sửa thông tin nào:
                                    1. Sửa thông tin lỗi và số lần vi phạm.
                                    2. Sửa thông tin mà nhân viên được thưởng
                                    0. Không thay đổi nữa
                            """);
                        try {
                            choice = scanner.nextInt();

                            switch (choice) {
                                case 1:
                                    System.out.println("Sửa thông tin lỗi và số lần vi phạm");
                                    System.out.println("Nhập thông tin lỗi và số lần vi phạm:");
                                    FullTimeStaff.mistake[] mistakes = FullTimeStaff.mistake.values();
                                    int[] errorCount = new int[mistakes.length];
                                    for (int j = 0; j < mistakes.length; j++) {
                                        boolean validInput = false;
                                        while (!validInput) {
                                            System.out.print("Số lần vi phạm " + mistakes[j].getName() + ": ");
                                            try {
                                                int intput = scanner.nextInt();
                                                if (intput < 0) {
                                                    System.out.println(ERROR_COLOR+"Số lần vi phạm phải là số dương"+RESET_COLOR);
                                                } else {
                                                    errorCount[j] = intput;
                                                    validInput = true;
                                                }

                                            } catch (InputMismatchException e) {
                                                scanner.nextLine();
                                                System.out.println(ERROR_COLOR+"Vui lòng nhập số nguyên"+RESET_COLOR);
                                            } catch (IllegalArgumentException e) {
                                                scanner.nextLine();
                                                System.out.println(ERROR_COLOR+"Sai định dạng"+RESET_COLOR);
                                            }
                                        }
                                    }
                                    ((FullTimeStaff) staff).setMistakes(mistakes);
                                    ((FullTimeStaff) staff).setErrorCount(errorCount);
                                    System.out.println(ACCESS_COLOR+"Sửa thành công"+RESET_COLOR);
                                    break;
                                case 2:
                                    System.out.println("Sửa thông tin mà nhân viên được thưởng");
                                    System.out.println("Nhập thông tin mà nhân viên đuơc thưởng");
                                    FullTimeStaff.reward[] rewards = FullTimeStaff.reward.values();
                                    byte[] countReward = new byte[rewards.length];
                                    for (int j = 0; j < rewards.length; j++) {
                                        boolean validInput = false;
                                        while (!validInput) {
                                            System.out.println("loại thưởng: " + rewards[j].getNameReward());
                                            try {
                                                byte input = scanner.nextByte();
                                                if (input < 0 || input > 1) {
                                                    System.out.println("Nhập 0 hoặc 1");
                                                } else {
                                                    countReward[j] = input;
                                                    validInput = true;
                                                }
                                            } catch (Exception e) {
                                                scanner.nextLine();
                                                System.out.println("Nhập 0 hoặc 1");
                                            }
                                        }
                                    }
                                    ((FullTimeStaff) staff).setRewards(rewards);
                                    System.out.println(ACCESS_COLOR+"Sửa thành công"+RESET_COLOR);
                                    break;
                                case 0:
                                    System.out.println("không thay đổi nữa");
                                    return;
                                default:
                                    System.out.println(ERROR_COLOR+"Không có lựa chọn này"+RESET_COLOR);
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println(ERROR_COLOR+"Không có lựa chọn này"+RESET_COLOR);
                        }

                    } while (choice != 0);
                }
            }
        }
    }
    public Staff newStaff() {
        Staff staff;
        System.out.println("Tạo nhân viên mới");
        int choice;
        while (true) {
            try {
                System.out.println("""
                        loại nhân viên:
                        1. Nhân viên chính thức.
                        2. Nhân viên bán thời gian
                """);
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println(ACCESS_COLOR+"Nhân viên chính thức: "+RESET_COLOR);
                        System.out.println("Nhập thông tin lỗi và số lần vi phạm:");
                        FullTimeStaff.mistake[] mistakes = FullTimeStaff.mistake.values();
                        int[] errorCount = new int[mistakes.length];
                        for (int j = 0; j < mistakes.length; j++) {
                            boolean validInput = false;
                            while (!validInput) {
                                System.out.print("Số lần vi phạm " + mistakes[j].getName() + ": ");
                                try {
                                    int intput = scanner.nextInt();
                                    if (intput < 0) {
                                        System.out.println(ERROR_COLOR+"Số lần vi phạm phải là số dương"+RESET_COLOR);
                                    } else {
                                        errorCount[j] = intput;
                                        validInput = true;
                                    }

                                } catch (InputMismatchException e) {
                                    scanner.nextLine();
                                    System.out.println(ERROR_COLOR+"Vui lòng nhập số nguyên"+RESET_COLOR);
                                } catch (IllegalArgumentException e) {
                                    scanner.nextLine();
                                    System.out.println(ERROR_COLOR+"Sai định dạng"+RESET_COLOR);
                                }
                            }
                        }

                        System.out.println("Nhập thông tin mà nhân viên đuơc thưởng");
                        FullTimeStaff.reward[] rewards = FullTimeStaff.reward.values();
                        int[] countReward = new int[rewards.length];
                        for (int j = 0; j < rewards.length; j++) {
                            boolean validInput = false;
                            while (!validInput) {
                                System.out.println("loại thưởng: " + rewards[j].getNameReward());
                                try {
                                    byte input = scanner.nextByte();
                                    if (input < 0 || input > 1) {
                                        System.out.println(ERROR_COLOR+"Nhập 0 hoặc 1"+RESET_COLOR);
                                    } else {
                                        countReward[j] = input;
                                        validInput = true;
                                    }
                                } catch (Exception e) {
                                    scanner.nextLine();
                                    System.out.println(ERROR_COLOR+"Nhập 0 hoặc 1"+RESET_COLOR);
                                }
                            }
                        }
                        staff = new FullTimeStaff(inputId(),inputName(),inputBirthDay(),inputPhoneNumber(),inputAddress(),rewards,countReward,mistakes,errorCount);
                        return staff;
                    case 2:
                        System.out.println(ACCESS_COLOR+"Nhân viên bán thời gian: "+RESET_COLOR);
                        staff = new PartTimeStaff(inputId(),inputName(),inputBirthDay(),inputPhoneNumber(),inputAddress(),workingHours());
                        return staff;
                }
            } catch (Exception e) {
                System.out.println(ERROR_COLOR+"Lựa chọn không hợp lệ"+RESET_COLOR);
                scanner.nextLine();
            }

        }

//        return staff;
    }
    public double workingHours() {
        System.out.println("Nhập giờ làm: ");
        double workingHours = 0;
        while (true) {
            try {
                workingHours = scanner.nextDouble();
                if (workingHours < 0) {
                    System.out.println(ERROR_COLOR+"Có giờ âm hả ????"+RESET_COLOR);
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println(ERROR_COLOR+"Vui lòng nhập cho đúng định dạng"+RESET_COLOR);
                scanner.nextLine();
            }
        }
        return workingHours;
    }
    public void showFullTime() {
            System.out.println(ACCESS_COLOR+"Danh sách nhân viên toàn thời gian: "+RESET_COLOR);
            staffs.stream().filter(staff -> staff instanceof FullTimeStaff).forEach(System.out::println);
    }
    public void showPartTime() {
        System.out.println(ACCESS_COLOR+"Danh sách nhân viên bán thời gian"+RESET_COLOR);
        staffs.stream().filter(staff -> staff instanceof PartTimeStaff).forEach(System.out::println);
    }
    public void showPayRoll() {

    }
}

