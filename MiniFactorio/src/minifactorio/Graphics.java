package minifactorio;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

/**
 *
 * @author keega
 */
public class Graphics {
    final static int TILE_SIZE = 50;
    
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
}
