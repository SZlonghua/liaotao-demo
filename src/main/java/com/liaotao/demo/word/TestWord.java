package com.liaotao.demo.word;

import com.google.common.base.CharMatcher;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class TestWord {
    public static void main(String[] args) {
        // s = TestWord.class.getResource("/").getPath().toString();
        String s = WordUtil.getClassPath().getPath().toString();
        String path = s+"首件.doc";
        List<String> strings = readWordFile(path);
        strings.stream().forEach(s1 -> System.out.println(s1));
    }


    public static <T> List<String> readWordFile(String path) {
        List<String> contextList = new ArrayList<String>();
        InputStream stream = null;
        try {
            stream = new FileInputStream(new File(path));
            if (path.endsWith(".doc")) {
                HWPFDocument document = new HWPFDocument(stream);
                WordExtractor extractor = new WordExtractor(document);
                String[] contextArray = extractor.getParagraphText();
                Arrays.asList(contextArray).forEach(context -> contextList.add(CharMatcher.whitespace().removeFrom(context)));
                extractor.close();
                document.close();
            } else {
                log.debug("此文件{}不是word文件", path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != stream) try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.debug("读取word文件失败");
            }
        }
        return contextList;
    }
}
