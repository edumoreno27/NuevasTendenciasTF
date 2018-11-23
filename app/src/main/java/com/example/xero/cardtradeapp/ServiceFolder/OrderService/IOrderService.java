package com.example.xero.cardtradeapp.ServiceFolder.OrderService;

import com.example.xero.cardtradeapp.Entities.Orden;

import java.util.ArrayList;

public interface IOrderService {
    public ArrayList<Orden> GetOrders(int id);

    public Orden getOrder(int idorder, String type);
}
