package us.bojie.tradebo.api;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import us.bojie.tradebo.bean.CommonResponse;
import us.bojie.tradebo.bean.Quote;
import us.bojie.tradebo.bean.ResultResponse;
import us.bojie.tradebo.database.entity.Instrument;
import us.bojie.tradebo.database.entity.Order;
import us.bojie.tradebo.database.entity.OwnedStock;
import us.bojie.tradebo.database.entity.Token;

public interface ApiService {

    @POST("oauth2/token/")
    Call<Token> getToken(@Body Map<String, String> body);

    @GET("positions/?nonzero=true/")
    Call<CommonResponse<List<OwnedStock>>> getUserOwnedStock(@Header("Authorization") String token);

    @GET("instruments/{instrument_id}/")
    Call<Instrument> getInstrument(@Path("instrument_id") String instrumentId);

    @GET("quotes/{symbol}/")
    Call<Quote> getQuoteFromSymbol(@Path("symbol") String symbol);

    @GET("quotes/")
    Call<ResultResponse<List<Quote>>> getQuotes(@Query("symbols") String symbols);

    @POST("orders/")
    Call<Order> postOrder(@Header("Authorization") String token);
}
