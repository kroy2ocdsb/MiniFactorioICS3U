/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minifactorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
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
    Entity interactingBuilding = null;
    String buildingSlot = "";
    
    HashMap<String, Integer> inventory;
    
    public Player() {
        super(0, 0, 1, 1);
        
        position = new Point2D(0,0);
        inventory = new HashMap<String, Integer>();
        inventory.put("ironOre",0);
        inventory.put("copperOre",0);
        inventory.put("ironBar",0);
        inventory.put("copperBar",0);
        inventory.put("circuit",0);
        
        ImageView playerView = MediaLoader.viewImage("jeremy.png");
        
        node = playerView;
        
        updatePosition();
        updateInventoryText();
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
            if (contents.get(i) == this || contents.get(i) instanceof SmelterTile)
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
        
        updateTooltip();
    }
    
    public void updateTooltip() {
        // Check for ores to mine
        Entity tileOn = MiniFactorio.world.getCurEnvironment().grid[(int)rect.getMinX()][(int)rect.getMinY()];
        
        interactingBuilding = null;
        buildingSlot = "";
        oreOn = "";
        
        if (tileOn instanceof Ore) {
            Graphics.updateBottomBar("Press M to mine " + ((Ore)tileOn).oreType + "!");
            oreOn = ((Ore)tileOn).oreType + "Ore";
        }
        // Check for buildings to interact with
        else if (tileOn instanceof SmelterTile) {
            String slot = ((SmelterTile) tileOn).slot;
            
            interactingBuilding = null;
            buildingSlot = "";
            
            if (inventory.get(slot + "Ore") > 0) {
                Graphics.updateBottomBar("Press E to smelt " + slot + " ore into a bar!");
                
                interactingBuilding = ((SmelterTile) tileOn).smelter;
                buildingSlot = slot;
            }
            else {
                Graphics.updateBottomBar("You need more " + slot + " ore to do this!");
            }
        }
        else if (tileOn instanceof CircuitAssemblerTile) {
            interactingBuilding = null;
            
            if (inventory.get("ironBar") >= 1 && inventory.get("copperBar") >= 2) {
                Graphics.updateBottomBar("Press C to turn 1 iron bar and 2 copper bars into 1 circuit!");
                
                interactingBuilding = ((CircuitAssemblerTile) tileOn).assembler;
            }
            else {
                Graphics.updateBottomBar("You need 1 iron bar and 2 copper bars to do this!");
            }
        }
        else if (tileOn instanceof UnlockingTile) {
            interactingBuilding = null;
            
            Unlockable unlockable = ((UnlockingTile) tileOn).unlockable;
            
            if (unlockable.meetsReqs(inventory)) {
                Graphics.updateBottomBar(unlockable.buildTooltip());
                
                interactingBuilding = unlockable;
            }
            else {
                Graphics.updateBottomBar(unlockable.collectTooltip());
            }
        }
        // No tooltip to display
        else {
            Graphics.updateBottomBar("");
        }
    }
    
    public void mine() {
        if (oreOn != "") {
            inventory.put(oreOn,inventory.get(oreOn) + 1);
            updateInventoryText();
        }
    }
     
    public void smelt() {
        if (interactingBuilding != null && buildingSlot != "") {
            String ore = buildingSlot + "Ore";
            String bar = buildingSlot + "Bar";
            
            if (inventory.get(ore) > 0) {
                inventory.put(ore, inventory.get(ore) - 1);
                inventory.put(bar, inventory.get(bar) + 1);
                
                updateInventoryText();
                updateTooltip();
            }
        }
    }
    
    public void craftCircuit() {
        if (interactingBuilding != null && buildingSlot == "") {
            if (inventory.get("ironBar") >= 1 && inventory.get("copperBar") >= 2) {
                inventory.put("ironBar", inventory.get("ironBar") - 1);
                inventory.put("copperBar", inventory.get("copperBar") - 2);
                
                inventory.put("circuit", inventory.get("circuit") + 1);
                
                updateInventoryText();
                updateTooltip();
            }
        }
    }
    
    public void updateInventoryText() {
        Graphics.updateTopBar(String.format(
                    "Iron Ore: %s Copper Ore: %s\nIron Bar: %s Copper Bar: %s Circuit: %s",
                    Graphics.trailingSpaces(inventory.get("ironOre").toString(), 5),
                    inventory.get("copperOre").toString(),
                    Graphics.trailingSpaces(inventory.get("ironBar").toString(), 5),
                    Graphics.trailingSpaces(inventory.get("copperBar").toString(), 5),
                    inventory.get("circuit").toString()
            ));
    }
    
    public String inventorySaveContent() {
        String saveContent = "";
        
        String[] keys = (((Set<String>)inventory.keySet()).toArray(new String[inventory.size()]));
        
        for (int i = 0; i < inventory.size(); i++) {
            String item = keys[i];
            int count = inventory.get(item);
            
            saveContent += String.format("%s:%d", item, count);
            
            if (i < inventory.size()-1)
                saveContent += "\n";
        }
        
        return saveContent;
    }
}
