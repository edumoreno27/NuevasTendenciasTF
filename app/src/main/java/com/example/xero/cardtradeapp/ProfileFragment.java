package com.example.xero.cardtradeapp;

import android.content.Context;
import android.content.Intent;
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

import com.example.xero.cardtradeapp.Entities.Perfil;
import com.example.xero.cardtradeapp.ServiceFolder.ProfileService.IProfileService;
import com.example.xero.cardtradeapp.ServiceFolder.ProfileService.ProfileService;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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



    public void fillSpace(ArrayList<Perfil> perfils){


        EditText txtview = getView().findViewById(R.id.name);
        txtview.setText(perfils.get(0).getName().toString());

        EditText txtview2 = getView().findViewById(R.id.email);
        txtview2.setText(perfils.get(0).getEmail().toString());

        EditText txtview3 = getView().findViewById(R.id.phonenumber);
        txtview3.setText(perfils.get(0).getPhone().toString());

        EditText txtview4 = getView().findViewById(R.id.age);
        txtview4.setText(perfils.get(0).getAge().toString());

        EditText txtview5 = getView().findViewById(R.id.gender);
        txtview5.setText(perfils.get(0).getSex().toString());

        EditText txtview6 = getView().findViewById(R.id.coins);
        int a;
        a= perfils.get(0).getCoins();
        txtview6.setText(String.valueOf(a));

        EditText txtview7 = getView().findViewById(R.id.address);
        txtview7.setText(perfils.get(0).getAddress().toString());

        EditText txtview8 = getView().findViewById(R.id.rating);
        txtview8.setText(perfils.get(0).getRating().toString());

    }


    @Override
    public void onStart() {
        super.onStart();

        final GetTask getTask = new GetTask();
        getTask.execute();

        final Button reglas = (Button)getView().findViewById(R.id.btnRules);
        reglas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent intent = new Intent(view.getContext(), ReglasActivity.class);
            getActivity().startActivity(intent);
            }
        });


        final Button editame =(Button)getView().findViewById(R.id.btnEdit);
        editame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText editanombre = (EditText)getView().findViewById(R.id.name);
                editanombre.setEnabled(true);

                final EditText editamail =(EditText)getView().findViewById(R.id.email);
                editamail.setEnabled(true);

                final EditText editaphone =(EditText)getView().findViewById(R.id.phonenumber);
                editaphone.setEnabled(true);

                final EditText editage =(EditText)getView().findViewById(R.id.age);
                editage.setEnabled(true);

                final EditText editagender =(EditText)getView().findViewById(R.id.gender);
                editagender.setEnabled(true);

                final EditText editadress =(EditText)getView().findViewById(R.id.address);
                editadress.setEnabled(true);

                final EditText monedas = getView().findViewById(R.id.coins);
                final EditText rate = getView().findViewById(R.id.rating);

                Button editame=(Button)getView().findViewById(R.id.btnEdit);
                editame.setEnabled(false);
                editame.setVisibility(View.INVISIBLE);

                Button salvame=(Button)getView().findViewById(R.id.btnSave);
                salvame.setEnabled(true);
                salvame.setVisibility(View.VISIBLE);


                salvame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        int userid=getIdUser();
                        String name="",type="",status="",email="",phone="",age="",sex="",address="";
                        int coins=0;

                        BigDecimal rating= BigDecimal.valueOf(0);

                        Perfil perfil = new Perfil(name,type,status,email,phone,age,sex,coins,rating,address,userid);

                        //  IProfileService servicio2=new ProfileService();
                        //  ArrayList<Perfil>profi=servicio2.GetProfile();


                        perfil.setId(userid);
                        perfil.setName(editanombre.getText().toString());
                        perfil.setType("Premium");
                        perfil.setStatus("Active");
                        perfil.setEmail(editamail.getText().toString());
                        perfil.setPhone(editaphone.getText().toString());
                        perfil.setAge(editage.getText().toString());
                        perfil.setSex(editagender.getText().toString());
                        perfil.setCoins(Integer.valueOf( monedas.getText().toString()));
                        perfil.setRating(new BigDecimal(rate.getText().toString()));
                        perfil.setAddress(editadress.getText().toString());
                        perfil.setUserId(userid);

                        PostTask postTask = new PostTask();
                        postTask.execute(perfil);




                        Button editame=(Button)getView().findViewById(R.id.btnEdit);
                        editame.setEnabled(true);
                        editame.setVisibility(View.VISIBLE);

                        Button salvame=(Button)getView().findViewById(R.id.btnSave);
                        salvame.setEnabled(false);
                        salvame.setVisibility(View.INVISIBLE);




                        EditText editanombre = (EditText)getView().findViewById(R.id.name);
                        editanombre.setEnabled(false);

                        EditText editamail =(EditText)getView().findViewById(R.id.email);
                        editamail.setEnabled(false);

                        EditText editaphone =(EditText)getView().findViewById(R.id.phonenumber);
                        editaphone.setEnabled(false);

                        EditText editage =(EditText)getView().findViewById(R.id.age);
                        editage.setEnabled(false);

                        EditText editagender =(EditText)getView().findViewById(R.id.gender);
                        editagender.setEnabled(false);

                        EditText editadress =(EditText)getView().findViewById(R.id.address);
                        editadress.setEnabled(false);





                    }
                });



            }






        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
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



    class PostTask extends AsyncTask<Perfil,Boolean,Boolean>{

        @Override
        protected Boolean doInBackground(Perfil... strings) {

            IProfileService itemsAPI = new ProfileService();
            Boolean funciona= itemsAPI.pushProfile(strings[0]);

            return funciona;
        }

        @Override
        protected void onPostExecute(Boolean prof) {
            super.onPostExecute(prof);

        }

    }

    class GetTask extends AsyncTask<String,ArrayList<Perfil>,ArrayList<Perfil>> {

        @Override
        protected ArrayList<Perfil> doInBackground(String... strings) {

            IProfileService itemsAPI = new ProfileService();
            ArrayList<Perfil> perfils = itemsAPI.GetProfile(getIdUser());

            return perfils;
        }

        @Override
        protected void onPostExecute(ArrayList<Perfil> perfils) {
            super.onPostExecute(perfils);
            fillSpace(perfils);
        }
    }



    private int getIdUser() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        int idUser = sharedPreferences.getInt("userId", 3);// si no lo encuentra toma 3
        return idUser;
    }
}
