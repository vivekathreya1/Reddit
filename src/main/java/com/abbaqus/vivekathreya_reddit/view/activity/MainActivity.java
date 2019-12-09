package com.abbaqus.vivekathreya_reddit.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.abbaqus.vivekathreya_reddit.R;
import com.abbaqus.vivekathreya_reddit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

private ActivityMainBinding binding;
private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.main_nav_host_fragment);
        navController.setGraph(R.navigation.navigation_graph);
        setSupportActionBar(binding.toolbar);

    }



}
