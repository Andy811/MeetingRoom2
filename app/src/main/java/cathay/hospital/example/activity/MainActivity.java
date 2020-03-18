package cathay.hospital.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import cathay.hospital.example.R;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//隱藏標題列
    }
}
