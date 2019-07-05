package com.liaotao.demo.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;

public class Demo {

    private static JFrame frame;
    private static  JTextField userText;

    private static JButton loginButton;
    public static void main(String[] args) {
        // 创建 JFrame 实例
        frame = new JFrame("产研解密工具");
        // Setting the width and height of frame
        frame.setSize(450, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();
        // 添加面板
        frame.add(panel);
        /*
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel);

        // 设置界面可见
        frame.setVisible(true);

        //设置窗体打开在电脑屏幕居中
        int windowWidth = frame.getWidth(); //获得窗口宽
        int windowHeight = frame.getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
    }

    private static class Button1Handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            //自己对应的按钮事件
            //String content = "muhc5/A40WhZWV6CmjUpSOXz2SJlrvQzhxR45zTNNAkC7Y6uVRNGBfrZguONVEcSc1ticOHSnYmjNuLH8bvBugy9CTm5Qykw6Mi+MD7AJPnwXkJGFzfZdL1mC2j9v6H6qQvDt6N5A1xyII0z0QgrVw==";
            String content=userText.getText();
            try {
                byte[] bytes = Base64.getDecoder().decode(content);
                //String contentJson = DesUtil.decoderDesOfByte("自己解密对应的key", bytes);
                String contentJson = content;
                        System.out.println(contentJson);
                if(contentJson==null){
                    JOptionPane.showMessageDialog(frame, "不是正确的加密串");
                }else {
                    JOptionPane.showMessageDialog(frame, contentJson);
                }
            }catch (Exception e1){
                JOptionPane.showMessageDialog(frame, "不是正确的加密串");
            }
        }
    }

    //清空按钮的监听事件
    private static class cleanButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            userText.setText("");
        }
    }

    private static void placeComponents(JPanel panel) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("字符串:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(10,20,65,25);
        panel.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */
        userText = new JTextField(20);
        userText.setBounds(100,20,300,25);
        panel.add(userText);


        // 创建破解按钮
        loginButton = new JButton("破解");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new Demo.Button1Handler());
        panel.add(loginButton);

        //创建清空按钮
        JButton cleanButton = new JButton("清空");
        cleanButton.setBounds(100, 80, 80, 25);
        cleanButton.addActionListener(new Demo.cleanButtonHandler());
        panel.add(cleanButton);
    }
}
