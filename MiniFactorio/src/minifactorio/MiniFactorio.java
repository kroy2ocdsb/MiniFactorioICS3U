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
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;


/**
 *
 * @author keega
 */
public class MiniFactorio extends Application {
    //public static Graphics graphics;
    public static World world;
    public static boolean showShed = true;
    public static Stage mainStage;
    
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        Environment environment = new Environment(Graphics.WORLD_WIDTH, Graphics.WORLD_HEIGHT);
        
        world = new World(new Environment[] {environment}, 0);
        
        launch(args);
    }
    
    public void start(Stage stage) throws FileNotFoundException {
        mainStage = stage;
        
        stage.setResizable(false);
        
        // Import images
        MediaLoader.importImages("C:\\Users\\keega\\Downloads", new String[] {"shed1.png", "jeremy.png", "grass1.png", "iron1.png", "copper1.png"});
        
        // Make elements
        Environment curEnv = world.getCurEnvironment();
        Pane pane = new Pane();
        
        // Graphics settings
        int tileSize = Graphics.TILE_SIZE;
        
        // Make grid of tiles
        for (int x = 0; x < curEnv.X_SIZE; x++) {
            for (int y = 0; y < curEnv.Y_SIZE; y++) {
                ImageView tileView = MediaLoader.viewImage("grass1.png");
                
                Point2D tilePos = Graphics.pixelPosition(x, y);
                Graphics.positionAt(tileView, tilePos);
                
                curEnv.grid[x][y].node = tileView;
                
                pane.getChildren().add(tileView);
            }
        }
        
        // Load shed image
        ImageView shedView = MediaLoader.viewImage("shed1.png");
        Point2D shedPos = Graphics.pixelPosition(2,1);
        Graphics.positionAt(shedView, shedPos);
        shedView.setVisible(showShed);
        
        pane.getChildren().add(shedView);
        
        Entity shed = new Entity(new Rectangle2D(2,1, 2, 2));
        shed.node = shedView;
        
        // Make + render scene
        Scene scene = new Scene(pane, curEnv.X_SIZE*tileSize, curEnv.Y_SIZE*tileSize);
        stage.setScene(scene);
        stage.show();
        
        curEnv.contents.add(shed);
        
        // Make player
        Player player = new Player();
        curEnv.contents.add(0,player);
        pane.getChildren().add(player.node);
        
        // Keybinds
        stage.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> { // Remember addEventHandler() can be used on any stage or scene!!
            if (key.getCode() == KeyCode.G) {
                showShed = !showShed;
                curEnv.contents.get(1).node.setVisible(showShed);
            }
            else if (key.getCode() == KeyCode.Q) {
                stage.close();
            }
        });
        
        PlayerInput.start(player, stage);
        
        
        // Add ores
        new Ore("iron", new Point2D(0, 0));
        new Ore("iron", new Point2D(Graphics.WORLD_WIDTH-1, Graphics.WORLD_HEIGHT-1));
        new Ore("copper", new Point2D(0, Graphics.WORLD_HEIGHT-1));
        new Ore("copper", new Point2D(Graphics.WORLD_WIDTH-1, 0));
        player.node.toFront();
    }
}

/* Old code dump:
// Load tile image
       /* FileInputStream inputstream;
        Image tileImage = null;
        try {
            inputstream = new FileInputStream("C:\\Users\\keega\\Downloads\\tile2.png");
            tileImage = new Image(inputstream); 
        }
        catch (Exception e) {
            System.out.println("Tile image not loaded!");
        }*/


/*Rectangle tile = new Rectangle(
                        x*(tileSize+tilePadding)+tilePadding,
                        y*(tileSize+tilePadding)+tilePadding,
                        tileSize-tilePadding*2,
                        tileSize-tilePadding*2
                );*/


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


/*System.out.println(Graphics.removeEntity(curEnv.grid[0][0]));
        curEnv.grid[0][0] = null;*/


//System.out.println(String.format("X: %d   Y: %d", curEnv.grid.length, curEnv.grid[0].length));
*/