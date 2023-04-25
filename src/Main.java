import controller.ManagerStaff;
import model.Address;
import model.FullTimeStaff;
import model.PartTimeStaff;
import model.Staff;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import user.Login;
import view.Regex;

public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        login.start();
    }
}