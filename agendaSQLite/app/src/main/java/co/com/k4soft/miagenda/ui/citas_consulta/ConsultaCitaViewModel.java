package co.com.k4soft.miagenda.ui.citas_consulta;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import co.com.k4soft.miagenda.entitiy.Cita;
import co.com.k4soft.miagenda.entitiy.Persona;
import co.com.k4soft.miagenda.persistencia.room.DataBaseHelper;

public class ConsultaCitaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConsultaCitaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Estas han sido tus citas agendadas");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Cita>> listAll(Context context) {
        return DataBaseHelper.getDBMainThread(context).getCitaDAO().findAll();
    }

    public LiveData<List<Cita>> listCitaActiva(Context context) {
        return DataBaseHelper.getDBMainThread(context).getCitaDAO().findByEstadoActivo();
    }

    public Persona listPersona(Context context, String documento) {
        return DataBaseHelper.getDBMainThread(context).getPersonaDAO().findByDocumento(documento);
    }

}
