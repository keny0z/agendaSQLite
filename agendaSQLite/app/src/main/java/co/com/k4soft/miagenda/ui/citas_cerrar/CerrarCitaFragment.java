package co.com.k4soft.miagenda.ui.citas_cerrar;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.com.k4soft.miagenda.R;

public class CerrarCitaFragment extends Fragment {

    private CerrarCitaViewModel mViewModel;

    public static CerrarCitaFragment newInstance() {
        return new CerrarCitaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cerrar_cita_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CerrarCitaViewModel.class);
        // TODO: Use the ViewModel
    }

}