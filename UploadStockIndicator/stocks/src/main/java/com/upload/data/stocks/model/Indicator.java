package com.upload.data.stocks.model;



import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Data

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

    public Indicator(IndicatorCSV indicatorCSV){
        this.ticker = indicatorCSV.getTicker();
        this.indicatorName = indicatorCSV.getIndicatorName();
        this.per = indicatorCSV.getPer();
        this.date = indicatorCSV.getDate();
        this.time = indicatorCSV.getTime();
        this.open = indicatorCSV.getOpen();
        this.high = indicatorCSV.getHigh();
        this.low = indicatorCSV.getLow();
        this.close = indicatorCSV.getClose();
        this.vol = indicatorCSV.getVol();
        this.openint = indicatorCSV.getOpenint();
    }
}
