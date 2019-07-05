package com.liaotao.demo.word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.net.URL;

public class Xwpf {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stubInputStream is = new FileInputStream("D:\\aloha.docx");
        String s = getPath();
        String path = s+"ss.docx";



        InputStream is = new FileInputStream(path);
        // 这里是你要写入的文件
        XWPFDocument xdoc = new XWPFDocument(is);
        // 创建一个段落
        XWPFParagraph xpara = xdoc.createParagraph();

        // 一个XWPFRun代表具有相同属性的一个区域。
        /*XWPFRun run = xpara.createRun();
        run.setBold(true); // 加粗
        run.setText("加粗的内容");
        run = xpara.createRun();
        run.setColor("FF0000");
        run.setFontSize(15);
        run.setText("插入内容。");*/
        OutputStream os = new FileOutputStream("D:\\aloha.docx");
        xdoc.write(os);
        os.close();
    }


    public static URL getClassPath(){
        return Thread.currentThread().getContextClassLoader ().getResource("");
    }

    public static String getPath(){
        return getClassPath().getPath().toString();
    }
}
