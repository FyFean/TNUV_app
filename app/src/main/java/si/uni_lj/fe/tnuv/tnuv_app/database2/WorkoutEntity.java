package si.uni_lj.fe.tnuv.tnuv_app.database2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WorkoutEntity {
    @PrimaryKey(autoGenerate = true)
    public int idWorkouta;

    @ColumnInfo(name = "ime_workouta")
    public String imeWorkouta;

    @ColumnInfo(name = "isCustom")
    public int isCustom;

    @ColumnInfo(name = "trajanje")
    public int trajanje;

    @ColumnInfo(name = "totalCals")
    public int totalCals;

}