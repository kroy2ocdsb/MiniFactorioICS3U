package minifactorio;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

/**
 *
 * @author keega
 */
public class Graphics {
    final static int TILE_SIZE = 50;
    
    public static Rectangle2D pixelPosition(int x, int y) {
        return new Rectangle2D(x*TILE_SIZE, y*TILE_SIZE, 0, 0);
    }
}
