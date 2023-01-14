/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minifactorio;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

/**
 *
 * @author keega
 */
public class Player {
    Point2D position;
    Entity playerEntity;
    
    public Player() {
        position = new Point2D(0,0);
        
        playerEntity = new Entity(0, 0, 1, 1);
        
        ImageView playerView = MediaLoader.viewImage("jeremy.png");
        
        playerEntity.node = playerView;
        
        updatePosition();
    }
    
    // Returns true if the player moved, false if the player could not move with the given vector.
    public boolean move(Point2D vector) {
        if (Math.abs(vector.getX()) > 0 && Math.abs(vector.getY()) > 0)
            return false; // Cannot move in two directions at once
        
        if (vector.magnitude() < 1)
            return false; // Cannot move less than one full tile
        
        Point2D newPosition = position.add(vector);
        Rectangle2D newRect = new Rectangle2D(
                newPosition.getX(),
                newPosition.getY(),
                playerEntity.rect.getWidth(),
                playerEntity.rect.getHeight()
        );
        
        if (newPosition.getX() < 0 || newPosition.getY() < 0 || newPosition.getX() >= 10 || newPosition.getY() >= 8)
            return false; // Out of world bounds
        
        Environment curEnv = MiniFactorio.world.getCurEnvironment();
        ArrayList<Entity> contents = curEnv.contents;
        
        // Check collisions
        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i) == playerEntity)
                continue;
            if (Physics.checkGridCollision(newRect, contents.get(i).rect)) {
                //System.out.println("Player collided with " + contents.get(i));
                return false; // Collided with an object
            }/*
            else {
                System.out.println("Player:" + playerEntity.rect);
                System.out.println("Coll:" + contents.get(i).rect);
            }*/
        }
        
        position = newPosition;
        
        updatePosition();
        
        return true;
    }
    
    public void updatePosition() {
        Point2D pixelPosition = Graphics.pixelPosition(position);
        ((ImageView)playerEntity.node).setX(pixelPosition.getX());
        ((ImageView)playerEntity.node).setY(pixelPosition.getY());
        
        playerEntity.rect = new Rectangle2D(
                position.getX(),
                position.getY(),
                playerEntity.rect.getWidth(),
                playerEntity.rect.getHeight()
        );
    }
}
