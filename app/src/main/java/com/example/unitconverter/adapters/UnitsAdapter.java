package com.example.unitconverter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unitconverter.models.Conversion;
import com.example.unitconverter.ItemClickListener;
import com.example.unitconverter.R;

import java.util.ArrayList;
import java.util.List;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.BaseViewHolder> {

    private final List<Conversion> mConversions;
    private final ItemClickListener mItemClickListener;

    public UnitsAdapter(@NonNull List<Conversion> conversions, ItemClickListener ItemClickListener) {
        mConversions = new ArrayList<>(conversions);
        mItemClickListener = ItemClickListener;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new BaseViewHolder(view, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindView(mConversions.get(position));
    }


    @Override
    public int getItemCount() {
        return  mConversions.size();
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        final TextView mTextView;
        final ItemClickListener mMainItemClickListener;

        public BaseViewHolder(@NonNull View itemView, ItemClickListener mainItemClickListener) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_view);
            mMainItemClickListener = mainItemClickListener;

        }

        void bindView(final Conversion conversion) {
            mTextView.setText(conversion.mLabelRes);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMainItemClickListener.onItemClickListener(conversion);
                }
            });
        }
    }
}
