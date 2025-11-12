package br.com.PdvFrontEnd.util;

import java.io.*;
import java.util.Properties;
import java.util.Set;

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
        File configFile = new File(CONFIG_FILE);
        if (configFile.exists()) {
            try (FileInputStream in = new FileInputStream(configFile)) {
                props.load(in);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        int userCount = props.stringPropertyNames().stream()
                .filter(key -> key.startsWith("username."))
                .mapToInt(key -> Integer.parseInt(key.substring("username.".length())))
                .max()
                .orElse(0);

        int nextUser = userCount + 1;

        try (FileOutputStream out = new FileOutputStream(CONFIG_FILE)) {
            props.setProperty("username." + nextUser, username);
            props.setProperty("password." + nextUser, password);
            props.store(out, "User Credentials");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkCredentials(String username, String password) {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(CONFIG_FILE)) {
            props.load(in);
            Set<String> usernames = props.stringPropertyNames();
            for (String key : usernames) {
                if (key.startsWith("username.")) {
                    String savedUsername = props.getProperty(key);
                    if (savedUsername.equals(username)) {
                        String passwordKey = "password." + key.substring("username.".length());
                        String savedPassword = props.getProperty(passwordKey);
                        return password.equals(savedPassword);
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean userExists() {
        File file = new File(CONFIG_FILE);
        if (!file.exists()) {
            return false;
        }
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(file)) {
            props.load(in);
            return props.stringPropertyNames().stream().anyMatch(key -> key.startsWith("username."));
        } catch (IOException e) {
            return false;
        }
    }
}