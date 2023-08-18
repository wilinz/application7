package com.example.application7;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.application7.databinding.ActivityRecyclerViewNetworkBinding;
import com.google.gson.Gson;
import com.just.agentweb.AgentWeb;

import java.io.IOException;
import java.util.List;

import kotlin.collections.CollectionsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecyclerViewNetworkActivity extends AppCompatActivity {

    private ActivityRecyclerViewNetworkBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecyclerViewNetworkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            // 执行下拉刷新操作
            getData();
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getData();
    }


    private void getData() {

        OkHttpClient client = new OkHttpClient();

        String apiKey = "201bb6d7f9f74fdb6c2ffb99be553d7a";
        String apiUrl = "https://apis.tianapi.com/social/index";
        int num = 30;

        HttpUrl.Builder urlBuilder = HttpUrl.parse(apiUrl).newBuilder();
        urlBuilder.addQueryParameter("key", apiKey);
        urlBuilder.addQueryParameter("num", String.valueOf(num));
        urlBuilder.addQueryParameter("rand","1");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseString = response.body().string();
                    NetworkNews news = new Gson().fromJson(responseString, NetworkNews.class);
                    List<News> news1 = CollectionsKt.map(news.getResult().getNewslist(), (new0) -> {
                        News n = new News();
                        n.setTitle(new0.getTitle());
                        n.setAuthor(new0.getSource());
                        n.setContent(new0.getDescription());
                        n.setUrl(new0.getUrl());
                        n.setImageUrl(new0.getPicUrl());
                        return n;
                    });

                    runOnUiThread(() -> {
                        NewsRecyclerViewAdapter newsAdapter = new NewsRecyclerViewAdapter(news1);
                        newsAdapter.setOnItemClick((p, item) -> {
                            if (item.getUrl() == null) {
                                return;
                            }
                            startActivity(new Intent(RecyclerViewNetworkActivity.this, AgentWebActivity.class).setData(Uri.parse(item.getUrl())));
                        });
                        binding.recyclerView.setAdapter(newsAdapter);
                    });

                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(RecyclerViewNetworkActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                    });
                }
                runOnUiThread(() -> {
                    if (binding.swipeRefreshLayout.isRefreshing()) {
                        binding.swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(RecyclerViewNetworkActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                    if (binding.swipeRefreshLayout.isRefreshing()) {
                        binding.swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });

    }
}