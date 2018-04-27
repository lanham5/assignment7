/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fxgraph.layout.random;

/**
 *
 * @author Michael
 */
import java.util.List;
import java.util.Random;

import com.fxgraph.graph.Cell;
import com.fxgraph.graph.Graph;
import com.fxgraph.layout.base.Layout;
import java.util.ArrayList;
import java.util.Iterator;

public class GridLayout extends Layout {

    Graph graph;
    int rows;
    int cols;
    Random rnd = new Random();

    public GridLayout(Graph graph) {
        this.graph = graph;
    }

    public void execute() {

        List<Cell> cells = graph.getModel().getAllCells();
        int length = 0;
        double screenWidth = 900;
        double screenHeight = 900;
        for(Cell cell : cells){
            length++;
        }
        rows = (int) Math.ceil(Math.sqrt(length));
        cols = rows;
        Iterator iter = cells.iterator();
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(iter.hasNext()){
                    Cell cell = (Cell) iter.next();
                    
                    if(i%2==0){
                        double x = j * screenWidth / cols;
                        double y = i * screenHeight / rows;
                        cell.relocate(x, y);
                    } else {
                        double x = (j * (2*screenWidth/3) / cols) + (screenWidth/6);
                        double y = i * screenHeight / rows;
                        cell.relocate(x, y);
                    }
                }
                              
            }
        }
//        for (Cell cell : cells) {
//            length++;
//            double x = rnd.nextDouble() * 1000;
//            double y = rnd.nextDouble() * 1000;
//
//            cell.relocate(x, y);
//
//        }

    }

}
