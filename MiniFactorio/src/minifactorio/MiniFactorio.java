/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package minifactorio;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;


/**
 *
 * @author keega
 */
public class MiniFactorio extends Application {
    public static Graphics graphics;
    public static World world;
    public static boolean showShed = false;
    
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        Environment environment = new Environment(10,8);
        
        world = new World(new Environment[] {environment}, 0);
        
        launch(args);
    }
    
    public void start(Stage stage) throws FileNotFoundException {
        stage.setResizable(false);
        
        // Make elements
        Environment curEnv = world.getCurEnvironment();
        Pane pane = new Pane();
        
        // Graphics settings
        int tileSize = Graphics.TILE_SIZE;
        
        // Load tile image
        FileInputStream inputstream;
        Image tileImage = null;
        try {
            inputstream = new FileInputStream("C:\\Users\\keega\\Downloads\\tile2.png");
            tileImage = new Image(inputstream); 
        }
        catch (Exception e) {
            System.out.println("Tile image not loaded!");
        }
        
        // Make grid of tiles
        for (int x = 0; x < curEnv.X_SIZE; x++) {
            for (int y = 0; y < curEnv.Y_SIZE; y++) {
                ImageView tile = new ImageView(tileImage);
                tile.setX(x*(tileSize-0));
                tile.setY(y*(tileSize));
                
                curEnv.grid[x][y].node = tile;

                /*Rectangle tile = new Rectangle(
                        x*(tileSize+tilePadding)+tilePadding,
                        y*(tileSize+tilePadding)+tilePadding,
                        tileSize-tilePadding*2,
                        tileSize-tilePadding*2
                );*/
                pane.getChildren().add(tile);
            }
        }
        
        // Load shed image
        Image shedImage = null;
        try {
            inputstream = new FileInputStream("C:\\Users\\keega\\Downloads\\shed1.png");
            shedImage = new Image(inputstream); 
        }
        catch (Exception e) {
            System.out.println("Shed image not loaded!");
        }
        ImageView shedView = new ImageView(shedImage);
        Rectangle2D shedPos = Graphics.pixelPosition(2,1);
        shedView.setX(shedPos.getMinX());
        shedView.setY(shedPos.getMinY());
        shedView.setVisible(false);
        
        pane.getChildren().add(shedView);
        
        Entity shed = new Entity(new Rectangle2D(2,1, 2, 2));
        shed.node = shedView;
        
        // Make + render scene
        Scene scene = new Scene(pane, curEnv.X_SIZE*tileSize, curEnv.Y_SIZE*tileSize);
        stage.setScene(scene);
        stage.show();
        
        
        curEnv.contents.add(shed);
        
        // Keybinds
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.A) {
                showShed = !showShed;
                System.out.println(curEnv.contents.get(0));
                curEnv.contents.get(0).node.setVisible(showShed);
            }
        });
        
        /*Circle circle = new Circle();
        circle.setCenterX(200);
        circle.setCenterY(150);
        circle.setRadius(100);
        //circle.setStroke(Color.BLACK);
        circle.setFill(Color.RED);
        
        FileInputStream inputstream;
        Image chungusImage;
        ImageView chungus = null;
        try {
            inputstream = new FileInputStream("C:\\Users\\keega\\Downloads\\bigchungus-removebg-preview.png");
            chungusImage = new Image(inputstream); 
            chungus = new ImageView(chungusImage);
            chungus.setX(200 - chungusImage.getWidth()/2);
            chungus.setY(150 - chungusImage.getHeight()/2);
        }
        catch (Exception e) {
            System.out.println("Image not loaded!");
        }
        
        Pane pane = new Pane();
        pane.getChildren().addAll(circle, chungus);
        
        Scene scene = new Scene(pane, 400, 300);
        stage.setTitle("Drawing a circle!");
        stage.setScene(scene);
        stage.show();*/
    }
}
