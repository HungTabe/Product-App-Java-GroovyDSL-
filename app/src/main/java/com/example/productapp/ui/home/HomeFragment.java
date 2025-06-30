package com.example.productapp.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productapp.R;
import com.example.productapp.model.Product;
import com.example.productapp.ui.adapter.ProductAdapter;
import com.example.productapp.viewmodel.ProductViewModel;

public class HomeFragment extends Fragment {
    private ProductViewModel viewModel;
    private ProductAdapter adapter;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        EditText searchBar = view.findViewById(R.id.search_bar);
        progressBar = view.findViewById(R.id.progress_bar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProductAdapter(new ProductAdapter.OnProductClickListener() {
            @Override
            public void onProductClick(Product product) {
                HomeFragmentDirections.ActionHomeToDetail action = HomeFragmentDirections.actionHomeToDetail(product);
                Navigation.findNavController(view).navigate(action);
            }

            @Override
            public void onFavoriteClick(Product product) {
                viewModel.toggleFavorite(product.getId());
                adapter.setProducts(viewModel.getFilteredProducts());
            }
        });
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        viewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
            progressBar.setVisibility(View.GONE);
            adapter.setProducts(viewModel.getFilteredProducts());
        });
        viewModel.getError().observe(getViewLifecycleOwner(), error -> {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.filterProducts(s.toString());
                adapter.setProducts(viewModel.getFilteredProducts());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        progressBar.setVisibility(View.VISIBLE);
        return view;
    }
}