package com.everlapp.cicdexample;

import android.content.Context;

public class ClassForTest {

    private Context mContext;

    public ClassForTest(Context context) {
        this.mContext = context;
    }


    public String getHelloWorld() {
        return mContext.getString(R.string.hello_world);
    }
}
