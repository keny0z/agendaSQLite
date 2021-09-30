package co.com.k4soft.miagenda.persistencia.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.com.k4soft.miagenda.entitiy.Cita;
import co.com.k4soft.miagenda.entitiy.Persona;
import co.com.k4soft.miagenda.persistencia.dao.CitaDAO;
import co.com.k4soft.miagenda.persistencia.dao.PersonaDAO;

@Database(entities = {
        Cita.class,
        Persona.class }, version = DataBaseHelper.VERSION_BASE_DATOS, exportSchema = false)
public abstract  class DataBaseHelper extends RoomDatabase {

    public static final int VERSION_BASE_DATOS = 1;
    public static final String NOMBRE_BASE_DATOS = "agenda";
    private static DataBaseHelper intance;

    /**
     * Se usa para las transacciones INSERT, UPDATE y DELETE
     * @param context
     * @return
     */
    public static DataBaseHelper getSimpleDB(Context context){
        if(intance == null){
            intance = Room.databaseBuilder(context, DataBaseHelper.class,NOMBRE_BASE_DATOS).build();
        }
        return intance;
    }

    /**
     * SELECT
     * @param context
     * @return
     */
    public static DataBaseHelper getDBMainThread(Context context){
        if(intance == null){
            intance = Room.databaseBuilder(context, DataBaseHelper.class,NOMBRE_BASE_DATOS).allowMainThreadQueries().build();
        }
        return intance;
    }


    public abstract PersonaDAO getPersonaDAO();

    public abstract CitaDAO getCitaDAO();



}
