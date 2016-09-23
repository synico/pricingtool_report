package com.avnet.pricingtool.reporting.item.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;

import com.avnet.pricingtool.reporting.model.PriceReport;

@SuppressWarnings("all")
public class ExcelItemWriter implements ItemWriter<PriceReport> {
	
	private final static Log log = LogFactory.getLog(ExcelItemWriter.class);
	
	private static final String[] HEADERS = {"Date", "Scenario_1", "Scenario_2", "Scenario_3", "Scenario_4", "Scenario_5", "Scenario_6", "Scenario_7", "Scenario_8", "Comments"};
	
	private String fileName;
	
	private Workbook workbook;
	
	private int currentRow = 0;	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		log.info("ExcelItemWriter.beforeStep");
		workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");
		addHeaderToSheet(sheet);
	}
	
	@AfterStep
	public void afterStep(StepExecution stepExecution) throws IOException {
		log.info("ExcelItemWriter.afterStep");
		FileOutputStream fos = new FileOutputStream(getFileName());
		workbook.write(fos);
		fos.close();
	}

	@Override
	public void write(List<? extends PriceReport> items) throws Exception {
		log.info("ExcelItemWriter.write");
		Sheet sheet = workbook.getSheetAt(0);
		for(PriceReport pr : items) {
			currentRow++;
			Row row = sheet.createRow(currentRow);
			createStringCell(row, pr.getAppl_date().toString(), 0);
			createNumericCell(row, pr.getScenario_1(), 1);
			createNumericCell(row, pr.getScenario_2(), 2);
			createNumericCell(row, pr.getScenario_3(), 3);
			createNumericCell(row, pr.getScenario_4(), 4);
			createNumericCell(row, pr.getScenario_5(), 5);
			createNumericCell(row, pr.getScenario_6(), 6);
			createNumericCell(row, pr.getScenario_7(), 7);
			createNumericCell(row, pr.getScenario_8(), 8);
			createStringCell(row, pr.getComments(), 9);
		}
	}
	
	private void addHeaderToSheet(Sheet sheet) {
		Workbook wb = sheet.getWorkbook();
		
		CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
 
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Arial");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(font);
 
        Row row = sheet.createRow(0);
        int col = 0;
 
        for (String header : HEADERS) {
            Cell cell = row.createCell(col);
            cell.setCellValue(header);
            cell.setCellStyle(style);
            col++;
        }
        currentRow++;
	}
	
	private void createStringCell(Row row, String val, int col) {
        Cell cell = row.createCell(col);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(val);
    }
 
    private void createNumericCell(Row row, Long val, int col) {
        Cell cell = row.createCell(col);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellValue(val);
    }

}
