package model;

import java.time.LocalDate;
import java.time.Period;

public abstract class Staff {
    private String id;
    private String name;
    private LocalDate birthDay;
    private int tel;
    private Address address;
    public Staff(){
    }

    public Staff(String id, String name,  LocalDate birthDay, int tel, Address address) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.tel = tel;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public int getTel() {
        return tel;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public int calculateAge(LocalDate birthDate){
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthDate, today);
        return period.getYears();
    }
    public abstract double payroll();

    @Override
    public String toString() {
        return "Nhân viên: " + +'\n' +
                "id=" + id + '\n' +
                "Họ và tên: " + name + '\n' +
                "Ngày sinh: " + birthDay + '\n' +
                "Tuổi: " + calculateAge(birthDay)+
                "Liên hệ: " + tel + '\n' +
                "Địa chỉ: " + address + '\n';
    }
}