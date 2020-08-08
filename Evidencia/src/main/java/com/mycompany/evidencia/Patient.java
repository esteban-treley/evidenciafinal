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
public class Patient {

    //Atributes
    public int id;
    public String name;

    private String fileName = "Patient.csv";

    //Constructors
    public Patient() {
    }

    public Patient(String name) {
        this.name = name;
    }

    public Patient(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //Methods
    public void setName(String name) {
        this.name = name;
    }

    public static File getPatientFile() {
        File here = new File(".");
        File patientFile = null;

        try {
            String herePath, directoryPath;
            
            directoryPath = here.getCanonicalPath() + "/db";
            if(!new File(directoryPath).exists()){
                new File(directoryPath).mkdir();
            }
            herePath = directoryPath + "/Patient.csv";
            patientFile = new File(herePath);
            if (!patientFile.exists()) {
                patientFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return patientFile;
    }

    public void setConsecutiveId() {

        String line = null;
        File patientFile = getPatientFile();
        int countLine = 0;
        try {
            Scanner scanPatientFile = new Scanner(patientFile);
            while (scanPatientFile.hasNextLine()) {
                line = scanPatientFile.nextLine();
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

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void save() {
        String data = Integer.toString(this.id) + "," + this.name;
        try {
            File file = getPatientFile();
            FileWriter fr = new FileWriter(file, true);
            fr.write(data);
            fr.write(System.lineSeparator());
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
