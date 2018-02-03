package main.aintest.sea.com.aintest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class RetrofitController {

    static final String BASE_URL = "http://ainsoft.pro/test/";

    public static ProductsApi getApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        ProductsApi productsApi = retrofit.create(ProductsApi.class);
        return productsApi;
    }
}
