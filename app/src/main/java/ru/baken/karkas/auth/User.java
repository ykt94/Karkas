/*
26/02/20 класс для работы с текущим пользователем
 */
package ru.baken.karkas.auth;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import java.util.UUID;

public class User {
    private UUID userId;
    private static User user;
    private User(Context context) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String stringUserId = appPreferences.getString("USER_UUID", "unknown");
        if (stringUserId.equals("unknown")) {
            userId = UUID.randomUUID();
            SharedPreferences.Editor spEditor = appPreferences.edit();
            spEditor.putString("USER_UUID", userId.toString());
            spEditor.apply();
        } else {
            userId = UUID.fromString(stringUserId);
        }
    }

    public static User getUser(Context context) {
        if (user == null) {
            user = new User(context);
        }
        return user;
    }

    public UUID getUserId() {
        return userId;
    }
}
