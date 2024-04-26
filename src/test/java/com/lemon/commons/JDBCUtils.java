package com.lemon.commons;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Project: JAVA_33_32_31
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: ©2021 版权所有 湖南省零檬信息技术有限公司
 * @Author: luojie
 * @Create: 2021-10-29 21:30
 * @Desc：
 **/
public class JDBCUtils {

    public static Connection getConnection() {
        //定义数据库连接
        //Oracle：jdbc:oracle:thin:@localhost:1521:DBName
        //SqlServer：jdbc:microsoft:sqlserver://localhost:1433; DatabaseName=DBName
        //MySql：jdbc:mysql://localhost:3306/DBName
        String url = "jdbc:mysql://api.lemonban.com/futureloan?useUnicode=true&characterEncoding=utf-8";
        String user = "future";
        String password = "123456";
        //定义数据库连接对象
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) throws Exception {
//        update();
//        insert();
        //查询单行单列数据
//        singleResult();
//        Multiline();
//        MultipleColumns();
    }

    /**
     * 单行多列
     * @throws SQLException
     */
    public static void MultipleColumns() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select sum(leave_amount) as amount ,count(*) as count from member where id < 10;";
        Connection conn = getConnection();
        Map<String, Object> resultSet = queryRunner.query(conn, sql, new MapHandler());
        System.out.println(resultSet);
    }

    /**
     * 多行多列
     * @throws SQLException
     */
    public static void Multiline() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from member where id < 10;";
        Connection conn = getConnection();
        List<Map<String, Object>> resultSet = queryRunner.query(conn, sql, new MapListHandler());
        for (Map<String, Object> map : resultSet) {
//            System.out.println(map);
            System.out.println(map.get("id"));
            System.out.println(map.get("mobile_phone"));
        }
    }

    /**
     * 查询单行单列数据
     * @throws SQLException
     */
    public static Object singleResult(String sql) throws SQLException {
        if(StringUtils.isNotBlank(sql)) {
            QueryRunner queryRunner = new QueryRunner();
            Connection conn = getConnection();
            Object result = queryRunner.query(conn, sql, new ScalarHandler<>());
            return result;
        }
        return null;
    }

    public static void insert() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into member VALUES (null,'flyfish','25D55AD283AA400AF464C76D713C07AD','18062159190',1,1000000000,now());";
        Connection conn = getConnection();
        int count = queryRunner.update(conn, sql);
        System.out.println(count);
    }

    public static void update() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "update member set reg_name = '飞鱼' where reg_name = 'flyfish';";
        Connection conn = getConnection();
        int count = queryRunner.update(conn, sql);
        System.out.println(count);
    }


}
