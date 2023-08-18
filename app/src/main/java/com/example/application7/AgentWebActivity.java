package com.example.application7;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application7.databinding.ActivityAgentWebBinding;
import com.just.agentweb.AgentWeb;


public class AgentWebActivity extends AppCompatActivity {

    private AgentWeb agentWeb;

    private ActivityAgentWebBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgentWebBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(binding.agentweb, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()//进度条
                .createAgentWeb()
                .ready()
                .go(intent.getData().toString());
    }


    @Override

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (agentWeb.handleKeyEvent(keyCode, event)) {

            return true;

        }

        return super.onKeyDown(keyCode, event);

    }

    @Override

    protected void onPause() {

        agentWeb.getWebLifeCycle().onPause();

        super.onPause();

    }

    @Override

    protected void onResume() {

        agentWeb.getWebLifeCycle().onResume();

        super.onResume();

    }

    @Override

    protected void onDestroy() {

        agentWeb.getWebLifeCycle().onDestroy();

        super.onDestroy();
    }

}
