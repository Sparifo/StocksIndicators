package com.upload.data.stocks.model;

import com.opencsv.bean.CsvBindByPosition;
import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class IndicatorCSV {

    @CsvBindByPosition(position = 0)
    private String ticker;
    private String indicatorName;
    @CsvBindByPosition(position = 1)
    private String per;
    @CsvBindByPosition(position = 2)
    private String date;
    @CsvBindByPosition(position = 3)
    private String time;
    @CsvBindByPosition(position = 4)
    private BigDecimal open;
    @CsvBindByPosition(position = 5)
    private BigDecimal high;
    @CsvBindByPosition(position = 6)
    private BigDecimal low;
    @CsvBindByPosition(position = 7)
    private BigDecimal close;
    @CsvBindByPosition(position = 8)
    private BigDecimal vol;
    @CsvBindByPosition(position = 9)
    private BigDecimal openint;

}
