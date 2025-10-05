package HospitalManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

class Patient {
    private int patientID;
    private String name;
    private int age;
    private String gender;

    public Patient(int patientID, String name, int age, String gender) {
        this.patientID = patientID;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void displayDetails() {
        System.out.println("Patient ID: " + patientID);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
    }

    public int getPatientID() {
        return patientID;
    }
}

class Doctor {
    private int doctorID;
    private String name;
    private String specialization;

    public Doctor(int doctorID, String name, String specialization) {
        this.doctorID = doctorID;
        this.name = name;
        this.specialization = specialization;
    }

    public void displayDetails() {
        System.out.println("Doctor ID: " + doctorID);
        System.out.println("Name: " + name);
        System.out.println("Specialization: " + specialization);
    }

    public int getDoctorID() {
        return doctorID;
    }
}

class Appointment {
    private int appointmentID;
    private String date;
    private String time;
    private String status;
    private Patient patient;
    private Doctor doctor;

    public Appointment(int appointmentID, String date, String time, Patient patient, Doctor doctor) {
        this.appointmentID = appointmentID;
        this.date = date;
        this.time = time;
        this.status = "Scheduled";
        this.patient = patient;
        this.doctor = doctor;
    }

    public void displayDetails() {
        System.out.println("Appointment ID: " + appointmentID);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Status: " + status);
        System.out.println("Patient: " + patient.getPatientID());
        System.out.println("Doctor: " + doctor.getDoctorID());
    }

    public void markCompleted() {
        status = "Completed";
    }

    public String getStatus() {
        return status;
    }
    public int getAppointmentID() {
        return appointmentID; 
    }
    public Patient getPatient() {
        return patient;
    }
    public Doctor getDoctor() {
        return doctor;
    }
}
class Prescription {
    private int prescriptionID;
    private String items;
    private int quantity;
    public Prescription(int prescriptionID, String items, int quantity) {
        this.prescriptionID = prescriptionID;
        this.items = items;
        this.quantity = quantity;
    }
    public void displayDetails() {
        System.out.println("Prescription ID: " + prescriptionID);
        System.out.println("Items: " + items);
        System.out.println("Quantity: " + quantity);
    }
    public double getCost() {
        return quantity * 50;
    }
}

class Consultation {
    private int consultationID;
    private String notes;
    private Appointment appointment;
    private Prescription prescription;

    public Consultation(int consultationID, String notes, Appointment appointment, Prescription prescription) {
        this.consultationID = consultationID;
        this.notes = notes;
        this.appointment = appointment;
        this.prescription = prescription;
    }
    public void displayDetails() {
        System.out.println("Consultation ID: " + consultationID);
        System.out.println("Notes: " + notes);
        appointment.displayDetails();
        prescription.displayDetails();
    }
    public Prescription getPrescription() {
        return prescription;
    }
}

class Invoice {
    private int invoiceID;
    private double amount;
    private double tax;
    Consultation consultation;

    public Invoice(int invoiceID, Consultation consultation) {
        this.invoiceID = invoiceID;
        this.consultation = consultation;
        generateInvoice();
    }

    public void generateInvoice() {
        amount = consultation.getPrescription().getCost() + 500;
        tax = amount * 0.1;
    }

    public void displayDetails() {
        System.out.println("Invoice ID: " + invoiceID);
        System.out.println("Amount: " + amount);
        System.out.println("Tax: " + tax);
        System.out.println("Total: " + (amount + tax));
    }
}

class Payment {
    private int paymentID;
    private String paymentMode;
    private String paymentDate;
    private double amount;

    public Payment(int paymentID, String paymentMode, String paymentDate, double amount) {
        this.paymentID = paymentID;
        this.paymentMode = paymentMode;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public void makePayment() {
        System.out.println("Payment of " + amount + " made successfully via " + paymentMode + " on " + paymentDate);
    }
}

public class HospitalManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static ArrayList<Consultation> consultations = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Hospital Management System =====");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. Record Consultation");
            System.out.println("5. Generate Invoice");
            System.out.println("6. Record Payment");
            System.out.println("7. List Appointments");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1: addPatient(); break;
                case 2: addDoctor(); break;
                case 3: scheduleAppointment(); break;
                case 4: recordConsultation(); break;
                case 5: generateInvoice(); break;
                case 6: recordPayment(); break;
                case 7: listAppointments(); break;
                case 8: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 8);
    }

    static void addPatient() {
        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();
        patients.add(new Patient(id, name, age, gender));
        System.out.println("Patient added successfully.");
    }

    static void addDoctor() {
        System.out.print("Enter Doctor ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Specialization: ");
        String specialization = sc.nextLine();
        doctors.add(new Doctor(id, name, specialization));
        System.out.println("Doctor added successfully.");
    }

    static void scheduleAppointment() {
        System.out.print("Enter Appointment ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Date (dd-mm-yyyy): ");
        String date = sc.nextLine();
        System.out.print("Enter Time (hh:mm): ");
        String time = sc.nextLine();
        System.out.print("Enter Patient ID: ");
        int pid = sc.nextInt();
        System.out.print("Enter Doctor ID: ");
        int did = sc.nextInt();

        Patient patient = null;
        Doctor doctor = null;
        for (Patient p : patients) {
            if (p.getPatientID() == pid) patient = p;
        }
        for (Doctor d : doctors) {
            if (d.getDoctorID() == did) doctor = d;
        }
        if (patient != null && doctor != null) {
            appointments.add(new Appointment(id, date, time, patient, doctor));
            System.out.println("Appointment scheduled successfully.");
        } else {
            System.out.println("Invalid Patient or Doctor ID.");
        }
    }

    static void recordConsultation() {
        System.out.print("Enter Consultation ID: ");
        int cid = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Appointment ID: ");
        int aid = sc.nextInt();
        sc.nextLine();

        Appointment appointment = null;
        for (Appointment a : appointments) {
            if (a.getStatus().equals("Scheduled") && a.getAppointmentID() == aid) {
                appointment = a;
                a.markCompleted();
                break;
            }
        }
        if (appointment != null) {
            System.out.print("Enter Consultation Notes: ");
            String notes = sc.nextLine();
            System.out.print("Enter Prescription Items: ");
            String items = sc.nextLine();
            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();
            Prescription prescription = new Prescription(cid, items, qty);
            consultations.add(new Consultation(cid, notes, appointment, prescription));
            System.out.println("Consultation recorded successfully.");
        } else {
            System.out.println("Appointment not found or already completed.");
        }
    }

    static void generateInvoice() {
        System.out.print("Enter Invoice ID: ");
        int iid = sc.nextInt();
        sc.nextLine();
        if (!consultations.isEmpty()) {
            Invoice invoice = new Invoice(iid, consultations.get(consultations.size() - 1));
            invoice.displayDetails();
        } else {
            System.out.println("No consultations recorded yet.");
        }
    }

    static void recordPayment() {
        System.out.print("Enter Payment ID: ");
        int pid = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Payment Mode: ");
        String mode = sc.nextLine();
        System.out.print("Enter Payment Date: ");
        String date = sc.nextLine();
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        Payment payment = new Payment(pid, mode, date, amount);
        payment.makePayment();
    }
    static void listAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment a : appointments) {
                a.displayDetails();
                System.out.println("-------------------");
            }
        }
    }
}
