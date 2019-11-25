package com.example.unitconverter.models;

import androidx.annotation.StringRes;

import com.example.unitconverter.R;

public enum Unit {
    SQ_CENTIMETRES(R.string.sq_centimetres, 0.0001, 10000.0),
    SQ_METRES(R.string.sq_metres, 1.0, 1.0),
    HECTARE(R.string.hectare, 10000.0, 0.0001),
    SQ_KILOMETRES(R.string.sq_kilometres, 1000000.0, 0.000001),

    RUBLE(R.string.unit_currency_ruble, 1.0, 1.0),
    DOLLAR(R.string.unit_currency_dollar, 64.0, 0.015625),
    EURO(R.string.unit_currency_euro, 71.0, 0.0140845),
    POUND(R.string.unit_currency_pound, 82.0, 0.0121951),

    KILOMETRE(R.string.hectare, 1000.0, 0.001),
    MILE(R.string.hectare, 1609.344, 0.00062137),
    METRE(R.string.hectare, 1.0, 1.0),
    CENTIMENTRE(R.string.hectare, 0.01, 0.000001),
    MILLIMETRE(R.string.hectare, 1000000.0, 0.000001),
    MICTOMETRE(R.string.hectare, 1000000.0, 0.000001);

    public int getLabelResource() {
        return mLabelResource;
    }

    public double getConversionToBase() {
        return mConversionToBase;
    }

    public double getConversionFromBase() {
        return mConversionFromBase;
    }

    @StringRes
    public int mLabelResource;
    public double mConversionToBase;
    public double mConversionFromBase;

    Unit(@StringRes int labelResource, double conversionToBase, double conversionFromBase) {
        mLabelResource = labelResource;
        mConversionToBase = conversionToBase;
        mConversionFromBase = conversionFromBase;
    }
}
