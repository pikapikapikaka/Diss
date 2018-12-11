package diss.android.com.diss;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.jar.Attributes;

public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.back, this);
        Button back = findViewById(R.id.back_button);//返回按钮
        back.setOnClickListener(new OnClickListener() {//其对应的监听事件
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });

    }
}
