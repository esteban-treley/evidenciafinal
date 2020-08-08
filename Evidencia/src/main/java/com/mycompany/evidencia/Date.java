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
public class Date {

    //Atributes
    public int id;
    public String date;
    public String time;
    public String motive;

    private String fileName = "Date.csv";

    //Constructors
    public Date() {
    }

    public Date(String date, String time, String motive) {
        this.date = date;
        this.time = time;
        this.motive = motive;
    }

    //Methods
    public static File getDateFile() {
        File here = new File(".");
        File dateFile = null;

        try {
            String herePath;
            herePath = here.getCanonicalPath() + "/Date.csv";
            dateFile = new File(herePath);
            if (!dateFile.exists()) {
                dateFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateFile;
    }

    public boolean validateDate(String date) {
        if (date.matches("^[0-3][0-9]\\/[0-1][0-9]\\/\\d{2}$")) {
            String[] matches = date.split("/");
            int day = Integer.parseInt(matches[0]);
            int month = Integer.parseInt(matches[1]);
            int year = Integer.parseInt(matches[2]);
            if(month > 12 || day > 31){
                return false;
            }
            if (year % 4 != 0) {
                if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12) {
                    if (day <= 31) {
                        return true;
                    }
                } else if (month == 2) {
                    if (day <= 28) {
                        return true;
                    }
                } else {
                    if (day <= 30) {
                        return true;
                    }
                }
            }
            if (month != 2) {
                if (day <= 29) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validateTime(String time) {
        if (time.matches("^[0-2][0-4]\\:[0-5][0-9]$")) {
            return true;
        }
        return false;
    }

    public void setConsecutiveId() {

        String line = null;
        File dateFile = getDateFile();
        int countLine = 0;
        try {
            Scanner scanDateFile = new Scanner(dateFile);
            while (scanDateFile.hasNextLine()) {
                line = scanDateFile.nextLine();
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public int getId() {
        return this.id;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public String getMotive() {
        return this.motive;
    }

    public void save() {
        String data = Integer.toString(this.id) + "," + this.date + "," +this.time+"," + this.motive;
        try {
            File file = getDateFile();
            FileWriter fr = new FileWriter(file, true);
            fr.write(data);
            fr.write(System.lineSeparator());
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
