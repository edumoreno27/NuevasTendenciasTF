package com.example.xero.cardtradeapp.BusinessLogicFolder.ProfileBusinessLogic;

import com.example.xero.cardtradeapp.Entities.Perfil;

import java.util.ArrayList;

public interface IProfileRepo {
    ArrayList<Perfil> GetProfile(int idUsuario);
    Boolean pushProfile(Perfil perfil);
}
