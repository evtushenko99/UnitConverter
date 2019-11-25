package com.example.unitconverter.models;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.example.unitconverter.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.unitconverter.models.Unit.DOLLAR;
import static com.example.unitconverter.models.Unit.EURO;
import static com.example.unitconverter.models.Unit.HECTARE;
import static com.example.unitconverter.models.Unit.POUND;
import static com.example.unitconverter.models.Unit.RUBLE;
import static com.example.unitconverter.models.Unit.SQ_CENTIMETRES;
import static com.example.unitconverter.models.Unit.SQ_KILOMETRES;
import static com.example.unitconverter.models.Unit.SQ_METRES;

public enum Conversion {
    AREA(R.string.area, Arrays.asList(SQ_CENTIMETRES, SQ_KILOMETRES, SQ_METRES, HECTARE)),
    COOKING(R.string.cooking, Collections.<Unit>emptyList()),
    CURRENCY(R.string.currency, Arrays.asList(RUBLE, DOLLAR, EURO, POUND)),
    STORAGE(R.string.storage, Collections.<Unit>emptyList()),
    ENERGY(R.string.energy, Collections.<Unit>emptyList()),
    FUEL(R.string.fuel, Collections.<Unit>emptyList()),
    LENGTH(R.string.length, Collections.<Unit>emptyList()),
    MASS(R.string.conversion_mass, Collections.<Unit>emptyList()),
    POWER(R.string.conversion_power, Collections.<Unit>emptyList()),
    PRESSURE(R.string.conversion_pressure, Collections.<Unit>emptyList()),
    SPEED(R.string.conversion_speed, Collections.<Unit>emptyList()),
    TEMPERATURE(R.string.conversion_temperature, Collections.<Unit>emptyList()),
    TIME(R.string.conversion_time, Collections.<Unit>emptyList()),
    VOLUME(R.string.conversion_volume, Collections.<Unit>emptyList());


    @StringRes
    public final int mLabelRes;
    public final List<Unit> mUnitsList;

    Conversion(@StringRes int res, @NonNull List<Unit> units) {
        this.mLabelRes = res;
        this.mUnitsList = units;

    }

    public int getLabelRes() {
        return mLabelRes;
    }

    public List<Unit> getUnitsList() {
        return mUnitsList;
    }
}
