package com.example.xero.cardtradeapp.ServiceFolder.AuctionService;

import com.example.xero.cardtradeapp.BusinessLogicFolder.AuctionBusinessLogic.AuctionRepo;
import com.example.xero.cardtradeapp.BusinessLogicFolder.AuctionBusinessLogic.IAuctionRepo;
import com.example.xero.cardtradeapp.Entities.Subasta;

import java.util.List;

public class AuctionService implements IAuctionService{
    IAuctionRepo repo;

    public AuctionService(){
        repo=new AuctionRepo();
    }

    @Override
    public List<Subasta> getAuctions() {
      return repo.getAuctions();
    }

    @Override
    public boolean putAuction(int idAuction, int idUser, double newcurrentAmount) {
      return repo.putAuction(idAuction,idUser,newcurrentAmount );
    }

    @Override
    public boolean pushAuction(Subasta subasta) {
        return repo.pushAuction(subasta);
    }


}
