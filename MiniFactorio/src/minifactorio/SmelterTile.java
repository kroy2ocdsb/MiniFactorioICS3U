/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minifactorio;

import javafx.geometry.Rectangle2D;

/**
 *
 * @author keega
 */
public class SmelterTile extends InteractableTile {
    public Smelter smelter;
    public String slot;
    
    public SmelterTile(Rectangle2D _rect, Smelter _smelter, String _slot) {
        rect = _rect;
        tileType = "Smelter";
        smelter = _smelter;
        slot = _slot;
    }
}
