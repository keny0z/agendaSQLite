package co.com.k4soft.miagenda.ui.citas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import co.com.k4soft.miagenda.R;
import co.com.k4soft.miagenda.databinding.FragmentCrearCitaBinding;
import co.com.k4soft.miagenda.entitiy.Cita;
import co.com.k4soft.miagenda.entitiy.Persona;
import co.com.k4soft.miagenda.persistencia.room.DataBaseHelper;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CitaFragment extends Fragment {

    private CitaViewModel citaViewModel;
    private FragmentCrearCitaBinding binding;
    private Cita cita;

    private EditText documento;
    private EditText fecha;
    private EditText horaInicio;
    private EditText observacion;
    private Integer estadoCita;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        citaViewModel =
                new ViewModelProvider(this).get(CitaViewModel.class);

        binding = FragmentCrearCitaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        fecha= binding.txtFecha;
        horaInicio = binding.txtHoraInicio;
        documento= binding.txtDocumento;
        observacion = binding.txtObservacion;
        final Button btnAgendar = binding.btnAgendar;
        cita = new Cita();

        citaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        btnAgendar.setOnClickListener( v-> {
            cita.setDocumentoPersona(documento.getText().toString());
            cita.setFecha(fecha.getText().toString());
            cita.setHoraInicio(horaInicio.getText().toString());
            cita.setObservacion(observacion.getText().toString());
            Persona citaPersonaConsultada = DataBaseHelper.getDBMainThread(getContext()).getPersonaDAO().findByDocumento(cita.getDocumentoPersona());

            Cita citaHoraFechaConsultada = DataBaseHelper.getDBMainThread(getContext()).getCitaDAO().findByHoraFecha(cita.getHoraInicio(), cita.getFecha());
            if(hayCamposVacíos()){
                Toast.makeText(getContext(),getText(R.string.campos_obligatorios),Toast.LENGTH_LONG).show();
            }else if(citaPersonaConsultada==null){
                Toast.makeText(getContext(),getText(R.string.persona_cita_no_existe),Toast.LENGTH_LONG).show();
            }else if(tieneCitasActivas(citaPersonaConsultada)){
                Toast.makeText(getContext(),getText(R.string.persona_cita_no_disponible),Toast.LENGTH_LONG).show();
            }else{
                if(citaHoraFechaConsultada == null){
                    cita.setEstado(1);
                    insertarInformacion();
                    Toast.makeText(getContext(),getText(R.string.cita_informacion_exitosa),Toast.LENGTH_LONG).show();
                    borrarInformacion();
                }
                else {
                    Toast.makeText(getContext(),getText(R.string.cita_ya_existe),Toast.LENGTH_LONG).show();
                }
            }


        });
        return root;
    }

    private void borrarInformacion() {
        cita = new Cita();
        documento.setText("");
        fecha.setText("");
        horaInicio.setText("");
        observacion.setText("");
    }

    private void insertarInformacion() {
        Observable.fromCallable(()-> {
            DataBaseHelper.getSimpleDB(getContext()).getCitaDAO().create(cita);
            return cita;
        }).subscribeOn(Schedulers.computation()).subscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean hayCamposVacíos(){
        boolean hayVacios= false;
        if(documento == null || "".equals(documento.getText().toString()) || fecha==null || "".equals(fecha.getText().toString())
                || horaInicio==null || "".equals(horaInicio.getText().toString()) || observacion==null || "".equals(observacion.getText().toString()) ){
            hayVacios=true;
        }
        return hayVacios;
    }

    private boolean tieneCitasActivas(Persona persona){
        boolean tieneActivas = true;
        Cita citaConsultada = DataBaseHelper.getDBMainThread(getContext()).getCitaDAO().findByEstadoActivo(persona.getDocumento());
        if(citaConsultada==null){
            tieneActivas=false;
        }
        return tieneActivas;
    }
}