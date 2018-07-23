package com.tksoft.weather2018.data.model.address;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class Address {
    @Id(autoincrement = true)
    public Long id;

    public String formatted_address = "";
    public boolean isAdView = false;
    public boolean isCurrentAddress = false;
    public String country = "";
    public String city = "";

    public long geometryID = 0;

    @ToOne(joinProperty = "geometryID")
    public Geometry geometry;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1580986028)
    private transient AddressDao myDao;

    @Generated(hash = 1046489258)
    public Address(Long id, String formatted_address, boolean isAdView,
            boolean isCurrentAddress, String country, String city,
            long geometryID) {
        this.id = id;
        this.formatted_address = formatted_address;
        this.isAdView = isAdView;
        this.isCurrentAddress = isCurrentAddress;
        this.country = country;
        this.city = city;
        this.geometryID = geometryID;
    }

    @Generated(hash = 388317431)
    public Address() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormatted_address() {
        return this.formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public boolean getIsAdView() {
        return this.isAdView;
    }

    public void setIsAdView(boolean isAdView) {
        this.isAdView = isAdView;
    }

    public boolean getIsCurrentAddress() {
        return this.isCurrentAddress;
    }

    public void setIsCurrentAddress(boolean isCurrentAddress) {
        this.isCurrentAddress = isCurrentAddress;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getGeometryID() {
        return this.geometryID;
    }

    public void setGeometryID(long geometryID) {
        this.geometryID = geometryID;
    }

    @Generated(hash = 1671694240)
    private transient Long geometry__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1423908428)
    public Geometry getGeometry() {
        long __key = this.geometryID;
        if (geometry__resolvedKey == null || !geometry__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GeometryDao targetDao = daoSession.getGeometryDao();
            Geometry geometryNew = targetDao.load(__key);
            synchronized (this) {
                geometry = geometryNew;
                geometry__resolvedKey = __key;
            }
        }
        return geometry;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1123968792)
    public void setGeometry(@NotNull Geometry geometry) {
        if (geometry == null) {
            throw new DaoException(
                    "To-one property 'geometryID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.geometry = geometry;
            geometryID = geometry.getId();
            geometry__resolvedKey = geometryID;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 543375780)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAddressDao() : null;
    }



}
