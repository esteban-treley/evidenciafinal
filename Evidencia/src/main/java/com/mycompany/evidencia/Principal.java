/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.evidencia;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author esteban
 */
public class Principal {

    public static void main(String[] args) {
        String user = "", password = "";
        Scanner readline = new Scanner(System.in);
        do {
            System.out.println("*****SISTEMA INTEGRAL DE REGISTROS MEDICOS*****");
            System.out.println("Ingrese usuario: ");
            user = readline.nextLine();
            System.out.println("Ingrese contraseña: ");
            password = readline.nextLine();

            if (user.equals("admin") && password.equals("admin123")) {
                showMenu();
            } else {
                System.out.println();
                System.out.println("Error! Las credenciales no son correctas. Intente de nuevo.");
            }
        } while (!user.equals("admin") || !password.equals("admin123"));

    }

    public static void showMenu() {
        int option = 0;
        Scanner readline = new Scanner(System.in);
        do {
            try {
                System.out.println();
                System.out.println("*****MENU*****");
                System.out.println("1. Registrar doctor");
                System.out.println("2. Registrar paciente");
                System.out.println("3. Registrar cita");
                System.out.println("4. Establecer doctor y paciente a cita");
                System.out.println("5. Ver lista de citas");
                System.out.println("6. Salir");
                option = Integer.parseInt(readline.nextLine());

                switch (option) {
                    case 1:
                        registerDoctor();
                        break;
                    case 2:
                        registerPatient();
                        break;
                    case 3:
                        registerDate();
                        break;
                    case 4:
                        manageDates();
                        break;
                    case 5:
                        showDates();
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println();
                        System.out.println("Esta opción no existe. Por favor, elige otra.");
                        System.out.println("********************");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } while (option != 6);

    }

    public static void registerDoctor() {
        try {
            Doctor newDoctor = new Doctor();
            newDoctor.setConsecutiveId();
            Scanner read = new Scanner(System.in);

            System.out.print("Escriba nombre del doctor: ");
            newDoctor.setName(read.nextLine());
            System.out.print("Escriba especialidad del doctor: ");
            newDoctor.setSpeciality(read.nextLine());

            newDoctor.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerPatient() {
        try {
            Patient newPatient = new Patient();
            newPatient.setConsecutiveId();
            Scanner read = new Scanner(System.in);

            System.out.print("Escriba nombre del paciente: ");
            newPatient.setName(read.nextLine());

            newPatient.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerDate() {
        try {
            Date newDate = new Date();
            newDate.setConsecutiveId();
            Scanner read = new Scanner(System.in);
            boolean validateDate = false;
            do {
                String date = "";
                System.out.print("Escriba la fecha de la cita(DD/MM/YY): ");
                date = read.nextLine();
                if (newDate.validateDate(date)) {
                    newDate.setDate(date);
                    validateDate = true;
                } else {
                    System.out.println();
                    System.out.println("Error de fecha! Ingrese una fecha válida.");
                    System.out.println();
                }
            } while (!validateDate);

            boolean validateTime = false;
            do {
                String time = "";
                System.out.print("Escriba la hora de la cita(HH:MM): ");
                time = read.nextLine();
                if (newDate.validateTime(time)) {
                    newDate.setTime(time);
                    validateTime = true;
                } else {
                    System.out.println();
                    System.out.println("Error de hora! Ingrese una hora válida.");
                    System.out.println();
                }
            } while (!validateTime);
            System.out.print("Escriba el motivo de la cita: ");
            newDate.setMotive(read.nextLine());

            newDate.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void manageDates() {
        System.out.println("*******ADMINISTARCION DE CITAS**********");
        System.out.println();
        Scanner read = new Scanner(System.in);

        /* 
        CITAS
        Se muestran todas las citas, y luego se escoge el ID de la cita a asignarle doctor y paciente.
         */
        ArrayList<Date> dateList = getDates();
        for (Date date : dateList) {
            System.out.println(date.getId() + ", " + date.getDate() + " " + date.getTime() + ", " + date.getMotive() + ", " + date.getDoctorId() + ", " + date.getPatientId());
        }
        System.out.print("Ingresa el ID de la cita a administrar: ");
        int citaId = Integer.parseInt(read.nextLine());

        /* 
        DOCTOR
        Se muestran todos los doctores, y luego se escoge el ID del doctor a quien se le asigará la cita.
         */
        ArrayList<Doctor> doctorList = getDoctors();
        for (Doctor doctor : doctorList) {
            System.out.println(doctor.getId() + ", " + doctor.getName() + " " + doctor.getSpeciality());
        }
        System.out.print("Ingresa el ID de la doctor a agendar cita: ");
        int doctorId = Integer.parseInt(read.nextLine());

        /* 
        Paciente
        Se muestran todos los pacientes, y luego se escoge el ID del paciente a quien se le asigará la cita.
         */
        ArrayList<Patient> patientList = getPatients();
        for (Patient patient : patientList) {
            System.out.println(patient.getId() + ", " + patient.getName());
        }
        System.out.print("Ingresa el ID de la doctor a agendar cita: ");
        int patientId = Integer.parseInt(read.nextLine());

        try {
            File file = Date.getDateFile();
            FileWriter fr = new FileWriter(file, false);
            for (Date date : dateList) {
                if (date.getId() == citaId) {
                    date.setDoctorId(doctorId);
                    date.setPatientId(patientId);
                    String data = Integer.toString(date.getId()) + "," + date.getDate() + "," + date.getTime() + "," + date.getMotive() + "," + Integer.toString(date.getDoctorId()) + "," + Integer.toString(date.getPatientId());
                    fr.write(data);
                    fr.write(System.lineSeparator());
                } else {
                    String data = Integer.toString(date.getId()) + "," + date.getDate() + "," + date.getTime() + "," + date.getMotive() + "," + Integer.toString(date.getDoctorId()) + "," + Integer.toString(date.getPatientId());
                    fr.write(data);
                    fr.write(System.lineSeparator());
                }
            }
            fr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList getDoctors() {
        ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
        File doctorFile = Doctor.getDoctorFile();
        try {
            String[] line;
            Scanner scanDoctorFile = new Scanner(doctorFile);
            while (scanDoctorFile.hasNextLine()) {
                line = scanDoctorFile.nextLine().split(",");
                doctorList.add(new Doctor(Integer.parseInt(line[0]), line[1], line[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doctorList;
    }

    public static ArrayList getPatients() {
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        File patientFile = Patient.getPatientFile();
        try {
            String[] line;
            Scanner scanPatientFile = new Scanner(patientFile);
            while (scanPatientFile.hasNextLine()) {
                line = scanPatientFile.nextLine().split(",");
                patientList.add(new Patient(Integer.parseInt(line[0]), line[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return patientList;
    }

    public static ArrayList getDates() {
        ArrayList<Date> dateList = new ArrayList<Date>();
        File dateFile = Date.getDateFile();
        try {
            String[] line;
            Scanner scanDoctorFile = new Scanner(dateFile);
            while (scanDoctorFile.hasNextLine()) {
                line = scanDoctorFile.nextLine().split(",");
                dateList.add(new Date(Integer.parseInt(line[0]), line[1], line[2], line[3], Integer.parseInt(line[4]), Integer.parseInt(line[5])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateList;
    }

    public static void showDates() {
        System.out.println("**********LISTA DE CITAS***********");
        ArrayList<Date> dates = getDates();
        ArrayList<Doctor> doctors = getDoctors();
        ArrayList<Patient> patients = getPatients();
        String doctorName, patientName;
        for (Date date : dates) {
            doctorName = "";
            patientName = "";
            for (Doctor doctor : doctors) {
                if (doctor.getId() == date.getDoctorId()) {
                    doctorName = doctor.getName();
                }
            }
            for (Patient patient : patients) {
                if (patient.getId() == date.getDoctorId()) {
                    patientName = patient.getName();
                }
            }
            System.out.println(date.getId() + ", " + date.getDate() + " " + date.getTime() + ", " + date.getMotive() + ", Doctor: " + doctorName + ", Paciente: " + patientName);
        }
    }
}
