/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minifactorio;

import javafx.geometry.Rectangle2D;

/**
 *
 * @author keega
 */
public class Physics {
    public static boolean checkGridCollision(Rectangle2D a, Rectangle2D b) {
        return a.intersects(b);
    }
    
    public static boolean checkGridCollision(Entity a, Entity b) {
        if (a.node == null || b.node == null)
            return false; // If they don't both have ImageViews then they can't possibly collide
        
        return checkGridCollision(a.rect, b.rect);
    }
}
