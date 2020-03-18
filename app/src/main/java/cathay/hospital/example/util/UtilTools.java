package cathay.hospital.example.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class UtilTools {
    /**
     *  至另一個 activity 不留歷史紀錄
     **/
    public static void goActivity(Activity activity,Class destination){
        Intent intent = new Intent(activity.getApplicationContext(), destination);
        activity.startActivity(intent);
        activity.finish();//銷毀activity
    }

    public static void goActivity(Activity activity, Class destination, Bundle bundle){
        Intent intent = new Intent(activity.getApplicationContext(), destination);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.finish();//銷毀activity
    }

    /**
     *  確認手機網路
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     *  確認手機內存權限
     */
    public static void verifyStoragePermissions(Activity activity) {
        // 內存權限
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    UtilCommonVariable.allowStoragePermission
            );
        }

    }

    /**
     *  確認手機相機權限
     */
    public static boolean verifyCameraPermissions(Activity activity,int requestCode) {
        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.CAMERA},requestCode);
            return false;
        }else{
            return true;
        }
    }

}
