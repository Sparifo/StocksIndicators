package com.upload.data.stocks.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.upload.data.stocks.model.Indicator;
import com.upload.data.stocks.model.IndicatorCSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class ReadCSV {

    private static final Logger logger = LoggerFactory.getLogger(ReadCSV.class);

    public List<Indicator> readFile(String fileLocation){
        try {
            List<Indicator> indicators = new CsvToBeanBuilder(new FileReader(fileLocation))
                    .withType(ImportCSV.class)
                    .build()
                    .parse();

            indicators.forEach(name -> logger.info(name.toString()));

            return indicators;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
