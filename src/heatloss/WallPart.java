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
public class WallPart {

    private final ArrayList<WallLayer> wallLayers = new ArrayList<>();

    /**
     * Get the value of wallLayers
     *
     * @return the value of wallLayers
     */
    public ArrayList<WallLayer> getWallLayers() {
        return wallLayers;
    }
   
    /**
     * Площадь, м2
     */
    private final double area;

    public WallPart(double area) {
        this.area = area;
    }
    
    /**
     * 
     * @return Коэффициент теплопередачи многослойной стены * площадь, Вт К 
     */
    public final double UxA()
    {
        double R = 0.0;
        // R += wallLayers[i].R();
        R = wallLayers.stream().map((l) -> l.R()).reduce(R, (accumulator, _item) -> accumulator + _item);
        
        return 1.0 / R * area;
    }

    @Override
    public String toString() {
       DecimalFormat format = new DecimalFormat("#.###");
       return "Элемент стены/Перекрытия\n\tПлощадь = " + format.format(area) + "\n\tUxA = " + format.format(UxA());
    }
    
    
}
