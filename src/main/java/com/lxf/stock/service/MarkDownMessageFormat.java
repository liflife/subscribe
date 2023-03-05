package com.lxf.stock.service;

import com.lxf.stock.bean.Stock;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class MarkDownMessageFormat implements MessageFormat {
    @Override
    public String format(List<Stock> stocks) {

       String text ="## 监控股票列表 <br/>\n";
        for (Stock stock : stocks) {
            String guPiaoCode = stock.getGuPiaoCode();
            String guPiaoName = stock.getGuPiaoName();
            String currentPrice = stock.getCurrentPrice();
            String shouPianJia4Yesterday = stock.getShouPianJia4Yesterday();
            String maxPrice = stock.getMaxPrice();
            String minxPrice = stock.getMinxPrice();
            BigDecimal shouPianJia4YesterdayBigDecimal = new BigDecimal(shouPianJia4Yesterday);
            BigDecimal currentPriceBigDecimal = new BigDecimal(currentPrice);
            String currentPriceFontHtml="<font color='Black'>";
            if(currentPriceBigDecimal.compareTo(shouPianJia4YesterdayBigDecimal)>0){
                currentPriceFontHtml ="<font color='red' size=\"6\"  >";
            }else if(currentPriceBigDecimal.compareTo(shouPianJia4YesterdayBigDecimal)<0){
                currentPriceFontHtml ="<font color='green' size=\"4\"   >";
            }else {
                currentPriceFontHtml ="<font color='DarkGray' >";
            }
            text+=">  "+guPiaoName+":"+guPiaoCode+"<br/>\n";
            text+=">>  "+"昨天收盘价:"+ shouPianJia4Yesterday+"  ";
            text+="当前价格："+currentPriceFontHtml+currentPrice+"</font><br/>\n";
            text+=">>  "+"最高价:"+maxPrice+"  ";
            text+="最低价:"+minxPrice+"<br/>\n";
            text+="\n";
        }
        System.out.println(text);
        return text;
    }

    @Override
    public String formatText(List<String> datas) {
        String text ="## ChatGpt <br/>\n";
        for (String str : datas) {
            text+=">  "+str+"<br/>\n";
            text+="\n";
        }
        System.out.println(text);
        return text;
    }
}
