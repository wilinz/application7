package com.example.application7;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application7.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.listViewPage.setOnClickListener((v) -> {
            startActivity(ListViewActivity.class);
        });
        binding.recyclerViewPage.setOnClickListener((v) -> {
            startActivity(RecyclerViewActivity.class);
        });
        binding.recyclerViewPageNetwork.setOnClickListener(v -> {
            startActivity(RecyclerViewNetworkActivity.class);
        });
    }

    void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
}