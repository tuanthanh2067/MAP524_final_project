package com.example.final_project;

import android.app.Application;

import com.example.final_project.database.DatabaseManager;

public class myApp extends Application {
    private NetworkingService networkingService = new NetworkingService();

    public JsonService getJsonService() {
        return jsonService;
    }

    private JsonService jsonService = new JsonService();


    public NetworkingService getNetworkingService() {
        return networkingService;
    }

    private DatabaseManager databaseManager = new DatabaseManager();

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
