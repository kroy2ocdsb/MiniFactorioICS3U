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
public class CircuitAssemblerTile extends InteractableTile {
    public CircuitAssembler assembler;
    public String slot;
    
    public CircuitAssemblerTile(Rectangle2D _rect, CircuitAssembler _assembler) {
        rect = _rect;
        tileType = "CircuitAssembler";
        assembler = _assembler;
    }
}
