/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fxgraph.cells;

/**
 *
 * @author Michael
 */
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import com.fxgraph.graph.Cell;

public class RectangleCell extends Cell {

    public RectangleCell(String id, int size) {
        super(id);

        Rectangle view = new Rectangle(size,size);

        view.setStroke(Color.DODGERBLUE);
        view.setFill(Color.DODGERBLUE);
        
        setView( view);

    }

}
