package com.example.productapp.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.productapp.R;
import com.example.productapp.model.Product;

public class DetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ImageView image = view.findViewById(R.id.detail_image);
        TextView title = view.findViewById(R.id.detail_title);
        TextView price = view.findViewById(R.id.detail_price);
        TextView category = view.findViewById(R.id.detail_category);
        TextView description = view.findViewById(R.id.detail_description);
        TextView rating = view.findViewById(R.id.detail_rating);

        Product product = DetailFragmentArgs.fromBundle(getArguments()).getProduct();
        title.setText(product.getTitle());
        price.setText(String.format("$%.2f", product.getPrice()));
        category.setText("Category: " + product.getCategory());
        description.setText(product.getDescription());
        rating.setText(String.format("Rating: %.1f (%d reviews)", product.getRating().getRate(), product.getRating().getCount()));
        Glide.with(this).load(product.getImage()).into(image);

        return view;
    }
}
