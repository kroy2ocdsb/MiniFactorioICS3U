package minifactorio;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

/**
 *
 * @author keega
 */
public class Graphics {
    final static int TILE_SIZE = 50;
    final static int WORLD_WIDTH = 10;
    final static int WORLD_HEIGHT = 8;
    
    public static Point2D pixelPosition(int x, int y) {
        return new Point2D(
                x*TILE_SIZE,
                y*TILE_SIZE
        );
    }
    
    public static Point2D pixelPosition(Point2D point) {
        return pixelPosition(
                (int)point.getX(),
                (int)point.getY()
        );
    }
    
    
    
    public static Point2D gridPosition(int x, int y) {
        return new Point2D(
            Math.floor((float)x / (float)TILE_SIZE),
            Math.floor((float)y / (float)TILE_SIZE)
        );
    }
    
    public static Point2D gridPosition(Point2D point) {
        return gridPosition(
                (int)point.getX(),
                (int)point.getY()
        );
    }
    
    
    public static ImageView positionAt(ImageView imageView, Point2D position) {
        imageView.setX(position.getX());
        imageView.setY(position.getY());
        
        return imageView;
    }
    
    public static Rectangle2D newRectSize(Rectangle2D rect, double newX, double newY) {
        return new Rectangle2D(
                newX,
                newY,
                rect.getWidth(),
                rect.getHeight()
        );
    }
    
    public static Rectangle2D newRectSize(Rectangle2D rect, Point2D vector) {
        return newRectSize(rect, vector.getX(), vector.getY());
    }
    
    public static void addEntity(Entity entity) {
        if (entity.node == null)
            return;
        
        Pane pane = (Pane)MiniFactorio.mainStage.getScene().getRoot();
        
        pane.getChildren().add(entity.node);
    }
    
    // Permanently deletes an entity from the current Scene.
    public static boolean removeEntity(Entity entity) {
        Pane pane = (Pane)MiniFactorio.mainStage.getScene().getRoot();
        
        if (entity.node != null) {
            int index = pane.getChildren().indexOf(entity.node);
            
            if (index == -1)
                return false;
            
            pane.getChildren().remove(index);
            
            return true;
        }
        
        return false;
    }
}
