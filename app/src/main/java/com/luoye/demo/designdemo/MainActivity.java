package com.luoye.demo.designdemo;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCollapsingToolbarLayout();
    }

    private void initCollapsingToolbarLayout() {
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setTitle("这是一个title");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ListView listView = (ListView) findViewById(R.id.listview);
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(i);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
                //点击跳转到 侧滑demo
            }
        });
    }


}
