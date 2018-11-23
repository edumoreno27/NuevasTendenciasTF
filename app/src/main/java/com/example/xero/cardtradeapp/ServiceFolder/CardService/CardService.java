package com.example.xero.cardtradeapp.ServiceFolder.CardService;

import com.example.xero.cardtradeapp.BusinessLogicFolder.CardBusinessLogic.CardRepo;
import com.example.xero.cardtradeapp.BusinessLogicFolder.CardBusinessLogic.ICardRepo;
import com.example.xero.cardtradeapp.Entities.Carta;

import java.util.List;

public class CardService implements ICardService {
    ICardRepo repo;

    public CardService(){
        repo=new CardRepo();

    }

    @Override
    public List<Carta> getCards() {
        return repo.getCards();
    }
}
