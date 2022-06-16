package si.uni_lj.fe.tnuv.tnuv_app;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import si.uni_lj.fe.tnuv.tnuv_app.Vaja;
import si.uni_lj.fe.tnuv.tnuv_app.Workout;

public class Person implements Parcelable {
    private String imePriimek;
    private String spol;
    private int caloriesGoal;
    private int timeGoal;
    private int bodyWeight;
    private int bodyHeight;
    private int timeDone;
    private int caloriesDone;
    private ArrayList<Workout> personalWorkouts;
    private ArrayList<Workout> pastWorkouts;

    public Person(String imePriimek, String spol, int caloriesGoal, int timeGoal, int bodyWeight, int bodyHeight, int timeDone, int caloriesDone, ArrayList<Workout> personalWorkouts, ArrayList<Workout> pastWorkouts){
        this.imePriimek = imePriimek;
        this.spol = spol;
        this.caloriesGoal = caloriesGoal;
        this.timeGoal = timeGoal;
        this.bodyWeight = bodyWeight;
        this.bodyHeight = bodyHeight;
        this.timeDone = timeDone;
        this.caloriesDone = caloriesDone;
        this.personalWorkouts = personalWorkouts;
        this.pastWorkouts = pastWorkouts;
    }

    protected Person(Parcel in) {
        imePriimek = in.readString();
        spol = in.readString();
        caloriesGoal = in.readInt();
        timeGoal = in.readInt();
        bodyWeight = in.readInt();
        bodyHeight = in.readInt();
        timeDone = in.readInt();
        caloriesDone = in.readInt();
        personalWorkouts = in.createTypedArrayList(Workout.CREATOR);
        pastWorkouts = in.createTypedArrayList(Workout.CREATOR);
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getImePriimek() {
        return imePriimek;
    }

    public void setImePriimek(String imePriimek) {
        this.imePriimek = imePriimek;
    }

    public void setSpol(String spol) {
        this.spol = spol;
    }

    public void setCaloriesGoal(int caloriesGoal) {
        this.caloriesGoal = caloriesGoal;
    }

    public void setTimeGoal(int timeGoal) {
        this.timeGoal = timeGoal;
    }

    public void setBodyWeight(int bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public void setBodyHeight(int bodyHeight) {
        this.bodyHeight = bodyHeight;
    }

    public void setTimeDone(int timeDone) {
        this.timeDone = timeDone;
    }

    public void setCaloriesDone(int caloriesDone) {
        this.caloriesDone = caloriesDone;
    }

    public void setPersonalWorkouts(ArrayList<Workout> personalWorkouts) {
        this.personalWorkouts = personalWorkouts;
    }

    public void setPastWorkouts(ArrayList<Workout> pastWorkouts) {
        this.pastWorkouts = pastWorkouts;
    }

    public String getSpol() {
        return spol;
    }

    public int getCaloriesGoal() {
        return caloriesGoal;
    }

    public int getTimeGoal() {
        return timeGoal;
    }

    public int getBodyWeight() {
        return bodyWeight;
    }

    public int getBodyHeight() {
        return bodyHeight;
    }

    public int getTimeDone() {
        return timeDone;
    }

    public int getCaloriesDone() {
        return caloriesDone;
    }

    public ArrayList<Workout> getPersonalWorkouts() {
        return personalWorkouts;
    }

    public ArrayList<Workout> getPastWorkouts() {
        return pastWorkouts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imePriimek);
        parcel.writeString(spol);
        parcel.writeInt(caloriesGoal);
        parcel.writeInt(timeGoal);
        parcel.writeInt(bodyWeight);
        parcel.writeInt(bodyHeight);
        parcel.writeInt(timeDone);
        parcel.writeInt(caloriesDone);
        parcel.writeTypedList(personalWorkouts);
        parcel.writeTypedList(pastWorkouts);
    }
}
