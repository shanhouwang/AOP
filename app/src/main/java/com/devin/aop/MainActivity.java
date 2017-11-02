package com.devin.aop;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.devin.tool_aop.annotation.Permission;
import com.devin.tool_aop.annotation.SingleClick;
import com.devin.tool_aop.annotation.SpendTimeLog;

/**
 * @author Devin
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            @Permission(value = Manifest.permission.CALL_PHONE, must = true)
            public void onClick(View v) {
                Toast.makeText(getApplication(), "打电话权限申请成功了", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tv_single_click).setOnClickListener(new View.OnClickListener() {
            @Override
            @SingleClick(value = 1000)
            public void onClick(View v) {
                Toast.makeText(getApplication(), "多次点击为一次，可以设定时间", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tv_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "方法耗时，请看Logcat", Toast.LENGTH_SHORT).show();
                method();
            }
        });
    }

    @SpendTimeLog
    private void method() {
        for (int i = 0; i < 100 * 100 * 100; i++) {
        }
    }
}
