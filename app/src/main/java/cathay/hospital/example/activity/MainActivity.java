package cathay.hospital.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import cathay.hospital.example.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//隱藏標題列


        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new QRCodeScanner()).commit();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation_id);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;

                    switch (item.getItemId()){
                        case  R.id.nev_sign_In:
                            fragment = new QRCodeScanner();
                            break;
                        case  R.id.nev_user_info:
                            fragment = new UserInfo();
                            break;
                        case R.id.nev_notification:
                            fragment = new Notification();
                            break;
                        case R.id.nev_meeting_record:
                            fragment = new MeetingRecord();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();

                    return true;
                }
            };


}
