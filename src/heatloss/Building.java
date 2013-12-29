/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package heatloss;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Dan
 */
public final class Building {

    private final ArrayList<Zone> zones = new ArrayList<>();

    /**
     * Get the value of zones
     *
     * @return the value of zones
     */
    public ArrayList<Zone> getZones() {
        return zones;
    }

    /**
     * Температура наружного воздуха, С
     */
    private final double externalTemperature;  
    
    /**
     * Температура внутреннего воздуха, С
     */
    private final double internalTemperature;

    public Building(double externalTemperature, double internalTemperature) {
        this.externalTemperature = externalTemperature;
        this.internalTemperature = internalTemperature;
    }
    
    /**
     * 
     * @return Теплопотери здания, Вт
     */
    public final double HeatLoss()
    {
        double P = 0.0;
        P = (internalTemperature - externalTemperature) * zones.stream().map((z) -> z.UxA()).reduce(P, (accumulator, _item) -> accumulator + _item);
        
        return P;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#.###");
        return "Здание\n\tВнешняя температура = " + format.format(externalTemperature) + "\n\tВнутренняя температура = " + format.format(internalTemperature) + "\n\tP = " + format.format(HeatLoss());
    }
    
    
}
