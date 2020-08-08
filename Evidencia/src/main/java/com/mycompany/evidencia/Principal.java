/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.evidencia;

import java.util.Scanner;
import com.mycompany.evidencia.Doctor;
import com.mycompany.evidencia.Patient;
import com.mycompany.evidencia.Date;

/**
 *
 * @author esteban
 */
public class Principal {

    public static void main(String[] args) {
        String user, password;
        Scanner readline = new Scanner(System.in);

        System.out.println("*****SISTEMA INTEGRAL DE REGISTROS MEDICOS*****");
        System.out.println("Ingrese usuario: ");
        user = readline.nextLine();
        System.out.println("Ingrese contraseña: ");
        password = readline.nextLine();
        showMenu();
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
                System.out.println("5. Salir");
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
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println();
                        System.out.println("Esta opción no existe. Por favor, elige otra.");
                        System.out.println("********************");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
                System.out.println("*****MENU*****");
                System.out.println("1. Registrar doctor");
                System.out.println("2. Registrar paciente");
                System.out.println("3. Registrar cita");
                System.out.println("4. Establecer doctor y paciente a cita");
                System.out.println("5. Salir");
                option = Integer.parseInt(readline.nextLine());
                System.out.println(option);
                switch (option) {
                    case 1:
                        registerDoctor();
                        break;
                    case 2:
                        registerPatient();
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println();
                        System.out.println("Esta opción no existe. Por favor, elige otra.");
                        System.out.println("********************");
                }
            }

        } while (option != 5);

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
}
