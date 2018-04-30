/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.fxgraph.graph.CellType;
import com.fxgraph.graph.Graph;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.base.Layout;
import com.fxgraph.layout.random.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    Graph graph = new Graph();
    static double[][] adjacencyList;
    static List<String> fileList;
    static String filePath;
    static int nGramLength;
    static int minSimilarities;
    
    @Override
    public void start(Stage primaryStage) {       
        
        BorderPane root = new BorderPane();

        graph = new Graph();

        root.setCenter(graph.getScrollPane());

        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();

        addGraphComponents();

        Layout layout = new GridLayout(graph);
        layout.execute();

    }

    private void addGraphComponents() {

        Model model = graph.getModel();
        List<Integer> numConnections = new ArrayList<>();
        graph.beginUpdate();
        
        try {
            adjacencyList = Cheaters.run(filePath, nGramLength, minSimilarities, -1);
            fileList = Cheaters.getFileList();
            final int MIN_SIMILARITY = minSimilarities;
            final int MAX_CONNECTIONS = 12;
            double maxVal = 0;
            for(int i = 0; i < adjacencyList.length; i++){
                boolean noConnections = true;
                numConnections.add(0);
                for(int j = 0; j < adjacencyList[0].length; j++){                   
                    if(j!=i && adjacencyList[i][j] > MIN_SIMILARITY){                       
                        numConnections.set(i, numConnections.get(i) + 1);
                        if(adjacencyList[i][j] > maxVal){
                            maxVal = adjacencyList[i][j];
                        }
                        noConnections = false;
                    }
                }
                if(!noConnections){
                    if(numConnections.get(i) > MAX_CONNECTIONS){
                        model.addCell(fileList.get(i), CellType.RECTANGLE, (int) (((.5*Math.log10((double) numConnections.get(i)/adjacencyList.length)+1)*90)+20), 1);
                    } else {
                        model.addCell(fileList.get(i), CellType.RECTANGLE, (int) (((.5*Math.log10((double) numConnections.get(i)/adjacencyList.length)+1)*90)+20), 0);
                    }
                }
            }      
            for(int i = 0; i < adjacencyList.length; i++){               
                for(int j = 0; j < adjacencyList[0].length && j<i; j++){
                    if(adjacencyList[i][j] > MIN_SIMILARITY){
                        model.addEdge(fileList.get(i), fileList.get(j), (adjacencyList[i][j]*4)/maxVal);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        graph.endUpdate();

    }

    public static void main(String[] args) {
        if(args != null && args.length == 3){
            filePath = args[0];
            nGramLength = Integer.parseInt(args[1]);
            minSimilarities = Integer.parseInt(args[2]);
        } else if(args != null && args.length == 2){
            filePath = args[0];
            nGramLength = Integer.parseInt(args[1]);
        } else {
            filePath = "C:\\Users\\Jared\\Desktop\\Assignment7Files";
            nGramLength = 15;
            minSimilarities = 700;
        }
        
        launch(args);
//        Main main = new Main();
//        main.addGraphComponents();
//        Stage stage = new Stage();
//        main.start(stage);
//        
    }
}