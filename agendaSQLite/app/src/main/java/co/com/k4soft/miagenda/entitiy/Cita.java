package co.com.k4soft.miagenda.entitiy;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.io.Serializable;

import co.com.k4soft.miagenda.persistencia.Tabla;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(tableName = Tabla.CITA)
@NoArgsConstructor
public class Cita implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="idCita")
    private Integer idCita;
    @ColumnInfo(name="documentoPersona")
    private String documentoPersona;
    @ColumnInfo(name="fecha")
    private String fecha;
    @ColumnInfo(name="horaInicio")
    private String horaInicio;
    @ColumnInfo(name="estado")
    private Integer estado;
    @ColumnInfo(name="observacion")
    private String observacion;
    @ColumnInfo(name="horaFin")
    private String horaFin;
    @ColumnInfo(name="fechaFin")
    private String fechaFin;

}
