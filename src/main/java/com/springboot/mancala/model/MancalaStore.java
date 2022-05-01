package com.springboot.mancala.model;

public class MancalaStore {

    private byte mancalaStoreNumber;
    private byte numberOfStones;
    private Player pitOwner;

    public MancalaStore(byte mancalaStoreNumber, Player pitOwner, byte numberOfStones) {
        this.mancalaStoreNumber = mancalaStoreNumber;
        this.pitOwner = pitOwner;
        this.numberOfStones = numberOfStones;
    }

    public byte getMancalaStoreNumber() {
        return mancalaStoreNumber;
    }

    public void setMancalaStoreNumber(byte mancalaStoreNumber) {
        this.mancalaStoreNumber = mancalaStoreNumber;
    }

    public Player getPitOwner() {
        return pitOwner;
    }

    public void setPitOwner(Player pitOwner) {
        this.pitOwner = pitOwner;
    }

    public byte getNumberOfStones() {
        return numberOfStones;
    }

    public void setNumberOfStones(byte numberOfStones) {
        this.numberOfStones = numberOfStones;
    }
}
