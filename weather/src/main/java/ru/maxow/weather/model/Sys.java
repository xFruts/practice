package ru.maxow.weather.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sys {
    private String country;
    private int sunrise;
    private int sunset;
}
