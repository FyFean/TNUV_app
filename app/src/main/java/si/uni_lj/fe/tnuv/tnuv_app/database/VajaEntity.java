package si.uni_lj.fe.tnuv.tnuv_app.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "vaje")
public class VajaEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "ime_vaje")
    public String imeVaje;

    @ColumnInfo(name = "muscle_group")
    public String muscleG;

    @ColumnInfo(name = "img_vaje")
    public int imgVaje;

}

