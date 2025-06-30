package com.example.productapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.productapp.model.Product;
import com.example.productapp.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductViewModel extends ViewModel {
    private ProductRepository repository = new ProductRepository();
    private LiveData<List<Product>> products;
    private List<Product> filteredProducts = new ArrayList<>();
    private String currentQuery = "";

    public ProductViewModel() {
        products = repository.getProducts();
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public LiveData<String> getError() {
        return repository.getError();
    }

    public void filterProducts(String query) {
        currentQuery = query.toLowerCase();
        filteredProducts.clear();
        if (products.getValue() != null) {
            filteredProducts.addAll(products.getValue().stream()
                    .filter(product -> product.getTitle().toLowerCase().contains(currentQuery))
                    .collect(Collectors.toList()));
        }
    }

    public List<Product> getFilteredProducts() {
        return currentQuery.isEmpty() ? products.getValue() : filteredProducts;
    }

    public void toggleFavorite(int productId) {
        List<Product> productList = products.getValue();
        if (productList != null) {
            for (Product product : productList) {
                if (product.getId() == productId) {
                    product.setFavorite(!product.isFavorite());
                    break;
                }
            }
            repository.getProducts().setValue(productList);
        }
    }
}
