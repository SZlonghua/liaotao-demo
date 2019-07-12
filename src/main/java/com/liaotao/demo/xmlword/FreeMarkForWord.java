package com.liaotao.demo.xmlword;

import com.liaotao.demo.word.Item;
import com.liaotao.demo.word.R;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkForWord {

    public static void main(String[] args) throws IOException, TemplateException {
        // 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 第二步：设置模板文件所在的路径。
        configuration.setDirectoryForTemplateLoading(new File(getPath()));
        // 第三步：设置模板文件使用的字符集。一般就是utf-8.
        configuration.setDefaultEncoding("utf-8");
        // 第四步：加载一个模板，创建一个模板对象。
        Template template = configuration.getTemplate("首件1.ftl");
        // 第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
        Map dataModel = new HashMap<>();

        List<R> rs =new ArrayList<>();
        R r = new R();
        List<Item> list =new ArrayList<>();
        for(int i=0;i<=8;i++){
            Item item=new Item();
            item.setSequece(i+1);
            item.setName("liaotao");
            list.add(item);
        }
        r.setList(list);
        rs.add(r);
        for(int j=0;j<=0;j++){
            rs.add(new R());
        }


        //向数据集中添加数据
        dataModel.put("rs", rs);
        // 第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
        Writer out = new FileWriter(new File("F:\\FreeMarkForWord.doc"));
        // 第七步：调用模板对象的process方法输出文件。
        template.process(dataModel, out);
        // 第八步：关闭流。
        out.close();
    }

    public static URL getClassPath(){
        return Thread.currentThread().getContextClassLoader ().getResource("");
    }

    public static String getPath(){
        return getClassPath().getPath().toString();
    }
}
