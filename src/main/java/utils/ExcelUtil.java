package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class ExcelUtil {

    private final String filePath = System.getProperty("testDataFilePath");
    String timeStamp = String.valueOf(System.currentTimeMillis());

    public String readExcel(int sheetIndex,String key){
        try(XSSFWorkbook workbook = new XSSFWorkbook(filePath+"test_"+timeStamp+".xlsx")){
            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            int rowCount = sheet.getPhysicalNumberOfRows();
            for(int i=0;i<rowCount;i++){
                Row row = sheet.getRow(i);
                if(row.getCell(0).toString().equals(key)){
                    return row.getCell(1).toString();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Provide valid file path for test data");
        }
        return null;
    }

    public void createExcel(){
        Map<String,Object> generateData = new GenerateData().createTestData();
        File testDataFile = new File(filePath+"test_"+timeStamp+".xlsx");

        try(XSSFWorkbook workbook = new XSSFWorkbook()){
            XSSFSheet sheet = workbook.createSheet();
            int rowIndex = 0;
            int cellIndex = 0;

            for(Map.Entry<String,Object> entry : generateData.entrySet()){
                Row row = sheet.createRow(rowIndex++);
                Cell keyCell = row.createCell(cellIndex++);
                Cell valueCell = row.createCell(cellIndex++);
                cellIndex = 0;

                keyCell.setCellValue(entry.getKey());
                valueCell.setCellValue(String.valueOf(entry.getValue()));
            }

            try(FileOutputStream fos = new FileOutputStream(testDataFile)){
                workbook.write(fos);
                System.out.println("Test data excel created with name: "+testDataFile.getAbsoluteFile());
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }


    }


}
