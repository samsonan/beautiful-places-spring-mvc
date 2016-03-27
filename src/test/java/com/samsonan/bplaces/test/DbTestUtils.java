package com.samsonan.bplaces.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

public class DbTestUtils {
	
	private DbTestUtils() {}
	 
    public static void resetAutoIncrementColumns(ApplicationContext applicationContext,
                                                 String... tableNames) throws SQLException {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        String resetSqlTemplate = getResetSqlTemplate(applicationContext);
        try (Connection dbConnection = dataSource.getConnection()) {
            //Create SQL statements that reset the auto increment columns and invoke 
            //the created SQL statements.
            for (String resetSqlArgument: tableNames) {
                try (Statement statement = dbConnection.createStatement()) {
                    String resetSql = String.format(resetSqlTemplate, resetSqlArgument);
                    statement.execute(resetSql);
                }
            }
        }
    }
 
    private static String getResetSqlTemplate(ApplicationContext applicationContext) {
        //Read the SQL template from the properties file
        Environment environment = applicationContext.getBean(Environment.class);
        return environment.getRequiredProperty("test.reset.sql.template");
    }
}
