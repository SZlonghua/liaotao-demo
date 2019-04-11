package com.liaotao.demo.swing;

import javax.swing.*;

public class JFrameDemo {

    public void CreateJFrame() {
        JFrame jf = new JFrame("这是一个JFrame窗体");        // 实例化一个JFrame对象
        jf.setVisible(true);        // 设置窗体可视
        jf.setSize(500, 350);        // 设置窗体大小
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);        // 设置窗体关闭方式
    }

    public static void main(String[] args) {
        new JFrameDemo().CreateJFrame();        // 调用CreateJFrame()方法
    }
}
