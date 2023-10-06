package com.upload.data.stocks.controller;

import com.upload.data.stocks.model.Indicator;
import com.upload.data.stocks.repository.ProductRepository;
import com.upload.data.stocks.service.ImportCSV;
import com.upload.data.stocks.service.ReadCSV;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
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

    private ApplicationEventPublisher eventPublisher;
    private ImportCSV importCSV;
    private ReadCSV readXlsx;

    private ProductRepository productRepository;

    @Value("${director.to.check}")
    private String directorToCheck;

    @Autowired
    public UploadIndicatorsController(ApplicationEventPublisher eventPublisher, ImportCSV importCSV, ReadCSV readXlsx, ProductRepository productRepository) {
        this.eventPublisher = eventPublisher;
        this.importCSV = importCSV;
        this.readXlsx = readXlsx;
        this.productRepository = productRepository;
    }

    public UploadIndicatorsController() {
        super();
    }

    @GetMapping(value = "/")
    public ResponseEntity getImportXslx(final HttpServletResponse response) {

        Optional<List<File>> fileXlsxOptional = importCSV.checkFileExist(directorToCheck);

        if(fileXlsxOptional.isPresent()){

            List<File> fileXls = (List<File>)fileXlsxOptional.get();

            List<Indicator> indicators = new ArrayList<>();
            for(File xsl : fileXls) {
                indicators.addAll(readXlsx.readFile(xsl.getAbsolutePath()));
                logger.info("END OD FILE");
            }

            productRepository.saveAll(indicators);

            logger.info("END OD WORK");
            return ResponseEntity.ok()
                    .body("Work");
        }else{
            return ResponseEntity.badRequest()
                    .body("Directory is empty");
        }
    }
}
