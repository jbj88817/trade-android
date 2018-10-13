package us.bojie.tradebo.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import us.bojie.tradebo.database.entity.Token;

public interface ApiService {

    @FormUrlEncoded
    @POST("oauth2/token/")
    Call<Token> getToken(@FieldMap Map<String, String> fields);
}
