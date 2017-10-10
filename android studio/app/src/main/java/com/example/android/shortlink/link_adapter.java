package com.example.android.shortlink;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by FANNAN on 6/9/2017.
 */

public class link_adapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public link_adapter (Context context) {
        this.context = context;
    }
    
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
