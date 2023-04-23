package controller;

import model.Address;
import model.FullTimeStaff;
import model.PartTimeStaff;
import model.Staff;
import view.Regex;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

public class ManagerStaff {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ManagerStaff.class));
    List<Staff> staffs = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Regex regex = new Regex();

    public ManagerStaff() {
    }

    public void displayStaff() {
        System.out.println("Danh sách nhân viên là: ");
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
            System.out.println("Không tồn tại nhân viên này trong hệ thống.");
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
                updateStaff(staff.getId());
            } else {
                System.out.println("Không cập nhật nữa.");
            }
        }
    }

    public void updateStaff(String id) {
        if (!checkStaffId(id)) {
            LOGGER.info("Không tồn tại nhân viên này trong hệ thống.");
        } else {
            LOGGER.warning("Bạn có chắc muốn sửa đổi thông tin (Y/N)?");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                while (true) {
                    System.out.println("""
                                                Bạn muốn sửa thông tin nào: 
                                                1. Tên
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
                                LOGGER.info("Đã cập nhập tên thành công.");
                                break;
                            case 2:
                                System.out.println("Sửa ngày tháng năm sinh: ");
                                staffs.stream().filter(staff -> staff.getId().equals(id)).findFirst().ifPresent(staff -> staff.setBirthDay(inputBirthDay()));
                                LOGGER.info("Cập nhật thành công ngày sinh.");
                                break;
                            case 3:
                                System.out.println("Sửa số điện thoại: ");
                                staffs.stream().filter(staff -> staff.getId().equals(id)).findFirst().ifPresent(staff -> staff.setTel(inputPhoneNumber()));
                                LOGGER.info("cập nhật thành công số điện thoại");
                                break;
                            case 4:
                                System.out.println("sửa địa chỉ: ");
                                staffs.stream().filter(staff -> staff.getId().equals(id)).findFirst().ifPresent(staff -> staff.setAddress(inputAddress()));
                                LOGGER.info("Cập nhật thành công địa chỉ mới");
                                break;
                            case 5:
                                System.out.println("Thông tin khác: ");

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
                                System.out.println("Không thay đổi nữa");
                                System.out.println("Nhân viên bạn vừa sửa đổi là: ");
                                staffs.stream().filter(staff -> staff.getId().equals(id)).findFirst().ifPresent(System.out::println);
                                return;
                            default:
                                LOGGER.info("lựa chọn không hợp lệ");
                        }
                    } catch (Exception e) {
                        LOGGER.info("Lựa chọn không hợp lệ");
                        scanner.next();
                    }
                }
            } else {
                System.out.println("không thay đổi gì cả.");
            }
        }
    } //TODO: chưa hoàn thành thay đổi loại nhân viên

    public void removeStaff(String id) {
        if (!checkStaffId(id)) {
            System.out.println("Không tồn tại nhân viên này trong hệ thống");
        } else {
            LOGGER.warning("Bạn có chắc muốn xóa nhân viên này (Y/N)? ");
            staffs.stream().filter(staff -> staff.getId().equals(id)).findFirst().ifPresent(System.out::println);
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                staffs.removeIf(staff -> staff.getId().equals(id));
                System.out.println("đã xóa thành công...");
            } else {
                System.out.println("không xóa nữa");
            }
        }
    }

    public void arrangement() {
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

    public boolean checkStaffId(String id) {
        boolean checkId;
        checkId = staffs.stream().anyMatch(staff -> staff.getId().equals(id));
        return checkId;
    }


    public String inputName() {
        String name;
        name = scanner.nextLine();
        while (!regex.checkName(name)) {
            LOGGER.info("Nhập đúng định dạng tên...");
            name = scanner.nextLine();
        }
        return name;
    }

    public LocalDate inputBirthDay() {
        boolean checkBirthDay = false;
        LocalDate birthDay = null;
        while (!checkBirthDay) {
            try {
                LOGGER.warning("Nhập đúng định dạng (yyyy-mm-dd)");
                birthDay = LocalDate.parse(scanner.nextLine());
                checkBirthDay = true;
            } catch (DateTimeException e) {
                LOGGER.info("Không đúng định dạng yyyy-mm-dd");
            }
        }
        return birthDay;
    }

    public String inputPhoneNumber() {
        String phoneNumberInput;
        do {
            LOGGER.info("Nhập đúng định dạng (+84)-(xxxxxxxxx) ");
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
            LOGGER.info("Nhập đúng định dạng");
            conscious = scanner.nextLine();
        }
        // Nhập QUận/Huyện
        System.out.println("Nhập Quận/Huyện: ");
        String district;
        district = scanner.nextLine();
        while (regex.checkAddress(district)) {
            LOGGER.info("Nhập đúng định dạng");
            district = scanner.nextLine();
        }
        // Nhập phường xã
        System.out.println("Nhập phường/xã: ");
        String commune = scanner.nextLine();
        while (regex.checkAddress(commune)) {
            LOGGER.info("Nhập đúng định dạng");
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
            LOGGER.info("nhập đúng quy định (#xxx)");
            id = scanner.nextLine();
        }
        return id;
    }

    public void convertToPartTime(String id) {
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getId().equals(id)) {
                Staff staff = staffs.get(i);
                if (staff instanceof FullTimeStaff) {
                    System.out.println("Nhập giờ làm: ");
                    double workingHours = 0;
                    while (true) {
                        try {
                            workingHours = scanner.nextDouble();
                            if (workingHours < 0) {
                                System.out.println("Có giờ âm hả ????");
                                continue;
                            }
                            break;
                        }catch (Exception e) {
                            System.out.println("Vui lòng nhập cho đúng định dạng");
                            scanner.nextLine();
                        }
                    }
                    FullTimeStaff fullTimeStaff = (FullTimeStaff) staff;
                    PartTimeStaff partTimeStaff = new PartTimeStaff(fullTimeStaff.getId(), fullTimeStaff.getName(), fullTimeStaff.getBirthDay(), fullTimeStaff.getTel(), fullTimeStaff.getAddress(), workingHours);
                    staffs.remove(i);
                    staffs.add(i, partTimeStaff);
                    System.out.println("Đã chuyển đổi thành công");
                    return;
                } else {
                    LOGGER.info("Không đúng loại nhân viên...");
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
                                    System.out.println("Số lần vi phạm phải là số dương");
                                } else {
                                    errorCount[j] = intput;
                                    validInput = true;
                                }

                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                System.out.println("vui lòng nhập số nguyên");
                            } catch (IllegalArgumentException e) {
                                scanner.nextLine();
                                System.out.println("Sai định dạng");
                            }
                        }
                    }

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
                    PartTimeStaff partTimeStaff = (PartTimeStaff) staff;
                    FullTimeStaff fullTimeStaff = new FullTimeStaff(partTimeStaff.getId(), partTimeStaff.getName(), partTimeStaff.getBirthDay(), partTimeStaff.getTel(), partTimeStaff.getAddress(), rewards, mistakes, errorCount);
                    staffs.remove(i);
                    staffs.add(i, fullTimeStaff);
                    System.out.println("Đã chuyển đổi thành công");
                    return;
                } else {
                    LOGGER.info("Không đúng loại nhân viên");
                }

            }
        }
    }
}
