package com.astro.tao.perfetto.memory;

import android.app.Activity;

public class LeakHolder {
    public static Activity activity;

    public static void leakActivity(Activity a) {
        activity = a;
    }

    public static void clear() {
        activity = null;
    }
}
