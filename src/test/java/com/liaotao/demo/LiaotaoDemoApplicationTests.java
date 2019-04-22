package com.liaotao.demo;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.dialect.postgresql.visitor.PGSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.stat.TableStat.*;
import com.alibaba.druid.util.JdbcConstants;
import com.liaotao.demo.test.ExportTableAliasVisitor;
import com.liaotao.demo.test.HelloWorld;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class LiaotaoDemoApplicationTests {

	@Test
	public void contextLoads() throws SQLException {

		Connection conn = getConn();

		String sql = "select table_name  from information_schema.tables where table_schema=?";
		//预编译SQL，减少sql执行
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1,"factory_application");
		//执行
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()){
			String table_name = rs.getString("table_name");
			System.out.println(table_name);
		}

		rs.close();
		ptmt.close();
		conn.close();
	}


	private static Connection getConn() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/factory_application?useSSL=false";
		String username = "root";
		String password = "root";
		Connection conn = null;
		try {
			DriverManager.setLogWriter(new PrintWriter(System.out));
			Class.forName(driver); //classLoader,加载对应驱动

			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}


	@Test
	public void contextLoads1()  {
		Method[] declaredMethods = HelloWorld.class.getDeclaredMethods();
		if(declaredMethods!=null&&declaredMethods.length>0){
			for(Method method:declaredMethods){
				Parameter[] parameters = method.getParameters();
				for(Parameter parameter:parameters){
					System.out.println(parameter.getName());
				}

			}
		}
	}




	@Test
	public void contextLoads2()  {
		final String dbType = JdbcConstants.MYSQL; // 可以是ORACLE、POSTGRESQL、SQLSERVER、ODPS等
		String sql = "select * from t";
		List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);

		System.out.println("sssssssssssss");
	}

	@Test
	public void contextLoads3()  {
		String sql= ""
				+ "insert into tar select * from boss_table bo, ("
				+ "select a.f1, ff from emp_table a "
				+ "inner join log_table b "
				+ "on a.f2 = b.f3"
				+ ") f "
				+ "where bo.f4 = f.f5 "
				+ "group by bo.f6 , f.f7 having count(bo.f8) > 0 "
				+ "order by bo.f9, f.f10;"
				+ "select func(f) from test1; "
				+ "";
		String dbType = JdbcConstants.MYSQL;

		//格式化输出
		//String result = SQLUtils.format(sql, dbType);
		//System.out.println(result); // 缺省大写格式
		List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);

		//解析出的独立语句的个数
		System.out.println("size is:" + stmtList.size());
		for (int i = 0; i < stmtList.size(); i++) {

			SQLStatement stmt = stmtList.get(i);

			ExportTableAliasVisitor visitor = new ExportTableAliasVisitor();
			stmt.accept(visitor);
			System.out.println(visitor.getConditions().toString());
			Map<String, SQLTableSource> aliasmap = visitor.getAliasMap();
			for (Iterator iterator = aliasmap.keySet().iterator(); iterator.hasNext();) {
				Object next = iterator.next();
				if(next!=null){
					String key = next.toString();
					System.out.println("[ALIAS]" + key + " - " + aliasmap.get(key));
				}

			}
			Set<Column> groupby_col = visitor.getGroupByColumns();
			//
			for (Iterator iterator = groupby_col.iterator(); iterator.hasNext();) {
				Column column = (Column) iterator.next();
				System.out.println("[GROUP]" + column.toString());
			}
			//获取表名称
			System.out.println("table names:");
			Map<Name, TableStat> tabmap = visitor.getTables();
			for (Iterator iterator = tabmap.keySet().iterator(); iterator.hasNext();) {
				Name name = (Name) iterator.next();
				System.out.println(name.toString() + " - " + tabmap.get(name).toString());
			}
			//System.out.println("Tables : " + visitor.getCurrentTable());
			//获取操作方法名称,依赖于表名称
			System.out.println("Manipulation : " + visitor.getTables());
			//获取字段名称
			System.out.println("fields : " + visitor.getColumns());
		}
	}


	@Test
	public void contextLoads4()  {
		String sql = "SELECT\n" +
				"	bm.machine_id machineId,\n" +
				"	bm.code code,\n" +
				"	bm.name name,\n" +
				"	bm.id id,\n" +
				"	bm.assdt_id assdtId,\n" +
				"	bm.serial_number serialNumber,\n" +
				"	bm.category_id categoryId,\n" +
				"	bm.placement placement,\n" +
				"	bm.flag flag,\n" +
				"	bm.department_id departmentId,\n" +
				"	bm.purchase_date purchaseDate,\n" +
				"	bm.purchase_cost purchaseCost,\n" +
				"	bm.life life,\n" +
				"	bm.utilization_time utilizationTime,\n" +
				"	bm.tank_capacity tankCapacity,\n" +
				"	bm.unit unit,\n" +
				"	bm.maintenance_staff maintenanceStaff,\n" +
				"	bm.technical_staff technicalStaff,\n" +
				"	bm.manager_staff managerStaff,\n" +
				"	bm.new_mono newMono,\n" +
				"	bm.image image,\n" +
				"	bm.sort_code sortCode,\n" +
				"	bm.enabled enabled,\n" +
				"	bm.description description,\n" +
				"	bm.create_on createOn,\n" +
				"	bm.create_by createBy,\n" +
				"	bm.modified_on modifiedOn,\n" +
				"	bm.modified_by modifiedBy,\n" +
				"	bi.item_name flagName,\n" +
				"	bi2.item_name placementName,\n" +
				"	o.department_name\n" +
				"FROM\n" +
				"	base_machine bm\n" +
				"LEFT JOIN base_items_target bi ON bi.id = bm.flag\n" +
				"LEFT JOIN base_items_target bi2 ON bi2.id = bm.placement\n" +
				"LEFT JOIN organization o ON (\n" +
				"	bm.department_id = o.uuid\n" +
				"	AND o.typesof = '部门'\n" +
				")\n"+
				"WHERE 1 = 1 or 1=1";
		String s = SQLUtils.addCondition(sql, "bm.groupId=1", null);
		System.out.println(s);
	}
}
