package com.tksoft.weather2018.data.model.address;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Location {
    @Id(autoincrement = true)
    public Long id;
    public double lat = 0;
    public double lng = 0;
    @Generated(hash = 868736495)
    public Location(Long id, double lat, double lng) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
    }
    @Generated(hash = 375979639)
    public Location() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getLat() {
        return this.lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLng() {
        return this.lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }

}
