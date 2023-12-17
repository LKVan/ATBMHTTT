package support;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.ProductReportDAO;
import model.report.ProductReport;
import model.report.RevenueReport;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExportExcel {
	
	public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_TITLE = 1;
    public static final int COLUMN_INDEX_QUANTITY = 2;
    public static final int COLUMN_INDEX_TOTAL = 3;
    private static CellStyle cellStyleFormatNumber = null;

    
 
    public static void main(String[] args) throws IOException {
       ProductReportDAO productReportDAO = new ProductReportDAO();
        final String excelFilePath = "F:/Demo/books_large.xlsx";
        new ExportExcel().writeExcel(productReportDAO.getAllProductReport(), excelFilePath);
        
    }
 
    public  void writeExcel(List<ProductReport> list, String excelFilePath,String...params) throws IOException {
        // Tạo Workbook
        SXSSFWorkbook workbook = new SXSSFWorkbook();
 
        // Tạo  sheet
        SXSSFSheet sheet = workbook.createSheet("Thongke_Sanpham"); // Create sheet with sheet name
         
        // register the columns you wish to track and compute the column width
        sheet.trackAllColumnsForAutoSizing();
 
        int rowIndex = 0;
 
        // ghi header
        writeHeader(sheet, rowIndex,params);
 
        // ghi data
        rowIndex++;
        for (ProductReport item : list) {
            // tạo dòng
            SXSSFRow row = sheet.createRow(rowIndex);
            // ghi dữ liệu lên dòng
            writeItem(item, row);
            rowIndex++;
        }
 
        // ghi footer
        writeFooter(sheet, rowIndex,params.length);
 
        // auto size
        int numberOfColumn = 5; // sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
 
        // ghi file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }
    
    public  void writeExcelRevenue(List<RevenueReport> list, String excelFilePath,String...params) throws IOException {
        // Tạo Workbook
        SXSSFWorkbook workbook = new SXSSFWorkbook();
 
        // Tạo  sheet
        SXSSFSheet sheet = workbook.createSheet("Thongke_Sanpham"); // Create sheet with sheet name
         
        // register the columns you wish to track and compute the column width
        sheet.trackAllColumnsForAutoSizing();
 
        int rowIndex = 0;
 
        // ghi header
        writeHeader(sheet, rowIndex,params);
 
        // ghi data
        rowIndex++;
        for (RevenueReport item : list) {
            // tạo dòng
            SXSSFRow row = sheet.createRow(rowIndex);
            // ghi dữ liệu lên dòng
            writeItem(item, row);
            rowIndex++;
        }
 
        // ghi footer
        writeFooter(sheet, rowIndex,params.length);
 
        // auto size
        int numberOfColumn = 5; // sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
 
        // ghi file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }
 
 
    // Ghi vào phần header
//    private static void writeHeader(SXSSFSheet sheet, int rowIndex) {
//        // Tạo style cho header
//        CellStyle cellStyle = createStyleForHeader(sheet);
// 
//        // Tạo dong để ghi
//        SXSSFRow row = sheet.createRow(rowIndex);
// 
//        // Ghi từng ô
//        SXSSFCell cell = row.createCell(COLUMN_INDEX_ID);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("Mã");
// 
//        cell = row.createCell(COLUMN_INDEX_TITLE);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("Tên sản phẩm");
// 
// 
//        cell = row.createCell(COLUMN_INDEX_QUANTITY);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("Số lượng bán ra");
// 
//        cell = row.createCell(COLUMN_INDEX_TOTAL);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("Tổng tiền bán ra");
//    }
    
    private  void writeHeader(SXSSFSheet sheet, int rowIndex,String... params) {
        // Tạo style cho header
        CellStyle cellStyle = createStyleForHeader(sheet);
 
        // Tạo dong để ghi
        SXSSFRow row = sheet.createRow(rowIndex);
 
        for(int i = 0; i < params.length;i++) {
        	   SXSSFCell cell = row.createCell(i);
               cell.setCellStyle(cellStyle);
               cell.setCellValue(params[i]);
        }
        
    }
 
    // Ghi dữ liệu
    private  void writeItem(ProductReport item, SXSSFRow row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");
     
            // Tạo style cho dữ liệu
            SXSSFWorkbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
 
        SXSSFCell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(item.getId());
 
        cell = row.createCell(COLUMN_INDEX_TITLE);
        cell.setCellValue(item.getName());
 
 
        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellValue(item.getQuanliSoldOut());
        
        cell = row.createCell(COLUMN_INDEX_TOTAL);
        cell.setCellValue(item.getPrice());
 
    }
    
    private  void writeItem(RevenueReport item, SXSSFRow row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");
     
            // Tạo style cho dữ liệu
            SXSSFWorkbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
 
        SXSSFCell cell = row.createCell(0);
        cell.setCellValue(item.getDate().toString());
 
        cell = row.createCell(1);
        cell.setCellValue(item.getQuanlity());
 
 
        cell = row.createCell(2);
        cell.setCellValue(item.getTotalPrice());
        
 
    }
 
    // Tạo style cho đầu của file Excel
    private  CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color
 
        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
 
    // Ghi tổng cộng ở cột cuối
    private  void writeFooter(SXSSFSheet sheet, int rowIndex,int totalColums) {
        // Create row
        SXSSFRow row = sheet.createRow(rowIndex);
        SXSSFCell cell = row.createCell(totalColums - 1, CellType.FORMULA);
        String endCell = cell.getAddress().toString();
        int rowEnd = cell.getRowIndex();
        System.out.println(endCell);
        
        //Sử dụng pattern để tách lấy tên cột
        Pattern pattern = Pattern.compile("\\D{1,2}");
        Matcher matcher = pattern.matcher(endCell);
        
        String columnCell ="";
        matcher.find();
        columnCell = cell.getAddress().toString().substring(matcher.start(),matcher.end());
        cell.setCellFormula("SUM("+columnCell+"2:"+columnCell+rowEnd +")");
    }
 
    // Auto resize column width
    private  void autosizeColumn(SXSSFSheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
 
    //Ghi file
    private  void createOutputFile(SXSSFWorkbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
 
}


