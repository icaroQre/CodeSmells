package EHMS.doctor;

import java.sql.ResultSet;
import java.util.Scanner;

import EHMS.util.Person;
import EHMS.util.Report;

public class Doctor extends Person {
    private int docID;
    private String doctorType;
    private String qualification;
    private int entryCharge;
    private Scanner sc = new Scanner(System.in);
    private DoctorDatabaseManager dbManager = new DoctorDatabaseManager();

    // Getters e Setters
    public int getDocID() {
        return docID;
    }

    public void setDocID(int docID) {
        this.docID = docID;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getEntryCharge() {
        return entryCharge;
    }

    public void setEntryCharge(int entryCharge) {
        this.entryCharge = entryCharge;
    }

    // Registro de um novo médico
    public void registerDoctor(int docID) {
        System.out.println("Enter the following Details");
        this.docID = docID;
        System.out.println("Doctor ID: " + docID);
        super.collectUserInformation();
        System.out.println("Entry Fee:");
        this.entryCharge = sc.nextInt();
        System.out.println("Qualification:");
        this.qualification = sc.next();
        System.out.println("Doctor Type:");
        System.out.println("1. Eyes\n2. Ear\n3. Heart\n4. Bone\n5. Lungs\n6. Kidney\n7. General Physicist");
        int choice = sc.nextInt();
        switch (choice) {
            case 1: this.doctorType = "Eyes"; break;
            case 2: this.doctorType = "Ear"; break;
            case 3: this.doctorType = "Heart"; break;
            case 4: this.doctorType = "Bone"; break;
            case 5: this.doctorType = "Lungs"; break;
            case 6: this.doctorType = "Kidney"; break;
            case 7: this.doctorType = "General Physicist"; break;
            default: System.out.println("Select an appropriate option"); break;
        }
        dbManager.registerDoctor(this);
    }

    // Exibe detalhes do médico
    public void showDoctorDetails(int d) {
        String query = "SELECT * FROM Doctors WHERE DoctorID=" + d;
        ResultSet rs = dbManager.executeQuery(query);
        try {
            while (rs.next()) {
                System.out.println("DoctorID: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2) + " " + rs.getString(3));
                System.out.println("Qualification: " + rs.getString(8));
                System.out.println("Department: " + rs.getString(9));
                System.out.println("Contact No: " + rs.getString(5));
                System.out.println("EmailId: " + rs.getString(10));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbManager.closeResultSet(rs);
        }
    }

    // Exibe consultas para um médico específico
    public void viewAppointments(int docID) {
        int count = 0;
        String query = "SELECT * FROM Appointments WHERE DoctorID=" + docID;
        ResultSet rs = dbManager.executeQuery(query);
        try {
            while (rs.next()) {
                if (rs.getString(9).equals("Paid") && rs.getString(10).equals("Pending")) {
                    count++;
                    System.out.println("\t*** APPOINTMENT - NUMBER : " + count);
                    System.out.print("\t* Appointment_ID : " + rs.getInt(1) + " \n");
                    System.out.print("\t* Problem  : " + rs.getString(2) + " \n");
                    System.out.print("\t* PatientId : " + rs.getInt(3) + " \n");
                    System.out.print("\t* DoctorFees : " + rs.getString(8) + " \n");
                    System.out.print("\t* PaymentStatus : " + rs.getString(9) + " \n");
                    System.out.print("\t*************************************************************\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbManager.closeResultSet(rs);
        }
        if (count == 0) {
            System.out.println("You Currently Don't Have Any Appointment");
        }
    }

    // Verifica se uma consulta existe
    private int appointmentChecker(int appID, int docID) {
        String query = "SELECT * FROM Appointments WHERE DoctorID=" + docID;
        ResultSet rs = dbManager.executeQuery(query);
        try {
            while (rs.next()) {
                if (rs.getInt(1) == appID) {
                    return 1;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbManager.closeResultSet(rs);
        }
        return 0;
    }

    // Diagnóstico de um paciente
    public void diagnosePatient(int id) {
        while (true) {
            System.out.println("Enter Appointment_Id of the patient which you want to check!!");
            int appID = sc.nextInt();
            int found = appointmentChecker(appID, id);
            if (found == 1) {
                int patientID = getPatientID(appID);
                Report rp = new Report();
                rp.diagnoseReport(patientID, appID, id);
                break;
            } else {
                System.out.println("******Wrong Appointment ID****");
                System.out.println("Enter 1 to leave!!!");
                if (sc.nextInt() == 1) {
                    break;
                }
            }
        }
    }

    // Obtém o ID do paciente para uma consulta específica
    private int getPatientID(int appID) {
        int patientID = 0;
        String query = "SELECT * FROM Appointments WHERE AppointmentID=" + appID;
        ResultSet rs = dbManager.executeQuery(query);
        try {
            while (rs.next()) {
                patientID = rs.getInt(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbManager.closeResultSet(rs);
        }
        return patientID;
    }
}
