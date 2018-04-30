/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
//s
/**
 *
 * @author Michael
 */
public class Cheaters {
    
    static File dir;
    static File[] directoryListing;
    static Map<String, String> files = new HashMap<>();
    static Map<String, ArrayList<String>> nGramWords = new HashMap<>();
    static double[][] similarities;
    static List<String> fileList;
    static int numFiles = 0;
    
    public static void cleanFile() throws IOException {
        System.out.println(System.getProperty("user.dir") + "\\sm_doc_set\\sm_doc_set");
        directoryListing = dir.listFiles();
        for (File child : directoryListing) {
            numFiles++;
            BufferedReader in = (new BufferedReader(new FileReader(child)));
            String line;
            String processedLine="";
            while ((line = in.readLine()) != null) {
                processedLine += line.replaceAll("[^a-zA-Z0-9\' ]", "").toLowerCase().replaceAll("( )+", " ");
            }
            
            files.put(child.getName(), processedLine);
            //System.out.println(processedLine);
            
        }
    }
    
    public static void createNGramMap(int N){
        String[] wordList;
        String consecutiveWords;
        String cleanedDoc;
        ArrayList<String> mapValues;
        for(String file : files.keySet()){
            cleanedDoc = files.get(file);
            wordList = cleanedDoc.split(" ");
            mapValues = new ArrayList<>();
            for(int i = 0; i < (wordList.length - N); i++){
                consecutiveWords = "";
                for(int j = i; j < i + N; j++){
                    consecutiveWords += wordList[j] + " ";
                }
                if(nGramWords.containsKey(consecutiveWords)){
                    mapValues = nGramWords.get(consecutiveWords);
                }
                if(!mapValues.contains(file)){
                    mapValues.add(file);
                }
                nGramWords.put(consecutiveWords, mapValues);
            }
        }
    }
    
    public static void createSimilarityMatrix(){
        similarities = new double[files.keySet().size()][files.keySet().size()];
        fileList = new ArrayList<>();
        int[] totalOccurences = new int[files.keySet().size()];
        for(String file : files.keySet()){
            fileList.add(file);  
            similarities[fileList.size()-1][fileList.size()-1] = 0;
        }
        for(String s : nGramWords.keySet()){
            for(String f : nGramWords.get(s)){
                similarities[fileList.indexOf(f)][fileList.indexOf(f)] += 1;
            }
            for(int i = 0; i < nGramWords.get(s).size() - 1; i++){
                for(int j = i+1; j < nGramWords.get(s).size(); j++){
                    int file1 = fileList.indexOf(nGramWords.get(s).get(i));
                    int file2 = fileList.indexOf(nGramWords.get(s).get(j));           
                    similarities[file1][file2] += 1;
                    similarities[file2][file1] += 1;                                   
                }
            }
        }
//        for(int i = 0; i < similarities.length; i++){
//            for(int j = 0; j < similarities[0].length; j++){
//                if(j > i){
//                    similarities[i][j] = similarities[i][j] / similarities[i][i];
//                }
//                else if( j < i){
//                    similarities[i][j] = similarities[i][j] / similarities[j][j];
//                }
//            }
//        }       
    }
    
    /**
     * 
     * @param numberofLines == -1 will output all similarities in the matrix
     */
    public static void outputSimilarities(int minSimilarities, int numberofLines){
        Map<List<String>, Double> unsortedMap = new HashMap<>();
        for(int i = 0; i < similarities.length; i++){
            for(int j = 0; j < similarities[0].length; j++){
                if(j > i){
                    List<String> pair = new ArrayList<>();
                    pair.add(fileList.get(i));
                    pair.add(fileList.get(j));
                    unsortedMap.put(pair, similarities[i][j]);
                }
            }
        }
        
        Map<List<String>, Double> sortedMapDesc = sortByComparator(unsortedMap, false);
        printMap(sortedMapDesc, minSimilarities, numberofLines);

    }
    
    private static Map<List<String>, Double> sortByComparator(Map<List<String>, Double> unsortMap, final boolean order)
    {

        List<Entry<List<String>, Double>> list = new LinkedList<Entry<List<String>, Double>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<List<String>, Double>>()
        {
            public int compare(Entry<List<String>, Double> o1,
                    Entry<List<String>, Double> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<List<String>, Double> sortedMap = new LinkedHashMap<List<String>, Double>();
        for (Entry<List<String>, Double> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static void printMap(Map<List<String>, Double> map, int minSimilarity, int numberOfLines){
        int count = 0;
        for (Entry<List<String>, Double> entry : map.entrySet()){
            if(entry.getValue() > minSimilarity && (count < numberOfLines || numberOfLines == -1)){
                System.out.println(entry.getValue() + ": " + entry.getKey().toString());
            }
            count++;
        }
    }
    
    public static double[][] run(String filePath, int nGramLength, int minSimilarities, int topFiles) throws IOException { 
        System.out.println("testerasdfsdlfk;lka");
        dir = new File(filePath + "\\sm_doc_set\\sm_doc_set");
        System.out.println("filepath again: " + filePath + "\\sm_doc_set\\sm_doc_set");
        cleanFile();
        numFiles++;
        createNGramMap(nGramLength);
        createSimilarityMatrix();
        outputSimilarities(minSimilarities, -1);
        return similarities;
    }
    
    public static List<String> getFileList(){
        return fileList;
    }
    
    
}
