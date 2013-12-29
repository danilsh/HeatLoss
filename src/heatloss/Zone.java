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
public class Zone {

    private final ArrayList<Cladding> claddings = new ArrayList<>();

    /**
     * Get the value of claddings
     *
     * @return the value of claddings
     */
    public ArrayList<Cladding> getCladdings() {
        return claddings;
    }

    /**
     * Объем зоны, м3
     */
    private final double zoneVolume;
    
    /**
     * Количество воздушных объемов зоны, оборачиваемых за час через вентиляцию
     */
    private final double airExchange;

    public Zone(double zoneVolume, double airExchange) {
        this.zoneVolume = zoneVolume;
        this.airExchange = airExchange;
    }

    /**
     * Удельная теплоемкость 1м3 воздуха при постоянном давлении, КДж / (Кг * К)
     * (1 Дж = 1 Вт * с)
     */
    protected static final double Cp = 1.0;
    
    /**
     * Вес 1 м3 воздуха при температуре 20 C
     */
    protected static final double p = 1.20;
    
    /**
     * 
     * @return Общий коэффициент теплопередачи всех ограждающих конструкции зоны * их площадь,
     * плюс затраты на оборот воздуха в помещениях, Вт К 
     */    
    public final double UxA()
    {
        double UxA = zoneVolume * Cp * p * airExchange * 1000.0 / 3600.0;
        
        UxA = claddings.stream().map((ew) -> ew.UxA()).reduce(UxA, (accumulator, _item) -> accumulator + _item);

        return UxA;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#.###");
        return "Зона\n\tОбъем = " + format.format(zoneVolume) + "\n\tЧасовой оборот воздуха = " + format.format(airExchange) + "\n\tUxA = " + format.format(UxA());
    }
    
    
}
