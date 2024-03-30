package org.cargobroker.steps;

import io.cucumber.java.en.And;
import lombok.extern.log4j.Log4j2;
import org.cargobroker.context.DataKeys;
import org.cargobroker.context.ScenarioContext;
import org.cargobroker.utils.DBUtils;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Log4j2
public class DBSteps {
    private static final ScenarioContext CONTEXT = ScenarioContext.getScenarioInstance();

    @And("created client exists in database")
    public void createdClientExistsInDB() {
        try {
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT COUNT(*) AS `recordCount` FROM `Client` WHERE" +
                            "`organization`=" + stmt.enquoteLiteral(CONTEXT.getData(DataKeys.CLIENT_ORGANIZATION)) + " AND " +
                            "`spokesperson`=" + stmt.enquoteLiteral(CONTEXT.getData(DataKeys.CLIENT_SPOKESPERSON)) + " AND " +
                            "`contact_email`=" + stmt.enquoteLiteral(CONTEXT.getData(DataKeys.CLIENT_EMAIL)) + " AND " +
                            "`contact_phone`=" + stmt.enquoteLiteral(CONTEXT.getData(DataKeys.CLIENT_PHONE)) + " AND " +
                            "`contact_address`=" + stmt.enquoteLiteral(CONTEXT.getData(DataKeys.CLIENT_ADDRESS))
            );

            rs.next();
            int count = rs.getInt("recordCount");

            Assert.assertEquals(count, 1);
            log.info("created client exists in database");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}