package EHMS.patient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import EHMS.db.ConnectionProvider;

public class PatientOperations {
    private Scanner sc;

    public PatientOperations(Scanner sc) {
        this.sc = sc;
    }

    public void handlePatientLogin() {
        boolean checkPatient = false;
        int flag = 0;
        System.out.println("*****************Welcome to patient portal***********************");
        int id;
        String pd;
        System.out.print("ID:"); id = sc.nextInt();
        System.out.print("Password:"); pd = sc.next();
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from Users");
            while (rs.next()) {
                if (rs.getInt(1) == id && rs.getString(2).equals("Patient") && rs.getString(3).equals(pd)) {
                    flag = 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag == 1) {
            Patient p = new Patient();
            while (true) {
                showPatientMenu();
                int ch = sc.nextInt();
                switch (ch) {
                    case 1: p.showPatientDetails(id); break;
                    case 2: p.viewDoctors(); break;
                    case 3: p.bookAppointment(id); break;
                    case 4: p.viewReports(id); break;
                    case 5: p.viewAppointments(id); break;
                    case 6: p.viewAppointmentHistory(id); break;
                    case 7: p.submitFeedback(id); break;
                    case 8: checkPatient = true; break;
                    default: System.out.println("Please Choose An Appropriate Option!!!");
                }
                if (checkPatient) break;
            }
        } else {
            System.out.println("Invalid UserID or password!!!");
        }
    }

    private void showPatientMenu() {
        System.out.print("\t**********************************************************************************************\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t*                  1.ViewProfile                                                             *\n");
        System.out.print("\t*                  2.viewDoctors                                                             *\n");
        System.out.print("\t*                  3.BookAppointments                                                        *\n");
        System.out.print("\t*                  4.ViewReport                                                              *\n");
        System.out.print("\t*                  5.viewAppointments                                                        *\n");
        System.out.print("\t*                  6.viewCompletedAppointments                                               *\n");
        System.out.print("\t*                  7.Give FeedBack                                                           *\n");
        System.out.print("\t*                  8.LOGOUT                                                                  *\n");
        System.out.print("\t**********************************************************************************************\n");
    }
}
