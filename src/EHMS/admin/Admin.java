package EHMS.admin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import EHMS.db.ConnectionProvider;
import EHMS.db.DBTablePrinter;
import EHMS.util.Person;
import EHMS.util.Report;

public class Admin extends Person {

    Scanner sc = new Scanner(System.in);

    private int AutoDoctorID() {
        int docid = 0;
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(UserID) AS NextUserID FROM Users WHERE userType='Doctor'");
            if (rs.next()) {
                docid = rs.getInt(1);
                if (rs.wasNull()) {
                    return 1;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return docid + 1;
    }

    public int addDoctor() {
        int DoctorID = AutoDoctorID();
        String password;
        String cpd;
        System.out.println("Doctor ID:" + DoctorID);
        System.out.println("Enter Password:");
        password = sc.next();
        while (true) {
            System.out.println("Confirm Password");
            cpd = sc.next();
            if (password.compareTo(cpd) == 0)
                break;
        }
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO Users VALUES('" + DoctorID + "','" + "Doctor" + "','" + password + "')");
            System.out.println("Registered Successfully!!");
        } catch (Exception e) {
            System.out.println("Please enter data in correct format!!");
        }
        return DoctorID;
    }

    public void viewDoctors() {
        try {
            Connection con = ConnectionProvider.getCon();
            DBTablePrinter.printTable(con, "Doctors");
            con.close();
        } catch (Exception e) {
            System.out.println("EXCEPTION OCCURS");
        }
    }

    public void viewPatients() {
        try {
            Connection con = ConnectionProvider.getCon();
            DBTablePrinter.printTable(con, "Patients");
        } catch (Exception e) {
            System.out.println("EXCEPTION OCCURS");
        }
    }

    public void RemoveDoctor(int id) {
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM Doctors WHERE DoctorID = " + id);
            System.out.println("Doctor Removed Successfully!!");
        } catch (Exception e) {
            System.out.println("EXCEPTION OCCURS" + e.getMessage());
        }
    }

    public void ViewFeedback() {
        try {
            Connection con = ConnectionProvider.getCon();
            DBTablePrinter.printTable(con, "feedback");
        } catch (Exception e) {
            System.out.println("EXCEPTION OCCURS");
        }
    }

    public void ViewAppointment() {
        try {
            Connection con = ConnectionProvider.getCon();
            DBTablePrinter.printTable(con, "Appointments");
        } catch (Exception e) {
            System.out.println("EXCEPTION OCCURS");
        }
    }

    public void ViewReports() {
        Report r = new Report();
        r.showReport(); // Corrigido para chamar o método showReport()
    }
}
