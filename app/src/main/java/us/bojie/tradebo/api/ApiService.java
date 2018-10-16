package us.bojie.tradebo.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import us.bojie.tradebo.database.entity.Token;

public interface ApiService {

    @POST("oauth2/token/")
    Call<Token> getToken(@Body Map<String, String> body);
}
