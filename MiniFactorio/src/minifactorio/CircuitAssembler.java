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
public class CircuitAssembler extends Entity {
    public CircuitAssembler(Point2D _position) {
        super((int)_position.getX(), (int)_position.getY(), 1, 1);
        
        node = MediaLoader.viewImage("circuitAssembler1.png");
        Graphics.positionAt((ImageView)node, Graphics.pixelPosition(_position));
        
        Point2D craftTilePos = new Point2D(rect.getMinX(), rect.getMinY() + 1);
        
        ImageView craftTileV = Graphics.positionAt(MediaLoader.viewImage("craftCircuit4.png"), Graphics.pixelPosition(craftTilePos));
        
        CircuitAssemblerTile craftTile = new CircuitAssemblerTile(Graphics.fromPoint(craftTilePos), this);
        craftTile.node = craftTileV;
        
        Entity[][] grid = MiniFactorio.world.getCurEnvironment().grid;
        Entity oldIronTile = grid[(int)craftTilePos.getX()][(int)craftTilePos.getY()];
        
        Graphics.replaceTile(oldIronTile, craftTile, craftTilePos);
        
        Graphics.addEntity(this);
        MiniFactorio.world.getCurEnvironment().contents.add(this);
    }
}
