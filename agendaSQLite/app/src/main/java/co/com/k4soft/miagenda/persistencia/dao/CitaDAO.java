package co.com.k4soft.miagenda.persistencia.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.com.k4soft.miagenda.entitiy.Cita;
import co.com.k4soft.miagenda.entitiy.Persona;

@Dao
public interface CitaDAO {

    @Insert
    void create(Cita cita);

    @Query("SELECT * FROM cita")
    LiveData<List<Cita>> findAll();

    @Query("SELECT * FROM cita where documentoPersona =:documentoPersona")
    Cita findByDocumentoPersona(String documentoPersona);

    @Query("SELECT * FROM cita where documentoPersona =:documentoPersona and estado=1")
    Cita findByEstadoActivo(String documentoPersona);

    @Query("SELECT * FROM cita where estado=1")
    LiveData<List<Cita>>  findByEstadoActivo();

    @Query("SELECT * FROM cita where fecha =:fecha")
    Cita findByFecha(String fecha);

    @Query("SELECT * FROM cita where horaInicio =:hora")
    Cita findByHoraInicio(String hora);

    @Query("SELECT * FROM cita where horaInicio =:hora AND fecha=:fecha")
    Cita findByHoraFecha(String hora, String fecha);

    @Update
    void actualizarCita(Cita cita);



}
