package ua.cn.stu.converter.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;

import ua.cn.stu.converter.R;


public class WeightFragment extends Fragment {
    private String[] units = {"g", "kg", "ton", "ct", "lb", "pood"};
    private Spinner spinner_unitsInput;
    private Spinner spinner_unitsOutput;
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
        spinner_unitsInput = view
                .findViewById(R.id.spinner_unitsInput);
        spinner_unitsOutput = view
                .findViewById(R.id.spinner_unitsOutput);
        setupGroupsSpinner();
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