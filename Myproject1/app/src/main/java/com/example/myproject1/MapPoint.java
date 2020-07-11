package com.example.myproject1;

public class MapPoint {

    private double posx; // 위도
    private double posy; // 경도

    public MapPoint() {
        super();
    }

    public MapPoint(double posx, double posy) {
        this.posx = posx;
        this.posy = posy;
    }

    public double getLatitude() {
        return posx;
    }

    public void setLatitude(double posx) {
        this.posx = posx;
    }

    public double getLongitude() {
        return posy;
    }

    public void setLongitude(double posy) {
        this.posy = posy;
    }

}
