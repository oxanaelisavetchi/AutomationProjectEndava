package com.automation.project.context;

import com.automation.project.pages.BasePage;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
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

    public static void closeScenario() {
        INSTANCE = null;
    }

    public BasePage getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(BasePage currentPage) {
        this.currentPage = currentPage;
    }

}