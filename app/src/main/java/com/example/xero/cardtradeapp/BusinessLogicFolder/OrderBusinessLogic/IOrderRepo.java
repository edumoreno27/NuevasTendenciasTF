package com.example.xero.cardtradeapp.BusinessLogicFolder.OrderBusinessLogic;

import com.example.xero.cardtradeapp.Entities.Orden;

import java.util.ArrayList;

public interface IOrderRepo {
    public ArrayList<Orden> GetOrders(int id);

    public Orden getOrder(int idorder, String type);
}
