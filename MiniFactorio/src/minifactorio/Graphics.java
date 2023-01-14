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
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author keega
 */
public class Graphics {
    final static int TILE_SIZE = 50;
    
    final static int TOPBAR_SIZE = 35;    
    final static int BOTTOMBAR_SIZE = 35;
    
    final static int WORLD_WIDTH = 10;
    final static int WORLD_HEIGHT = 8;
    
    private static Rectangle topBar;
    private static Text topBarText;
    private static Rectangle bottomBar;
    private static Text bottomBarText;
    
    /// Utility methods
    public static Point2D pixelPosition(int x, int y) {
        return new Point2D(
                x*TILE_SIZE,
                y*TILE_SIZE + TOPBAR_SIZE
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
            Math.floor((float)(y - TOPBAR_SIZE) / (float)TILE_SIZE)
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
    
    public static String trailingSpaces(String text, int numSpaces) {
        return text + (" ".repeat(numSpaces - text.length()));
    }
    
    
    /// Environment editing methods
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
    
    
    /// Initialization methods
    public static void loadBars(Pane pane) {
        // Top bar
        int screenWidth = TILE_SIZE * WORLD_WIDTH;
        topBar = new Rectangle(screenWidth, TOPBAR_SIZE, Color.GRAY);
        topBarText = new Text();
        topBarText.setFont(Font.font("Consolas", 20));
        
        updateTopBar("Iron: 0     Copper: 0     Circuit: 0");
        
        pane.getChildren().addAll(topBar, topBarText);
        
        // Bottom bar
        bottomBar = new Rectangle(screenWidth, BOTTOMBAR_SIZE, Color.GRAY);
        bottomBar.setX(0);
        bottomBar.setY(TILE_SIZE * (WORLD_HEIGHT) + BOTTOMBAR_SIZE);
        bottomBarText = new Text();
        bottomBarText.setFont(Font.font("Consolas", 20));
        
        updateBottomBar("");
        
        pane.getChildren().addAll(bottomBar, bottomBarText);
    }
    
    public static void updateTopBar(String text) {
        topBarText.setText(text);
        
        topBarText.setX(TILE_SIZE * WORLD_WIDTH/2-topBarText.getLayoutBounds().getWidth()/2);
        topBarText.setY(23);
    }
    
    public static void updateBottomBar(String text) {
        bottomBarText.setText(text);
        
        bottomBarText.setX(TILE_SIZE * WORLD_WIDTH/2-bottomBarText.getLayoutBounds().getWidth()/2);
        bottomBarText.setY(10 + TILE_SIZE * (WORLD_HEIGHT + 1));
    }
}
