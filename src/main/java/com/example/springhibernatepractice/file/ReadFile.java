package com.example.springhibernatepractice.file;

import java.io.*;
import java.nio.file.Files;
import org.jsoup.*;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.net.*;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.example.springhibernatepractice.controller.WordSearch ;




@Component

public class ReadFile {

    public void ReadDataFromFile(Resource fileName) {
        try   {

            Stream<String> urlStream = Files.lines(Paths.get(fileName.getURI())).map(s -> s.trim()) ;

            urlStream.forEach(link -> ReadDataFromWebPage(link ));
            //System.out.println("over ReadDataFromFile:");
            return;

        }

        catch (IOException e) {
            System.out.println("ERROR: unable to read file " + fileName);
            e.printStackTrace();
        }

        catch (Exception e) {
            e.printStackTrace();
        }


    } // method close


    public void ReadDataFromWebPage(String urlLink) {
        try   {

                //System.out.println("IP link is:"+urlLink);

                URL url;
                url = new URL(urlLink );
                HttpURLConnection  conn = (HttpURLConnection)url.openConnection();
                conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String outputFilename = getHostName(urlLink)   + ".html" ;

                //System.out.println("filename:" + outputFilename);

                BufferedWriter bw = new BufferedWriter(new FileWriter( outputFilename ));

                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    //System.out.println("val:"+inputLine);
                    bw.write(inputLine);
                    bw.newLine();
                }
                //System.out.println("over ReadDataFromWebPage:");
                br.close();
                bw.close();

                // search for words in the downloaded file

                WordSearch wordSearchObj = new WordSearch();
                wordSearchObj.wordCount(outputFilename) ;



                return;

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        catch (Exception e) {
            e.printStackTrace();
        }


    } // method close

    public String getHostName(String url) throws URISyntaxException {
        URI websiteName = new URI(url);
        String hostname = websiteName.getHost();
        hostname = hostname.substring(0,hostname.lastIndexOf("."));

        if (hostname != null) {
            return hostname.startsWith("www.") ? hostname.substring(4) : hostname;
        }
        return hostname;
    }






}
