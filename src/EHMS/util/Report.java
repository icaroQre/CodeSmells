package EHMS.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import EHMS.db.ConnectionProvider;
import EHMS.db.DBTablePrinter;

public class Report {
    Scanner input = new Scanner(System.in);
    private int repId;
    private int patientId;
    private int appointmentId;
    private int doctorId;
    private String medicinePrescribed;
    private String doctorsComment;

    // Gera um ID de relatório automático
    private int autoReportID() {
        int repID = 0;
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(ReportId) AS NextReportID FROM Reports");
            if (rs.next()) {
                repID = rs.getInt(1);
                if (rs.wasNull()) {
                    return 1;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return repID + 1;
    }

    // Método de diagnóstico de relatório
    public void diagnoseReport(int patientId, int appointmentId, int doctorId) {
        this.repId = autoReportID();
        System.out.println("ReportID--" + repId);
        this.patientId = patientId;
        System.out.println("PatientID--" + patientId);
        this.appointmentId = appointmentId;
        System.out.println("AppointmentID--" + appointmentId);
        this.doctorId = doctorId;
        System.out.println("DoctorID--" + doctorId);
        System.out.println("Prescribed medicine to patient:");
        input.nextLine(); // Consome a nova linha pendente do input anterior
        this.medicinePrescribed = input.nextLine();
        System.out.println("Additional Information:");
        this.doctorsComment = input.nextLine();
        System.out.println("Enter 1 to Generate Report:");
        int x = input.nextInt();
        if (x == 1) {
            generateReport();
            showReport();
        } else {
            System.out.println("** Enter Appropriate Details Please **");
        }
    }

    // Gera o relatório e o insere no banco de dados
    private void generateReport() {
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO Reports VALUES ('" + repId + "', '" + appointmentId + "', '" + patientId + "', '" + doctorId + "', '" + medicinePrescribed + "', '" + doctorsComment + "')");
            System.out.println("Report Generated Successfully!!!");
            changeStatus();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Atualiza o status da consulta
    private void changeStatus() {
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE Appointments SET Appointment_Status='Completed' WHERE AppointmentID=" + appointmentId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Exibe o relatório
    public void showReport() { // Alterado para public
        try {
            Connection con = ConnectionProvider.getCon();
            DBTablePrinter.printTable(con, "Reports");
        } catch (Exception e) {
            System.out.println("EXCEPTION OCCURRED: " + e.getMessage());
        }
    }
}
