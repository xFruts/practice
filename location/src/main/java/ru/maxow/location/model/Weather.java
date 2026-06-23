package ru.maxow.location.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int humidity;
    private int sea_level;
    private int grnd_level;
}
