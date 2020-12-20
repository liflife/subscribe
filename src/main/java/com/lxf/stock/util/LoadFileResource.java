package com.lxf.stock.util;


import com.lxf.stock.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class LoadFileResource {

    private static Logger logger = LoggerFactory.getLogger(StockService.class);

    /**
     * 从外部资源读取配置文件
     *
     * @return config
     */
    public static String loadConfigJsonFromFile() {
        String config = null;
        try {
            String outPath = LoadFileResource.class.getResource("/").getPath()+ File.separator + "config.json";
            InputStream is = new FileInputStream(outPath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            config = new String(buffer, "UTF-8");
        } catch (FileNotFoundException e) {
            logger.info("未扫描到外部配置文件");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }


    /**
     * 从resource读取版本文件
     *
     * @param fileName 文件名
     * @return 返回读取到文件
     */
    public static String loadJsonFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = LoadFileResource.class.getClassLoader().getResourceAsStream(fileName);
            assert is != null;
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * @param filePath 读入的文件路径
     * @return 返回str
     */
    public static String loadFile(String filePath) {
        String log = null;
        try {
            InputStream is = new FileInputStream(filePath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            log = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return log;
    }
}
