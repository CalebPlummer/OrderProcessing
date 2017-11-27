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
    private final double tax = .2994;
    private final double shipping = .7485;
    private File input;
    private File output;
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
        try(BufferedReader br = new BufferedReader(new FileReader(input)))
        {
            br.readLine();
            for(int i = 0; true; i++)
            {
                if(br.ready())
                {
                    String[] order = br.readLine().split("\\|");
                    System.out.println("Order ID: " + order[0]);
                    System.out.println("Part Num: " + order[1]);
                    System.out.println("Price: " + order[2]);
                    System.out.println("Quantity: " + order[3]);
                    System.out.println("Tax: " + tax);
                    System.out.println("Shipping: " + shipping);
                    System.out.println("\n");
                }
                else
                {
                   break; 
                }
                
            }
        }
        catch(IOException e)
        {
            System.out.println("File could not be read from, or written to.");
        }
        catch(Exception e)
        {
            System.out.println("Error reading file.");
        }
        
    }
}
