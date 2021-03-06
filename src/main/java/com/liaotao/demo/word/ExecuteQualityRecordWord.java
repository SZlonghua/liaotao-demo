package com.liaotao.demo.word;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.net.URL;

@Slf4j
public class ExecuteQualityRecordWord {



    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        String s = getPath();
        String path = s+"ss.doc";



        InputStream is = new FileInputStream(path);
        OutputStream os = new FileOutputStream(new File("F:\\test.doc"));
        //ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        HWPFDocument doc = new HWPFDocument(is);
        /*//获取页眉
        Range headerStoryRange = doc.getHeaderStoryRange();
        //获取内容
        Range range = doc.getRange();
        //获取页脚
        Range footnoteRange = doc.getFootnoteRange();
        printInfo(range);
        //读表格
        readTable(range);
        //读列表
        readList(range);
        int i = range.numCharacterRuns();
        CharacterRun characterRun = range.getCharacterRun(i-1);*/
        //characterRun.insertAfter("日期");
        doc.write(os);
        is.close();
        os.close();

    }

    /**
     * 输出书签信息
     * @param bookmarks
     */
    private static void printInfo(Bookmarks bookmarks) {
        int count = bookmarks.getBookmarksCount();
        System.out.println("书签数量：" + count);
        Bookmark bookmark;
        for (int i=0; i<count; i++) {
            bookmark = bookmarks.getBookmark(i);
            System.out.println("书签" + (i+1) + "的名称是：" + bookmark.getName());
            System.out.println("开始位置：" + bookmark.getStart());
            System.out.println("结束位置：" + bookmark.getEnd());
        }
    }

    /**
     * 读表格
     * 每一个回车符代表一个段落，所以对于表格而言，每一个单元格至少包含一个段落，每行结束都是一个段落。
     * @param range
     */
    private static void readTable(Range range) {
        //遍历range范围内的table。
        TableIterator tableIter = new TableIterator(range);
        Table table;
        TableRow row;
        TableCell cell;
        while (tableIter.hasNext()) {
            table = tableIter.next();
            log.debug("输出table start");
            int rowNum = table.numRows();
            for (int j=0; j<rowNum; j++) {
                row = table.getRow(j);
                int cellNum = row.numCells();
                for (int k=0; k<cellNum; k++) {
                    cell = row.getCell(k);
                    //输出单元格的文本
                    System.out.println(cell.text().trim());
                }
            }
        }
    }

    /**
     * 读列表
     * @param range
     */
    private static void readList(Range range) {
        int num = range.numParagraphs();
        Paragraph para;
        for (int i=0; i<num; i++) {
            para = range.getParagraph(i);
            if (para.isInList()) {
                System.out.println("list: " + para.text());
            }
        }
    }

    /**
     * 输出Range
     * @param range
     */
    private static void printInfo(Range range) {
        log.debug("输出Range start");
        //获取段落数
        int paraNum = range.numParagraphs();
        System.out.println(paraNum);
        for (int i=0; i<paraNum; i++) {
            System.out.println("段落" + (i+1) + "：" + range.getParagraph(i).text());
        }
        int secNum = range.numSections();
        System.out.println(secNum);
        Section section;
        for (int i=0; i<secNum; i++) {
            section = range.getSection(i);

            System.out.println(section.getMarginLeft());
            System.out.println(section.getMarginRight());
            System.out.println(section.getMarginTop());
            System.out.println(section.getMarginBottom());
            System.out.println(section.getPageHeight());
            System.out.println(section.text());
        }
        log.debug("输出Range end");
    }

    public static URL getClassPath(){
        return Thread.currentThread().getContextClassLoader ().getResource("");
    }

    public static String getPath(){
        return getClassPath().getPath().toString();
    }
}
