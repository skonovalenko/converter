package ua.cn.stu.converter.fragments;

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

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import javax.measure.Measure;
import javax.measure.converter.UnitConverter;
import static javax.measure.unit.NonSI.*;
import static javax.measure.unit.SI.*;

import ua.cn.stu.converter.R;

public class LengthFragment extends Fragment {
    private final String[] units = {"sm", "m", "km", "in", "mi", "yd", "ft"};
    private Spinner spinner_unitsInput;
    private Spinner spinner_unitsOutput;
    private EditText inputNumber;
    private EditText outputNumber;
    private String outputUnit;
    private String inputUnit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_length, container, false);
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
                    case "sm":
                        switch (outputUnit){
                            case "sm":  {
                                toConvert = false;
                                result = value;
                            }
                            break;
                            case "m": {
                                fromUnits = CENTIMETER.getConverterTo(METER);
                            } break;
                            case "km": {
                                fromUnits = CENTIMETER.getConverterTo(KILOMETER);
                            } break;
                            case "in": {
                                fromUnits = CENTIMETER.getConverterTo(INCH);
                            } break;
                            case "mi": {
                                fromUnits = CENTIMETER.getConverterTo(MILE);
                            } break;
                            case "yd": {
                                fromUnits = CENTIMETER.getConverterTo(YARD);
                            } break;
                            case "foor": {
                                fromUnits = CENTIMETER.getConverterTo(FOOT);
                            } break;
                            default: result = value; break;
                        }
                        break;
                    case "m":
                        switch (outputUnit){
                        case "sm":  {
                            fromUnits = METER.getConverterTo(CENTIMETER);
                        }
                        break;
                        case "m": {
                            toConvert = false;
                            result = value;
                        } break;
                        case "km": {
                            fromUnits = METER.getConverterTo(KILOMETER);
                        } break;
                        case "in": {
                            fromUnits = METER.getConverterTo(INCH);
                        } break;
                        case "mi": {
                            fromUnits = METER.getConverterTo(MILE);
                        } break;
                        case "yd": {
                            fromUnits = METER.getConverterTo(YARD);
                        } break;
                        case "foor": {
                            fromUnits = METER.getConverterTo(FOOT);
                        } break;
                        default: result = value; break;
                    }
                        break;
                    case "km":
                        switch (outputUnit){
                        case "sm":  {
                            fromUnits = KILOMETER.getConverterTo(CENTIMETER);
                        }
                        break;
                        case "m": {
                            fromUnits = KILOMETER.getConverterTo(METER);
                        } break;
                        case "km": {
                            toConvert = false;
                            result = value;
                        } break;
                        case "in": {
                            fromUnits = KILOMETER.getConverterTo(INCH);
                        } break;
                        case "mi": {
                            fromUnits = KILOMETER.getConverterTo(MILE);
                        } break;
                        case "yd": {
                            fromUnits = KILOMETER.getConverterTo(YARD);
                        } break;
                        case "foor": {
                            fromUnits = KILOMETER.getConverterTo(FOOT);
                        } break;
                        default: result = value; break;
                    }
                        break;
                    case "in":
                        switch (outputUnit){
                            case "sm":  {
                                fromUnits = INCH.getConverterTo(CENTIMETER);
                            }
                            break;
                            case "m": {
                                fromUnits = INCH.getConverterTo(METER);
                            } break;
                            case "km": {
                                fromUnits = INCH.getConverterTo(KILOMETER);
                            } break;
                            case "in": {
                                toConvert = false;
                                result = value;
                            } break;
                            case "mi": {
                                fromUnits = INCH.getConverterTo(MILE);
                            } break;
                            case "yd": {
                                fromUnits = INCH.getConverterTo(YARD);
                            } break;
                            case "foor": {
                                fromUnits = INCH.getConverterTo(FOOT);
                            } break;
                            default: result = value; break;
                        }
                        break;
                    case "mi":
                        switch (outputUnit){
                            case "sm":  {
                                fromUnits = MILE.getConverterTo(CENTIMETER);
                            }
                            break;
                            case "m": {
                                fromUnits = MILE.getConverterTo(METER);
                            } break;
                            case "km": {
                                fromUnits = MILE.getConverterTo(KILOMETER);
                            } break;
                            case "in": {
                                fromUnits = MILE.getConverterTo(INCH);
                            } break;
                            case "mi": {
                                toConvert = false;
                                result = value;
                            } break;
                            case "yd": {
                                fromUnits = MILE.getConverterTo(YARD);
                            } break;
                            case "foor": {
                                fromUnits = MILE.getConverterTo(FOOT);
                            } break;
                            default: result = value; break;
                        }
                        break;
                    case "yd":
                        switch (outputUnit){
                            case "sm":  {
                                fromUnits = YARD.getConverterTo(CENTIMETER);
                            }
                            break;
                            case "m": {
                                fromUnits = YARD.getConverterTo(METER);
                            } break;
                            case "km": {
                                fromUnits = YARD.getConverterTo(METER);
                            } break;
                            case "in": {
                                fromUnits = YARD.getConverterTo(INCH);
                            } break;
                            case "mi": {
                                fromUnits = YARD.getConverterTo(MILE);
                            } break;
                            case "yd": {
                                toConvert = false;
                                result = value;
                            } break;
                            case "foor": {
                                fromUnits = YARD.getConverterTo(FOOT);
                            } break;
                            default: result = value; break;
                        }
                        break;
                    case "foor": switch (outputUnit){
                        case "sm":  {
                            fromUnits = FOOT.getConverterTo(CENTIMETER);
                        }
                        break;
                        case "m": {
                            fromUnits = FOOT.getConverterTo(METER);
                        } break;
                        case "km": {
                            fromUnits = FOOT.getConverterTo(KILOMETER);
                        } break;
                        case "in": {
                            fromUnits = FOOT.getConverterTo(INCH);
                        } break;
                        case "mi": {
                            fromUnits = FOOT.getConverterTo(MILE);
                        } break;
                        case "yd": {
                            fromUnits = FOOT.getConverterTo(YARD);
                        } break;
                        case "foor": {
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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_dropdown_item_1line,
                units
        );
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
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

    public static LengthFragment newInstance() {
        Bundle args = new Bundle();
        LengthFragment fragment = new LengthFragment();
        fragment.setArguments(args);
        return fragment;
    }
}