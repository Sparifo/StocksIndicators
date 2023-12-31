package com.upload.data.stocks.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Indicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStockIndicators;

    @Column(name = "TICKER")
    private String ticker;
    @Column(name = "INDICATOR")
    private String indicatorName;
    @Column(name = "PER")
    private String per;
    @Column(name = "DATE")
    private Date date;
    @Column(name = "TIME")
    private String time;
    @Column(name = "OPEN")
    private BigDecimal open;
    @Column(name = "HIGH")
    private BigDecimal high;
    @Column(name = "LOW")
    private BigDecimal low;
    @Column(name = "CLOSE")
    private BigDecimal close;
    @Column(name = "VOL")
    private BigDecimal vol;
    @Column(name = "OPENINT")
    private BigDecimal openint;

    public Indicator(IndicatorCSV indicatorCSV) throws ParseException {
        this.ticker = indicatorCSV.getTicker();
        this.indicatorName = indicatorCSV.getIndicatorName();
        this.per = indicatorCSV.getPer();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        this.date = formatter.parse(indicatorCSV.getDate());
        this.time = indicatorCSV.getTime();
        this.open = indicatorCSV.getOpen();
        this.high = indicatorCSV.getHigh();
        this.low = indicatorCSV.getLow();
        this.close = indicatorCSV.getClose();
        this.vol = indicatorCSV.getVol();
        this.openint = indicatorCSV.getOpenint();
    }
}
