package utils;

import com.creditdatamw.zerocell.Reader;

import java.io.File;
import java.util.List;

public final class ExcelReader {
    private ExcelReader() {
    }

    private static List<TestDataUtils> testData = null;

    static {
        testData = Reader.of(TestDataUtils.class)
                .from(new File(System.getProperty("user.dir") + "/src/main/resources/testdata/excel.xlsx"))
                .sheet("Sheet0")
                .skipHeaderRow(true)
                .list();
    }

    public static List<TestDataUtils> getTestData() {
        return ExcelReader.testData;
    }
}