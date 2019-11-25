
package com.example.unitconverter;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unitconverter.adapters.UnitsAdapter;
import com.example.unitconverter.models.Conversion;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView(savedInstanceState == null);
    }

    private void initRecyclerView(boolean isFirstCreate) {
        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(new UnitsAdapter(Arrays.asList(Conversion.values()), new ItemClickListener() {
            @Override
            public void onItemClickListener(Conversion conversion) {
                Intent intent = ConverterActivity.getStartedIntent(MainActivity.this, conversion);
                startActivity(intent);
            }
        }));
    }

}
