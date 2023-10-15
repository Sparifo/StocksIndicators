package com.upload.data.stocks.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.upload.data.stocks.model.IndicatorCSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class ReadCSV {

    private static final Logger logger = LoggerFactory.getLogger(ReadCSV.class);

    public List<IndicatorCSV> readFile(String fileLocation){
        try {

            List<IndicatorCSV> indicators = new CsvToBeanBuilder(new FileReader(fileLocation))
                    .withType(IndicatorCSV.class)
                    .withSkipLines(1)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
