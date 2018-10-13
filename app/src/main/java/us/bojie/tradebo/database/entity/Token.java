package us.bojie.tradebo.database.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import androidx.room.Entity;

@Entity
public class Token {

    /**
     * backup_code : null
     * access_token : eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6ImJCNHJ2VG8zbDIzV2lybERzZzRPR2Fvb1ptR3hacSIsInVzZXJfaWQiOiI0OGM4NTIwNC1kZTYwLTQwNDAtYTQ4Mi05ZmMwNWRkNTNhYzQiLCJvcHRpb25zIjp0cnVlLCJleHAiOjE1MzkzOTE2OTN9.ZIJsUwUn98OxTEK_TdtFmfu2U-dSyVOM76yW6lo8nL1paRL9FVrnIfvjVDM1Zc8WBHcahxGPXtctHTAmUn_2WPcasDIcmY-I0Ekk0CKbqpgLO2OdV5sgdBOHjh0lKX6H705kwQGTEitY6YnFMtt6rmdq6e4yEELKZeUw_EhR7jCS_r5XmaA6x3U4J1tLOXK4-UB3Qgqq3Gt8IZFs6zaqR1Zt88XQG8DAkQWG-F3sWdQkZ6890xyNvaR6s6jce08-pThFMkhy-WbFyRvSDdUGQFWRXpuEIcR7quMWd1sa7ua2_2soGfWTuh7MvM00RQUIiBUDpLhp59_2jbKeHX3RYw
     * expires_in : 86400
     * mfa_code : null
     * token_type : Bearer
     * scope : internal
     * refresh_token : WkJr9LrKnQ3j0qzS3zjedMsKZQI2KN
     */

    @SerializedName("backup_code")
    private Object backupCode;
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("mfa_code")
    private Object mfaCode;
    @SerializedName("token_type")
    private String tokenType;
    private String scope;
    @SerializedName("refresh_token")
    private String refreshToken;

    private Date lastRefresh;

    public Token(Object backupCode, String accessToken, int expiresIn, Object mfaCode, String tokenType, String scope, String refreshToken, Date lastRefresh) {
        this.backupCode = backupCode;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.mfaCode = mfaCode;
        this.tokenType = tokenType;
        this.scope = scope;
        this.refreshToken = refreshToken;
        this.lastRefresh = lastRefresh;
    }

    public Object getBackupCode() {
        return backupCode;
    }

    public void setBackupCode(Object backupCode) {
        this.backupCode = backupCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Object getMfaCode() {
        return mfaCode;
    }

    public void setMfaCode(Object mfaCode) {
        this.mfaCode = mfaCode;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getLastRefresh() {
        return lastRefresh;
    }

    public void setLastRefresh(Date lastRefresh) {
        this.lastRefresh = lastRefresh;
    }
}
