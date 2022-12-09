package ua.cn.stu.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import ua.cn.stu.converter.databinding.ActivityMainBinding;
import ua.cn.stu.converter.fragments.LengthFragment;
import ua.cn.stu.converter.fragments.MenuFragment;
import ua.cn.stu.converter.fragments.TemperatureFragment;
import ua.cn.stu.converter.fragments.WeightFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(R.id.place_holder, new MenuFragment()).commit();
    }
    public void onClickLength(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.place_holder,LengthFragment.newInstance()).commit();
    }
    public void onClickWeight(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.place_holder, WeightFragment.newInstance()).commit();
    }
    public void onClickTemperature(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.place_holder, TemperatureFragment.newInstance()).commit();
    }
}