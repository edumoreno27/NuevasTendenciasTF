package com.example.xero.cardtradeapp.Entities;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Subasta {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getUsernameUserSeller() {
        return usernameUserSeller;
    }

    public void setUsernameUserSeller(String usernameUserSeller) {
        this.usernameUserSeller = usernameUserSeller;
    }

    public String getDescriptionCard() {
        return descriptionCard;
    }

    public void setDescriptionCard(String descriptionCard) {
        this.descriptionCard = descriptionCard;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private int id;
    private String cardName;
    private String descriptionCard;
    private String usernameUserSeller;
    private int idUserSeller;
    private Date beginDate;
    private Date endDate;
    private String status;
    private String type;
    private double currentAmount;
    private double amount;
    private int idCard;
    private int idCurrentUser;

    public int getIdUserSeller() {
        return idUserSeller;
    }

    public void setIdUserSeller(int idUserSeller) {
        this.idUserSeller = idUserSeller;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }



    public String toJson(){
        String jsonText = null;
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            //jsonObject.put("id", getId());
            jsonObject.put("idCard", getIdCard());
            jsonObject.put("idUserSeller", getIdUserSeller());
            jsonObject.put("currentAmount", getCurrentAmount());
            jsonObject.put("idCurrentUser",getIdCurrentUser());
            jsonObject.put("beginDate", formatter.format(getBeginDate()));
            jsonObject.put("endDate", formatter.format(getEndDate()));
            jsonObject.put("status", getStatus());
            jsonObject.put("type", getType());
            jsonObject.put("amount", getAmount());

            jsonText = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonText;
    }


    public int getIdCurrentUser() {
        return idCurrentUser;
    }

    public void setIdCurrentUser(int idCurrentUser) {
        this.idCurrentUser = idCurrentUser;
    }
}
