package ua.cn.stu.converter.fragments;

import static javax.measure.unit.NonSI.FOOT;
import static javax.measure.unit.NonSI.INCH;
import static javax.measure.unit.NonSI.MILE;
import static javax.measure.unit.NonSI.POUND;
import static javax.measure.unit.NonSI.TON_US;
import static javax.measure.unit.NonSI.YARD;
import static javax.measure.unit.SI.CENTIMETER;
import static javax.measure.unit.SI.GRAM;
import static javax.measure.unit.SI.KILOGRAM;
import static javax.measure.unit.SI.KILOMETER;
import static javax.measure.unit.SI.METER;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

import javax.measure.converter.UnitConverter;

import ua.cn.stu.converter.R;


public class WeightFragment extends Fragment {
    private String[] units = {"g", "kg", "ton", "lb"};
    private Spinner spinner_unitsInput;
    private Spinner spinner_unitsOutput;
    private EditText inputNumber;
    private EditText outputNumber;
    private String outputUnit;
    private String inputUnit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner_unitsInput = view.findViewById(R.id.spinner_unitsInput);
        spinner_unitsOutput = view.findViewById(R.id.spinner_unitsOutput);
        inputNumber = view.findViewById(R.id.inputField);
        outputNumber = view.findViewById(R.id.outputField);
        setupGroupsSpinner();
        setupEditText();
    }
    private void setupEditText() {
        inputNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                long value = 0;
                if(!inputNumber.getText().toString().isEmpty()) value = Long.parseLong(inputNumber.getText().toString());
                UnitConverter fromUnits = null;
                boolean toConvert = true;
                float result = 0;
                switch (inputUnit){
                    case "g":
                        switch (outputUnit){
                            case "g":  {
                                toConvert = false;
                                result = value;
                            }
                            break;
                            case "kg": {
                                fromUnits = GRAM.getConverterTo(KILOGRAM);
                            } break;
                            case "ton": {
                                fromUnits = GRAM.getConverterTo(TON_US);
                            } break;
                            case "lb": {
                                fromUnits = GRAM.getConverterTo(POUND);
                            } break;
                            default: result = value; break;
                        }
                        break;
                    case "kg":
                        switch (outputUnit){
                            case "g":  {
                                fromUnits = KILOGRAM.getConverterTo(GRAM);
                            }
                            break;
                            case "kg": {
                                toConvert = false;
                                result = value;
                            } break;
                            case "ton": {
                                fromUnits = KILOGRAM.getConverterTo(TON_US);
                            } break;
                            case "lb": {
                                fromUnits = KILOGRAM.getConverterTo(POUND);
                            } break;
                            default: result = value; break;
                        }
                        break;
                    case "ton":
                        switch (outputUnit){
                            case "g":  {
                                fromUnits = TON_US.getConverterTo(GRAM);
                            }
                            break;
                            case "kg": {
                                fromUnits = TON_US.getConverterTo(KILOGRAM);
                            } break;
                            case "ton": {
                                toConvert = false;
                                result = value;
                            } break;
                            case "lb": {
                                fromUnits = TON_US.getConverterTo(POUND);
                            } break;
                            default: result = value; break;
                        }
                        break;
                    case "lb":
                        switch (outputUnit){
                            case "g":  {
                                fromUnits = POUND.getConverterTo(GRAM);
                            }
                            break;
                            case "kg": {
                                fromUnits = POUND.getConverterTo(KILOGRAM);
                            } break;
                            case "ton": {
                                fromUnits = POUND.getConverterTo(TON_US);
                            } break;
                            case "lb": {
                                toConvert = false;
                                result = value;
                            } break;
                            default: result = value; break;
                        }
                        break;
                    default: result = value; break;
                }
                if(toConvert) result = (float) fromUnits.convert(value);
                outputNumber.setText(String.format("%.3f",result));
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("---before empty", inputNumber.getText().toString());
            }
        });
    }
    private void setupGroupsSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                units
        );
        adapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line);
        spinner_unitsInput.setAdapter(adapter);
        spinner_unitsOutput.setAdapter(adapter);
        spinner_unitsInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                inputUnit = parent.getItemAtPosition(position).toString();
                Toast.makeText(view.getContext(), inputUnit, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_unitsOutput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                outputUnit = parent.getItemAtPosition(position).toString();
                Toast.makeText(view.getContext(), outputUnit, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    @NonNull
    @Override
    public String toString() {
        return Arrays.toString(units);
    }

    public static WeightFragment newInstance() {
        Bundle args = new Bundle();
        WeightFragment fragment = new WeightFragment();
        fragment.setArguments(args);
        return fragment;
    }
}