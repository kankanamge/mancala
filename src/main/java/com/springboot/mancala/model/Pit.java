package com.springboot.mancala.model;

public class Pit {

    private byte pitNumber;
    private Player pitOwner;
    private byte numberOfStones;

    public Pit(byte pitNumber, Player pitOwner, byte numberOfStones) {
        this.pitNumber = pitNumber;
        this.pitOwner = pitOwner;
        this.numberOfStones = numberOfStones;
    }

    public byte getPitNumber() {
        return pitNumber;
    }

    public void setPitNumber(byte pitNumber) {
        this.pitNumber = pitNumber;
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
