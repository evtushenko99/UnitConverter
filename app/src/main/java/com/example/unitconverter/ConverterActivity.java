package com.example.unitconverter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unitconverter.adapters.SpinnerAdapter;
import com.example.unitconverter.models.Conversion;
import com.example.unitconverter.models.Unit;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConverterActivity extends AppCompatActivity {

    private static final String ITEM_KEY = "converter";
    private TextView mHeadLineTextView;

    private TextInputLayout mHelpTextFrom;
    private TextInputEditText mInputFromEditText;
    private TextInputLayout mHelpTextTo;
    private TextInputEditText mInputToEditText;

    private Spinner mSpinnerFrom;
    private Spinner mSpinnerTo;

    private Conversion mConversion;
    private List<Unit> mUnitList;
    private Unit mUnitFrom;
    private Unit mUnitTo;
    private BigDecimal mGetNumber;

    public static Intent getStartedIntent(Context context, @NonNull Conversion conversion) {
        Intent intent = new Intent(context, ConverterActivity.class);
        intent.putExtra(ITEM_KEY, conversion);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        mGetNumber = new BigDecimal("0");


        mConversion = (Conversion) getIntent().getExtras().getSerializable(ITEM_KEY);
        mUnitList = mConversion.getUnitsList();
        mHeadLineTextView = findViewById(R.id.headline);

        initSpinners();
        mHeadLineTextView.setText(getString(mConversion.getLabelRes()));

        mInputFromEditText = findViewById(R.id.text_input_conversion_from);
        mInputToEditText = findViewById(R.id.text_input_conversion_to);
        mInputFromEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    mGetNumber = (new BigDecimal(s.toString()));
                    setConverter();
                } else {
                    mInputToEditText.setText(s.toString());
                }
            }
        });

    }

    private void setConverter() {
        Converter converter = new Converter(mGetNumber, mUnitTo, mUnitFrom);
        mInputToEditText.setText(converter.makeCalculation());
    }


    private void initSpinners() {
        mSpinnerFrom = findViewById(R.id.spinner_from);
        mSpinnerTo = findViewById(R.id.spinner_to);
        mHelpTextFrom = findViewById(R.id.help_text_from);

        mHelpTextTo = findViewById(R.id.help_text_to);
        List<String> spinnerItems = new ArrayList<>();

        for (Unit unit : mUnitList) {
            spinnerItems.add(getString(unit.mLabelResource));
        }
        SpinnerAdapter spinnerAdapterFrom = new SpinnerAdapter(spinnerItems);
        mSpinnerFrom.setAdapter(spinnerAdapterFrom);
        SpinnerAdapter spinnerAdapterTo = new SpinnerAdapter(spinnerItems);
        mSpinnerTo.setAdapter(spinnerAdapterTo);
        mSpinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = view.findViewById(R.id.spinner_text_view);
                mHelpTextFrom.setHint(textView.getText());
                mUnitFrom = mUnitList.get(position);
                if (mUnitTo != null)
                    setConverter();
//
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = view.findViewById(R.id.spinner_text_view);
                mHelpTextTo.setHint(textView.getText());
                mUnitTo = mUnitList.get(position);
                if (mUnitFrom != null)
                    setConverter();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


}
