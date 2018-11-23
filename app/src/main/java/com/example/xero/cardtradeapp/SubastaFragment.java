package com.example.xero.cardtradeapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xero.cardtradeapp.ServiceFolder.AuctionService.AuctionService;
import com.example.xero.cardtradeapp.ServiceFolder.AuctionService.IAuctionService;
import com.example.xero.cardtradeapp.Entities.Subasta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SubastaFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public int CardId;
    public String CardName;
    DatePickerDialog picker;
    TextView name;
    EditText minAmount;
    EditText startDate;
    EditText endDate;
    Button createAuction;
    private OnFragmentInteractionListener mListener;

    public SubastaFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SubastaFragment newInstance(String param1, String param2) {
        SubastaFragment fragment = new SubastaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        name =  view.findViewById(R.id.etProductName_auction);
        minAmount = view.findViewById(R.id.etMinValue_auction);
        startDate = view.findViewById(R.id.etStartDate_auction);
        endDate = view.findViewById(R.id.etFinishDate_auction);
        createAuction = view.findViewById(R.id.btCreateAuction_auction);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            CardId = bundle.getInt("CardId", 0);
            CardName = bundle.getString("CardName");
            name.setText(CardName);
        }

        startDate.setInputType(InputType.TYPE_NULL);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                startDate.setText(year + "-" + ((monthOfYear + 1)>=10? "":"0")+(monthOfYear + 1) + "-" + (dayOfMonth>=10? "":"0")+dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        endDate.setInputType(InputType.TYPE_NULL);
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                endDate.setText(year + "-" + ((monthOfYear + 1)>=10? "":"0")+(monthOfYear + 1) + "-" + (dayOfMonth>=10? "":"0")+dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("fragmentKey",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        createAuction.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Context context = getActivity();

                Subasta subasta = new Subasta();
                // get idUser from phone
                SharedPreferences sharedPref = context.getSharedPreferences("Usuario", Context.MODE_PRIVATE);
                int userId = sharedPref.getInt("userId", 0);


                subasta.setIdUserSeller(userId);
                subasta.setStatus("active");
                subasta.setType("normal");
                subasta.setIdCurrentUser(0);
                subasta.setIdCard(CardId);

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                String dateInString1 = startDate.getText().toString()+"T00:00:00";
                String dateInString2 = endDate.getText().toString()+"T00:00:00";
                try {
                    subasta.setBeginDate(formatter.parse(dateInString1));
                    subasta.setEndDate(formatter.parse(dateInString2));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //subasta.setEndDate(Date.from(LocalDateTime.ofInstant(Instant.parse(dateInString2), ZoneId.of(ZoneOffset.UTC.getId())).atZone(ZoneId.systemDefault()).toInstant()));

                subasta.setCurrentAmount(Double.valueOf(0.0));
                subasta.setAmount( new Double(minAmount.getText().toString()));

                PushAuction pushAuction = new PushAuction();
                pushAuction.execute(subasta);
            }
        });
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
        return inflater.inflate(R.layout.fragment_auction, container, false);
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

    class PushAuction extends AsyncTask<Subasta,Boolean,Boolean> {


        @Override
        protected Boolean doInBackground(Subasta... strings) {

            IAuctionService iAuctionService = new AuctionService();
            return iAuctionService.pushAuction(strings[0]);
        }

        @Override
        protected void onPostExecute(Boolean isValid) {
            super.onPostExecute(isValid);
            //TOASTY
            if(isValid) {
                Toast.makeText(getContext(), "BIEN!", Toast.LENGTH_SHORT).show();
                CardName="";
                CardId=0;
                name.setText("Product Name");
                minAmount.setText("Minimun Amount");
                startDate.setText("Start Date");
                endDate.setText("Finish Date");
            }

            else Toast.makeText(getContext(),"ERROR!",Toast.LENGTH_SHORT).show();

        }
    }
}
