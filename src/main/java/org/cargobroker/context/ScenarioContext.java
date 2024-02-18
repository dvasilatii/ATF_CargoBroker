package org.cargobroker.context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private final Map<DataKeys, Object> CONTEXT = new HashMap<>();
    private static ScenarioContext scenarioInstance;

    public static ScenarioContext getScenarioInstance() {
        if (scenarioInstance == null) {
            scenarioInstance = new ScenarioContext();
        }
        return scenarioInstance;
    }

    public void saveData(DataKeys key, Object value) {

        CONTEXT.put(key, value);
    }

    public <T> T getData(DataKeys key) {

        return (T) CONTEXT.get(key);
    }
}