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
public class WallLayer {
    /**
     * Коэффициент теплопроводности, Вт / м К
     */
    private final double thermalConductivity;
    
    /**
     * Толщина, м
     */
    private final double layerThickness;

    public WallLayer(double thermalConductivity, double layerThickness) {
        this.thermalConductivity = thermalConductivity;
        this.layerThickness = layerThickness;
    }

    /**
     * 
     * @return Теплосопротивление слоя стены, м2 К / Вт
     */
    public final double R()
    {
        return layerThickness / thermalConductivity;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#.###");
        return "Материал многослойной конструкции\n\tТеплопроводность = " + format.format(thermalConductivity) + "\n\tТолщина = " + format.format(layerThickness) + "\n\tR = " + format.format(R());
    }
    
    
}
