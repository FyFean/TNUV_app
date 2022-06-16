package si.uni_lj.fe.tnuv.tnuv_app.database2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDAO {

    @Query("SELECT * FROM PersonEntity")
    List<PersonEntity> getAll();

    @Insert
    void insert(si.uni_lj.fe.tnuv.tnuv_app.database2.PersonEntity person);


}
