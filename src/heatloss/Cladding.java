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
public class Cladding {

    private final ArrayList<Aperture> apertures = new ArrayList<>();

    /**
     * Get the value of apertures
     *
     * @return the value of apertures
     */
    public ArrayList<Aperture> getApertures() {
        return apertures;
    }
    
    private final ArrayList<WallPart> wallParts = new ArrayList<>();

    /**
     * Get the value of wallParts
     *
     * @return the value of wallParts
     */
    public ArrayList<WallPart> getWallParts() {
        return wallParts;
    }
    
    /**
     * 
     * @return Общий коэффициент теплопередачи ограждающей конструкции * площадь, Вт К 
     */    
    public final double UxA()
    {
        double UxA = 0.0;
        
        UxA = apertures.stream().map((a) -> a.UxA()).reduce(UxA, (accumulator, _item) -> accumulator + _item);
        UxA = wallParts.stream().map((wp) -> wp.UxA()).reduce(UxA, (accumulator, _item) -> accumulator + _item);
        
        return UxA;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#.###");
        return "Ограждающая конструкция\n\tUxA = " + format.format(UxA());
    }
    
    
}
