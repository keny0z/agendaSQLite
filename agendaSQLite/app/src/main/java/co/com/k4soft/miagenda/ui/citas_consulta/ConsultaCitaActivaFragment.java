package co.com.k4soft.miagenda.ui.citas_consulta;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.com.k4soft.miagenda.R;
import co.com.k4soft.miagenda.databinding.FragmentConsultaCitaActivaBinding;
import co.com.k4soft.miagenda.entitiy.Cita;
import co.com.k4soft.miagenda.entitiy.Persona;
import co.com.k4soft.miagenda.ui.citas_cerrar.CerrarCita;
import co.com.k4soft.miagenda.ui.citas_cerrar.CerrarCitaFragment;

public class ConsultaCitaActivaFragment extends Fragment {

    private ConsultaCitaViewModel citasConsultaViewModel;
    private FragmentConsultaCitaActivaBinding binding;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        citasConsultaViewModel =
                new ViewModelProvider(this).get(ConsultaCitaViewModel.class);

        binding = FragmentConsultaCitaActivaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCitasActivas;
        final ListView listViewCitas = binding.listViewCitasActivas;


        citasConsultaViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        citasConsultaViewModel.listCitaActiva(getContext()).observe(getViewLifecycleOwner(), lista ->  {
            List<String> citasArray = new ArrayList<>(lista.size());

            for(Cita cita : lista){
                Persona persona = citasConsultaViewModel.listPersona(getContext(),cita.getDocumentoPersona().toString());
                citasArray.add(cita.getDocumentoPersona()+" "+persona.getNombres()+" "+cita.getFecha()+" "+cita.getHoraInicio()+" "+cita.getObservacion()+" "+cita.getEstado());

                listViewCitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        iniciarCerrarCita(cita,persona);
                    }
                });
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

    private void iniciarCerrarCita(Cita cita, Persona persona){
        Intent intent = new Intent(getActivity().getApplicationContext(), CerrarCita.class);

        intent.putExtra("cita",cita);

        intent.putExtra("documento",persona.getDocumento().toString());
        intent.putExtra("nombres",persona.getNombres().toString());
        intent.putExtra("fechaInicio",cita.getFecha().toString());
        intent.putExtra("horaInicio",cita.getHoraInicio().toString());
        intent.putExtra("observacion",cita.getObservacion().toString());
        intent.putExtra("estado",cita.getEstado().toString());

        startActivity(intent);

    }



}