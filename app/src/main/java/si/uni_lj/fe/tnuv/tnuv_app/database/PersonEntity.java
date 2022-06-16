//package si.uni_lj.fe.tnuv.tnuv_app.database;//package si.uni_lj.fe.tnuv.tnuv_app.database;
//
//
//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.ForeignKey;
//import androidx.room.PrimaryKey;
//
//@Entity
//public class PersonEntity {
//    @PrimaryKey(autoGenerate = true)
//    public int id;
//
////    @ColumnInfo(name = "pripada_vaji")
////    public int idVaje;
////
////    @ColumnInfo(name = "pripada_workoutu")
////    public int idWorkouta;
//
//    @ColumnInfo(name = "imePriimek")
//    public String imePriimek;
//
//    @ColumnInfo(name = "gender")
//    public char gender;
//
//    @ColumnInfo(name = "weight_person")
//    public int weight_person;
//
//    @ColumnInfo(name = "weight_person")
//    public int weight_person;
//
//    @ColumnInfo(name = "height_person")
//    public int height_person;
//
//    @ColumnInfo(name = "time_excercise")
//    public int time_excercise;
//
//    @ColumnInfo(name = "time_excercise")
//    public int time_excercise;
//
//    @ColumnInfo(name = "time_excercise")
//    public int time_excercise;
//}
//
//@Entity(tableName = "table1")
//public class WorkoutEntity {
//    @PrimaryKey(autoGenerate = true)
//    public int idWorkouta;
//
//    @ColumnInfo(name = "ime_workouta")
//    public String imeWorkouta;
//
//}
//
//
//@Entity(
//        tableName = "table2",
//        foreignKeys = {
//                @ForeignKey(
//                        entity = si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutEntity.class,
//                        parentColumns = "table1_id",
//                        childColumns = "map_to_parent_in_table1",
//                        onDelete = ForeignKey.CASCADE,
//                        onUpdate = ForeignKey.CASCADE
//                )
//        }
//)
//        public class Table2{
//        @PrimaryKey
//        @ColumnInfo(name = "table2_id")
//        val id:Long?= null,
//        @ColumnInfo(name = "map_to_parent_in_table1", index = true)
//        val map_to_parent:Long,
//        @ColumnInfo(name = "table2_name")
//        val name:String
//}
//
//@Entity(
//        tableName = "table3",
//        foreignKeys = {
//                @ForeignKey(
//                        entity = Table2.class,
//                        parentColumns = "table2_id",
//                        childColumns = "map_to_parent_in_table2",
//                        onDelete = ForeignKey.CASCADE,
//                        onUpdate = ForeignKey.CASCADE
//                )
//        }
//)
//public class Table3 {
//    @PrimaryKey
//    @ColumnInfo(name = "table3_id")
//    val id:Long?=null,
//    @ColumnInfo(name = "map_to_parent_in_table2", index = true)
//    val map_to_parent:Long,
//    @ColumnInfo(name = "table3_name")
//    val name:String
//}
