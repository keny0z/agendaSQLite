package co.com.k4soft.miagenda.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import co.com.k4soft.miagenda.R;
import co.com.k4soft.miagenda.databinding.FragmentHomeBinding;
import co.com.k4soft.miagenda.entitiy.Persona;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        final ListView listViewPersonas = binding.listViewPersonas;
        homeViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        homeViewModel.listAll(getContext()).observe(getViewLifecycleOwner(), lista ->  {
            List<String> personasArray = new ArrayList<>(lista.size());
            for(Persona persona : lista){
                personasArray.add(persona.getDocumento()+" "+persona.getNombres());
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.select_dialog_item, personasArray);
            listViewPersonas.setAdapter(arrayAdapter);
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}