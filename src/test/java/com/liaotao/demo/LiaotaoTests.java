package com.liaotao.demo;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.stat.TableStat.Column;
import com.alibaba.druid.stat.TableStat.Name;
import com.alibaba.druid.util.JdbcConstants;
import com.liaotao.demo.test.ExportTableAliasVisitor;
import com.liaotao.demo.test.HelloWorld;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.junit.Test;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class LiaotaoTests {

	@Test
	public void test12() throws SQLException {
		List<Double> list = new ArrayList<>();
		list.add(1.2);
		list.add(1.3);
		list.add(1.4);
		Double aDouble = list.stream().reduce((one, two) -> one * two).orElse(null);
		Double s=1.0;
		for(Double d:list){
			s=s*d;
		}
		BigDecimal b =new BigDecimal(1.2895);
		//b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		DecimalFormat df = new DecimalFormat("#0.00");
		System.out.printf(df.format(b)+"\n");
		System.out.printf(aDouble.toString()+"\n");
		System.out.printf(s.toString());
	}

	@Test
	public void test() throws SQLException {
		String[] ids ={"1","2"};
		List<String> list = Arrays.asList(ids);
		System.out.println(list.toString());

	}

	@Test
	public void contextLoads() throws SQLException {
		//File file = new File("F:\\tmp\\tomcat.5359390081760107931.8081\\work\\Tomcat\\localhost\\ROOT");
		//File[] fileList = file.listFiles();
		List<File> fileList = new ArrayList();
		getFiles(fileList,"F:\\tmp\\tomcat.5359390081760107931.8081\\work\\Tomcat\\localhost\\ROOT");

		for (File file:fileList) {
			System.out.println("ssss:"+file.getPath());
			if (file.isFile()&&file.exists()) {//判断是否为文件
				String fileName  = file.getName();
				System.out.println("dddd:"+fileName);
			}
		}

	}


	@Test
	public void contextLoads2() throws SQLException {
		String path = "F:\\tmp\\tomcat.5359390081760107931.8081\\work\\Tomcat\\localhost\\ROOT";
		File file = new File(path);//File类型可以是文件也可以是文件夹
		File[] fileList = file.listFiles();

		System.out.println("length:"+fileList.length);
		for (int i=0;i<fileList.length;i++) {
			System.out.println("ssss:"+file.getPath());
			if (file.isFile()&&file.exists()) {//判断是否为文件
				String fileName  = file.getName();
				System.out.println("dddd:"+fileName);
			}
		}

	}

	@Test
	public void contextLoads3() throws SQLException {
		String path = "F:\\tmp\\tomcat.5359390081760107931.8081\\work\\Tomcat\\localhost\\ROOT";
		File file = new File(path);//File类型可以是文件也可以是文件夹
		//File[] fileList = file.listFiles();
		List<File> allFile = getAllFile(file);
		for (File f:allFile){
			System.out.println(f.getPath());
		}
		/*Collection<File> txt = FileUtils.listFiles(file, FileFilterUtils.suffixFileFilter("txt"), DirectoryFileFilter.INSTANCE);
		ArrayList<File> fileList = new ArrayList<>(txt);

		System.out.println("length:"+fileList.size());
		for (int i=0;i<fileList.size();i++) {
			System.out.println("ssss:"+fileList.get(i).getPath());
			if (fileList.get(i).isFile()&&fileList.get(i).exists()) {//判断是否为文件
				String fileName  = fileList.get(i).getName();
				System.out.println("dddd:"+fileList.get(i));
			}
		}*/

	}

	private List<File> getAllFile(File file){
		List<File> list = new ArrayList<>();
		File[] files = file.listFiles();
		for (int i=0;i<files.length;i++) {
			if (files[i].isFile()&&files[i].exists()) {//判断是否为文件
				list.add(files[i]);
			}else {
				list.addAll(getAllFile(files[i]));
			}
		}
		return list;
	}

	private void getFiles(List<File> fileList, String path) {
		File[] allFiles = new File(path).listFiles();
		for (int i = 0; i < allFiles.length; i++) {
			File file = allFiles[i];

			if (file.isFile()) {
				fileList.add(file);
			} else  {
				getFiles(fileList, file.getAbsolutePath());
			}
		}
	}


}
