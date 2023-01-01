/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportproject;

import java.awt.Color;
import java.awt.TextArea;
import java.io.OutputStream;
import java.io.PrintStream;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 *
 * @author IMOE001
 */
public class Flights extends Thread{
    private String flightName;
    private AirPort airPort;
    private Directions direction;
    private Action action;
    private RunWays ClosestRunWay;
    
    static PrintStream waitingFlightsTextArea;
    static PrintStream finishedFlightsTextArea;
    

    public Flights(String flightName, Directions direction, Action action,AirPort airPort) {
        this.flightName=flightName;
        this.direction = direction;
        this.action = action;
        this.airPort=airPort;
        this.ClosestRunWay=airPort.FindClosestRunWay(direction);
    }
    
    public String getFlightName() {
        return flightName;
    }
    
    public void run(){
        try {
           System.setOut(waitingFlightsTextArea);
           System.out.println("flight "+flightName+" wants to "+ action +" in runWay "+ClosestRunWay.getRunwayID()); 

            this.ClosestRunWay=airPort.FindClosestRunWay(direction);
                
        synchronized(this.ClosestRunWay){
            if (ClosestRunWay.textArea.getForeground()==Color.BLUE){
                ClosestRunWay.textArea.setForeground(Color.BLACK);
            }
            else{
                ClosestRunWay.textArea.setForeground(Color.BLUE);
            }
            
            long length=ClosestRunWay.getLengthInMinutes();
            while(length>0)
            {
                ClosestRunWay.textArea.setText("  flight "+flightName +" now "+action+" for "+TimeUnit.MILLISECONDS.toSeconds(length)+" seconds");                    
                sleep(1000);
                length-=1000;
            }
            ClosestRunWay.textArea.setText("");
            this.airPort.removeFlight(this);
        }
        
        System.setOut(finishedFlightsTextArea);
        System.out.println("flight "+flightName+" finished");
        } 
        
        catch (InterruptedException ex) {
            Logger.getLogger(Flights.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
}
