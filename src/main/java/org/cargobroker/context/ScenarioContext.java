package org.cargobroker.context;

import org.cargobroker.pageObjects.*;

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

    public <T> T getPage(DataKeys key) {
        if (CONTEXT.get(key) == null) {
            switch (key) {
                case PAGE_LOGIN:
                    saveData(key, new LoginPage());
                    break;
                case PAGE_ORDERS:
                    saveData(key, new OrdersPage());
                    break;
                case PAGE_ORDER_CREATION:
                    saveData(key, new OrderCreationPage());
                    break;
                case PAGE_CLIENTS:
                    saveData(key, new ClientsPage());
                    break;
                case PAGE_CLIENT_CREATION:
                    saveData(key, new ClientCreationPage());
                    break;
                default:
                    throw new RuntimeException("Unknown page type");
            }
        }

        return getData(key);
    }
}