package com.example.xero.cardtradeapp.ServiceFolder.AuctionService;

import com.example.xero.cardtradeapp.Entities.Subasta;

import java.util.List;

public interface IAuctionService {

    List<Subasta> getAuctions();
    boolean putAuction(int idAuction, int idUser, double newcurrentAmount);
    boolean pushAuction(Subasta subasta);
}
