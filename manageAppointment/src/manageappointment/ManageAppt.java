/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageappointment;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author cesarcaceres
 */
public class ManageAppt {

    final static int DOCSIZE = 5;
    final static int PATSIZE = 15;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Doctor> doctorArr = new ArrayList<>();
        ArrayList<Patient> patientArr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        int docID = 0;
        int patID = 0;
        
        do {
            try {
                System.out.println("---------------------------------------------");
                System.out.println("Please select from the following options: ");
                System.out.println("1. Simulate");
                System.out.println("2. View Doctors");
                System.out.println("3. View patients");
                System.out.println("4. View appointments for a particulat doctor");
                System.out.println("5. View appointments for a particular patient");
                System.out.println("6. Exit");
                System.out.println("---------------------------------------------");

                choice = sc.nextInt();
                if (choice == 1) {
                    simulate(doctorArr, patientArr);
                } else if (doctorArr.isEmpty()) {
                    if (choice == 2) {
                        System.out.println("Please simulate first");
                    } else if (choice == 3) {
                        System.out.println("Please simulate first");
                    } else if (choice == 4) {
                        System.out.println("Please simulate first");
                    } else if (choice == 5) {
                        System.out.println("Please simulate first");

                    } else if (choice == 6) {
                        System.out.println("\n Goodbye!\n");
                    } else {
                        System.out.println("\n Please enter an option number"
                                + " between 1 and 6 \n");
                    }
                }//end of clause if it has not been any simulation
                
                //the next is to do when it has been simulated
                else if (choice == 2) {
                    printDocInfo(doctorArr);
                } else if (choice == 3) {
                    printPatInfo(patientArr);
                } else if (choice == 4) {

                    viewDocAppt(docID, doctorArr);
                } else if (choice == 5) {
                    viewPatAppt(patID, patientArr);
                } else if (choice == 6) {
                    System.out.println("\n Goodbye!\n");
                } else {
                    System.out.println("\n Please enter an option number"
                            + " between 1 and 6 \n");
                }
            } catch (InputMismatchException ime) {
                System.err.println("\nInvalid input, please enter a number"
                        + " between 1 and 6");
                sc.nextLine();
            }
        } while (choice != 6);

    }//end of main

    public static void simulate(ArrayList<Doctor> docList, ArrayList<Patient> patList) {
        Random assignSpec = new Random();
        Random patientTreat = new Random();
        Specialty Spec;
        Specialty Treat;
        int docSpec = 0;
        int pTreat = 0;
        int dID = 100;
        int pID = 5000;
        Doctor newDoc = null;
        Patient newPat = null;

        //creating  Doctors
        for (int i = 0; i < DOCSIZE; i++) {
            dID++;
            docSpec = assignSpec.nextInt(4);
            if (docSpec == 0) {//Doctor's Specialty is Internal Medicine
                Spec = Specialty.INTMED;
                newDoc = new Doctor(dID, Spec);
            } else if (docSpec == 1) {//Doctor's Specialty is Cardiology
                Spec = Specialty.CARDIO;
                newDoc = new Doctor(dID, Spec);
            } else if (docSpec == 2) {//Doctor's Specialty is Neurology
                Spec = Specialty.NEUROL;
                newDoc = new Doctor(dID, Spec);
            } else {//Doctor's Specialty is Neurology
                Spec = Specialty.DERMAT;
                newDoc = new Doctor(dID, Spec);
            }
            docList.add(newDoc);
        }
        System.out.println(docList.size() + " doctors have been created");

        //creating Patients
        for (int i = 0; i < PATSIZE; i++) {
            pID++;
            pTreat = patientTreat.nextInt(4);
            if (pTreat == 0) {//Patient's Treatment is for Internal Medicine
                Treat = Specialty.INTMED;
                newPat = new Patient(pID, Treat);
            } else if (pTreat == 1) {//Patient's Treatment is for Cardiology
                Treat = Specialty.CARDIO;
                newPat = new Patient(pID, Treat);
            } else if (pTreat == 2) {//Patient's Treatment is for Neurology
                Treat = Specialty.NEUROL;
                newPat = new Patient(pID, Treat);
            } else {//Patient's Treatment is for Dermatology
                Treat = Specialty.DERMAT;
                newPat = new Patient(pID, Treat);
            }
            patList.add(newPat);
        }
        System.out.println(patList.size() + " patients have been created");

        //passing it to assignAppt
        assignAppt(docList, patList);
    }// end of simulate method

    public static void assignAppt(ArrayList<Doctor> dList, ArrayList<Patient> pList) {
        int assignedAppt = 0;
        int rejectNoDoc = 0;
        int rejectNoAppt = 0;
        boolean docFound = false;
        boolean appSet = false;

        for (Patient aPatient : pList) {
            for (Doctor aDoctor : dList) {
                if (aPatient.getTreatment().equals(aDoctor.getSpecialty())) {
                    docFound = true;
                    if (aDoctor.isAvailable()) {
                        aDoctor.addToSchedule(aPatient);
                        assignedAppt++;
                        appSet = true;
                        break;
                    } else {
                        appSet = false;
                    }
                } else {
                    docFound = false;
                }
            }//end of inner for loop   
            if (docFound == false) {
                rejectNoDoc++;
            }
            if (appSet == false && docFound == true) {
                rejectNoAppt++;
            }

        }//end of outer for loop

        System.out.println("Number of patients with an assigned "
                + "appointment: " + assignedAppt);
        System.out.println("Number of patients rejected due to not founding a "
                + "Doctor of the needed specialty: " + rejectNoDoc);
        System.out.println("Number of patients rejected due to no appointments "
                + "available for the Doctor in their treatment"
                + " Specialty: " + rejectNoAppt);
    }//end of assignAppt method

    public static void printDocInfo(ArrayList<Doctor> dList) {
        int dID = 0;
        Specialty s = null;
        int apptBooked = 0;
        String spec = "";
        System.out.println("Number of Doctors: " + dList.size());
        System.out.println("dID" + "  Specialty " + "  #Apps Booked");
        System.out.println("******************************");

        for (Doctor d : dList) {
            dID = d.getID();
            s = d.getSpecialty();
            spec = s.toString();
            apptBooked = d.getSchedule().size();
            System.out.println(dID + "\t" + spec + "\t\t" + apptBooked);

        }

    }//end of printDocInfo method

    public static void printPatInfo(ArrayList<Patient> pList) {
        int pID = 0;
        Specialty s = null;
        String treat = "";

        System.out.println("Number of Patients: " + pList.size());
        System.out.println("pID" + "  Treatment ");
        System.out.println("*****************");

        for (Patient p : pList) {
            pID = p.getID();
            s = p.getTreatment();
            treat = s.toString();

            System.out.println(pID + "\t" + treat);

        }

    }//end of printPatInfo method

    public static void viewDocAppt(int docIDToSearch, ArrayList<Doctor> docList) {

        int docID = 0;
        Scanner in = new Scanner(System.in);
        boolean found = false;
        ArrayList<Appointment> docAppt = new ArrayList<>();

        try {
            System.out.println("Please enter Doctor ID: ");
            docIDToSearch = in.nextInt();

            for (Doctor d : docList) {
                docID = d.getID();
                if (docID == docIDToSearch) {
                    found = true;
                    docAppt = d.getSchedule();
                    for (Appointment appt : docAppt) {
                        System.out.println(appt.getApptInfo());
                    }
                }
            }
            if (!found) {
                System.out.println("Sorry, that Doctor does not exist");
            }

        } catch (InputMismatchException ime) {
            in.nextLine();
            System.err.println("Bad input, please enter a valid Doctor");

        }
    }//end of viewDocAppt method

    public static void viewPatAppt(int patIDToSearch, ArrayList<Patient> patList) {

        int patID = 0;
        Scanner in = new Scanner(System.in);
        boolean found = false;

        Appointment patAppt = null;

        try {
            System.out.println("Please enter patient ID: ");
            patIDToSearch = in.nextInt();

            for (Patient p : patList) {
                patID = p.getID();
                if (patID == patIDToSearch) {
                    found = true;
                    patAppt = p.getAppt();
                    if (patAppt != null) {
                        System.out.println(patAppt.getApptInfo());
                    } else {
                        System.out.println("Patient " + p.getID() + " does "
                                + "not have an appointment ");
                    }

                }

            }
            if (!found) {
                System.out.println("Sorry, that patient does not exist");
            }

        } catch (InputMismatchException ime) {
            in.nextLine();
            System.err.println("Bad input, please enter a valid Patient");

        }
    }//end of viewPatAppt method

}
