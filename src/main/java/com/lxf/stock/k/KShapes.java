package com.lxf.stock.k;

import javax.swing.*;
import java.awt.*;

public class KShapes extends JLabel {
    private int openPrice;
    private int highestPrice;
    private int minimumPrice;
    private int closingPrice;
    private int x;
    private int y;


    KShapes(int x,int y,int openPrice, int highestPrice, int minimumPrice, int closingPrice){
        this.x =  x;
        this.y =  y;
        this.openPrice =  openPrice;
        this.highestPrice =  highestPrice;
        this.minimumPrice =  minimumPrice;
        this.closingPrice =  closingPrice;
    }

    public void refresh(int openPrice, int highestPrice, int minimumPrice, int closingPrice){
        this.openPrice =  openPrice;
        this.highestPrice =  highestPrice;
        this.minimumPrice =  minimumPrice;
        this.closingPrice =  closingPrice;
        super.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int diff = highestPrice - minimumPrice;
        int codiff = closingPrice-openPrice;

        int x14xline= x;
        int y14xline= y;
        int x24xline= x;
        int y24xline= y14xline + (codiff>0?closingPrice - minimumPrice :openPrice - minimumPrice);

        int width = 10;
        int x4Rect = x - width/2 ;
        int y4Rect = y24xline;
        int height = Math.abs(codiff);



        int x14sline= x;
        int y14sline= y4Rect + height;
        int x24sline= x;
        int y24sline= y14sline+(codiff>0?highestPrice - closingPrice :highestPrice - openPrice);
        if(codiff>0){
            g.setColor(Color.RED);
        }else {
            g.setColor(Color.GREEN);
        }
        System.out.println(String.format("x14sline=%d,y14sline=%d,x24sline=%d,y24sline=%d,",x14sline,y14sline,x24sline,y24sline));
        System.out.println(String.format("x4Rect=%d,y4Rect=%d,width=%d,height=%d,",x4Rect,y4Rect,width,height));
        g.drawLine(x14sline,y14sline,x24sline,y24sline);
        g.drawLine(x14xline,y14xline,x24xline,y24xline);
        g.drawRect(x4Rect,y4Rect,width,height);
    }
}
