package kmitl.proj.jittakan58070012.takemehomedemo.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Roy on 11/17/2017.
 */

public class NewDrivecourse {

    String Start;
    String Destination;
    String Contact;
    String Time;
    String Date;
    int SeatAmount;
    List<seat> seat = new ArrayList<>();



    int seatCost;
    String Carbrand;
    String Model;
    String plate;
    String Color;

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getSeatAmount() {
        return SeatAmount;
    }

    public void setSeatAmount(int seatAmount) {
        SeatAmount = seatAmount;
    }

    public int getSeatCost() {
        return seatCost;
    }

    public void setSeatCost(int seatCost) {
        this.seatCost = seatCost;
    }

    public String getCarbrand() {
        return Carbrand;
    }

    public void setCarbrand(String carbrand) {
        Carbrand = carbrand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public List<kmitl.proj.jittakan58070012.takemehomedemo.model.seat> getSeat() {
        return seat;
    }

    public void setSeat(List<kmitl.proj.jittakan58070012.takemehomedemo.model.seat> seat) {
        this.seat = seat;
    }

}





