package pages;

import configurator.ConfigReader;
import utils.ElementActionUtils;

public class DataDrivenPage {

    private final ElementActionUtils testUtil;

    //DataDrivenPage dataDrivenPage;

    public DataDrivenPage(ElementActionUtils testUtil){
        this.testUtil = testUtil;
        //dataDrivenPage = new DataDrivenPage(testUtil);
    }

    public void searchQuery(String value){
        testUtil.type(ConfigReader.getSearchText(),value);
    }
}
