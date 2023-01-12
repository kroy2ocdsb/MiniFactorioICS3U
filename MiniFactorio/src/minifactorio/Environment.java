/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minifactorio;

import java.util.ArrayList;

/**
 *
 * @author keega
 */
public class Environment {    
    final int X_SIZE;
    final int Y_SIZE;
    
    Entity[][] grid;
    ArrayList<Entity> contents;
    
    public Environment(int xSize, int ySize) throws Exception {
        if (xSize < 1 || ySize < 1) {
            throw new Exception("Dimensions must be above 0.");
        }
        
        X_SIZE = xSize;
        Y_SIZE = ySize;
        
        grid = new Entity[X_SIZE][Y_SIZE];
        contents = new ArrayList<Entity>();
        
        for (int x = 0; x < X_SIZE; x++) {
            for (int y = 0; y < Y_SIZE; y++) {
                Entity entity = new Entity(x, y, 1, 1);
                grid[x][y] = entity;
            }
        }
    }
}
