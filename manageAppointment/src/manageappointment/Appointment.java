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
public class Appointment {
    private int timeH;
    private Doctor doctor;
    private Patient patient;
    
    public Appointment(int timeH, Doctor doctor, Patient patient){
        this.timeH = timeH;
        this.doctor = doctor;
        this.patient = patient;
    }
        
        //methods of Appointment
        
        public String getApptInfo(){
            return   "Appointment time: "+ timeH + "am. Doctor: "+ doctor.getID() +
                    " Specialty: "+  doctor.getSpecialty() + " Patient: " + 
                    patient.getID() + " Treatment: " + patient.getTreatment();
   }
    
    
}
