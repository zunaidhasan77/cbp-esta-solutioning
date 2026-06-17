package tss.automation.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    public static List<UserDetails> readExcel(String filePath) {
        List<UserDetails> userList = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Read header
            Row headerRow = rowIterator.hasNext() ? rowIterator.next() : null;
            if (headerRow == null) return userList;

            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue().trim());
            }

            // Read data rows
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                UserDetails user = new UserDetails();

                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = row.getCell(i);
                    String fieldName = convertHeaderToFieldName(headers.get(i));
                    String cellValue = getCellValueAsString(cell);



                    try {
                        Field field = UserDetails.class.getDeclaredField(fieldName);
                        field.setAccessible(true);
                        field.set(user, cellValue != null ? cellValue : ""); // Handle null values
                    } catch (NoSuchFieldException | IllegalAccessException ignored) {
                        // Handle or log the exception if needed
                    }
                }

                userList.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return null;

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }

    // Converts headers like "DOB_DAY" to "dobDay"
    private static String convertHeaderToFieldName(String header) {
        if (header == null || header.isEmpty()) return "";
        return header.toLowerCase().replaceAll("[^a-zA-Z0-9]", ""); // Lowercase and remove non-alphanumeric
    }
}
