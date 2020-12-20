package com.lxf.stock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
public class StockService {
    private static Logger log = LoggerFactory.getLogger(StockService.class);
    private static String baseUrl = "http://hq.sinajs.cn/list=";
    private static String SZ = "sz";
    private static String SH = "sh";

    public String queryStock(String code) {
        log.info("queryStock.code={}",code);
        if(code==null){
            return null;
        }
        String prefix = "";
        if (code.startsWith("0")) {
            prefix = SZ;
        } else if (code.startsWith("6")) {
            prefix = SH;
        }
        String url = baseUrl + prefix + code;
        try {
            //Jsoup打开连接
            System.out.println(url);
            Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                    .ignoreContentType(true)
                    .get();
            if (doc != null) {
                String text = doc.body().text();
                String substring = text.substring(text.indexOf("=")+2,text.length()-2);
                System.out.println(substring);
                return substring;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
