package com.example.unitconverter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.unitconverter.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {


    private List<String> mUnits;


    public SpinnerAdapter(@NonNull List<String> units) {
        mUnits = units;

    }

    @Override
    public int getCount() {
        return mUnits.size();
    }

    @Override
    public String getItem(int i) {
        return mUnits.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.spinner_item, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.mUnit.setText(getItem(i));

        return view;
    }

    private class ViewHolder {
        private final TextView mUnit;

        private ViewHolder(@NonNull View view) {
            mUnit = view.findViewById(R.id.spinner_text_view);

        }

    }

}
