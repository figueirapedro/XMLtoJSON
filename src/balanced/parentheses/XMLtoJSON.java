/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balanced.parentheses;

import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;// Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*;

/**
 *
 * @author Escola
 */
public class XMLtoJSON {

    /**
     * @param args the command line arguments
     */
    
    public static String validarLinha(String x) {
        String text = "";
        
        if(x.matches("^<[A-Z]+>$")){
            text = x.replace("<", "{\"");
            
            text = x.contains("CATALOG") ? text.replace(">", "\":[\n") : text.replace(">", "\":{\n");
        }
        

        if(x.matches("<[A-Z]+>.*<\\/[A-Z]+>")){
            text = x.replaceFirst("<", "\"").replaceFirst(">", "\":\"").replaceFirst("<\\/[A-Z]+>", "\",\n");
        }
        
        if(x.matches("<\\/[A-Z]+>")){
            text = 
            
            text = x.contains("/CATALOG") ? x.replaceFirst("<\\/[A-Z]+>", "]\n}") : x.replaceFirst("<\\/[A-Z]+>", "},\n},\n");
        }

        return text;
    }

    public static void main(String[] args) {
        String txt = "";

        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                txt = txt + validarLinha(myReader.nextLine());
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred reading.");
            e.printStackTrace();
        }

        try {
            File myObj = new File("output.txt");
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter("output.txt");
            myWriter.write(txt);
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred writing.");
            e.printStackTrace();
        }

    }
}
