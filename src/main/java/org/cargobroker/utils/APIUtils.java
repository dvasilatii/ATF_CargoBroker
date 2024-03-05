package org.cargobroker.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class APIUtils {
    public static Response sendRequest(String method, String endpoint, Map<String, Object> params) {
        RequestSpecification request = RestAssured.given();
        request.baseUri(Utils.getProperty("cargoURL"));
        request.basePath("/api/");
        request.header("Authorization", "Bearer " + Utils.getProperty("apiToken"));

        switch (method) {
            case "get":
                return request.get(endpoint);
            case "post":
                request.formParams(params);
                return request.post(endpoint);
            case "delete":
                return request.delete(endpoint);
            case "put":
                request.formParams(params);
                return request.put(endpoint);
            default:
                throw new RuntimeException(method + "- is not a valid request method");
        }

    }
}
