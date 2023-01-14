/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minifactorio;

import java.io.FileInputStream;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author keega
 */
public class MediaLoader { // Important: ALL IMAGES NEED UNIQUE NAMES
    public static ArrayList<String> imageIndexes = new ArrayList<String>();
    public static ArrayList<Image> images = new ArrayList<Image>();
    
    public static Pattern fileNamePattern = Pattern.compile("([^\\\\]+)$");
    
    public static Image importImage(String filePath) {
        Matcher fileNameMatcher = fileNamePattern.matcher(filePath);
        fileNameMatcher.find();
        String fileName = fileNameMatcher.group(1);
        
        System.out.println("Filename:" + fileName);
        
        FileInputStream inputstream;
        Image image = null;
        try {
            inputstream = new FileInputStream(filePath);
            image = new Image(inputstream); 
        }
        catch (Exception e) {
            System.out.println("Image not loaded!");
        }
        
        return image;
    }
    
    public static ImageView viewImage(String fileName) {
        return null;
    }
}
