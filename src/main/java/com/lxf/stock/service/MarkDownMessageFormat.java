package com.lxf.stock.service;

import com.lxf.stock.bean.Stock;

import java.util.List;
import java.util.stream.Collectors;

public class MarkDownMessageFormat implements MessageFormat {
    @Override
    public String format(List<Stock> stocks) {
        String text = stocks.stream().map(stock -> {
            return stock.toMKString();
        }).collect(Collectors.joining("\n"));
        text ="## 股票列表 \n"+text;
        return text;
    }
}
