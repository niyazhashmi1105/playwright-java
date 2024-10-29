package tests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelReader;
import utils.TestDataUtils;

import java.util.Iterator;
import java.util.List;

public class DataDrivenTest extends BaseTest {

    @Test(dataProvider = "excelData")
    public void dataDrivenTest(TestDataUtils testData){


        dataDrivenPage.searchQuery(testData.getUsername());
    }

    @DataProvider(name = "excelData")
    public Iterator<Object[]> provideTestData() {
        List<TestDataUtils> testDataList = ExcelReader.getTestData();
        return testDataList.stream()
                            .map(data -> new Object[]{data})
                            .iterator();
    }
}
