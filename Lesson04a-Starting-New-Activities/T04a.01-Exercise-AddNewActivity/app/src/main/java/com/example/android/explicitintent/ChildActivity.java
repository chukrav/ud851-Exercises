package com.example.android.explicitintent;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class ChildActivity extends Activity {

    private TextView mtextMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        mtextMessage = (TextView) findViewById(R.id.tv_message);

    }
}
