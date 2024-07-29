package EHMS.patient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import EHMS.db.ConnectionProvider;

import java.sql.SQLException;

public class PatientDatabaseManager {

    // Obtém o próximo ID do paciente
    public int getNextPatientID() {
        int idPatient = 0;
        try (Connection con = ConnectionProvider.getCon();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT MAX(userID) AS 'NextPatientID' FROM Users")) {
            if (rs.next()) {
                idPatient = rs.getInt(1);
                if (rs.wasNull()) {
                    return 1;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return idPatient + 1;
    }

    // Adiciona um novo usuário
    public void addUser(int patientID, String password) {
        try (Connection con = ConnectionProvider.getCon();
             Statement st = con.createStatement()) {
            st.executeUpdate("INSERT INTO Users (userID, userType, password) VALUES ('"
                + patientID + "', 'Patient', '" + password + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Registra um paciente
    public void registerPatient(Patient patient) {
        try (Connection con = ConnectionProvider.getCon();
             Statement st = con.createStatement()) {
            String query = "INSERT INTO Patients (PatientID, FirstName, LastName, EmailAddress, Gender, Age, CN, City, State, Country, Address, BloodGroup, RegistrationDate) VALUES ('"
                + patient.getUserID() + "', '"
                + patient.getFirstName() + "', '"
                + patient.getLastName() + "', '"
                + patient.getEmailAddress() + "', '"
                + patient.getGender() + "', "
                + patient.getAge() + ", '"
                + patient.getContactNumber() + "', '"
                + patient.getCity() + "', '"
                + patient.getState() + "', '"
                + patient.getCountry() + "', '"
                + patient.getAddress() + "', '"
                + patient.getBloodGroup() + "', '"
                + new java.sql.Date(patient.getRegistrationDate().getTime()) + "')";
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Obtém detalhes do paciente
    public void getPatientDetails(int id) {
        try (Connection con = ConnectionProvider.getCon();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Patients WHERE PatientID=" + id)) {
            while (rs.next()) {
                System.out.println("PatientID: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2) + " " + rs.getString(3));
                System.out.println("Blood-Group: " + rs.getString(8));
                System.out.println("Address: " + rs.getString(9));
                System.out.println("Contact-Number: " + rs.getString(5));
                System.out.print("\t********************\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Obtém a lista de médicos
    public void getDoctors() {
        try (Connection con = ConnectionProvider.getCon();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Doctors")) {
            while (rs.next()) {
                System.out.println("DoctorID: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Specialization: " + rs.getString(3));
                System.out.println("Contact: " + rs.getString(4));
                System.out.print("\t********************\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Obtém consultas agendadas
    public void getAppointments(int id) {
        try (Connection con = ConnectionProvider.getCon();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Appointments WHERE PatientID=" + id)) {
            int t = 0;
            while (rs.next()) {
                t++;
                System.out.println("\t*** APPOINTMENT - NUMBER: " + t);
                System.out.println("\t* Appointment_ID: " + rs.getInt(1));
                System.out.println("\t* Problem: " + rs.getString(2));
                System.out.println("\t* Doctor_ID: " + rs.getInt(5));
                System.out.println("\t* DoctorFees: " + rs.getString(8));
                System.out.println("\t* PaymentStatus: " + rs.getString(9));
                System.out.print("\t*************************************************************\n");
            }
            if (t == 0) {
                System.out.println("*******You Currently Have No Appointments********");
                System.out.println("Enter 3 To Book Appointment!!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Obtém o histórico de consultas
    public void getAppointmentHistory(int id) {
        try (Connection con = ConnectionProvider.getCon();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Appointments WHERE PatientID=" + id)) {
            int t = 0;
            while (rs.next()) {
                if ("Completed".equals(rs.getString(10))) {
                    t++;
                    System.out.println("\t*** APPOINTMENT - NUMBER: " + t);
                    System.out.println("\t* Appointment_ID: " + rs.getInt(1));
                    System.out.println("\t* Problem: " + rs.getString(2));
                    System.out.println("\t* Doctor_ID: " + rs.getInt(5));
                    System.out.println("\t* DoctorFees: " + rs.getString(8));
                    System.out.println("\t* PaymentStatus: " + rs.getString(9));
                    System.out.println("\t* Status: " + rs.getString(10));
                    System.out.print("\t*************************************************************\n");
                }
            }
            if (t == 0) {
                System.out.println("*******You Currently Have No Appointment History********");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Obtém relatórios
    public void getReports(int id) {
        try (Connection con = ConnectionProvider.getCon();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Reports WHERE PatientID=" + id)) {
            int t = 0;
            while (rs.next()) {
                t++;
                System.out.println("\t*** REPORT - NUMBER: " + t);
                System.out.println("\t* Report_ID: " + rs.getInt(1));
                System.out.println("\t* Date: " + rs.getDate(2));
                System.out.println("\t* Details: " + rs.getString(3));
                System.out.print("\t*************************************************************\n");
            }
            if (t == 0) {
                System.out.println("*******You Currently Have No Reports********");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Envia feedback
    public void submitFeedback(int id, int points, String docNature, String location, String comment) {
        try (Connection con = ConnectionProvider.getCon();
             Statement st = con.createStatement()) {
            String query = "INSERT INTO Feedback (PatientID, Points, DoctorNature, Location, Comment) VALUES ('"
                + id + "', "
                + points + ", '"
                + docNature + "', '"
                + location + "', '"
                + comment + "')";
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
