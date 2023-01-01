/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportproject;

import static java.lang.Thread.sleep;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author IMOE001
 */
public class AirPort{
    
    private String name;
    public ArrayList<RunWays> runWays;
    private Queue<Flights> flights;
    
    public AirPort(String name) {
        this.name = name;
        this.flights=new LinkedList<>();
        this.runWays=new ArrayList<>();
    }
    
    public void AddRunWay(RunWays newRunWay){
        this.runWays.add(newRunWay);        
    }
    
    public void AddFlight(Flights newFlight){
        this.flights.add(newFlight);
        newFlight.start();
    }
    
    public RunWays FindClosestRunWay(Directions direction){     
        long minDistance=runWays.get(0).getLengthInMinutes(),currentDistance;
        String a;
        RunWays minDistanceRunWay=new RunWays();
        
        for (RunWays runWay : runWays) {
            if(runWay.getCurrentFlight()==null){
                a=runWay.getDistancesDictionary().get(direction.toString()); 
                currentDistance=Long.parseUnsignedLong(a);
                minDistance=Math.min(minDistance, currentDistance);
                if(minDistance==currentDistance)
                    minDistanceRunWay=runWay;
            }
        }
        
        if(minDistanceRunWay==null){
            for (RunWays runWay : runWays) {
                a=runWay.getDistancesDictionary().get(direction.toString()); 
                currentDistance=Long.parseUnsignedLong(a);
                minDistance=Math.min(minDistance, currentDistance);
                if(minDistance==currentDistance)
                    minDistanceRunWay=runWay;
            }
        }
        return minDistanceRunWay;
    }

    public Queue<Flights> getFlights() {
        return flights;
    }
    
    public void removeFlight(Flights f)
    {
        this.flights.remove(f);
    }
}
