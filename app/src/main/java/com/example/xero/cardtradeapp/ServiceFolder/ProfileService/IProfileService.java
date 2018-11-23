package com.example.xero.cardtradeapp.ServiceFolder.ProfileService;

import com.example.xero.cardtradeapp.Entities.Perfil;

import java.util.ArrayList;

public interface IProfileService {

    ArrayList<Perfil>GetProfile(int idUsuario);
    Boolean pushProfile(Perfil perfil);

}
