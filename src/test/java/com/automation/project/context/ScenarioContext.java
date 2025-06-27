package com.automation.project.context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private static ScenarioContext INSTANCE;

    private final Map<String, Object> data = new HashMap<>();

    private ScenarioContext() {
    }

    public static ScenarioContext getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScenarioContext();
        }
        return INSTANCE;
    }

    public static void closeScenario() {
        if (INSTANCE != null) {
            INSTANCE.data.clear();  // clear saved values
            INSTANCE = null;
        }
    }

    public void saveData(String key, Object value) {
        data.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getData(String key) {
        return (T) data.get(key);
    }

    public void clearData() {
        data.clear();
    }
}
