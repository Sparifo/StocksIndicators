package com.upload.data.stocks.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IndicatorCSV {

    @CsvBindByPosition(position = 0)
    private String ticker;
    @CsvBindByPosition(position = 1)
    private String indicatorName;
    @CsvBindByPosition(position = 2)
    private String per;
    @CsvBindByPosition(position = 3)
    private String date;
    @CsvBindByPosition(position = 4)
    private String time;
    @CsvBindByPosition(position = 5)
    private BigDecimal open;
    @CsvBindByPosition(position = 6)
    private BigDecimal high;
    @CsvBindByPosition(position = 7)
    private BigDecimal low;
    @CsvBindByPosition(position = 8)
    private BigDecimal close;
    @CsvBindByPosition(position = 9)
    private BigDecimal vol;
    @CsvBindByPosition(position = 10)
    private BigDecimal openint;

}
