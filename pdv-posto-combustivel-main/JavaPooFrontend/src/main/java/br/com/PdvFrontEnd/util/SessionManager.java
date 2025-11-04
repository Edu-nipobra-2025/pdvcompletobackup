package br.com.PdvFrontEnd.util;

import java.io.*;
import java.util.Properties;

public class SessionManager {
    private static final String CONFIG_FILE = "user_config.properties";
    private static SessionManager instance;
    private String currentUsername;
    private String currentUserId;
    private boolean isLoggedIn;

    private SessionManager() {
        this.isLoggedIn = false;
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void login(String username, String userId) {
        this.currentUsername = username;
        this.currentUserId = userId;
        this.isLoggedIn = true;
    }

    public void logout() {
        this.currentUsername = null;
        this.currentUserId = null;
        this.isLoggedIn = false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void saveCredentials(String username, String password) {
        Properties props = new Properties();
        try (FileOutputStream out = new FileOutputStream(CONFIG_FILE)) {
            props.setProperty("username", username);
            props.setProperty("password", password);
            props.store(out, "User Credentials");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkCredentials(String username, String password) {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(CONFIG_FILE)) {
            props.load(in);
            String savedUsername = props.getProperty("username");
            String savedPassword = props.getProperty("password");
            return username.equals(savedUsername) && password.equals(savedPassword);
        } catch (IOException e) {
            return false;
        }
    }

    public boolean userExists() {
        File file = new File(CONFIG_FILE);
        return file.exists();
    }
}

