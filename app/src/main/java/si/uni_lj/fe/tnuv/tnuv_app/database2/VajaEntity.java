package si.uni_lj.fe.tnuv.tnuv_app.database2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class VajaEntity {
    @PrimaryKey(autoGenerate = true)
    public int idVaje;

    @ColumnInfo(name = "ime_vaje")
    public String imeVaje;

    @ColumnInfo(name = "muscle_group")
    public String muscleG;

    @ColumnInfo(name = "img_vaje")
    public int imgVaje;

    @ColumnInfo(name = "desc")
    public String desc;

    @ColumnInfo(name = "cals")
    public int cals;

    @ColumnInfo(name = "recommendedSets")
    public int recommendedSets;

    @ColumnInfo(name = "recomendedReps")
    public int recomendedReps;

}


