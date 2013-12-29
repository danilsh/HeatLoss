/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package heatloss;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author Dan
 */
public class FXMLDocumentController implements Initializable, ChangeListener<TreeItem> {   
    @FXML
    private TreeView buildingObjsTree;
    
    // Командные кнопки
    @FXML
    private Button addZone;
    @FXML
    private Button deleteZone;
    @FXML
    private Button addCladding;
    
    // Свойства
    @FXML
    private HBox nameBox;   
    @FXML
    private TextField name;
    
    private void FillTreeView(Building building)
    {
        TreeItem bti = new TreeItem(building);
        buildingObjsTree.setRoot(bti);
        for(Zone z: building.getZones())
        {
            TreeItem zti = new TreeItem(z);
            bti.getChildren().add(zti);
            for(Cladding c: z.getCladdings())
            {
                TreeItem cti = new TreeItem(c);
                zti.getChildren().add(cti);
                for(Aperture a: c.getApertures())
                {
                    TreeItem g = new TreeItem("Окна/Проемы/Люки");
                    cti.getChildren().add(g);
                    TreeItem ati = new TreeItem(a);
                    g.getChildren().add(ati);
                }
                for(WallPart wp: c.getWallParts())
                {
                    TreeItem g = new TreeItem("Элементы стен/Перекрытий");
                    cti.getChildren().add(g);
                    TreeItem wpti = new TreeItem(wp);
                    g.getChildren().add(wpti);
                    for(WallLayer wl: wp.getWallLayers())
                    {
                        TreeItem wlti = new TreeItem(wl);
                        wpti.getChildren().add(wlti);        
                    }
                }
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildingObjsTree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);  
        buildingObjsTree.getSelectionModel().selectedItemProperty().addListener(this);
        addZone.managedProperty().bind(addZone.visibleProperty());
        deleteZone.managedProperty().bind(deleteZone.visibleProperty());
        addCladding.managedProperty().bind(addCladding.visibleProperty());
        
        Building building = new Building(-28.0, 20.0);
        Zone zone = new Zone(72.0, 2.0);
        building.getZones().add(zone);
        
        Cladding cladding = new Cladding();
        zone.getCladdings().add(cladding);
        Aperture aperture = new Aperture(1.2, 3.0);
        cladding.getApertures().add(aperture);
        WallPart wallPart = new WallPart(18.0);
        cladding.getWallParts().add(wallPart);
        WallLayer wallLayer = new WallLayer(0.12, 0.375);
        wallPart.getWallLayers().add(wallLayer);
        
        FillTreeView(building);
    }    

    @Override
    public void changed(ObservableValue<? extends TreeItem> ov, TreeItem t, TreeItem t1) {
        if(t1.getValue().getClass().equals(Building.class))
        {
            addZone.setVisible(true);
            deleteZone.setVisible(false);
            addCladding.setVisible(false);
            
            nameBox.setVisible(true);
        }
        else if(t1.getValue().getClass().equals(Zone.class))
        {
            addZone.setVisible(false);
            deleteZone.setVisible(true);
            addCladding.setVisible(true);
            
            nameBox.setVisible(true);
        }
        else
        {
            addZone.setVisible(false);
            deleteZone.setVisible(false);
            addCladding.setVisible(false);
            
            nameBox.setVisible(false);
        }
    }
    
}
