package com.example.project.GeneralRepositoryModel;

import android.content.Context;

import com.example.project.Network.AreaNetworkDelegate;
import com.example.project.Network.RemoteSource;

public class GeneralRepository implements GeneralRepositoryInterface{
    Context context;
    RemoteSource RS;


    private static  GeneralRepository gr = null ;

    public GeneralRepository(Context context, RemoteSource RS) {
        this.context = context;
        this.RS = RS;
    }

    public static GeneralRepository getInstance(RemoteSource RS , Context context){
        if(gr ==null){
            gr = new GeneralRepository(context,RS);
        }
        return gr;
    }

    @Override
    public void resultMealsSelectedArea(AreaNetworkDelegate networkDelegate, String nationality) {
        RS.resultMealsSelectedArea(networkDelegate,nationality);

    }
}
