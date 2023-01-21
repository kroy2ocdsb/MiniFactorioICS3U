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
public class UnlockingTile extends InteractableTile {
    Unlockable unlockable;
    
    public UnlockingTile(Rectangle2D _rect, Unlockable _unlockable) {
        rect = _rect;
        unlockable = _unlockable;
    }
}
