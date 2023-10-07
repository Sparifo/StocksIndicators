package com.upload.data.stocks.controller;

import com.upload.data.stocks.model.Indicator;
import com.upload.data.stocks.model.IndicatorCSV;
import com.upload.data.stocks.repository.IndicatorRepository;
import com.upload.data.stocks.service.ImportCSV;
import com.upload.data.stocks.service.ReadCSV;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/import")
public class UploadIndicatorsController {

    private static final Logger logger = LoggerFactory.getLogger(UploadIndicatorsController.class);

    private ImportCSV importCSV;
    private ReadCSV readCSV;

    private IndicatorRepository indicatorRepository;

    @Value("${director.to.check}")
    private String directorToCheck;

    @Autowired
    public UploadIndicatorsController(ImportCSV importCSV, ReadCSV readCSV, IndicatorRepository indicatorRepository) {
        this.importCSV = importCSV;
        this.readCSV = readCSV;
        this.indicatorRepository = indicatorRepository;
    }

    public UploadIndicatorsController() {
        super();
    }

    @GetMapping(value = "/")
    public ResponseEntity getImportCSV(final HttpServletResponse response) {

        Optional<List<File>> fileCSVOptional = importCSV.checkFileExist(directorToCheck);

        if(fileCSVOptional.isPresent()){

            List<File> fileCSV = fileCSVOptional.get();

            List<ImportCSV> indicators = new ArrayList<>();
            for(File csv : fileCSV) {
                indicators.addAll(readCSV.readFile(csv.getAbsolutePath()));
                logger.info("END OD FILE");
            }

//            indicatorRepository.saveAll(indicators);

            logger.info("END OD WORK");
            return ResponseEntity.ok()
                    .body("Work");
        }else{
            return ResponseEntity.badRequest()
                    .body("Directory is empty");
        }
    }
}
