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

    //update ime in priimek
    @Query("UPDATE PersonEntity SET imePriimek = :ip WHERE idPerson = :idPerson")
    void updateImePriimek(String ip, int idPerson);

    //update teza
    @Query("UPDATE PersonEntity SET bodyWeight = :t WHERE idPerson = :idPerson")
    void updateTeza(int t, int idPerson);

    //update visina
    @Query("UPDATE PersonEntity SET bodyHeight = :v WHERE idPerson = :idPerson")
    void updateVisina(int v, int idPerson);

    //update zeljene calorie goal
    @Query("UPDATE PersonEntity SET caloriesGoal = :zelCal WHERE idPerson = :idPerson")
    void updateZelCal(int zelCal, int idPerson);

    //update zeljen cas goal
    @Query("UPDATE PersonEntity SET timeGoal = :zelCas WHERE idPerson = :idPerson")
    void updateZelCas(int zelCas, int idPerson);

    @Query("UPDATE PersonEntity SET spol = :sp WHERE idPerson = :idPerson")
    void updateSpol(String sp, int idPerson);

}
