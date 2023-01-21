/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minifactorio;

import java.util.HashMap;
import java.util.Set;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 *
 * @author keega
 */
public class Unlockable extends Entity {
    public String buildingName;
    public HashMap<String, Integer> requirements;
    
    public Unlockable(String _buildingName, Point2D _position, HashMap<String, Integer> _requirements) {
        super(Graphics.fromPoint(Graphics.pixelPosition(_position)));
        
        buildingName = _buildingName;
        requirements = _requirements;
        
        ImageView tileImage = MediaLoader.viewImage("circuitAssembler1.png");
        Graphics.positionAt((ImageView)tileImage, Graphics.pixelPosition(_position));
        
        UnlockingTile tile = new UnlockingTile(Graphics.fromPoint(Graphics.pixelPosition(_position)), this);
        tile.setNode(tileImage);
        
        Entity[][] grid = MiniFactorio.world.getCurEnvironment().grid;
        Entity oldTile = grid[(int)_position.getX()][(int)_position.getY()];
        
        Graphics.replaceTile(oldTile, tile, _position);
        
        //Graphics.addEntity(tile);
        MiniFactorio.world.getCurEnvironment().contents.add(tile);
    }
    
    public String reqText() {
        String reqs = "";
        String[] keys = ((String[])((Set<String>)requirements.keySet()).toArray());
        
        for (int i = 0; i < requirements.size(); i++) {
            String item = keys[i];
            int req = requirements.get(item);
            
            if (i == requirements.size() - 1)
                reqs += ", and " + req + " " + item;
            else
                reqs += ", " + req + " " + item;
        }
        
        return reqs;
    }
    
    public String collectTooltip() {
        return String.format("Collect %s to unlock a %s here!", reqText(), buildingName);
    }
    
    public String buildTooltip() {
        return String.format("Press T to unlock a %s here!", buildingName);
    }
    
    public boolean meetsReqs(HashMap<String, Integer> inventory) {
        String[] keys = (((Set<String>)inventory.keySet()).toArray(new String[inventory.size()]));
        
        for (int i = 0; i < inventory.size(); i++) {
            String item = keys[i];
            int req = inventory.get(item);
            
            if (inventory.get(item) < req)
                return false;
        }
        
        return true;
    }
}
