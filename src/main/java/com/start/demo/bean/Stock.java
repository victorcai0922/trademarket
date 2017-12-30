package com.start.demo.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Entity
public class Stock implements Serializable {
    private static final long serialVersionUID = -1L;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    /**
     * 实时行情
     */
    @javax.persistence.Id
    @GeneratedValue
    private int Id;

    //代码
    private String code;

    //名称
    private String name;

    //涨跌幅
    private float chanepercent;

    // 现价
    private float trade;

    // 开盘价
    private float open;

    //最高价
    private float high;

    //最低价
    private float low;

    //时间
    private float time;

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getChanepercent() {
        return chanepercent;
    }

    public void setChanepercent(float chanepercent) {
        this.chanepercent = chanepercent;
    }

    public float getTrade() {
        return trade;
    }

    public void setTrade(float trade) {
        this.trade = trade;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getSettlement() {
        return settlement;
    }

    public void setSettlement(float settlement) {
        this.settlement = settlement;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public float getTurnoverratio() {
        return turnoverratio;
    }

    public void setTurnoverratio(float turnoverratio) {
        this.turnoverratio = turnoverratio;
    }

    //昨日收盘价
    private float settlement;

    //成交量
    private long volume;

    //换手率
    private float turnoverratio;






}
