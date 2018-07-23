package com.tksoft.weather2018.ui.controllers;

import com.google.android.gms.common.api.Status;

public interface LocationApiListener {
    void doConnectGPS();

    void doNotConnectGPS(Status status);
}
