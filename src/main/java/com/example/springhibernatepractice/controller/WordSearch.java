package com.example.springhibernatepractice.controller;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.stream.Collectors;

public class WordSearch {

    public void wordCount (String fileName) throws IOException {

        long time = System.currentTimeMillis();

        //System.out.println("Searching in the file:" + fileName );
        //System.out.println("Search start time: " + time );

        Map<String, Integer> countMap = new HashMap<>();
        List<String> listOfWords = wordListToBeSearched() ;

        System.out.println("Downloading page...");
        Document doc = Jsoup.parse(new File(fileName), "UTF-8");

        //Get the actual text from the page, excluding the HTML
        String fileToBeSearched = doc.body().text();



        //Create BufferedReader so the words can be counted
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(fileToBeSearched.getBytes(StandardCharsets.UTF_8))));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split("[^A-Z“a-z]+");
            for (String word : words) {
                word = word.toLowerCase() ;
                if ("".equals(word)) {
                    continue;
                }
                else {

                    for (String searchWord : listOfWords   ) {

                        if ( word.equalsIgnoreCase(searchWord)) {
                            if ( !countMap.containsKey(word)  ) {
                                countMap.put(word, 0);
                            }
                            countMap.put(word, countMap.get(word) + 1);
                            break;
                        }



                    }





                }


            }
        }

        reader.close();

        Map sortedWords = countMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));


        sortedWords.forEach((k, v) -> System.out.println(k + "\t" + v));





        System.out.println("Search end time: " + System.currentTimeMillis() );
        time = System.currentTimeMillis() - time;
        System.out.println("Finished in " + time + " ms");
        System.out.println("Finished Searching in the file:" + fileName );
    }


    public  List<String>  wordListToBeSearched()
    {

        List<String> op = Arrays.asList("Spring","Tutorial","Java","Spring boot","Prerequisite","guide") ;

        return op;

    }
}
