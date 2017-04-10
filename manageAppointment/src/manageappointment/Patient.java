/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageappointment;

/**
 *
 * @author cesarcaceres
 */
public class Patient {

    private final int pID;
    private Specialty treat = null;
    private Appointment appt = null;

    public Patient(int patID, Specialty treat) {
        pID = patID;
        this.treat = treat;
    }

    // Patient methods
    public int getID() {
        return pID;
    }

    public Specialty getTreatment() {
        return treat;
    }

    public void setAppt(Appointment info) {
        appt = info;
    }

    public Appointment getAppt() {
        return appt;
    }

}
