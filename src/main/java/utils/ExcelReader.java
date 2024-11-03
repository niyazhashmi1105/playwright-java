package utils;

import com.creditdatamw.zerocell.Reader;

import java.io.File;
import java.util.List;

public final class ExcelReader {
    private ExcelReader() {
    }

    private static List<TestDataUtils> testData = null;

    static {

    }

    public static List<TestDataUtils> getTestData(String filePath, String sheetName) {

        testData = Reader.of(TestDataUtils.class)
                .from(new File(System.getProperty("user.dir") + filePath))
                .sheet(sheetName)
                .skipHeaderRow(true)
                .list();
        return testData;
    }
}