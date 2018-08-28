package com.everlapp.cicdexample.utils;

import android.content.Context;
import android.content.Intent;

import com.everlapp.cicdexample.MainActivity;

public class Util {

    public static Intent createQuery(Context context, String query, String value) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("QUERY", query);
        intent.putExtra("VALUE", value);
        return intent;
    }

}
