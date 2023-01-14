/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minifactorio;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

/**
 *
 * @author keega
 */
public class Player extends Entity {
    Point2D position;
    String oreOn = "";
    HashMap<String, Integer> inventory;
    
    public Player() {
        super(0, 0, 1, 1);
        
        position = new Point2D(0,0);
        inventory = new HashMap<String, Integer>();
        inventory.put("iron",0);
        inventory.put("copper",0);
        inventory.put("circuit",0);
        
        ImageView playerView = MediaLoader.viewImage("jeremy.png");
        
        node = playerView;
        
        updatePosition();
    }
    
    // Returns true if the player moved, false if the player could not move with the given vector.
    public boolean move(Point2D vector) {
        if (Math.abs(vector.getX()) > 0 && Math.abs(vector.getY()) > 0)
            return false; // Cannot move in two directions at once
        
        if (vector.magnitude() < 1)
            return false; // Cannot move less than one full tile
        
        Point2D newPosition = position.add(vector);
        Rectangle2D newRect = Graphics.newRectSize(rect, newPosition);
        
        Environment curEnv = MiniFactorio.world.getCurEnvironment();
        ArrayList<Entity> contents = curEnv.contents;
        
        if (newPosition.getX() < 0 || newPosition.getY() < 0 || newPosition.getX() >= curEnv.grid.length || newPosition.getY() >= curEnv.grid[0].length)
            return false; // Out of world bounds
        
        // Check collisions
        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i) == this)
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
        ((ImageView)node).setX(pixelPosition.getX());
        ((ImageView)node).setY(pixelPosition.getY());
        
        rect = Graphics.newRectSize(rect, position);
        
        // Check for ores to mine
        Entity tileOn = MiniFactorio.world.getCurEnvironment().grid[(int)rect.getMinX()][(int)rect.getMinY()];
        
        oreOn = "";
        
        if (tileOn instanceof Ore) {
            Graphics.updateBottomBar("Press M to mine " + ((Ore)tileOn).oreType + "!");
            oreOn = ((Ore)tileOn).oreType;
        }
        // Check for buildings to interact with
        else {
            Graphics.updateBottomBar("");
        }
    }
    
    public void mine() {
        if (oreOn != "") {
            inventory.put(oreOn,inventory.get(oreOn) + 1);
            
            Graphics.updateTopBar(String.format(
                    "Iron: %s Copper: %s Circuit: %s",
                    Graphics.trailingSpaces(inventory.get("iron").toString(), 5),
                    Graphics.trailingSpaces(inventory.get("copper").toString(), 5),
                    inventory.get("circuit").toString()
            ));
        }
    }
}
