package co.com.k4soft.miagenda.ui.citas_consulta;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import co.com.k4soft.miagenda.R;
import co.com.k4soft.miagenda.databinding.FragmentConsultaCitaBinding;
import co.com.k4soft.miagenda.entitiy.Cita;
import co.com.k4soft.miagenda.entitiy.Persona;

public class ConsultaCitaFragment extends Fragment {

    private ConsultaCitaViewModel citasConsultaViewModel;
    private FragmentConsultaCitaBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        citasConsultaViewModel =
                new ViewModelProvider(this).get(ConsultaCitaViewModel.class);

        binding = FragmentConsultaCitaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textConsultaCitas;
        final ListView listViewCitas = binding.listViewCitas;


        citasConsultaViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        citasConsultaViewModel.listAll(getContext()).observe(getViewLifecycleOwner(), lista ->  {
            List<String> citasArray = new ArrayList<>(lista.size());

            for(Cita cita : lista){
                Persona persona = citasConsultaViewModel.listPersona(getContext(),cita.getDocumentoPersona().toString());
                citasArray.add(cita.getDocumentoPersona()+" "+persona.getNombres()+" "+cita.getFecha()+" "+cita.getHoraInicio()+" "+cita.getObservacion()+" "+cita.getEstado());
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.select_dialog_item, citasArray);
            listViewCitas.setAdapter(arrayAdapter);


        });


        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}