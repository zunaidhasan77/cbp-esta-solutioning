import tss.automation.utilities.ExcelReader;
import tss.automation.utilities.UserDetails;

import java.io.File;
import java.net.URL;
import java.util.List;

public class MainApplication {

    public static void main(String[] args) {
        // Define the relative path to the Excel file
        String relativePath = "files/test_data.xlsx";

        // Get the resource as URL (will resolve relative path from classpath)
        URL resource = MainApplication.class.getClassLoader().getResource(relativePath);

        if (resource == null) {
            System.out.println("File not found in the resources folder: " + relativePath);
            return;
        }

        // Get the file path from URL (works for classpath resources)
        String excelFilePath = new File(resource.getFile()).getPath();

        // Read data from the Excel file into a list of UserDetails objects
        List<UserDetails> userDetailsList = ExcelReader.readExcel(excelFilePath);

        int i=1;
//         Print the data to verify
        for (UserDetails userDetails : userDetailsList) {
            System.out.println("For user "+ i++);
            System.out.println(userDetails);
        }
    }
}
