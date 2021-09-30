package co.com.k4soft.miagenda.ui.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import co.com.k4soft.miagenda.entitiy.Persona;
import co.com.k4soft.miagenda.persistencia.room.DataBaseHelper;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Estas son las personas registradas");
    }

    public LiveData<String> getText() {
        return mText;
    }


    public LiveData<List<Persona>> listAll(Context context) {
        return DataBaseHelper.getDBMainThread(context).getPersonaDAO().findAll();
    }
}