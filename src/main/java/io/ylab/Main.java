package io.ylab;


import io.ylab.in.Menu;
import io.ylab.service.impl.ServiceFactory;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String URL = "jdbc:postgresql://localhost:5432/ylabdb";
    private static final String USER_NAME = "ylabname";
    private static final String PASSWORD = "ylabpass";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase("db/changelog/changelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update();
        } catch (SQLException | LiquibaseException e) {
            System.out.println(e.getMessage());
        }

        ServiceFactory factory = new ServiceFactory();
        Menu menu = new Menu(factory);
        menu.mainMenu();



    }

}
