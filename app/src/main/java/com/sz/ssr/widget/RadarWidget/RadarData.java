package com.sz.ssr.widget.RadarWidget;



public class RadarData {

    private String title;
    private double percentage;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public RadarData(String title, double percentage) {
        this.title = title;
        this.percentage = percentage;
    }

    public String getTitle() {
        return title;
    }

    public double getPercentage() {
        return percentage;
    }
}
