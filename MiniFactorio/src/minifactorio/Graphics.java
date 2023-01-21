package minifactorio;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// Help making button from https://stackoverflow.com/a/69781310

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author keega
 */
public class Graphics {
    final static int TILE_SIZE = 75;
    
    final static int TOPBAR_SIZE = 60;    
    final static int BOTTOMBAR_SIZE = 55;
    
    final static int TOPBAR_BUTTON_WIDTH = 60;
    
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
    
    public static Rectangle2D fromPoint(Point2D point) {
        return new Rectangle2D(point.getX(), point.getY(), 1, 1);
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
    
    // Replace a tile with another (eg. ore, input slots)
    public static void replaceTile(Entity oldTile, Entity newTile, Point2D position) {
        removeEntity(oldTile);
        addEntity(newTile);
        
        MiniFactorio.world.getCurEnvironment().grid[(int)position.getX()][(int)position.getY()] = newTile;
    }
    
    
    /// Initialization methods
    public static void loadBars(Pane pane) {
        // Top bar
        int screenWidth = TILE_SIZE * WORLD_WIDTH;
        topBar = new Rectangle(screenWidth-TOPBAR_BUTTON_WIDTH, TOPBAR_SIZE, Color.GRAY);
        topBar.setX(TOPBAR_BUTTON_WIDTH);
        topBarText = new Text();
        topBarText.setTextOrigin(VPos.TOP);
        topBarText.setFont(Font.font("Consolas", 16));
        topBarText.setTextAlignment(TextAlignment.CENTER);
        
        //updateTopBar("Iron: 0     Copper: 0     Circuit: 0");
        
        // Top buttons
        Button loadButton = new Button("Load");
        loadButton.setLayoutX(0);
        loadButton.setLayoutY(0);
        loadButton.setPrefWidth(TOPBAR_BUTTON_WIDTH);
        loadButton.setPrefHeight(30);
        loadButton.setStyle("-fx-background-color:rgb(200,200,225);-fx-background-radius:0");
        ObservableList<String> s = loadButton.getStylesheets();
        /*s.forEach((String e) -> {
            System.out.println(e);
        });*/
        System.out.println(s.size());
        for (int i = 0; i < s.size(); i++) {
            System.out.println(s.get(i));
        }
        Button saveButton = new Button("Save");
        saveButton.setLayoutX(0);
        saveButton.setLayoutY(30);
        saveButton.setPrefWidth(TOPBAR_BUTTON_WIDTH);
        saveButton.setPrefHeight(30);
        saveButton.setStyle("-fx-background-color:rgb(220,200,210);-fx-background-radius:0");
        
        pane.getChildren().addAll(topBar, topBarText, loadButton, saveButton);
        
        // Bottom bar
        bottomBar = new Rectangle(screenWidth, BOTTOMBAR_SIZE, Color.GRAY);
        bottomBar.setX(0);
        bottomBar.setY(TILE_SIZE * (WORLD_HEIGHT) + TOPBAR_SIZE);
        bottomBarText = new Text();
        bottomBarText.setTextOrigin(VPos.BOTTOM);
        bottomBarText.setWrappingWidth(screenWidth * 0.9);
        bottomBarText.setTextAlignment(TextAlignment.CENTER);
        bottomBarText.setFont(Font.font("Consolas", 18));
        
        updateBottomBar("");
        
        pane.getChildren().addAll(bottomBar, bottomBarText);
        
        System.out.println(topBarText.getTextOrigin());
        System.out.println(bottomBarText.getTextOrigin());
    }
    
    public static void updateTopBar(String text) {
        topBarText.setText(text);
        
        topBarText.setX(TILE_SIZE * WORLD_WIDTH/2-topBarText.getLayoutBounds().getWidth()/2 + 30);
        topBarText.setY(10);
    }
    
    public static void updateBottomBar(String text) {
        bottomBarText.setText(text);
        
        bottomBarText.setX(TILE_SIZE * WORLD_WIDTH/2-bottomBarText.getLayoutBounds().getWidth()/2);
        bottomBarText.setY((TILE_SIZE * (WORLD_HEIGHT)) + TOPBAR_SIZE + BOTTOMBAR_SIZE - 6);// + TOPBAR_SIZE - BOTTOMBAR_SIZE - 5);
    }
}
