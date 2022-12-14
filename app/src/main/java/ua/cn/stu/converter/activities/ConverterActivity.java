package ua.cn.stu.converter.activities;

import static javax.measure.unit.NonSI.FAHRENHEIT;
import static javax.measure.unit.NonSI.FOOT;
import static javax.measure.unit.NonSI.INCH;
import static javax.measure.unit.NonSI.MILE;
import static javax.measure.unit.NonSI.POUND;
import static javax.measure.unit.NonSI.TON_US;
import static javax.measure.unit.NonSI.YARD;
import static javax.measure.unit.SI.CELSIUS;
import static javax.measure.unit.SI.CENTIMETER;
import static javax.measure.unit.SI.GRAM;
import static javax.measure.unit.SI.KELVIN;
import static javax.measure.unit.SI.KILOGRAM;
import static javax.measure.unit.SI.KILOMETER;
import static javax.measure.unit.SI.METER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

import javax.measure.converter.UnitConverter;

import ua.cn.stu.converter.ConverterThread;
import ua.cn.stu.converter.R;
import ua.cn.stu.converter.databinding.ActivityMainBinding;
import ua.cn.stu.converter.services.ConverterService;

public class ConverterActivity extends AppCompatActivity {

    private String[] units;
    private Spinner spinner_unitsInput;
    private Spinner spinner_unitsOutput;
    private EditText inputNumber;
    private EditText outputNumber;
    private String outputUnit;
    private String inputUnit;
    private float result = 0;

    private ConverterThread converterThread;
    //private ConverterService converterService = new ConverterService();
    //private boolean isConnected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);//it depends on the unit
        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("units").toString();
        switch (name) {
            case "length":
                units = new String[]{"sm", "m", "km", "in", "mi", "yd", "ft"};
                break;
            case "weight":
                units = new String[]{"g", "kg", "ton", "lb"};
                break;
            case "temperature":
                units = new String[]{"K", "C", "F"};
                break;
        }
        spinner_unitsInput = findViewById(R.id.spinner_unitsInput);
        spinner_unitsOutput = findViewById(R.id.spinner_unitsOutput);
        inputNumber = findViewById(R.id.inputField);
        outputNumber = findViewById(R.id.outputField);
        setupGroupsSpinner();
        setupEditText();
        converterThread = new ConverterThread();
        converterThread.start();
    }

    private void setupEditText() {
        inputNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!inputNumber.getText().toString().isEmpty()) textChange();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void setupGroupsSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
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
                if (!inputNumber.getText().toString().isEmpty()) textChange();
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
                if (!inputNumber.getText().toString().isEmpty()) textChange();
                Toast.makeText(view.getContext(), outputUnit, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }
    private void textChange() {
        CountDownLatch latch = new CountDownLatch(1);
        ConverterThread.ConverterCounting c =
                new ConverterThread.ConverterCounting(latch,inputNumber.getText().toString(),outputUnit,inputUnit);
        Log.d("------------", "Adding new computing to looper");
        // add this runnable to looper
        converterThread.addTask(c);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        float result = c.getResult();
        Log.d("----------------", String.valueOf(result));
        outputNumber.setText(String.valueOf(BigDecimal.valueOf(result).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue()));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        converterThread.interrupt();
    }
}