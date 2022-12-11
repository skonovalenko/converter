package ua.cn.stu.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ua.cn.stu.converter.activities.ConverterActivity;
import ua.cn.stu.converter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();
    }

    public void onClickLength(View view) {
        Intent intent = new Intent(this, ConverterActivity.class);
        intent.putExtra("units", "length");
        startActivity(intent);
    }
    public void onClickWeight(View view) {
        Intent intent = new Intent(this, ConverterActivity.class);
        intent.putExtra("units", "weight");
        startActivity(intent);
    }
    public void onClickTemperature(View view) {
        Intent intent = new Intent(this, ConverterActivity.class);
        intent.putExtra("units", "temperature");
        startActivity(intent);
    }
}