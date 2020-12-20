package com.lxf.stock.service;

import com.lxf.stock.bean.Stock;

import java.util.List;

public interface MessageFormat {
    String format(List<Stock> stocks);
}
