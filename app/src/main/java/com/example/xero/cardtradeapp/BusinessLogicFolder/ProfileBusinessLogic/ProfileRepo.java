package com.example.xero.cardtradeapp.BusinessLogicFolder.ProfileBusinessLogic;

import android.util.JsonReader;

import com.example.xero.cardtradeapp.CONSTANTES;
import com.example.xero.cardtradeapp.Entities.Perfil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ProfileRepo implements IProfileRepo {
    @Override
    public ArrayList<Perfil> GetProfile(int idUsuario) {

        ArrayList<Perfil> Items = null;

        CONSTANTES constantes = new CONSTANTES();
        URL apiUrl = null;
        try {

            apiUrl = new URL(constantes.getURLBASE()+"Profiles/"+idUsuario);
            // Create connection
            HttpURLConnection myConnection = (HttpURLConnection) apiUrl.openConnection();
            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here

                //Read Response
                InputStream responseBody = myConnection.getInputStream();
                // Read Json for response
                InputStreamReader responseBodyReader =
                        new InputStreamReader(responseBody, "UTF-8");

                JsonReader jsonReader = new JsonReader(responseBodyReader);

                //start reading array
                jsonReader.beginObject();

                //read elements
                Items = new ArrayList<>();
                //read every

                int id=0;
                String name="";
                String type="";
                String status="";
                String email="";
                String phone="";
                String age="";
                String sex="";
                int coins=0;
                double rating=0;
                String address="";
                int userid=0;

                while (jsonReader.hasNext()) {

                    String property = jsonReader.nextName();
                    switch (property.toLowerCase()) {

                        case "address":
                            address = jsonReader.nextString();
                            break;

                        case "age":
                            age=jsonReader.nextString();
                            break;

                        case"coins":
                            coins=jsonReader.nextInt();
                            break;

                        case"email":
                            email=jsonReader.nextString();
                            break;

                        case"id":
                            id=jsonReader.nextInt();
                            break;

                        case"IdUser":
                            userid=jsonReader.nextInt();
                            break;

                        case"name":
                            name=jsonReader.nextString();
                            break;

                        case"phone":
                            phone=jsonReader.nextString();
                            break;

                        case"rating":
                            rating=jsonReader.nextDouble();
                            break;

                        case"sex":
                            sex=jsonReader.nextString();
                            break;

                        case"status":
                            status=jsonReader.nextString();
                            break;

                        case"type":
                            type=jsonReader.nextString();
                            break;

                        default:
                            jsonReader.skipValue();
                            break;

                    }

                }

                Perfil objProf = new Perfil( name, type, status, email, phone, age, sex, coins, BigDecimal.valueOf(rating), address,userid);
                Items.add(objProf);
                jsonReader.endObject();


                jsonReader.close();
                myConnection.disconnect();

            }else {
                return null;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Items;





    }

    @Override
    public Boolean pushProfile(Perfil perfil){

        Boolean result = false;
        //Default new item with status Created
        CONSTANTES constantes = new CONSTANTES();
        try {
            URL apiUrl =
                    new URL(constantes.getURLBASE()+"Profiles/");

            // Create connection
            HttpURLConnection myConnection =
                    (HttpURLConnection) apiUrl.openConnection();


            myConnection.setDoOutput(true);
            //Set method type
            myConnection.setRequestMethod("PUT");// CAMBIAR POR PUT!


            //Set data
            myConnection.setRequestProperty("Content-Type", "application/json");
            myConnection.setRequestProperty("Accept", "application/json");
            String itemJson = perfil.toJson();
            myConnection.getOutputStream().write(itemJson.getBytes());

            //Process response
            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here
                result = true;
            }
            myConnection.disconnect();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;


    }
}
