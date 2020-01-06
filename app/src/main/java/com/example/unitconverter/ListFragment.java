package com.example.unitconverter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unitconverter.adapters.UnitsAdapter;
import com.example.unitconverter.models.Conversion;

import java.util.Arrays;
import java.util.Objects;

public class ListFragment extends Fragment {
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(new UnitsAdapter(Arrays.asList(Conversion.values()), new ItemClickListener() {
            @Override
            public void onItemClickListener(Conversion conversion) {

                FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                Fragment fragmentConverter = fm.findFragmentById(R.id.fragment_host_converter);
                if (fragmentConverter != null) {
                    fm.beginTransaction()
                            .replace(R.id.fragment_host_converter, ConverterFragment.newInstance(conversion))
                            .commit();
                }
            }
        }));

        return root;
    }
}

