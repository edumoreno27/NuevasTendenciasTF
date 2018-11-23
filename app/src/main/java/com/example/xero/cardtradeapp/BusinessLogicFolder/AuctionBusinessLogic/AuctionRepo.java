package com.example.xero.cardtradeapp.BusinessLogicFolder.AuctionBusinessLogic;

import android.util.JsonReader;
import android.util.Log;

import com.example.xero.cardtradeapp.CONSTANTES;
import com.example.xero.cardtradeapp.Entities.Subasta;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AuctionRepo implements IAuctionRepo {

    @Override
    public List<Subasta> getAuctions() {
        CONSTANTES constantes = new CONSTANTES();
        List<Subasta> subastas = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try{

            URL url = new URL(constantes.getURLBASE()+"Auctions"); //3719 //45455
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if(httpURLConnection.getResponseCode()==200){
                InputStream inputStreamResponse = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReaderResponse = new InputStreamReader(inputStreamResponse,"UTF-8");
                JsonReader jsonReader = new JsonReader(inputStreamReaderResponse);
                jsonReader.beginArray();
                // parsing ...
                while(jsonReader.hasNext()){
                    jsonReader.beginObject();
                    Subasta subasta = new Subasta();
                    while(jsonReader.hasNext()){
                        String s = jsonReader.nextName();
                        switch (s){
                            case "Id":
                                subasta.setId(jsonReader.nextInt());
                                break;
                            case "CardName":
                                subasta.setCardName(jsonReader.nextString());
                                break;
                            case "UsernameUserSeller ":
                                try{ subasta.setUsernameUserSeller(jsonReader.nextString());
                                }catch (Exception e){
                                    subasta.setUsernameUserSeller("");
                                    jsonReader.skipValue();
                                }

                                break;
                            case "Type":
                                subasta.setType(jsonReader.nextString());
                                break;
                            case "Amount":
                                subasta.setAmount(jsonReader.nextDouble());
                                break;
                            case "CurrentAmount":
                                try{ subasta.setCurrentAmount(jsonReader.nextDouble());
                                }catch (Exception e){
                                    subasta.setCurrentAmount(0.0);
                                    jsonReader.skipValue();
                                }

                                break;
                            case "BeginDate":
                                subasta.setBeginDate(formatter.parse(jsonReader.nextString()));
                                break;
                            case "EndDate":
                                subasta.setEndDate(formatter.parse(jsonReader.nextString()));
                                break;
                            case "DescriptionCard":
                                subasta.setDescriptionCard(jsonReader.nextString());
                                break;
                            default:
                                jsonReader.skipValue();
                                break;
                        }
                    }
                    subastas.add(subasta);
                    jsonReader.endObject();
                }
                jsonReader.endArray();
                jsonReader.close();
                httpURLConnection.disconnect();
            }
            return subastas;
        }catch (Exception ex){
            Log.d("ERROR: ",ex.toString()+": "+ex.getMessage());return null;}
    }

    @Override
    public boolean putAuction(int idAuction, int idUser, double newcurrentAmount) {
        CONSTANTES constantes = new CONSTANTES();
        boolean result = false;
        try{
            URL url = new URL(constantes.getURLBASE()+"Auctions?id="+idAuction+"&idUser="+idUser);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type","application/json");
            httpURLConnection.setRequestProperty("Accept","application/json");
            String resultJSON="";
            JSONObject jsonObject = new JSONObject();
            try{
                jsonObject.put("id",idAuction); // no es necesario peeerooo en el api esta asi >:(
                jsonObject.put("currentAmount",newcurrentAmount);
                resultJSON = jsonObject.toString();
            }catch (Exception ex){return false;}
            // enviando respuesta
            httpURLConnection.getOutputStream().write(resultJSON.getBytes());
            if(httpURLConnection.getResponseCode()==200) result = true;
            httpURLConnection.disconnect();
        }catch (Exception ex){Log.d("ERROR (putAuction): ",ex.toString()+": "+ex.getMessage());}
        return result;
    }

    @Override
    public boolean pushAuction(Subasta subasta) {

        Log.d("BIEN (pushAuction): ","ENTRANDO 1...!");
        Boolean result = false;
        //Default new item with status Created
        subasta.setStatus("Created");
        Log.d("BIEN (pushAuction): ","ENTRANDO 2...!");
        CONSTANTES constantes = new CONSTANTES();
        try {
            URL apiUrl = new URL(constantes.getURLBASE()+"Auctions");

            // Create connection
            HttpURLConnection myConnection = (HttpURLConnection) apiUrl.openConnection();

            //Set method type
            myConnection.setRequestMethod("POST");
            myConnection.setDoOutput(true);

            Log.d("BIEN (pushAuction): ","ENTRANDO 3...!");
            //Set data
            myConnection.setRequestProperty("Content-Type", "application/json");
            myConnection.setRequestProperty("Accept", "application/json");
            String itemJson = subasta.toJson();
            myConnection.getOutputStream().write(itemJson.getBytes());
            Log.d("BIEN (pushAuction): ","ENTRANDO 4...!");
            Log.d("JSON: ",itemJson);
            //Process response
            if (myConnection.getResponseCode() == 201) {
                // Success
                // Further processing here
                Log.d("BIEN (pushAuction): ","FUNCIONO 55555!");
                result = true;
            }
            myConnection.disconnect();
        }
        catch (Exception ex){Log.d("ERROR (pushAuction): ",ex.toString()+": "+ex.getMessage());}
        return result;


    }


}
