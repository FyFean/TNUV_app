package si.uni_lj.fe.tnuv.tnuv_app.database2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutVaje;

@Dao
public interface WorkoutVajeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(CrossRefVajaWorkout join);

    @Transaction
    @Query("SELECT * FROM WorkoutEntity")
    public List<WorkoutVaje> getWorkoutVaje();
}
