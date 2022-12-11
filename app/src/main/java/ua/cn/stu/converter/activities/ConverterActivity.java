package ua.cn.stu.converter.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

    private ConverterService converterService = new ConverterService();
    private boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);//it depends on the unit
        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("units").toString();
        switch (name) {
            case "length": units = new String[]{"sm", "m", "km", "in", "mi", "yd", "ft"};break;
            case "weight": units = new String[]{"g", "kg", "ton", "lb"}; break;
            case "temperature": units = new String[]{"K", "C", "F"}; break;
        }
        Log.d("-------------------",units.toString());
        spinner_unitsInput = findViewById(R.id.spinner_unitsInput);
        spinner_unitsOutput = findViewById(R.id.spinner_unitsOutput);
        inputNumber = findViewById(R.id.inputField);
        outputNumber = findViewById(R.id.outputField);
        setupGroupsSpinner();
        setupEditText();
        Intent intent = new Intent(this,ConverterService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }
    private void setupEditText() {
        inputNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                float result = converterService.computing(inputNumber.getText().toString(), outputUnit, inputUnit);
                Log.d("----------------", String.valueOf(result));
                outputNumber.setText(String.format("%.3f",result));
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
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            ConverterService.ConverterBinder binder = (ConverterService.ConverterBinder) service;
            converterService = binder.getBoundService();
            isConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isConnected = false;
        }
    };

}