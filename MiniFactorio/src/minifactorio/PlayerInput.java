/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minifactorio;

import java.util.HashMap; // Help from https://stackoverflow.com/questions/37472273/detect-single-key-press-in-javafx
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author keega
 */
public class PlayerInput {
    public static Player player;
    public static Stage mainStage;
    
    private static HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();
    
    public static boolean canMove = false;
    
    public static void start(Player _player, Stage _mainStage) {
        player = _player;
        mainStage = _mainStage;
        
        // Key down
        mainStage.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            String codeString = key.getCode().toString();
            if (!currentlyActiveKeys.containsKey(codeString)) {
                currentlyActiveKeys.put(codeString, true);
                keyPressed(codeString);
            }
        });
        
        // Key up
        mainStage.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
            String codeString = key.getCode().toString();
            currentlyActiveKeys.remove(codeString);
            keyReleased(codeString);
        });
    }
    
    public static void keyPressed(String key) {
        if (key.equals("W")) {
            player.move(new Point2D(0, -1));
        }
        else if (key.equals("S")) {
            player.move(new Point2D(0, 1));
        }
        else if (key.equals("A")) {
            player.move(new Point2D(-1,0));
        }
        else if (key.equals("D")) {
            player.move(new Point2D(1,0));
        }
    }
    
    public static void keyReleased(String key) {
        
    }
}

/**/