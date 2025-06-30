package com.example.productapp.network;
import com.example.productapp.model.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;
public interface ApiService {
    @GET("products")
    Call<List<Product>> getProducts();
}