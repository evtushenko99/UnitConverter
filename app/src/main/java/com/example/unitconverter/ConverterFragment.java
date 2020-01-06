package com.example.unitconverter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.unitconverter.adapters.SpinnerAdapter;
import com.example.unitconverter.models.Conversion;
import com.example.unitconverter.models.Unit;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConverterFragment extends Fragment {

    private static final String NEW_CONVERTER = "new";
    private static final String NUMBER = "number";
    private static final String UNITTO = "mUnitTo";
    private static final String UNITFROM = "mUnitFrom";
    private View root;
    private TextView mHeadLineTextView;

    private TextInputLayout mHelpTextFrom;
    private TextInputEditText mInputFromEditText;
    private TextInputLayout mHelpTextTo;
    private TextInputEditText mInputToEditText;

    private Spinner mSpinnerFrom;
    private Spinner mSpinnerTo;
    private Converter mConverter;
    private Conversion mConversion;
    private List<Unit> mUnitList;
    private Unit mUnitFrom;
    private Unit mUnitTo;
    private BigDecimal mGetNumber;

    public static ConverterFragment newInstance(Conversion conversion) {

        Bundle args = new Bundle();
        args.putSerializable(NEW_CONVERTER, conversion);
        ConverterFragment fragment = new ConverterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mConversion = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mGetNumber = new BigDecimal("0");

        if (getArguments() != null) {
            mConversion = (Conversion) getArguments().getSerializable(NEW_CONVERTER);
        }
        if (savedInstanceState != null) {
            mUnitFrom = (Unit) savedInstanceState.getSerializable(UNITFROM);
            mUnitTo = (Unit) savedInstanceState.getSerializable(UNITFROM);
        }

        if (mConversion != null) {
            mUnitList = mConversion.getUnitsList();
            root = inflater.inflate(R.layout.fragment_converter, container, false);

            mHeadLineTextView = root.findViewById(R.id.headline);
            mHeadLineTextView.setText(getString(mConversion.getLabelRes()));

            mSpinnerFrom = root.findViewById(R.id.spinner_from);
            mSpinnerTo = root.findViewById(R.id.spinner_to);
            mHelpTextFrom = root.findViewById(R.id.help_text_from);

            mHelpTextTo = root.findViewById(R.id.help_text_to);
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
                    if (view != null) {
                        TextView textView = view.findViewById(R.id.spinner_text_view);
                        mHelpTextFrom.setHint(textView.getText());
                    }
                    if (mUnitFrom == null)
                        mUnitFrom = mUnitList.get(position);
                    if (mUnitTo != null)
                        setConverter();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            mSpinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (view != null) {
                        TextView textView = view.findViewById(R.id.spinner_text_view);
                        mHelpTextTo.setHint(textView.getText());
                    }
                    if (mUnitTo == null)
                    mUnitTo = mUnitList.get(position);
                    if (mUnitFrom != null)
                        setConverter();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            mInputFromEditText = root.findViewById(R.id.text_input_conversion_from);
            mInputToEditText = root.findViewById(R.id.text_input_conversion_to);
            mInputFromEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() != 0) {
                        mGetNumber = (new BigDecimal(s.toString()));
                        setConverter();
                    } else {
                        mInputToEditText.setText("");
                    }
                }
            });
        } else {
            root = inflater.inflate(R.layout.empty_fragment, container, false);
        }
        return root;
    }


    private void setConverter() {
        mConverter = new Converter(mGetNumber, mUnitTo, mUnitFrom);
        mInputToEditText.setText(mConverter.makeCalculation());
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(NUMBER, mGetNumber.intValue());
        outState.putSerializable(UNITTO, mUnitTo);
        outState.putSerializable(UNITFROM, mUnitFrom);


    }
}
