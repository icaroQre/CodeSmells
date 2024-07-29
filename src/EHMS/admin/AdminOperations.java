package EHMS.admin;

import java.util.Scanner;

import EHMS.doctor.Doctor;

public class AdminOperations {
    private Scanner sc;
    private Admin admin;

    public AdminOperations(Scanner sc, Admin admin) {
        this.sc = sc;
        this.admin = admin;
    }

    public void handleAdminOperations() {
        boolean checkadmin = false;
        System.out.println("*****************Welcome to Admins portal***********************");
        String un;
        String pd;
        System.out.print("USERNAME-->"); un = sc.next();
        System.out.print("Password-->"); pd = sc.next();
        if ((un.equals("abc") && pd.equals("1234")) || (un.equals("xyz") && pd.equals("1234"))) {
            while (true) {
                showAdminMenu();
                int ch = sc.nextInt();
                switch (ch) {
                    case 1: admin.viewDoctors(); break;
                    case 2: admin.viewPatients(); break;
                    case 3: addDoctor(); break;
                    case 4: removeDoctor(); break;
                    case 5: admin.ViewAppointment(); break;
                    case 6: admin.ViewFeedback(); break;
                    case 7: admin.ViewReports(); break;
                    case 8: checkadmin = true; break;
                    default: System.out.println("Please Choose An Appropriate Option!!!");
                }
                if (checkadmin) break;
            }
        } else {
            System.out.println("Invalid Username or Password");
        }
    }

    private void showAdminMenu() {
        System.out.print("\t**********************************************************************************************\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t*                  1.DoctorsList                                                             *\n");
        System.out.print("\t*                  2.PatientsList.                                                           *\n");
        System.out.print("\t*                  3.AddDoctor                                                               *\n");
        System.out.print("\t*                  4.RemoveDoctor                                                            *\n");
        System.out.print("\t*                  5.AppointmentsDetail                                                      *\n");
        System.out.print("\t*                  6.ViewFeedbacks                                                           *\n");
        System.out.print("\t*                  7.ViewReports                                                             *\n");
        System.out.print("\t*                  8.LOGOUT                                                                  *\n");
        System.out.print("\t**********************************************************************************************\n");
    }

    private void addDoctor() {
        int Id = admin.addDoctor();
        Doctor doctor = new Doctor();
        doctor.registerDoctor(Id);
    }

    private void removeDoctor() {
        System.out.println("Enter doctorID!!");
        int id = sc.nextInt();
        admin.RemoveDoctor(id);
    }
}
