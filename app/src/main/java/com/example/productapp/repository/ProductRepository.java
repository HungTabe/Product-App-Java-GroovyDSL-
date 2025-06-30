package com.example.productapp.repository;

import android.os.AsyncTask;
import androidx.lifecycle.MutableLiveData;

import com.example.productapp.model.Product;
import com.example.productapp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class ProductRepository {
    private MutableLiveData<List<Product>> products = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getProducts() {
        new FetchProductsTask().execute();
        return products;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    private class FetchProductsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            RetrofitClient.getApiService().getProducts().enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful()) {
                        products.postValue(response.body());
                    } else {
                        error.postValue("Failed to load products");
                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    error.postValue(t.getMessage());
                }
            });
            return null;
        }
    }
}