package us.bojie.tradebo.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.database.dao.TokenDao;
import us.bojie.tradebo.repositories.TokenRepository;

@Module
public class AppModule {

    // --- NETWORK INJECTION ---

    private static String BASE_URL = "https://api.robinhood.com/";

    @Provides
    Gson provideGson() { return new GsonBuilder().create(); }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        // logging
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        
        // headers
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Accept", "*/*")
                        .header("Accept-Encoding", "gzip, deflate")
                        .header("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7")
                        .header("X-Robinhood-API-Version", "1.152.0")
                        .header("User-Agent", "Robinhood/5.32.0 (com.robinhood.release.Robinhood; build:3814; iOS 10.3.3)")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        };
        
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(headerInterceptor)
                .build();

    }

    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    ApiService provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(ApiService.class);
    }

    // --- REPOSITORY INJECTION ---

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    TokenRepository provideTokenRepository(ApiService webservice, TokenDao tokenDao, Executor executor) {
        return new TokenRepository(webservice, tokenDao, executor);
    }
}
