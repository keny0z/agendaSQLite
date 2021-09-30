package co.com.k4soft.miagenda.persistencia.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import co.com.k4soft.miagenda.entitiy.Persona;

@Dao
public interface PersonaDAO {

    @Insert
    void create(Persona persona);

    @Query("SELECT * FROM persona")
    LiveData<List<Persona>> findAll();
    @Query("SELECT * FROM persona where documento =:documento")
    Persona findByDocumento(String documento);
}
