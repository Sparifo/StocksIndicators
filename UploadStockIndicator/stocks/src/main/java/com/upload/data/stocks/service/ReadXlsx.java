package com.upload.data.stocks.service;

import com.upload.data.stocks.model.Indicator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ReadXlsx {

    private static final Logger logger = LoggerFactory.getLogger(ReadXlsx.class);

    public List<Indicator> readFile(String fileLocation){
        FileInputStream file = null;
        try {
            file = new FileInputStream(new File(fileLocation));
            Sheet sheet;
            try(Workbook workbook = new XSSFWorkbook(file);) {
                sheet = workbook.getSheetAt(1);
            }
            Map<Integer, List<String>> data = new HashMap<>();
            List<Indicator> products = new ArrayList<>();
            int i = 0;
            boolean missFirstRow=true;
            for (Row row : sheet) {
                if(!missFirstRow) {
                    data.put(i, new ArrayList<>());
                    int a = 0;
                    Indicator indicator = new Indicator();
                    fillProduct(row, a, indicator);
                    products.add(indicator);
                    i++;
                    logger.info("Read cell of xml from {} is {}",row.getRowNum(), i);
                }
                missFirstRow=false;
            }

        return products;
        }catch (IOException e) {
            logger.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private static void fillProduct(Row row, int a, Indicator indicator) {
        for (Cell cell : row) {

            switch (a) {
                case 0:
                    String[] ticker = getValueFromCell(cell).split("_",2);
                    indicator.setTicker(ticker[0]);
                    indicator.setTicker(ticker[1]);
                    break;
                case 1:
                    indicator.setPer(getValueFromCell(cell));
                    break;
                case 2:
                    indicator.setDate(new Date(getValueFromCell(cell)));
                    break;
                case 3:
                    indicator.setTime(getValueFromCell(cell));
                    break;
                case 4:
                    indicator.setOpen(new BigDecimal(getValueFromCell(cell)));
                    break;
                case 5:
                    indicator.setHigh(new BigDecimal(getValueFromCell(cell)));
                    break;
                case 6:
                    indicator.setLow(new BigDecimal(getValueFromCell(cell)));
                    break;
                case 7:
                    indicator.setClose(new BigDecimal(getValueFromCell(cell)));
                    break;
                case 8:
                    indicator.setVol(new BigDecimal(getValueFromCell(cell)));
                    break;
                case 9:
                    indicator.setOpenint(new BigDecimal(getValueFromCell(cell)));
                    break;
                default:
                    logger.info("Brak pasujacego miejsca danych: {}", getValueFromCell(cell));
            }

            a++;
        }
    }

    private static String getValueFromCell(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return  String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return cell.getBooleanCellValue()?"Y":"N";
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
