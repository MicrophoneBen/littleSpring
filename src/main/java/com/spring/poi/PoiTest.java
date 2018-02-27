package com.spring.poi;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

/**
 * @author:独角兽zhuojl
 * @date :2017/11/29
 * @saysth:
 */
public class PoiTest {

    public static void main(String[] args) {
        PoiTest poiTest = new PoiTest();
        poiTest.exportWord(null);
    }

    public void fillTable(XWPFTable table) {
        for (int rowIndex = 0; rowIndex < table.getNumberOfRows(); rowIndex++) {
            XWPFTableRow row = table.getRow(rowIndex);
            row.setHeight(380);
            for (int colIndex = 0; colIndex < row.getTableCells().size(); colIndex++) {
                XWPFTableCell cell = row.getCell(colIndex);
                if (rowIndex % 2 == 0) {
                    setCellText(cell, " cell " + rowIndex + colIndex + " ", "D4DBED", 1000);
                } else {
                    setCellText(cell, " cell " + rowIndex + colIndex + " ", "AEDE72", 1000);
                }
            }
        }
    }

    public void setCellText(XWPFTableCell cell, String text, String bgcolor, int width) {
        CTTc cttc = cell.getCTTc();
        CTTcPr cellPr = cttc.addNewTcPr();
        cellPr.addNewTcW().setW(BigInteger.valueOf(width));
        //cell.setColor(bgcolor);
        CTTcPr ctPr = cttc.addNewTcPr();
        CTShd ctshd = ctPr.addNewShd();
        ctshd.setFill(bgcolor);
        ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
        cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
        cell.setText(text);
    }

    /**
     * @Description: 跨列合并
     */
    public void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    /**
     * @Description: 跨行合并
     * @see ://stackoverflow.com/questions/24907541/row-span-with-xwpftable
     */
    public void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if (rowIndex == fromRow) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    public void setTableWidth(XWPFTable table, String width) {
        CTTbl ttbl = table.getCTTbl();
        CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
        CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
        CTJc cTJc = tblPr.addNewJc();
        cTJc.setVal(STJc.Enum.forString("center"));
        tblWidth.setW(new BigInteger(width));
        tblWidth.setType(STTblWidth.DXA);
    }

    public void fillTable2(XWPFTable table) {
        for (int rowIndex = 0; rowIndex < table.getNumberOfRows(); rowIndex++) {
            XWPFTableRow row = table.getRow(rowIndex);
            row.setHeight(380);
            for (int colIndex = 0; colIndex < row.getTableCells().size(); colIndex++) {
                XWPFTableCell cell = row.getCell(colIndex);
                if (rowIndex % 2 == 0) {
                    setCellText(cell, " cell " + rowIndex + colIndex + " ", "D4DBED", 1800);
                } else {
                    setCellText(cell, " cell " + rowIndex + colIndex + " ", "AEDE72", 1800);
                }
            }
        }
    }

    public void createTable(XWPFDocument doc, String str) {
        XWPFTable table = null;
        table = doc.createTable(1, 1);
        setTableWidth(table, "9000");
        XWPFTableRow row = null;
        row = table.getRow(0);
        row.setHeight(380);
        XWPFTableCell cell = null;
        cell = row.getCell(0);
        setCellText(cell, str, "CCCCCC", 9000);

    }

