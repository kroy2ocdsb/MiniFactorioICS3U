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
        
        FileInputStream inputstream;
        Image image = null;
        try {
            inputstream = new FileInputStream(filePath);
            image = new Image(inputstream); 
        }
        catch (Exception e) {
            System.out.println("Image not loaded!");
        }
        
        if (image != null) {
            imageIndexes.add(fileName);
            
            images.add(image);
        }
        
        return image;
    }
    
    public static Image[] importImages(String filePath, String[] fileNames) {
        Image[] images = new Image[fileNames.length];
        
        for (int i = 0; i < fileNames.length; i++) {
            images[i] = importImage(filePath + "\\" + fileNames[i]);
        }
        
        return images;
    }
    
    public static ImageView viewImage(String fileName) {
        int index = imageIndexes.indexOf(fileName);
        
        if (index == -1)
            return null;
        
        ImageView imageView = new ImageView(images.get(index));
        
        return imageView;
    }
}
