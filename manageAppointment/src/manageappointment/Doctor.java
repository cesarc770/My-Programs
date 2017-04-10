/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageappointment;

import java.util.ArrayList;

/**
 *
 * @author cesarcaceres
 */
public class Doctor {

    private final int dID;
    private Specialty dSpec = null;
    private ArrayList<Appointment> schedule = new ArrayList<>();
    private boolean available = true;
    private static final int maxAppt = 3;
    private static int startTime = 9;

    //constructor Doctor to initialize dID and dSpec
    public Doctor(int dID, Specialty dSpec) {
        this.dID = dID;
        this.dSpec = dSpec;
    }

    //methods of Doctor
    public int getID() {
        return dID;
    }

    public Specialty getSpecialty() {
        return dSpec;
    }

    public boolean isAvailable() {
        return (schedule.size() < maxAppt);
    }

    public int addToSchedule(Patient patID) {
        int timeAvail = schedule.size();
        Appointment appt = null;

        if (timeAvail == 0) {
            appt = new Appointment(startTime, this, patID);
        } else if (timeAvail == 1) {
            appt = new Appointment(startTime + 1, this, patID);
        } else if (timeAvail == 2) {
            appt = new Appointment(startTime + 2, this, patID);
        }else if(timeAvail == 3){
            timeAvail = maxAppt;
        }
        
        schedule.add(appt);
        patID.setAppt(appt);
        
        timeAvail = schedule.size();
        
        return timeAvail;

    }

    public ArrayList<Appointment> getSchedule() {
        return schedule;
    }

}