    public void exportWord(OutputStream outputStream) {
        // Document 对应一个word应用文件
        XWPFDocument doc = new XWPFDocument();

        createTable(doc, "renyuanxinxi");

        XWPFTable table1 = doc.createTable(1, 6);
        XWPFTableRow row0 = table1.getRow(0);
        row0.setHeight(380);
        XWPFTableCell cell = null;
        cell = row0.getCell(0);
        setCellText(cell, " 人员编号", "FFFFFF", 1500);
        cell = row0.getCell(1);
        setCellText(cell, " 文本", "FFFFFF", 1500);
        cell = row0.getCell(2);
        setCellText(cell, " 姓名", "FFFFFF", 1500);
        cell = row0.getCell(3);
        setCellText(cell, " 文本", "FFFFFF", 1500);
        cell = row0.getCell(4);
        setCellText(cell, " 身份证号", "FFFFFF", 1500);
        cell = row0.getCell(5);
        setCellText(cell, " 文本", "FFFFFF", 1500);


        createTable(doc, "jiancejihua");

        XWPFTable table2 = doc.createTable(2, 6);
        XWPFTableRow row = null;
        row.setHeight(380);
        row = table2.getRow(0);
        cell = row.getCell(0);
        setCellText(cell, " 处室", "FFFFFF", 1500);
        cell = row.getCell(1);
        setCellText(cell, " 文本", "FFFFFF", 1500);
        cell = row.getCell(2);
        setCellText(cell, " 科室", "FFFFFF", 1500);
        cell = row.getCell(3);
        setCellText(cell, " 文本", "FFFFFF", 1500);
        cell = row.getCell(4);
        setCellText(cell, " 单位", "FFFFFF", 1500);
        cell = row.getCell(5);
        setCellText(cell, " 文本", "FFFFFF", 1500);
        row = table2.getRow(1);
        cell = row.getCell(0);
        setCellText(cell, " 生产单元", "FFFFFF", 1500);
        cell = row.getCell(1);
        setCellText(cell, " 文本", "FFFFFF", 1500);
        cell = row.getCell(2);
        setCellText(cell, " 接口人", "FFFFFF", 1500);
        cell = row.getCell(3);
        setCellText(cell, " 文本", "FFFFFF", 1500);
        cell = row.getCell(4);
        setCellText(cell, " 监测计划状态", "FFFFFF", 1500);
        cell = row.getCell(5);
        setCellText(cell, " 文本", "FFFFFF", 1500);

        createTable(doc, "异常描述");
        XWPFTable table3 = doc.createTable(4, 4);
        row = table3.getRow(0);
        //row.setHeight(380);
        cell = row.getCell(0);
        setCellText(cell, " 监测类型", "FFFFFF", 1500);
        cell = row.getCell(1);
        setCellText(cell, " 文本", "FFFFFF", 3000);
        cell = row.getCell(2);
        setCellText(cell, " 异常类型", "FFFFFF", 1500);
        cell = row.getCell(3);
        setCellText(cell, " 文本", "FFFFFF", 3000);
        row = table2.getRow(1);
        cell = row.getCell(0);
        setCellText(cell, " 机组", "FFFFFF", 1500);
        cell = row.getCell(1);
        setCellText(cell, " 文本", "FFFFFF", 3000);
        cell = row.getCell(2);
        setCellText(cell, " 时间", "FFFFFF", 1500);
        cell = row.getCell(3);
        setCellText(cell, " 文本", "FFFFFF", 3000);
        row = table2.getRow(2);
        cell = row.getCell(0);
        setCellText(cell, " 异常值", "FFFFFF", 1500);
        cell = row.getCell(1);
        setCellText(cell, " 文本", "FFFFFF", 3000);
        cell = row.getCell(2);
        setCellText(cell, " 异常值单位", "FFFFFF", 1500);
        cell = row.getCell(3);
        setCellText(cell, " 文本", "FFFFFF", 3000);
        row = table2.getRow(3);
        cell = row.getCell(0);
        setCellText(cell, " 发起人", "FFFFFF", 1500);
        cell = row.getCell(1);
        setCellText(cell, " 文本", "FFFFFF", 3000);
        cell = row.getCell(2);
        setCellText(cell, " 发起时间", "FFFFFF", 1500);
        cell = row.getCell(3);
        setCellText(cell, " 文本", "FFFFFF", 3000);

        createTable(doc, "辐射防护组调查（500个字符）");
        XWPFTable table4 = doc.createTable(1, 2);
        row = table4.getRow(0);
        row.setHeight(900);
        cell = row.getCell(0);
        setCellText(cell, "调查描述", "FFFFFF", 1500);
        cell = row.getCell(1);
        setCellText(cell, " 文本", "FFFFFF", 7500);
        XWPFTable table5 = doc.createTable(1, 4);
        row = table5.getRow(0);
        row.setHeight(380);
        cell = row.getCell(0);
        setCellText(cell, "调查人", "FFFFFF", 1500);
        cell = row.getCell(1);
        setCellText(cell, " 文本", "FFFFFF", 3000);
        cell = row.getCell(2);
        setCellText(cell, "日期", "FFFFFF", 1500);
        cell = row.getCell(3);
        setCellText(cell, "文本", "FFFFFF", 3000);

        createTable(doc, "个人剂量组调查（500个字符）");
        XWPFTable table6 = doc.createTable(1, 2);
        row = table6.getRow(0);
        row.setHeight(900);
        cell = row.getCell(0);
        setCellText(cell, "调查描述", "FFFFFF", 1500);
        cell = row.getCell(1);
        setCellText(cell, " 文本", "FFFFFF", 7500);
        XWPFTable table7 = doc.createTable(1, 6);
        row = table7.getRow(0);
        row.setHeight(380);
        cell = row.getCell(0);
        setCellText(cell, "名义剂量确定方式", "FFFFFF", 2000);
        cell = row.getCell(1);
        setCellText(cell, " 文本", "FFFFFF", 2000);
        cell = row.getCell(2);
        setCellText(cell, "名义剂量/最终值", "FFFFFF", 2000);
        cell = row.getCell(3);
        setCellText(cell, "文本", "FFFFFF", 2000);
        cell = row.getCell(4);
        setCellText(cell, "名义剂量/最终值单位", "FFFFFF", 2000);
        cell = row.getCell(5);
        setCellText(cell, "文本", "FFFFFF", 2000);
        XWPFTable table8 = doc.createTable(1, 4);
        row = table8.getRow(0);
        cell = row.getCell(0);
        setCellText(cell, "调查人", "FFFFFF", 1500);
        cell = row.getCell(1);
        setCellText(cell, " 文本", "FFFFFF", 3000);
        cell = row.getCell(2);
        setCellText(cell, "日期", "FFFFFF", 1500);
        cell = row.getCell(3);
        setCellText(cell, "文本", "FFFFFF", 3000);

        createTable(doc, "结论与处理意见（200个字符）");
        XWPFTable table9 = doc.createTable(2, 2);
        row = table9.getRow(0);
        cell = row.getCell(0);
        setCellText(cell, "跟踪", "FFFFFF", 1500);
        cell = row.getCell(1);
        setCellText(cell, "", "FFFFFF", 7500);
        row = table9.getRow(1);
        cell = row.getCell(0);
        setCellText(cell, "结论", "FFFFFF", 1500);
        cell = row.getCell(1);
        setCellText(cell, "", "FFFFFF", 7500);
        XWPFTable table10 = doc.createTable(1, 4);
        row = table10.getRow(0);
        cell = row.getCell(0);
        setCellText(cell, "审核人", "FFFFFF", 1500);
        cell = row.getCell(1);
        setCellText(cell, " 文本", "FFFFFF", 3000);
        cell = row.getCell(2);
        setCellText(cell, "日期", "FFFFFF", 1500);
        cell = row.getCell(3);
        setCellText(cell, "文本", "FFFFFF", 3000);

        XWPFTable table11 = doc.createTable(1, 1);
        setTableWidth(table11, "9000");
        row = table11.getRow(0);
        row.setHeight(380);
        cell = row.getCell(0);
        setCellText(cell, "提交辐射防护组调查   提交个人剂量组调查  提交审核  审核  导出  打印调查尿样标签", "FFFFFF", 9000);

        try {
            //outputStream=  “e:/123.docx”
//            doc.write(outputStream);
            doc.write(new FileOutputStream("C:\\Users\\wubo\\Desktop\\11.doc"));
//            outputStream.flush();
//            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //加个表格
      /*XWPFTable tab = docx.createTable(2,3);
      tab.setWidth(10000);
      //增加一行
      XWPFTableRow row1  = tab.createRow();
      row1.setHeight(3500);
      //增加列
      XWPFTableCell cell1 =null;
      cell1 = row1.createCell();
      cell1.setText("姓名");
      String hql="select t from TPdmisExceptionEntity t where t.userid='511021196307179130'";
      List<TPdmisExceptionEntity> tPdmisExceptionEntityList = commonDao.findByQueryString(hql);
      cell1 = row1.createCell();
      cell1.setText(tPdmisExceptionEntityList.get(0).getUname());

      cell1 = row1.createCell();
      XWPFParagraph pIO =cell1.addParagraph();
      XWPFRun rIO = pIO.createRun();
      rIO.setFontFamily("宋体");
      rIO.setFontSize(8);
      rIO.setBold(true);
      rIO.setText(tPdmisExceptionEntityList.get(0).getUname());

      XWPFParagraph p1 = docx.createParagraph();
      XWPFRun r1 = p1.createRun();
      r1.setText("hello world");*/


      /*try {
         doc.write(outputStream);
         outputStream.flush();
         outputStream.close();
      } catch (IOException e) {
         e.printStackTrace();
      }*/

      /*//创建一个5行5列的表格
      XWPFTable table = doc.createTable(5, 5);
      //这里增加的列原本初始化创建的那5行在通过getTableCells()方法获取时获取不到，但通过row新增的就可以。
//    table.addNewCol(); //给表格增加一列，变成6列
      table.createRow(); //给表格新增一行，变成6行
      List<XWPFTableRow> rows = table.getRows();
      //表格属性
      CTTblPr tablePr = table.getCTTbl().addNewTblPr();
      //表格宽度
      CTTblWidth width = tablePr.addNewTblW();
      width.setW(BigInteger.valueOf(8000));
      XWPFTableRow row;
      List<XWPFTableCell> cells;
      XWPFTableCell cell;
      int rowSize = rows.size();
      int cellSize;
      for (int i = 0; i < rowSize; i++) {
         row = rows.get(i);
         //新增单元格
         row.addNewTableCell();
         //设置行的高度
         row.setHeight(500);
         //行属性
//       CTTrPr rowPr = row.getCtRow().addNewTrPr();
         //这种方式是可以获取到新增的cell的。
//       List<CTTc> list = row.getCtRow().getTcList();
         cells = row.getTableCells();
         cellSize = cells.size();
         for (int j = 0; j < cellSize; j++) {
            cell = cells.get(j);
            if ((i + j) % 2 == 0) {
               //设置单元格的颜色
               cell.setColor("ff0000"); //红色
            } else {
               cell.setColor("0000ff"); //蓝色
            }
            //单元格属性
            CTTcPr cellPr = cell.getCTTc().addNewTcPr();
            cellPr.addNewVAlign().setVal(STVerticalJc.CENTER);
            if (j == 3) {
               //设置宽度
               cellPr.addNewTcW().setW(BigInteger.valueOf(3000));
            }
            cell.setText(i + ", " + j);
         }
         }*/
}
