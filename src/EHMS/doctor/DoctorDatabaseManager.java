package EHMS.doctor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import EHMS.db.ConnectionProvider;

import java.sql.SQLException;

public class DoctorDatabaseManager {

    // Método para executar uma consulta SELECT
    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            resultSet = st.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return resultSet;
    }

    // Método para executar uma atualização (INSERT, UPDATE, DELETE)
    public void executeUpdate(String query) {
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

    // Método para fechar ResultSet
    public void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

    // Método para fechar Statement
    public void closeStatement(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

    // Método para fechar Connection
    public void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

    // Método para registrar um novo médico
    public void registerDoctor(Doctor doctor) {
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO Doctors VALUES ('"
            + doctor.getDocID() + "','"
            + doctor.getFirstName() + "','"
            + doctor.getLastName() + "','"
            + doctor.getGender() + "','"
            + doctor.getContactNumber() + "','"
            + doctor.getAge() + "','"
            + doctor.getEntryCharge() + "','"
            + doctor.getQualification() + "','"
            + doctor.getDoctorType() + "','"
            + doctor.getEmailAddress() + "')");
            System.out.println("Doctor Added Successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
