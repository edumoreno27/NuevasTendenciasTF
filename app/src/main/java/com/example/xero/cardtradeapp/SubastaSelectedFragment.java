package com.example.xero.cardtradeapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xero.cardtradeapp.ServiceFolder.AuctionService.AuctionService;
import com.example.xero.cardtradeapp.Entities.Subasta;

import java.util.Date;


public class SubastaSelectedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private EditText etPuja;
    private double minValue;

    private OnFragmentInteractionListener mListener;

    private Subasta subasta_selected;
    public void setSubasta_selected(Subasta subasta_selected) {
        this.subasta_selected = subasta_selected;
    }

    public SubastaSelectedFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubastaSelectedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubastaSelectedFragment newInstance(String param1, String param2) {
        SubastaSelectedFragment fragment = new SubastaSelectedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_auction_selected, container, false);

        // Button event
        etPuja = v.findViewById(R.id.etValorPuja);
        minValue = subasta_selected.getCurrentAmount()*1.2;
        Button btPujar = v.findViewById(R.id.btPujar_card_selected);
        btPujar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txValue = etPuja.getText().toString();
                if(!txValue.isEmpty()){
                    if(Double.valueOf(txValue)>=minValue){
                        Pujar pujar = new Pujar();
                        pujar.execute(Double.valueOf(txValue));
                    }
                    else {Toast.makeText(getContext(),"Must be higher or equals than 120% of the last Subasta value",Toast.LENGTH_SHORT).show();}
                }else {Toast.makeText(getContext(),"Needs a value",Toast.LENGTH_SHORT).show();}
            }
        });

        // Cargando item seleccionado...
        TextView txDescription = v.findViewById(R.id.description_card_selected);
        txDescription.setText(subasta_selected.getDescriptionCard());
        TextView txCurrentAmount = v.findViewById(R.id.puja_card_selected);
        txCurrentAmount.setText(""+ subasta_selected.getCurrentAmount());

        Date nowDate = new Date();
        long different = subasta_selected.getEndDate().getTime() - nowDate.getTime();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;
        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;
        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;
        long elapsedSeconds = different / secondsInMilli;
        TextView txTiempoRestante = v.findViewById(R.id.tiempo_restante_card_selected);
        txTiempoRestante.setText(""+elapsedDays+" Days - "+elapsedHours+" Hours - "+elapsedMinutes+" Min - "+elapsedSeconds+" Sec");
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private class Pujar extends AsyncTask<Double,Boolean,Boolean> {
        @Override
        protected Boolean doInBackground(Double... doubles) {
            double newcurrentAmount = doubles[0];
            boolean result = new AuctionService().putAuction(subasta_selected.getId(),getIdUser(),newcurrentAmount);
            return result;
        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            // lo demas luego del OK o del FAIL
            if(aBoolean){
                Toast.makeText(getContext(),"BIEN!", Toast.LENGTH_SHORT).show();
            }else Toast.makeText(getContext(),"An error occurred :(", Toast.LENGTH_SHORT).show();
        }
    }

    private int getIdUser(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Usuario",Context.MODE_PRIVATE);
        int idUser = sharedPreferences.getInt("userId",3);// si no lo encuentra toma 3
        return idUser;
    }
}
