/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package minifactorio;

import java.io.File;
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
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author keega
 */
public class MiniFactorio extends Application {
    public static World world;
    public static boolean showShed = true;
    public static Stage mainStage;
    public static Player mainPlayer;
    
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
        String imageDirectory = getClass().getResource("images").getPath();
        imageDirectory = imageDirectory.replace("/", "\\").substring(1);
        imageDirectory = imageDirectory.replace("%20", " ");
        //System.out.println(imageDirectory);
        MediaLoader.importImages(imageDirectory, new String[] {
            "jeremy.png", "grass1.png", "iron1.png", "copper1.png", "smeltIron2.png", "smeltCopper2.png", "craftCircuit4.png",
            "circuitAssembler1.png"
        }, new Point2D(1, 1));
        MediaLoader.importImage(imageDirectory + "\\building2.png", new Point2D(2, 2));
        
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
                
                curEnv.grid[x][y].setNode(tileView);
                
                pane.getChildren().add(tileView);
            }
        }
        
        // Make + render scene
        Scene scene = new Scene(pane, curEnv.X_SIZE*tileSize, curEnv.Y_SIZE*tileSize + (Graphics.TOPBAR_SIZE + Graphics.BOTTOMBAR_SIZE));
        stage.setScene(scene);
        stage.getIcons().add(MediaLoader.getImage("jeremy.png"));
        stage.setTitle("Mini Factorio by Keegan for ICS3U! Meet Jeremy <3");
        stage.show();
        
        //curEnv.contents.add(shed);
        
        // Keybinds
        stage.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> { // Remember addEventHandler() can be used on any stage or scene!!
            if (key.getCode() == KeyCode.G) {
                showShed = !showShed;
                curEnv.contents.get(1).node.setVisible(showShed);
            }
            else if (key.getCode() == KeyCode.Q) {
                onQuitting(this);
            }
        });
        
        
        // Add ores
        new Ore("iron", new Point2D(0, 0));
        new Ore("iron", new Point2D(Graphics.WORLD_WIDTH-1, Graphics.WORLD_HEIGHT-1));
        new Ore("copper", new Point2D(0, Graphics.WORLD_HEIGHT-1));
        new Ore("copper", new Point2D(Graphics.WORLD_WIDTH-1, 0));
        
        // Smelter
        new Smelter(new Point2D(2, 1));
        /*HashMap<String, Integer> smelterReqs = new HashMap<String, Integer>();
        smelterReqs.put("ironOre", 10);
        smelterReqs.put("copperOre", 10);
        new Unlockable("Smelter", new Point2D(2, 1), smelterReqs);
        No time for this unfortunately*/
        
        // Circuit Assembler
        new CircuitAssembler(new Point2D(5, 2));
        
        // Load top + bottom bars
        Graphics.loadBars(pane);
        
        
        // Make player
        mainPlayer = new Player();
        curEnv.contents.add(0,mainPlayer);
        pane.getChildren().add(mainPlayer.node);
        
        // Listen for player input
        PlayerInput.start(mainPlayer, stage);
        
        mainPlayer.node.toFront();
        
        // Output save
        mainStage.setOnCloseRequest(x -> {
            onQuitting(this);
        });
    }
    
    public static int getTime() {
        return (int)(System.currentTimeMillis());
    } 
    
    // saves most recent game to one file
    public static void onQuitting(MiniFactorio instance) {
        System.out.println("Stage is closing");
        // Save file
        String saveDirectory = instance.getClass().getResource("images").getPath();
        saveDirectory = saveDirectory.replace("/", "\\").substring(1);
        saveDirectory = saveDirectory.replace("%20", " ");
        saveDirectory = saveDirectory.replace("build\\classes\\minifactorio\\images", "saves\\gameSave.txt");
        //System.out.println(saveDirectory);
        File save = new File(saveDirectory);
        try {
            FileWriter writer = new FileWriter(save);
            
            String saveContent = mainPlayer.inventorySaveContent();
            
            writer.write(saveContent);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occured, can't save.");
        }
        
        // quit
        mainStage.close();
    }
}