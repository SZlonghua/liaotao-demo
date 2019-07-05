package com.liaotao.demo.word;

import com.google.common.base.CharMatcher;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.*;
import java.net.URL;
import java.util.Arrays;

public class WordUtil {

    public static URL getClassPath(){
        return Thread.currentThread().getContextClassLoader ().getResource("");
    }

    public static HWPFDocument getHWPFDocument(String path) throws IOException {
        InputStream stream = new FileInputStream(new File(path));
        if (path.endsWith(".doc")) {
            return new HWPFDocument(stream);
        } else {
            throw new IllegalArgumentException("word格式不正确");
        }
    }
}
