package co.com.k4soft.miagenda.entitiy;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import co.com.k4soft.miagenda.persistencia.Tabla;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(tableName = Tabla.PERSONA)
@NoArgsConstructor
public class Persona {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="idPersona")
    private Integer idPersona;
    @ColumnInfo(name="documento")
    private String documento;
    @ColumnInfo(name="nombres")
    private String nombres;
}
