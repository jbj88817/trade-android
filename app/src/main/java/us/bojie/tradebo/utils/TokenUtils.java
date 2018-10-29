package us.bojie.tradebo.utils;

import android.content.SharedPreferences;

import javax.inject.Inject;

import us.bojie.tradebo.database.entity.Token;

public class TokenUtils {

    final SharedPreferences sharedPreferences;
    private String tokenString;

    @Inject
    public TokenUtils(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void updateTokenString(Token token) {
        if (token != null) {
            StringBuilder sb = new StringBuilder();
            String accessToken = token.getAccessToken();
            String tokenType = token.getTokenType();
            tokenString = sb.append(tokenType).append(" ").append(accessToken).toString();
        }
    }

    public String getTokenString() {
        return tokenString;
    }
}
