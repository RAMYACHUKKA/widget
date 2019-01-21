package com.example.ramya.widgettest;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        int i=getIntent().getIntExtra("pos",0);
        String[] detailsarray=getResources().getStringArray(R.array.details);
       // ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,detailsarray);
        String data=detailsarray[i];
        tv=findViewById(R.id.text);
        tv.setText(data);
        SharedPreferences sharedPreferences=getSharedPreferences("mypref",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("mydata",data);
        editor.commit();
        Intent intent=new Intent(this,NewAppWidget.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        int[] widget= AppWidgetManager.getInstance(this).getAppWidgetIds(new ComponentName(this,NewAppWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,widget);
        sendBroadcast(intent);
    }
}
