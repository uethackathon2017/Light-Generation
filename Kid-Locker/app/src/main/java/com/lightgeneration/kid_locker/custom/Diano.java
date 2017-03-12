package com.lightgeneration.kid_locker.custom;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.lightgeneration.kid_locker.R;

/**
 * Created by Ngoc Sang on 3/10/2017.
 */

public class Diano extends ImageView{
    public Diano(Context context) {
        super(context);
        init();
    }

    public Diano(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Diano(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Diano(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init()
    {
        setBackgroundResource(R.drawable.dino_dance);
        final AnimationDrawable drawable= (AnimationDrawable) getBackground();
        post(new Runnable() {
            @Override
            public void run() {
                drawable.start();
            }
        });
    }
    public void doTrue()
    {
        
        setBackgroundResource(R.drawable.dino_true);
        final AnimationDrawable drawable= (AnimationDrawable) getBackground();
        post(new Runnable() {
            @Override
            public void run() {
                drawable.start();
            }
        });
    }
    public void doFail()
    {
        setBackgroundResource(R.drawable.dino_false);
        final AnimationDrawable drawable= (AnimationDrawable) getBackground();
        post(new Runnable() {
            @Override
            public void run() {
                drawable.start();
            }
        });
    }
    public void doDance()
    {
        setBackgroundResource(R.drawable.dino_dance);
        final AnimationDrawable drawable= (AnimationDrawable) getBackground();
        post(new Runnable() {
            @Override
            public void run() {
                drawable.start();
            }
        });
    }
}
