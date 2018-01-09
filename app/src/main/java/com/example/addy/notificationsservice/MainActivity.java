package com.example.addy.notificationsservice;

/* в самой верхней строке экрана смартфона есть поле, где показываются уведомления от фоновых процессов(сервисов) - статусбар
* на примере кастомного сервиса рассмотрены возможности работы с данными уведомлениями*
* */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String FILE_NAME = "filename";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.text);

        Intent intent = getIntent();

        String filename = intent.getStringExtra(FILE_NAME);
        if (!TextUtils.isEmpty(filename))
            text.setText(filename);

    }

    public void onClickStart(View v){
        startService(new Intent(this, MyService.class));
    }

    public void onClickStop(View v){
        stopService(new Intent(this, MyService.class));
    }
}
