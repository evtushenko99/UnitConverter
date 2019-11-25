package com.example.unitconverter;

import androidx.annotation.NonNull;

import com.example.unitconverter.models.Unit;

import java.math.BigDecimal;

public class Converter {
    private BigDecimal mNumber;
    private final Double mUnitTo;
    private final Double mUnitFrom;

    public Converter(@NonNull BigDecimal number, @NonNull Unit unitTo, @NonNull Unit unitFrom) {
        mNumber = number;
        mUnitFrom = unitFrom.mConversionToBase;
        mUnitTo = unitTo.mConversionFromBase;
    }

    public String makeCalculation() {
        if (mNumber.compareTo(new BigDecimal("0")) > 0) {
            BigDecimal numberFrom = new BigDecimal(mUnitFrom);
            BigDecimal numberTo = new BigDecimal(mUnitTo);

            BigDecimal temp = mNumber.multiply(numberFrom);
            BigDecimal result = temp.multiply(numberTo);
            if (result.compareTo(new BigDecimal("1")) >= 0) {
                return (result.setScale(0, BigDecimal.ROUND_DOWN).toString());
            } else
                return (result.setScale(6, BigDecimal.ROUND_UP).toString());

        } else return "";
    }
}
