package com.example.xero.cardtradeapp.BusinessLogicFolder.LoginBusinessLogic;

import android.support.v4.util.Pair;

public interface ILoginRepo {
    Pair<Boolean,Integer> UserValid(String username, String password);
}
