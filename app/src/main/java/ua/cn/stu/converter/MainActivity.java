package ua.cn.stu.converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import ua.cn.stu.converter.databinding.ActivityMainBinding;
import ua.cn.stu.converter.fragments.LengthFragment;
import ua.cn.stu.converter.fragments.MenuFragment;
import ua.cn.stu.converter.fragments.TemperatureFragment;
import ua.cn.stu.converter.fragments.WeightFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Fragment menu = MenuFragment.newInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.place_holder, menu)
                .addToBackStack("menu")
                .commit();
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();
    }

    public void onClickLength(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(menu)
                .add(R.id.place_holder,LengthFragment.newInstance())
                .addToBackStack("Length")
                .commit();
    }
    public void onClickWeight(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(menu)
                .add(R.id.place_holder,WeightFragment.newInstance())
                .addToBackStack("Weight")
                .commit();
    }
    public void onClickTemperature(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(menu)
                .add(R.id.place_holder,TemperatureFragment.newInstance())
                .addToBackStack("Temperature")
                .commit();
    }
}