package com.lxf.stock.service;

import com.lxf.stock.bean.Stock;

import java.math.BigDecimal;
import java.util.List;

public class HtmlMessageFormat implements MessageFormat {
    @Override
    public String format(List<Stock> stocks) {
        String text="<div><h1>监控股票列表</h1>";
        text+="<table>";
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
            text+="<tr>";
            text+="<td  colspan = '4' >"+guPiaoName+":"+guPiaoCode+"</td>";
            text+="</tr>";
            text+="<tr>";
            text+="<td  >"+"昨天收盘价"+"</td>"+"<td>"+shouPianJia4Yesterday+"</font></td>";
            text+="<td  >"+"当前价格"+"</td>"+"<td>"+currentPriceFontHtml+currentPrice+"</font></td>";
            text+="</tr>";
            text+="<tr>";
            text+="<td  >"+"最高价"+"</td>"+"<td>"+maxPrice+"</td>";
            text+="<td  >"+"最低价"+"</td>"+"<td>"+minxPrice+"</td>";
            text+="</tr>";
            text+="<tr>";
            text+="<td  colspan = '4' >------------------------------------------------------</td>";
            text+="</tr>";
        }
        text+="</table>";
        text+="</div>";
        System.out.println(text);
        return text;
    }

    @Override
    public String formatText(List<String> datas) {
        return null;
    }

}
