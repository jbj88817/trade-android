package us.bojie.tradebo.bean.request;

import com.google.gson.annotations.SerializedName;

import us.bojie.tradebo.utils.Constants;

public class OauthRequest {

    @SerializedName("grant_type")
    private final String grantType;
    private final String scope;
    @SerializedName("client_id")
    private final String clientId;
    private final String username;
    private final String password;

    private OauthRequest(Builder builder) {
        this.grantType = builder.grantType;
        this.scope = builder.scope;
        this.clientId = builder.clientId;
        this.username = builder.username;
        this.password = builder.password;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String grantType;
        private String scope;
        private String clientId;
        private String username;
        private String password;

        public Builder() {
            this.username = Constants.USERNAME;
            this.password = Constants.PASSWORD;
            this.grantType = Constants.GRANT_TYPE;
            this.scope = Constants.SCOPE;
            this.clientId = Constants.CLIENT_ID;
        }

        public Builder grantType(String grantType) {
            this.grantType = grantType;
            return this;
        }

        public Builder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public OauthRequest build() {
            return new OauthRequest(this);
        }
    }
}
