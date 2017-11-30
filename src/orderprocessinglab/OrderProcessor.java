/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderprocessinglab;

import java.io.*;

/**
 *
 * @author cplummer
 */
public class OrderProcessor {
    private final double tax = .02;
    private final double shipping = .05;
    private File input;
    private File output;
    private String[] order;
    public OrderProcessor()
    {
        input = new File("Orders.txt");
        output = new File("OrdersProcessed.txt");
    };
    /**
     * Overloaded constructor for the OrderProcessor class.
     * @param inputFile The file used to input the orders.
     * @param outputFile The file name the processed orders should be saved to.
     */
    public OrderProcessor(File inputFile, File outputFile)
    {
        input = inputFile;
        output = outputFile;
    };
    
    /**
     * Reads the file
     */
    public void readFile()
    {
        System.out.println("Starting to process file!\n");
        try(BufferedReader br = new BufferedReader(new FileReader(input)))
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            br.readLine();//Reads and ignores the header line.
            for(int i = 0; true; i++)
            {
                if(br.ready())//Checks if the next line to be read is empty. 
                {
                    order = br.readLine().split("\\|");//Seperates the line by each pipe and gives it a seperate index in the array.
                    System.out.println("Order ID: " + order[0]);
                    System.out.println("Part Num: " + order[1]);
                    System.out.println("Price: " + order[2]);
                    System.out.println("Quantity: " + order[3]);
                    System.out.println("Tax: " + calculate("Tax"));
                    System.out.println("Shipping: " + calculate("Shipping"));
                    System.out.println("Total: " + calculate("Total"));
                    System.out.println("\n");
                    writeToFile(writer);
                }
                else
                {
                   break;//Ends the for loop if the next line is empty. 
                } 
            }
            writer.close();//Closes the writer and saves the file.
            System.out.println("Finished processing file!");
        }
        catch(IOException e)
        {
            System.out.println("File could not be read from, or written to. Please make sure the file is present and in the correct location");
        }
        catch(Exception e)
        {
            System.out.println("Error reading file.");
        }
        
    }
    
    /**
     * Calculates shipping, tax, and total for the order.
     * @param whatToCalculate Tells the method what to calculate for.
     * @return 
     */
    public double calculate(String whatToCalculate)
    {
        if(whatToCalculate.equals("Tax"))
        {
            return (Double.parseDouble(order[2]) * tax * Integer.parseInt(order[3]));
        }
        else if(whatToCalculate.equals("Shipping"))
        {
            return (Double.parseDouble(order[2]) * shipping * Integer.parseInt(order[3]));
        }
        else if(whatToCalculate.equals("Total"))
        {
            return ((Double.parseDouble(order[2]) * tax * Integer.parseInt(order[3])) + (Double.parseDouble(order[2]) * shipping * Integer.parseInt(order[3])) + ((Double.parseDouble(order[2])*(Double.parseDouble(order[3])))));
        }
        else
        {
          return -1;  
        } 
    }
    
    /**
     * Writes each order to the file.
     * @param writer The writer for the orders.
     */
    public void writeToFile(BufferedWriter writer)
    {
        try
        {
                    writer.write("Order ID: " + order[0]);
                    writer.write("\n");
                    writer.write("Part Num: " + order[1]);
                    writer.write("\n");
                    writer.write("Price: " + order[2]);
                    writer.write("\n");
                    writer.write("Quantity: " + order[3]);
                    writer.write("\n");
                    writer.write("Tax: " + (Double.parseDouble(order[2]) * tax * Integer.parseInt(order[3])));
                    writer.write("\n");
                    writer.write("Shipping: " + (Double.parseDouble(order[2]) * shipping * Integer.parseInt(order[3])));
                    writer.write("\n");
                    writer.write("Total: " + ((Double.parseDouble(order[2]) * tax * Integer.parseInt(order[3])) + (Double.parseDouble(order[2]) * shipping * Integer.parseInt(order[3])) + ((Double.parseDouble(order[2])*(Double.parseDouble(order[3]))))));
                    writer.write("\n");
                    writer.write("\n");
                    writer.write("-----------------------------");//Seperates each order from the last and the next.
                    writer.write("\n");
                    writer.write("\n");
        }
        catch(IOException e)
        {
            System.out.println("Error writing to file. Is the HDD full?");
        }
    }
}
