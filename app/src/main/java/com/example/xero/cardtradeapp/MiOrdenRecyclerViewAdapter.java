package com.example.xero.cardtradeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xero.cardtradeapp.Entities.Orden;
import com.example.xero.cardtradeapp.OrdenFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MiOrdenRecyclerViewAdapter extends RecyclerView.Adapter<MiOrdenRecyclerViewAdapter.ViewHolder> {

    private final List<Orden> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MiOrdenRecyclerViewAdapter(List<Orden> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mCardName.setText(mValues.get(position).getCardName());
        holder.mDate.setText(mValues.get(position).getBeginDate());
        holder.mAmount.setText(String.valueOf(mValues.get(position).getCost()));
        holder.mType.setText(mValues.get(position).getBuyerOrSeller());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {

                    Bundle bundle = new Bundle();
                    bundle.putString("CardName", mValues.get(position).getCardName());
                    bundle.putString("Date", mValues.get(position).getBeginDate());
                    bundle.putString("Amount", String.valueOf(mValues.get(position).getCost()));
                    bundle.putString("Type", mValues.get(position).getBuyerOrSeller());
                    bundle.putInt("Id", mValues.get(position).getId());


                    Intent intent = new Intent(v.getContext(), OrdenActivity.class);
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);


                    //Inflar Activity con info de la Orden y mostrar datos de contacto
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mCardName;
        public final TextView mDate;
        public final TextView mAmount;
        public final TextView mType;
        public Orden mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCardName = (TextView) view.findViewById(R.id.card_name_order);
            mDate = (TextView) view.findViewById(R.id.date_order);
            mAmount = (TextView) view.findViewById(R.id.amount_order);
            mType = (TextView) view.findViewById(R.id.type_order);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mCardName.getText() + "'";
        }
    }


}
