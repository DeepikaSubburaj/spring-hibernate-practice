package com.example.springhibernatepractice;

import com.example.springhibernatepractice.file.ReadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component

public class RunApplication implements CommandLineRunner {

    @Value("classpath:Input.txt")
    private Resource res;

    @Autowired
    private ReadFile rf ;

    @Override
    public void run ( String[] args) throws Exception{

        rf.ReadDataFromFile(res);


    }



}
