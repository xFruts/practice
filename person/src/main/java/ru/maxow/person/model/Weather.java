package ru.maxow.person.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {
    private Double temp;
    private Double feels_like;
    private Double temp_min;
    private Double temp_max;
    private Integer pressure;
    private Integer humidity;
    private Integer sea_level;
    private Integer grnd_level;
}
