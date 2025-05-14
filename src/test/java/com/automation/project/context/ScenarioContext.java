package com.automation.project.context;

import com.automation.project.pages.BasePage;

public class ScenarioContext {

    private static ScenarioContext INSTANCE;
    private BasePage currentPage;

    private ScenarioContext() {
    }

    public static ScenarioContext getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScenarioContext();
        }
        return INSTANCE;
    }

    // for that did you use closeScenario?) -> clear scenario
    public static void closeScenario() {
        INSTANCE = null;
    }

//    public void saveData(ScenarioObjectKey key, Object value) {
//        data.put(key, value);
//    }
//
//    public <T> T getData(ScenarioObjectKey key) {
//        return (T) data.get(key);
//    }
//
//    public void clearData() {
//        data.clear();
//    }

    // not to be here
    public BasePage getCurrentPage() {
        return currentPage;
    }

    // not to be here
    public void setCurrentPage(BasePage currentPage) {
        this.currentPage = currentPage;
    }

}