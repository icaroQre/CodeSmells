package EHMS.util;
import java.sql.Connection;
import java.sql.Statement;

import EHMS.doctor.Doctor;
import EHMS.db.ConnectionProvider;

public class Register {

    public void doctorRegistration(Doctor doctor) {
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
