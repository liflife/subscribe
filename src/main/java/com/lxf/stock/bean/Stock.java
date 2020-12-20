package com.lxf.stock.bean;

import java.lang.reflect.Field;

public class Stock {
    private String guPiaoName;
    private String guPiaoCode;
    private String kaiPianJia;
    private String shouPianJia4Yesterday;
    private String currentPrice;
    private String maxPrice;
    private String minxPrice;
    //var hq_str_sz002307="北新路桥,8.700,8.820,9.080,9.310,8.630,9.080,9.100,108452484,968707402.800,42300,9.080,91100,9.070,17500,9.060,42200,9.050,19200,9.040,7048,9.100,16600,9.110,47300,9.120,20460,9.130,21400,9.140,2019-03-27,14:33:03,00";
    /**
     * 0：”大秦铁路”，股票名字；
     * 1：”27.55″，今日开盘价；
     * 2：”27.25″，昨日收盘价；
     * 3：”26.91″，当前价格；
     * 4：”27.55″，今日最高价；
     * 5：”26.20″，今日最低价；
     * 6：”26.91″，竞买价，即“买一”报价；
     * 7：”26.92″，竞卖价，即“卖一”报价；
     * 8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
     * 9：”589824680″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
     * 10：”4695″，“买一”申请4695股，即47手；
     * 11：”26.91″，“买一”报价；
     * 12：”57590″，“买二”
     * 13：”26.90″，“买二”
     * 14：”14700″，“买三”
     * 15：”26.89″，“买三”
     * 16：”14300″，“买四”
     * 17：”26.88″，“买四”
     * 18：”15100″，“买五”
     * 19：”26.87″，“买五”
     * 20：”3100″，“卖一”申报3100股，即31手；
     * 21：”26.92″，“卖一”报价
     * (22, 23), (24, 25), (26,27), (28, 29)分别为“卖二”至“卖四的情况”
     * 30：”2008-01-11″，日期；
     * 31：”15:05:32″，时间；
     */
    public String getGuPiaoName() {
        return guPiaoName;
    }

    public void setGuPiaoName(String guPiaoName) {
        this.guPiaoName = guPiaoName;
    }

    public String getKaiPianJia() {
        return kaiPianJia;
    }

    public void setKaiPianJia(String kaiPianJia) {
        this.kaiPianJia = kaiPianJia;
    }

    public String getShouPianJia4Yesterday() {
        return shouPianJia4Yesterday;
    }

    public void setShouPianJia4Yesterday(String shouPianJia4Yesterday) {
        this.shouPianJia4Yesterday = shouPianJia4Yesterday;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getMinxPrice() {
        return minxPrice;
    }

    public void setMinxPrice(String minxPrice) {
        this.minxPrice = minxPrice;
    }

    public String getGuPiaoCode() {
        return guPiaoCode;
    }

    public void setGuPiaoCode(String guPiaoCode) {
        this.guPiaoCode = guPiaoCode;
    }

    public String toMKString() {
        return "" + '\n'+
                "+ 股票名称:" + guPiaoName + '\n' +
                "股票代码:" + guPiaoCode + '\n' +
                "开盘价格:" + kaiPianJia + '\n' +
                "当前价格:" + currentPrice + '\n' +
                "最高价格:" + maxPrice + '\n' +
                "最低价格:" + minxPrice + '\n'
               ;
    }

    public static void main(String[] args) {
        Field[] declaredFields = Stock.class.getDeclaredFields();
        int index = 0;
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            name=name.substring(0,1).toUpperCase()+name.substring(1);
            System.out.println("stock.set"+name+"(split["+index+"]);");
            index++;
        }
    }
}
