package com.example.xero.cardtradeapp.ServiceFolder.LoginService;

import android.util.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.support.v4.util.Pair;
import android.util.Log;

import com.example.xero.cardtradeapp.BusinessLogicFolder.LoginBusinessLogic.ILoginRepo;
import com.example.xero.cardtradeapp.BusinessLogicFolder.LoginBusinessLogic.LoginRepo;
import com.example.xero.cardtradeapp.CONSTANTES;

public class LogInService implements  ILogInService {
    ILoginRepo repo;
    public LogInService(){
        repo=new LoginRepo();

    }
    @Override
    public Pair<Boolean,Integer> UserValid(String username, String password) {
        return repo.UserValid(username,password);
    }
}