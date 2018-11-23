package com.example.xero.cardtradeapp.ServiceFolder.ProfileService;

import com.example.xero.cardtradeapp.BusinessLogicFolder.ProfileBusinessLogic.IProfileRepo;
import com.example.xero.cardtradeapp.BusinessLogicFolder.ProfileBusinessLogic.ProfileRepo;
import com.example.xero.cardtradeapp.Entities.Perfil;

import java.util.ArrayList;

public class ProfileService implements IProfileService {
    IProfileRepo repo;
    public ProfileService(){repo=new ProfileRepo();}
    @Override
    public ArrayList<Perfil> GetProfile(int idUsuario) { return repo.GetProfile(idUsuario);}



    @Override
    public Boolean pushProfile(Perfil perfil) {return repo.pushProfile(perfil);}
}
