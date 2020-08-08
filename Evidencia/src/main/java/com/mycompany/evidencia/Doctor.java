/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.evidencia;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;

/**
 *
 * @author esteban
 */
public class Doctor {

    //Atributes
    public int id;
    public String name;
    public String speciality;

    private String fileName = "Doctor.csv";

    //Constructors
    public Doctor() {
    }

    public Doctor(String name, String speciality) {
        this.name = name;
        this.speciality = speciality;
    }
    public Doctor(int id, String name, String speciality) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
    }

    //Methods
    public void setName(String name) {
        this.name = name;
    }

    public static File getDoctorFile() {
        File here = new File(".");
        File doctorFile = null;

        try {
            String herePath, directoryPath;
            
            directoryPath = here.getCanonicalPath() + "/db";
            if(!new File(directoryPath).exists()){
                new File(directoryPath).mkdir();
            }
            herePath = directoryPath + "/Doctor.csv";
            doctorFile = new File(herePath);
            if (!doctorFile.exists()) {
                doctorFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doctorFile;
    }

    public void setConsecutiveId() {
        String line = null;
        File doctorFile = getDoctorFile();
        int countLine = 0;
        try {
            Scanner scanDoctorFile = new Scanner(doctorFile);
            while (scanDoctorFile.hasNextLine()) {
                line = scanDoctorFile.nextLine();
                countLine++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.id = line != "" && countLine > 0 ? Integer.parseInt(line.split(",")[0]) + 1 : 1;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSpeciality() {
        return this.speciality;
    }

    public void save() {
        String data = Integer.toString(this.id) + "," + this.name + "," + this.speciality;
        try {
            File file = getDoctorFile();
            FileWriter fr = new FileWriter(file, true);
            fr.write(data);
            fr.write(System.lineSeparator());
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
