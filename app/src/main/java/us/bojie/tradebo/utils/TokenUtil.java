package us.bojie.tradebo.utils;

import android.content.SharedPreferences;

import javax.inject.Inject;

import us.bojie.tradebo.database.entity.Token;

import static us.bojie.tradebo.utils.Constants.KEY_TOKEN;

public class TokenUtil {

    final SharedPreferences sharedPreferences;
    private String tokenString;

    @Inject
    public TokenUtil(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void updateTokenString(Token token) {
        if (sharedPreferences.getString(KEY_TOKEN, null) == null) {
            if (token != null) {
                StringBuilder sb = new StringBuilder();
                String accessToken = token.getAccessToken();
                String tokenType = token.getTokenType();
                tokenString = sb.append(tokenType).append(" ").append(accessToken).toString();
                sharedPreferences.edit().putString(KEY_TOKEN, tokenString).apply();
            }
        } else {
            tokenString = sharedPreferences.getString(KEY_TOKEN, "");
        }
    }

    public String getTokenString() {
        return tokenString;
    }
}
