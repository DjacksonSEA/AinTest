package main.aintest.sea.com.aintest;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ProductsApi {
    @GET("test.xml")
    Call<ProductsXml> getProducts();
}
