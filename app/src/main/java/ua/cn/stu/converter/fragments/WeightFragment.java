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
        String selectedGroup = null;
        if (savedInstanceState != null) {
// EditText fields save/restore input automatically
// so need to restore only Spinner data
            selectedGroup = savedInstanceState
                    .getString("sm");
        }
        setupGroupsSpinner(null);
    }
    private void setupGroupsSpinner(@Nullable String selectedGroup) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                R.layout.item_group,
                units
        );
        adapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line);
        spinner_unitsInput.setAdapter(adapter);
        if (selectedGroup != null) {
            int index = Arrays.asList(units).indexOf(selectedGroup);
            if (index != -1) spinner_unitsInput.setSelection(index);
        }
    }

    public static WeightFragment newInstance() {
        Bundle args = new Bundle();
        WeightFragment fragment = new WeightFragment();
        fragment.setArguments(args);
        return fragment;
    }
}