package ua.cn.stu.converter.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.cn.stu.converter.R;

public class LengthFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_length, container, false);
    }

    public static LengthFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LengthFragment fragment = new LengthFragment();
        fragment.setArguments(args);
        return fragment;
    }
}