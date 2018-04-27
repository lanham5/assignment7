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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class RectangleCell extends Cell {

    public RectangleCell(String id, int size, int color) {
        super(id);

        Rectangle view = new Rectangle(size,size);

        
        if(color == 1){
            view.setStroke(Color.RED);
            view.setFill(Color.RED);
        } else {
            view.setStroke(Color.DODGERBLUE);
            view.setFill(Color.DODGERBLUE);
        }
        StackPane stack = new StackPane();
        stack.getChildren().addAll(view, new Text(id));
        setView(stack);

    }

}
