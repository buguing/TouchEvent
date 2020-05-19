package com.wellee.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyView extends View {
    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // return true 不会调用onTouchEvent()
                // 执行在onTouchEvent()之前
                return false;
            }
        });
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 优先级最低 dispatchTouchEvent onTouch onTouchEvent任何一个方法返回true 将不执行onClick(v)
                Toast.makeText(getContext(), "点击MyView", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // return true  事件结束
        // return false 调用父ViewGroup.onTouchEvent(ev);
        // return super 调用this.onTouchEvent(ev);
        // 首先执行
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(getContext(), "MyView哈哈", Toast.LENGTH_SHORT).show();
        // return super/false 向上回传事件
        // return true 消费事件
        // 其次执行
        return super.onTouchEvent(event);
    }
}
