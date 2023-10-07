package com.upload.data.stocks.service;

import lombok.ToString;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@ToString
public class ImportCSV {


    public Optional<List<File>> checkFileExist(String filePathString){
        File directory = new File(filePathString);

        if(directory.exists() && directory.isDirectory()){
            return Optional.of(Arrays.stream(directory.listFiles()).toList());
        }

        return Optional.empty();
    }



}
