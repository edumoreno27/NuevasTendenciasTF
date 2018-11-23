package com.example.xero.cardtradeapp.BusinessLogicFolder.CardBusinessLogic;

import android.util.JsonReader;
import android.util.Log;

import com.example.xero.cardtradeapp.CONSTANTES;
import com.example.xero.cardtradeapp.Entities.Carta;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CardRepo implements ICardRepo {
    @Override
    public List<Carta> getCards() {
        List<Carta> cartas = new ArrayList<>();
        try{
            CONSTANTES constantes = new CONSTANTES();
            URL url = new URL(constantes.getURLBASE()+"Cards/"); //3719 //45455
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if(httpURLConnection.getResponseCode()==200){
                InputStream inputStreamResponse = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReaderResponse = new InputStreamReader(inputStreamResponse,"UTF-8");
                JsonReader jsonReader = new JsonReader(inputStreamReaderResponse);
                jsonReader.beginArray();
                // parsing ...

                while(jsonReader.hasNext()){
                    jsonReader.beginObject();
                    Carta carta = new Carta();
                    while(jsonReader.hasNext()){
                        String s = jsonReader.nextName();
                        switch (s.toLowerCase()){
                            case "id":
                                carta.setId(jsonReader.nextInt());
                                break;
                            case "name":
                                carta.setName(jsonReader.nextString());
                                break;
                            case "description":
                                carta.setDescription(jsonReader.nextString());
                                break;
                            case "cost":
                                carta.setCost(jsonReader.nextDouble());
                                break;
                            case "minvalue":
                                carta.setMinvalue(jsonReader.nextDouble());
                                break;
                            case "manacost":
                                carta.setManaCost(jsonReader.nextInt());
                                break;
                            case "power":
                                try {
                                    carta.setPower(jsonReader.nextInt());
                                }catch (Exception e){
                                    carta.setPower(0);
                                    jsonReader.skipValue();
                                }
                                break;
                            case "toughtness":
                                try{
                                    carta.setToughness(jsonReader.nextInt());
                                }catch (Exception e){
                                    jsonReader.skipValue();
                                    carta.setToughness(0);
                                }

                                break;
                            case "IsFoil":
                                carta.setFoil(jsonReader.nextBoolean());
                                break;
                            default:
                                jsonReader.skipValue();
                                break;
                        }
                    }
                    cartas.add(carta);
                    jsonReader.endObject();
                }
                jsonReader.endArray();
                jsonReader.close();
                httpURLConnection.disconnect();
            }
            return cartas;
        }catch (Exception ex)
        {Log.d("ERROR: ",ex.toString()+": "+ex.getMessage());return null;}
    }
}
