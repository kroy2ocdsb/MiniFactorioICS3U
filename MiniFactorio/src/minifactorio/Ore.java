/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minifactorio;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

/**
 *
 * @author keega
 */
public class Ore extends Entity {
    String oreType;
    
    public Ore(String _oreType, Point2D _position) {
        // Make ore
        super((int)_position.getX(), (int)_position.getY(), 1, 1);
        
        oreType = _oreType;
        
        node = Graphics.positionAt(MediaLoader.viewImage(oreType + "1.png"), Graphics.pixelPosition(new Point2D(rect.getMinX(), rect.getMinY())));
        
        // Replace grass tile with new
        Entity[][] grid = MiniFactorio.world.getCurEnvironment().grid;
        Entity oldTile = grid[(int)rect.getMinX()][(int)rect.getMinY()];
        
        Graphics.replaceTile(oldTile, this, new Point2D(_position.getX(), _position.getY()));
        // Remove old tile
        /*
        
        Graphics.removeEntity(grid[(int)rect.getMinX()][(int)rect.getMinY()]);
        
        // Add ore to grid
        grid[(int)rect.getMinX()][(int)rect.getMinY()] = this;
        
        Graphics.addEntity(this);*/
    }
}
