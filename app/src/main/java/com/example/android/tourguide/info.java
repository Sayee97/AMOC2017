package com.example.android.tourguide;

/**
 * Created by Dream on 04-04-2017.
 */

public class info {

    private String  id,destination,address,inDate,outDate,inTime,outTime;


    public info(String id, String destination, String address, String inDate, String outDate, String inTime, String outTime){
        this.setId(id);
        this.setAddress(address);
        this.setDestination(destination);
        this.setInDate(inDate);
        this.setInTime(inTime);
        this.setOutTime(outTime);
        this.setOutDate(outDate);

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
}
