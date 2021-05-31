package com.infiniteskills.data.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
public class StockDailyRecord {
	@Id
    private Integer recordId;
    @ManyToOne
    @JoinColumn(name = "STOCK_ID", nullable = false)
    private Stock stock;
    private Float priceOpen;
    private Float priceClose;
    private Float priceChange;
    private Long volume;
    private Date date;

    public StockDailyRecord() {
    }

    public StockDailyRecord(Stock stock, Date date) {
        this.stock = stock;
        this.date = date;
    }

    public StockDailyRecord(Stock stock, Float priceOpen, Float priceClose,
            Float priceChange, Long volume, Date date) {
        this.stock = stock;
        this.priceOpen = priceOpen;
        this.priceClose = priceClose;
        this.priceChange = priceChange;
        this.volume = volume;
        this.date = date;
    }

    public Integer getRecordId() {
        return this.recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }


    public Stock getStock() {
        return this.stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Float getPriceOpen() {
        return this.priceOpen;
    }

    public void setPriceOpen(Float priceOpen) {
        this.priceOpen = priceOpen;
    }


    public Float getPriceClose() {
        return this.priceClose;
    }

    public void setPriceClose(Float priceClose) {
        this.priceClose = priceClose;
    }

    public Float getPriceChange() {
        return this.priceChange;
    }

    public void setPriceChange(Float priceChange) {
        this.priceChange = priceChange;
    }

    public Long getVolume() {
        return this.volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE", nullable = false, length = 10)
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
