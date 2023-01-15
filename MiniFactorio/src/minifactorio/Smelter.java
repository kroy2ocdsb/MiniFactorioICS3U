/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minifactorio;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 *
 * @author keega
 */
public class Smelter extends Entity {
    
    public Smelter(Point2D _position) {
        super((int)_position.getX(), (int)_position.getY(), 2,2);
        
        node = MediaLoader.viewImage("building2.png");
        Graphics.positionAt((ImageView)node, Graphics.pixelPosition(_position));
        
        Point2D ironTilePos = new Point2D(rect.getMinX(), rect.getMinY() + 2);
        Point2D copperTilePos = new Point2D(rect.getMinX() + 1, rect.getMinY() + 2);
        
        ImageView ironTileV = Graphics.positionAt(MediaLoader.viewImage("smeltIron1.png"), Graphics.pixelPosition(ironTilePos));
        ImageView copperTileV = Graphics.positionAt(MediaLoader.viewImage("smeltCopper1.png"), Graphics.pixelPosition(copperTilePos));
        
        SmelterTile ironTile = new SmelterTile(Graphics.fromPoint(ironTilePos), this, "iron");
        ironTile.node = ironTileV;
        SmelterTile copperTile = new SmelterTile(Graphics.fromPoint(copperTilePos), this, "copper");
        copperTile.node = copperTileV;
        
        
        Entity[][] grid = MiniFactorio.world.getCurEnvironment().grid;
        Entity oldIronTile = grid[(int)ironTilePos.getX()][(int)ironTilePos.getY()];
        Entity oldCopperTile = grid[(int)copperTilePos.getX()][(int)copperTilePos.getY()];
        
        Graphics.replaceTile(oldIronTile, ironTile, ironTilePos);
        Graphics.replaceTile(oldCopperTile, copperTile, copperTilePos);
        
        Graphics.addEntity(this);
        MiniFactorio.world.getCurEnvironment().contents.add(this);
    }
}
