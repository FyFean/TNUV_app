package si.uni_lj.fe.tnuv.tnuv_app.database2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutEntity;

@Dao
public interface WorkoutDAO {

    @Query("SELECT * FROM WorkoutEntity")
    List<WorkoutEntity> getAll();

    @Query("SELECT * FROM WorkoutEntity WHERE isCustom = 1")
    List<WorkoutEntity> getCustom();

    @Insert
    void insert(si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutEntity workout);


}
