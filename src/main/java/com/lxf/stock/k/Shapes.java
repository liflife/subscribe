package com.lxf.stock.k;

import javax.swing.*;
import java.awt.*;

public class Shapes extends JLabel {
    private int x;
    private int y;
    private int width;
    private int height;

    Shapes(int x, int y, int width, int height){
        this.x =  x;
        this.y =  y;
        this.width =  width;
        this.height =  height;
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(x,y,width,height);

    }
}
