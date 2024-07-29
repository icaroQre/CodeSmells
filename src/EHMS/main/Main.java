package EHMS.main;

import java.util.Scanner;

import EHMS.admin.Admin;
import EHMS.admin.AdminOperations;
import EHMS.patient.PatientOperations;
import EHMS.patient.Patient;
import EHMS.doctor.Doctor;
import EHMS.doctor.DoctorOperations;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n\t******************************E-HealthCare-Management-System***********************************\n");
        boolean check = false;
        Scanner sc = new Scanner(System.in);
        Admin a = new Admin();
        Patient p = null;
        Doctor d = null;

        while (true) {
            showMainMenu();
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    AdminOperations adminOps = new AdminOperations(sc, a);
                    adminOps.handleAdminOperations();
                    break;
                case 2:
                    PatientOperations patientOps = new PatientOperations(sc);
                    patientOps.handlePatientLogin();
                    break;
                case 3:
                    DoctorOperations doctorOps = new DoctorOperations(sc);
                    doctorOps.handleDoctorLogin();
                    break;
                case 4:
                    p = new Patient();
                    int pid = p.addPatient();
                    System.out.println("*** Fill the following details ***");
                    p.registerPatient(pid);
                    break;
                case 5:
                    System.out.println("**THANKS FOR VISITING US - Have A Nice Day**");
                    check = true;
                    break;
                default:
                    System.out.println("** PLEASE CHOOSE AN APPROPRIATE OPTION **");
            }

            if (check) break;
        }
    }

    private static void showMainMenu() {
        System.out.print("\t**********************************************************************************************\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t*                  1. ADMIN - LOGIN                                                          *\n");
        System.out.print("\t*                  2. PATIENT - LOGIN                                                        *\n");
        System.out.print("\t*                  3. DOCTOR - LOGIN                                                         *\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t*                  4. PATIENT-SIGN-UP                                                        *\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t*                  5. EXIT                                                                   *\n");
        System.out.print("\t**********************************************************************************************\n");
    }
}