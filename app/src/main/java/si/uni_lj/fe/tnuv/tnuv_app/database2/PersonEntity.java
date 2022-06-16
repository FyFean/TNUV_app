package si.uni_lj.fe.tnuv.tnuv_app.database2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

import si.uni_lj.fe.tnuv.tnuv_app.Workout;

@Entity
public class PersonEntity {
    @PrimaryKey(autoGenerate = true)
    public int idPerson;

    @ColumnInfo(name = "imePriimek")
    public String imePriimek;

    @ColumnInfo(name = "spol")
    public String spol;

    @ColumnInfo(name = "caloriesGoal")
    public int caloriesGoal;

    @ColumnInfo(name = "timeGoal")
    public int timeGoal;

    @ColumnInfo(name = "bodyWeight")
    public int bodyWeight;

    @ColumnInfo(name = "bodyHeight")
    public int bodyHeight;

    @ColumnInfo(name = "timeDone")
    public int timeDone;

    @ColumnInfo(name = "caloriesDone")
    public int caloriesDone;
}
