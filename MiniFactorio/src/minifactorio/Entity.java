/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minifactorio;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;


/**
 *
 * @author keega
 */
public class Entity {
    public Rectangle2D rect; // Rectangle in grid tile units to describe the position and size of this entity
    
    public Node node;
    
    public Entity(Rectangle2D _rect) {
        rect = _rect;
    }
    
    public Entity(int x, int y, int width, int height) {
        this(new Rectangle2D(x, y, width, height));
    }
    
    public Entity() {
        this(new Rectangle2D(0, 0, 0, 0));
    }
    
    public void setNode(Node _node) {
        node = _node;
    }
}
