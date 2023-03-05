package com.lxf.stock.k;

import javax.swing.*;
import java.awt.*;

public class KLine {
    // 得到显示器屏幕的宽高
    public int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    // 定义窗体的宽高
    public int windowsWedth = 600;
    public int windowsHeight = 600;
    /**{
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
    private  void createAndShowGUI() {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jPanel=new JPanel();    //创建面板
        KShapes kshapes1 = new KShapes(60, 200, 10, 50,18,21);

        jPanel.add(kshapes1);
//        jPanel.add(kshapes2);
//        jPanel.add(kshapes3);
//        jPanel.add(kshapes4);
        jPanel.setLayout(new FlowLayout(FlowLayout.LEADING,20,20));
//        jPanel.setBackground(Color.black);    //设置背景色

        frame.getContentPane().add(kshapes1);    //添加面板到容器
//        // 设置窗体位置和大小
        frame.setBounds((width - windowsWedth) / 2,
                (height - windowsHeight) / 2, windowsWedth, windowsHeight);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // 显示应用 GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new KLine().createAndShowGUI();
            }
        });
    }
}
