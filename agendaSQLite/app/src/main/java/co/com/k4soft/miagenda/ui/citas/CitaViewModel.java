package co.com.k4soft.miagenda.ui.citas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CitaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CitaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Asigna una nueva cita");
    }

    public LiveData<String> getText() {
        return mText;
    }
}