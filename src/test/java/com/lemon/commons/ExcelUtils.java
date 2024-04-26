package com.lemon.commons;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.lemon.pojo.CaseInfo;

import java.io.FileInputStream;
import java.util.List;

/**
 * @Project: JAVA_33_32_31
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: ©2021 版权所有 湖南省零檬信息技术有限公司
 * @Author: luojie
 * @Create: 2021-10-25 20:35
 * @Desc：
 **/
public class ExcelUtils {

    /**
     * 返回指定sheet的所有数据
     * @param startSheetIndex  从1开始，底层做了-1操作。
     * @return
     */
    public static List<CaseInfo> getExcelSheetAllData(int startSheetIndex) {
        List<CaseInfo> caseInfoList = null;
        try {
            //1、加载excel
            FileInputStream fis = new FileInputStream(Constants.EXCEL_PATH);
            //2、导入参数（excel数据导入java代码）
            ImportParams importParams = new ImportParams();
            //设置sheet开始索引
            importParams.setStartSheetIndex(startSheetIndex - 1);
            //3、加载数据
            //importExcel(execl文件，映射类.class,导入参数对象)
            caseInfoList = ExcelImportUtil.importExcel(fis, CaseInfo.class, importParams);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return caseInfoList;
    }

    /**
     * 返回指定sheet的指定数据
     * @param startSheetIndex       开始sheet，从1开始，底层做了-1操作。
     * @param startRows             开始行，从1开始，底层做了-1操作。
     * @param readRows              读取多少行。如果传入0就是默认a值，读到末尾
     * @return
     */
    public static List<CaseInfo> getExcelSheetPartData(int startSheetIndex,int startRows,int readRows) {
        List<CaseInfo> caseInfoList = null;
        try {
            //1、加载excel
            FileInputStream fis = new FileInputStream(Constants.EXCEL_PATH);
            //2、导入参数（excel数据导入java代码）
            ImportParams importParams = new ImportParams();
            //设置sheet开始索引
            importParams.setStartSheetIndex(startSheetIndex - 1);
            //设置开始row
            importParams.setStartRows(startRows - 1);
            //设置读取多少行
            importParams.setReadRows(readRows);
            //3、加载数据/*-+
            //importExcel(execl文件，映射类.class,导入参数对象)
            caseInfoList = ExcelImportUtil.importExcel(fis, CaseInfo.class, importParams);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return caseInfoList;
    }

    public static void main(String[] args) {
        System.out.println(getExcelSheetPartData(2, 1, 1));
    }




}
