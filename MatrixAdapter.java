package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by elngo on 5/26/2017.
 */

public class MatrixAdapter extends BaseAdapter {
    Context context;
    List<Matrix> matrixList;

    //Or create an int[rows][columns] CHANGE INTO THAT LATER
    public MatrixAdapter(Context context, List<Matrix> matrixList) {
        this.context = context;
        this.matrixList = matrixList;
    }

    @Override
    public int getCount() {

        return matrixList.size();
    }

    @Override
    public Object getItem(int i) {

        return matrixList.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=View.inflate(context,R.layout.grid_item,null);
        return v;
    }
}
