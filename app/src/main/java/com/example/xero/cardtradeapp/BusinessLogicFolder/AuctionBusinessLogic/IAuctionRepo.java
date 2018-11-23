package com.example.xero.cardtradeapp.BusinessLogicFolder.AuctionBusinessLogic;

import com.example.xero.cardtradeapp.Entities.Subasta;

import java.util.List;

public interface IAuctionRepo {
    List<Subasta> getAuctions();
    boolean putAuction(int idAuction, int idUser, double newcurrentAmount);
    boolean pushAuction(Subasta subasta);
}
