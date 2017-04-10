# doctorApptJava
Java Doctor Appointment Simulation Program
This is a simple Program created in Java that simulates a doctor's office environment. It simulates Doctors and patients and
assigns them an appointment according to the Doctor's Specialty and the Patient's needed treatment. It also evaluates availability 
of the doctor in such specialty. There are 5 doctors and 15 patients simulated in the program. The Doctor can only take 
three patients at a time and scheduled appointments start at 9 am and continue on the hour. 
The main menu helps you decide what you want to do. You need to simulate first for the program to work. After simulating you can
view doctors, patients, appointments by doctor or by patient ID, etc. 
The main class is called ManageAppt and contains the menu in the main method and other methods which allow you to simulate and 
do all the other activities you need to do. 
The other classes include the Enum class for Specialty and Doctor, Patient and Appointment classes. Each class has setters and
getters. 
As of right now the program works great for only one simulation, if you want to do multiple you need to adjust the code so that 
the Doctor and Patient Id does not reset and duplicate since I have a max set for 5 Doctors and 15 Patients. 

