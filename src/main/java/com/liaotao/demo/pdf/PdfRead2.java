package com.liaotao.demo.pdf;


import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class PdfRead2 {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\10535\\Desktop\\mes\\首件2.pdf");
        try {
            // 新建一个PDF解析器对象
            //PDFParser parser = new PDFParser(new RandomAccessFile(file,"rw"));
            // 对PDF文件进行解析
            //parser.parse();
            // 获取解析后得到的PDF文档对象
            //PDDocument pdfdocument = parser.getPDDocument();

            PDDocument pdDocument = PDDocument.load(file);
            PDPage page = pdDocument.getPage(0);
            PDFStreamParser parser = new PDFStreamParser(page);
            parser.parse();
            List<Object> tokens = parser.getTokens();
            for(int j = 0; j < tokens.size(); j++){
                Object next = tokens.get(j);
                /*if (next instanceof Operator){
                    Operator op = (Operator) next;
                    //System.out.println(next.getClass());
                    if(op.getName().equals(OperatorName.SHOW_TEXT)){
                        COSString previous = (COSString) tokens.get(j - 1);
                        System.out.println("--:"+previous.getString());
                    }
                    if(op.getName().equals(OperatorName.SHOW_TEXT_ADJUSTED)){

                    }
                }*/
                if(next instanceof COSString){
                    COSString cosString = (COSString) next;
                    System.out.println("COSString:"+cosString);
                }
                if(next instanceof COSName){
                    COSName cosString = (COSName) next;
                    System.out.println("COSName:"+cosString.getName());
                }

            }


            File targetFile = new File("C:\\Users\\10535\\Desktop\\mes\\首件", "test.pdf");
            pdDocument.save(targetFile);

            //关闭
            pdDocument.close();

        } catch (Exception e) {
            System.out.println("读取PDF文件" + file.getAbsolutePath() + "生失败！" + e);
            e.printStackTrace();
        } finally {
        }
    }
}
