package com.example.xero.cardtradeapp.ServiceFolder.OrderService;


import com.example.xero.cardtradeapp.BusinessLogicFolder.OrderBusinessLogic.IOrderRepo;
import com.example.xero.cardtradeapp.BusinessLogicFolder.OrderBusinessLogic.OrderRepo;
import com.example.xero.cardtradeapp.Entities.Orden;

import java.util.ArrayList;

public class OrderService implements IOrderService {
    IOrderRepo repo;

    public OrderService(){
        repo=new OrderRepo();
    }

    @Override
    public ArrayList<Orden> GetOrders(int idUser){
        return repo.GetOrders(idUser);
    }

    @Override
    public Orden getOrder(int idorder, String type){return getOrder(idorder,type);}
}

