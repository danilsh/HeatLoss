/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package heatloss;

import java.text.DecimalFormat;

/**
 *
 * @author Dan
 */
public class Aperture {
    /**
     * Коэффициент теплопередачи, Вт / м2 К
     */
    private final double heatTransferCoefficient;
    
    /**
     * Площадь, м2
     */
    private final double area;

    public Aperture(double heatTransferCoefficient, double area) {
        this.heatTransferCoefficient = heatTransferCoefficient;
        this.area = area;
    }
    
    /**
     * 
     * @return Коэффициент теплопередачи окна/двери * площадь, Вт К 
     */    
    public final double UxA()
    {
        return heatTransferCoefficient * area;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#.###");
        return "Окно/Дверь/Люк\n\tКоэффициент теплопередачи U = " + format.format(heatTransferCoefficient) + "\n\tПлощадь = " + format.format(area) + "\n\tUxA = " + format.format(UxA());
    }
    
    
}
