package si.uni_lj.fe.tnuv.tnuv_app.database2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DetailsDAO {
    @Query("SELECT * FROM DetailsEntity")
    List<DetailsEntity> getAll();

    @Query("SELECT * FROM DetailsEntity "+
            "WHERE DetailsEntity.pripadaWorkoutu = :idW AND " +
            "DetailsEntity.pripadaVaji = :idV ")
    List<DetailsEntity> findSpecificneDetailse(int idW, int idV);

    @Query("SELECT * FROM DetailsEntity "+
            "WHERE DetailsEntity.pripadaWorkoutu = :idW"
            )
    List<DetailsEntity> findDetailsOdWorkouta(int idW);

    @Query("SELECT * FROM DetailsEntity "+
            "WHERE DetailsEntity.pripadaVaji = :idV"
    )
    List<DetailsEntity> findDetailsOdVaje(int idV);

    @Insert
    void insert(DetailsEntity details);

}
//
//@Query("SELECT * FROM DetailsEntity "+
//        "INNER JOIN DetailsEntity ON DetailsEntity.pripadaWorkoutu = WorkoutEntity.idWorkouta " +
//        "WHERE DetailsEntity.pripadaWorkoutu LIKE :idW ")
