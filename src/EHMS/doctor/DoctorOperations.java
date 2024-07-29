package EHMS.doctor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import EHMS.db.ConnectionProvider;

public class DoctorOperations {
    private Scanner sc;

    public DoctorOperations(Scanner sc) {
        this.sc = sc;
    }

    public void handleDoctorLogin() {
        boolean checkDoctor = false;
        System.out.println("***************Welcome to Doctors portal******************");
        int flag = 0;
        int id;
        String pd;
        System.out.print("DOCTOR - ID : "); id = sc.nextInt();
        System.out.print("Password : "); pd = sc.next();
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from Users");
            while (rs.next()) {
                if (rs.getInt(1) == id && rs.getString(2).equals("Doctor") && rs.getString(3).equals(pd)) {
                    flag = 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Not Registered");
        }
        if (flag == 1) {
            Doctor d = new Doctor();
            while (true) {
                showDoctorMenu();
                int ch = sc.nextInt();
                switch (ch) {
                    case 1: d.showDoctorDetails(id); break;
                    case 2: d.viewAppointments(id); break;
                    case 3: d.diagnosePatient(id); break;
                    case 4: checkDoctor = true; break;
                    default: System.out.println("Select Appropriate option");
                }
                if (checkDoctor) break;
            }
        } else {
            System.out.println("Invalid Username or Password!!!");
        }
    }

    private void showDoctorMenu() {
        System.out.print("\t**********************************************************************************************\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t*                  1.view_DOCTOR_Profile                                                     *\n");
        System.out.print("\t*                  2.viewAppointments                                                        *\n");
        System.out.print("\t*                  3.DiagnosePatient                                                         *\n");
        System.out.print("\t*                  4.LOGOUT                                                                  *\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t**********************************************************************************************\n");
    }
}
