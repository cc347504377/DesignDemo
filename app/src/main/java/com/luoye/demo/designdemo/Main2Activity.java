package com.luoye.demo.designdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ListView listView = (ListView) findViewById(R.id.listview);
        List<Integer> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add(i);
        }
        Button button = (Button) findViewById(R.id.button);

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas));
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        setDrawerLeftEdgeSize(this,drawerLayout,0.5f);
    }

    //通过反射 修改edgeSize
    public void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout,
                                      float displayWidthPercentage) {
        if (activity == null || drawerLayout == null)
            return;
        try {
            Field leftDraggerField = drawerLayout.getClass().getDeclaredField(
                    "mLeftDragger");
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField
                    .get(drawerLayout);
            // find edgesize and set is accessible
            Field edgeSizeField = leftDragger.getClass().getDeclaredField(
                    "mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);
            // set new edgesize
            // Point displaySize = new Point();
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize,
                    (int) (dm.widthPixels * displayWidthPercentage)));
        } catch (NoSuchFieldException e) {
            // ignore
        } catch (IllegalArgumentException e) {
            // ignore
        } catch (Exception e) {
            // ignore
        }
    }
}
