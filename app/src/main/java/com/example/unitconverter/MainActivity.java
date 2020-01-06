
package com.example.unitconverter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentList = fragmentManager.findFragmentById(R.id.fragment_host_list);
        Fragment fragmentConverter = fragmentManager.findFragmentById(R.id.fragment_host_converter);
        if (fragmentList == null && fragmentConverter == null) {
            fragmentList = new ListFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_host_list, fragmentList)
                    .add(R.id.fragment_host_converter, ConverterFragment.newInstance(null))
                    .commit();
        }
    }


}
