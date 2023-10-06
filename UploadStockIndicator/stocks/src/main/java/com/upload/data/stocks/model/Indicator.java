package com.upload.data.stocks.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Indicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;
    private String code;
    private String region;
    private String mounth;
    private String name;
    private String type;
    private String year;
    private String value;
    private String currency;
    private String attribute;

    public Indicator() {
    }

    public Indicator(int id, String code, String region, String mounth, String name, String type, String year, String value, String currency, String attribute) {
        this.idProduct = id;
        this.code = code;
        this.region = region;
        this.mounth = mounth;
        this.name = name;
        this.type = type;
        this.year = year;
        this.value = value;
        this.currency = currency;
        this.attribute = attribute;
    }
}
