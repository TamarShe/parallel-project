/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportproject;

import java.awt.TextArea;
import java.io.PrintStream;
import java.util.AbstractMap;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author IMOE001
 */


public class RunWays {
    private String runwayID;
    private long lengthInMinutes;
    private Flights currentFlight;
    private Map<String,String> distancesDictionary=new HashMap<>();

    public JTextArea textArea;

    public RunWays() { }
        
    public RunWays(String runwayID,long lengthInMinutes,JTextArea textArea) {
        this.runwayID = runwayID;
        this.lengthInMinutes=lengthInMinutes;
        this.textArea=textArea;
    } 

    public void AddItemToDistancesDictionay(Directions direction,long distance){
        this.distancesDictionary.put(direction.toString(), String.valueOf(distance));
    }
    
    public String getRunwayID() {
        return runwayID;
    }

    public long getLengthInMinutes() {
        return lengthInMinutes;
    }

    public Map<String, String> getDistancesDictionary() {
        return distancesDictionary;
    }
    
    public void SetCurrentFlight(Flights currentFlight){
        this.currentFlight=currentFlight;
    }

    public Flights getCurrentFlight() {
        return currentFlight;
    }
}
