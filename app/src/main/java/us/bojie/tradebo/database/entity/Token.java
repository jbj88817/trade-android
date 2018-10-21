package us.bojie.tradebo.database.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Token {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("backup_code")
    private String backupCode;
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("mfa_code")
    private String mfaCode;
    @SerializedName("token_type")
    private String tokenType;
    private String scope;
    @SerializedName("refresh_token")
    private String refreshToken;

    private Date lastRefresh;

    public Token(String backupCode, String accessToken, int expiresIn, String mfaCode, String tokenType, String scope, String refreshToken, Date lastRefresh) {
        this.backupCode = backupCode;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.mfaCode = mfaCode;
        this.tokenType = tokenType;
        this.scope = scope;
        this.refreshToken = refreshToken;
        this.lastRefresh = lastRefresh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackupCode() {
        return backupCode;
    }

    public void setBackupCode(String backupCode) {
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

    public String getMfaCode() {
        return mfaCode;
    }

    public void setMfaCode(String mfaCode) {
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
