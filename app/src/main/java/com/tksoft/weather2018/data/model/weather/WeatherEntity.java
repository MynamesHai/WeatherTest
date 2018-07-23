package com.tksoft.weather2018.data.model.weather;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class WeatherEntity {
    @Id(autoincrement = true)
    public Long id;

    @Generated(hash = 1818562779)
    public WeatherEntity(Long id) {
        this.id = id;
    }

    @Generated(hash = 1598697471)
    public WeatherEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
