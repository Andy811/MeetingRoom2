package cathay.hospital.example.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.HashMap;

public class SharedPreferencesModel{

    private SharedPreferences sharedPreferences;
    private Gson gson = new Gson();

    public SharedPreferencesModel(Context context){
        sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);

    }

    public SharedPreferences getSharedPrefs() {
        return sharedPreferences;
    }

    public void setSharedPrefsData(HashMap<String,String> hashMap){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (String key : hashMap.keySet()) {
            editor.putString(key, hashMap.get(key));
        }

        editor.apply();
    }

    public void setSharedPrefsDataObj(HashMap<String,Object> hashMap){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (String key : hashMap.keySet()) {
            String objString = gson.toJson(hashMap.get(key));
            editor.putString(key, objString);
        }

        editor.apply();
    }

}
