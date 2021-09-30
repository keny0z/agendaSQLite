package co.com.k4soft.miagenda.ui.citas_cerrar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import co.com.k4soft.miagenda.R;
import co.com.k4soft.miagenda.entitiy.Cita;
import co.com.k4soft.miagenda.persistencia.room.DataBaseHelper;
import co.com.k4soft.miagenda.ui.citas_consulta.ConsultaCitaActivaFragment;

public class CerrarCita extends AppCompatActivity {

    private TextView txtDocumento;
    private TextView txtNombres;
    private TextView txtFechaInicio;
    private TextView txtHoraInicio;
    private TextView txtObservacion;
    private TextView txtEstado;

    private EditText txtFechaFin;
    private EditText txtHoraFin;

    private Button btnCerrarCita;

    private Cita cita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_cita);

        initComponents();

        try{
            cita= (Cita) getIntent().getExtras().getSerializable("cita");
        }catch (Exception e){
            finish();
        }

        String documento = getIntent().getStringExtra("documento");
        txtDocumento.setText(documento);

        String nombres = getIntent().getStringExtra("nombres");
        txtNombres.setText(nombres);

        String fechaInicio = getIntent().getStringExtra("fechaInicio");
        txtFechaInicio.setText(fechaInicio);

        String horaInicio = getIntent().getStringExtra("horaInicio");
        txtHoraInicio.setText(horaInicio);

        String observacion = getIntent().getStringExtra("observacion");
        txtObservacion.setText(observacion);

        String estado = getIntent().getStringExtra("estado");
        txtEstado.setText(estado);

        btnCerrarCita.setOnClickListener(v -> {
            if(hayCamposVacíos()){
                Toast.makeText(getApplicationContext(),getText(R.string.campos_obligatorios),Toast.LENGTH_LONG).show();

            }else {
                cita.setEstado(0);
                cita.setFechaFin(txtFechaFin.getText().toString());
                cita.setHoraFin(txtHoraFin.getText().toString());
                DataBaseHelper.getDBMainThread(getApplicationContext()).getCitaDAO().actualizarCita(cita);
                //iniciarConsultaCitaActivaFragment();
                Toast.makeText(getApplicationContext(),getText(R.string.cita_desactivada),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initComponents(){
        txtDocumento = findViewById(R.id.txtDocumento);
        txtNombres = findViewById(R.id.txtNombres);
        txtFechaInicio = findViewById(R.id.txtFechaInicio);
        txtHoraInicio = findViewById(R.id.txtHoraInicio);
        txtObservacion = findViewById(R.id.txtObservacion);
        txtEstado = findViewById(R.id.txtEstado);
        txtFechaFin = findViewById(R.id.txtFechaFin);
        txtHoraFin = findViewById(R.id.txtHoraFin);
        btnCerrarCita = findViewById(R.id.btnCerrarCita);


    }

    private boolean hayCamposVacíos(){
        boolean hayVacios= false;
        if(txtFechaFin == null || "".equals(txtFechaFin.getText().toString()) || txtHoraFin==null || "".equals(txtHoraFin.getText().toString())){
            hayVacios=true;
        }
        return hayVacios;
    }

    private void iniciarConsultaCitaActivaFragment(){
        Intent intent = new Intent(getApplicationContext(), ConsultaCitaActivaFragment.class);
        startActivity(intent);
    }
}