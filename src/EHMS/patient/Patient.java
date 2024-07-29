package EHMS.patient;

import java.util.Scanner;

import EHMS.util.Person;

public class Patient extends Person {
    private String bloodGroup;
    private int userID;
    private PatientDatabaseManager dbManager = new PatientDatabaseManager();
    private Scanner sc = new Scanner(System.in);

    // Getters e Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    // Método para gerar ID do paciente
    private int generatePatientID() {
        return dbManager.getNextPatientID();
    }

    // Adiciona um novo paciente
    public int addPatient() {
        int patientID = generatePatientID();
        System.out.println("Patient ID: " + patientID);
        this.userID = patientID;

        String password = getPasswordFromUser();
        dbManager.addUser(patientID, password);

        System.out.println("Registered Successfully!!");
        return patientID;
    }

    // Coleta a senha do usuário
    private String getPasswordFromUser() {
        String password;
        String confirmPassword;

        System.out.println("Enter Password:");
        password = sc.next();
        while (true) {
            System.out.println("Confirm Password:");
            confirmPassword = sc.next();
            if (password.equals(confirmPassword)) {
                break;
            } else {
                System.out.println("Your Password is not matching!!!");
                System.out.println("*\tRE-ENTER The Password*");
            }
        }
        return password;
    }

    // Registra um paciente
    public void registerPatient(int pid) {
        this.userID = pid;
        // Coleta informações do usuário
        collectUserInformation();
        System.out.println("Blood Group:");
        this.bloodGroup = sc.next();
        dbManager.registerPatient(this);
    }

    // Exibe detalhes do paciente
    public void showPatientDetails(int id) {
        dbManager.getPatientDetails(id);
    }

    // Exibe a lista de médicos
    public void viewDoctors() {
        dbManager.getDoctors();
    }

    // Agenda uma consulta
    public void bookAppointment(int id) {
        // Implementar a lógica de agendamento de consulta
        System.out.println("Booking appointment for Patient ID: " + id);
    }

    // Exibe consultas agendadas
    public void viewAppointments(int id) {
        dbManager.getAppointments(id);
    }

    // Exibe o histórico de consultas
    public void viewAppointmentHistory(int id) {
        dbManager.getAppointmentHistory(id);
    }

    // Exibe relatórios
    public void viewReports(int id) {
        dbManager.getReports(id);
    }

    // Envia feedback
    public void submitFeedback(int id) {
        System.out.println("*********Please Fill The Following Feedback Form*********");
        System.out.println("Patient Id: " + id);
        System.out.println("Please Give points to our services out of 10:");
        int points = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.println("Nature Of The Doctor:");
        String docNature = sc.nextLine();
        System.out.println("Enter Your Address below:");
        String location = sc.nextLine();
        System.out.println("Your Comment:");
        String comment = sc.nextLine();

        dbManager.submitFeedback(id, points, docNature, location, comment);
        System.out.println("-->>Thank You For Visiting Us<<--");
        System.out.println("-->>Your Feedback Meant a lot to Us<<--");
    }
}
