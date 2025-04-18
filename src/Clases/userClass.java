/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Axel Reyes
 */
public class userClass {
       private static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        userClass.username = username;
    }

    public static void clearSession() {
        username = null;
    }
}
