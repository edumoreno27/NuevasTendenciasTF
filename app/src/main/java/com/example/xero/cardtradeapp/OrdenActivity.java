package com.example.xero.cardtradeapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.xero.cardtradeapp.Entities.Orden;
import com.example.xero.cardtradeapp.ServiceFolder.OrderService.IOrderService;
import com.example.xero.cardtradeapp.ServiceFolder.OrderService.OrderService;

public class OrdenActivity extends AppCompatActivity {

    TextView cardName;
    TextView cardCost;
    TextView endDateOrder;
    TextView shippingMethod;
    TextView contactName;
    TextView contactPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

    @Override
    protected void onStart() {
        super.onStart();

        cardName = findViewById(R.id.card_name_order);
        cardCost = findViewById(R.id.card_cost_order);
        endDateOrder = findViewById(R.id.end_date_order);
        shippingMethod = findViewById(R.id.shipping_method_order);
        contactName = findViewById(R.id.contact_name_order);
        contactPhone = findViewById(R.id.phonenumber_order);
        Intent intent = getIntent();
        int idOrder = intent.getIntExtra("Id",0);
        String type = intent.getStringExtra("Type");
        final GetOrderTask getTask = new GetOrderTask();
        getTask.execute(String.valueOf(idOrder),type);
    }

    public void fillSpace(Orden orden) {
        cardName.setText("Carta Name: " + orden.getCardName());
        cardCost.setText("Carta Cost: " + orden.getCost());
        endDateOrder.setText("End Date: " + orden.getEndDate());
        shippingMethod.setText("Shipping Method: " + orden.getShippingMethod());
        contactName.setText("Contact Name: " + orden.getContactName());
        contactPhone.setText("Contact Phone: " + orden.getContactPhone());
        return;
    }

    class GetOrderTask extends AsyncTask<String, Orden, Orden> {
        @Override
        protected Orden doInBackground(String... strings) {

            IOrderService orderAPI = new OrderService();
            int id = Integer.parseInt(strings[0]);
            Orden orden = orderAPI.getOrder(id, strings[1]);

            return orden;
        }

        @Override
        protected void onPostExecute(Orden orden) {
            super.onPostExecute(orden);
            fillSpace(orden);
        }
    }
}