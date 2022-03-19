package com.jap.salesanalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 This class performs the file reading and stores the data in SalesRecord array
 */
public class SalesAnalysis {
    // This method reads a file and returns the count of lines in the file
    // omit any headers present in the file
    public int countOfSalesRecords(String fileName){
        int recordCount = -1;

        int count = 0;

        try {
            // create a new file object
            File file = new File("src/main/resources/purchase_details.csv");

            // create an object of Scanner
            // associated with the file
            Scanner sc = new Scanner(file);

            // read each line and
            // count number of lines
            while(sc.hasNextLine()) {
                sc.nextLine();
                recordCount++;
            }



            // close scanner
            sc.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return recordCount;
    }

    // This method reads a file and adds each line of the file into the corresponding SalesRecord object
    public SalesRecord[] readRecords(String fileName,int recordCount) throws ParseException{
        SalesRecord[] salesData = new SalesRecord[recordCount];
        int i = 0;
        String line = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            br.skip(100);
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Date date;
                try {
                    date = formatter.parse(values[0]);
                }
                catch (ParseException e){
                    throw e;
                }
                salesData[i] = new SalesRecord(date, Integer.parseInt(values[1]),
                        Integer.parseInt(values[2]), values[3], Double.parseDouble(values[4]),
                        Double.parseDouble(values[5]), Integer.parseInt(values[6]));
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salesData;
    }

    public static void main(String[] args) throws ParseException{
        SalesAnalysis salesAnalysis = new SalesAnalysis();
        // read the file purchase_details.csv
        String fileName = "src/main/resources/purchase_details.csv";
        // the count of lines in the file
        int count = 0;
        //array to hold the sales records
        SalesRecord[] salesRecords = null;

        // display the records that have been read from the file
    }
}