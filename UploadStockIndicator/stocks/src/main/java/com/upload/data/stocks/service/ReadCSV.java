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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReadCSV {

    private static final Logger logger = LoggerFactory.getLogger(ReadCSV.class);

    public List<Indicator> readFile(String fileLocation){
        FileInputStream file = null;
        try {
            file = new FileInputStream(new File(fileLocation));
            Workbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(1);

            Map<Integer, List<String>> data = new HashMap<>();
            List<Indicator> indicators = new ArrayList<>();
            int i = 0;
            boolean missFirstRow=true;
            for (Row row : sheet) {
                if(!missFirstRow) {
                    data.put(i, new ArrayList<String>());
                    int a = 0;
                    Indicator indicator = new Indicator();
                    fillProduct(row, a, indicator);
                    indicators.add(indicator);
                    i++;
                    logger.info("Read cell of xml from {} is {}",row.getRowNum(), i);
                }
                missFirstRow=false;
            }

        return indicators;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fillProduct(Row row, int a, Indicator indicator) {
        for (Cell cell : row) {

            switch (a) {
                case 0:
                    indicator.setCode(getValueFromCell(cell));
                    break;
                case 1:
                    indicator.setRegion(getValueFromCell(cell));
                    break;
                case 2:
                    indicator.setMounth(getValueFromCell(cell));
                    break;
                case 3:
                    indicator.setName(getValueFromCell(cell));
                    break;
                case 4:
                    indicator.setType(getValueFromCell(cell));
                    break;
                case 5:
                    indicator.setYear(getValueFromCell(cell));
                    break;
                case 6:
                    indicator.setValue(getValueFromCell(cell));
                    break;
                case 7:
                    indicator.setCurrency(getValueFromCell(cell));
                    break;
                case 8:
                    indicator.setAttribute(getValueFromCell(cell));
                    break;
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
