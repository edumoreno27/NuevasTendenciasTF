package com.example.xero.cardtradeapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xero.cardtradeapp.SubastasFragment.OnListFragmentInteractionListener;
import com.example.xero.cardtradeapp.Entities.Subasta;

import java.util.ArrayList;
import java.util.List;

public class MisSubastasRecyclerViewAdapter extends RecyclerView.Adapter<MisSubastasRecyclerViewAdapter.ViewHolder> {

    private List<Subasta> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MisSubastasRecyclerViewAdapter(List<Subasta> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_auctions, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        final String id = String.valueOf(holder.mItem.getId());
        holder.mIdView.setText(id);
        final String name = holder.mItem.getCardName();
        holder.mContentView.setText(name);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem); // esto se va al main y de alli hago el cambio de fragment
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
        public final TextView mIdView;
        public final TextView mContentView;
        public Subasta mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public void setfilter(List<Subasta> listitem){
        mValues = new ArrayList<Subasta>();
        mValues.addAll(listitem);
        notifyDataSetChanged();
    }

}
