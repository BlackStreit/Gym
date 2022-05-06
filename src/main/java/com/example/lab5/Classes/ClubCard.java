package com.example.lab5.Classes;

import com.example.lab5.DataBase.DataBase;

import java.sql.Date;
import java.time.LocalDate;

public class ClubCard {
    private int cardId;
    private Date startCard;
    private Date endCard;
    private int price;
    private int serviceId;
    private int clientId;

    public ClubCard(int cardId, Date startCard, Date endCard, int price, int serviceId, int clientId) {
        this.cardId = cardId;
        this.startCard = startCard;
        this.endCard = endCard;
        this.price = price;
        this.serviceId = serviceId;
        this.clientId = clientId;
    }

    public ClubCard() {
        this.cardId = 0;
        this.startCard = Date.valueOf(LocalDate.now());
        this.endCard = Date.valueOf(LocalDate.now().plusDays(2));
        this.price = 0;
        this.serviceId = 0;
        this.clientId = 0;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public Date getStartCard() {
        return startCard;
    }

    public void setStartCard(Date startCard) {
        this.startCard = startCard;
    }

    public Date getEndCard() {
        return endCard;
    }

    public void setEndCard(Date endCard) {
        this.endCard = endCard;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}