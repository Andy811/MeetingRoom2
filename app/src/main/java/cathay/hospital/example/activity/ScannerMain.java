package cathay.hospital.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;



import cathay.hospital.example.R;

public class ScannerMain extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);
        TextView tvResult =findViewById(R.id.tv_result);
        Button btnScan =  findViewById(R.id.btn_scan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btnScan", "onClick: btnScan be touched");
                new IntentIntegrator(ScannerMain.this)
                        .setCaptureActivity(ScanningActivity.class)
                        .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)//掃條碼的類型
                        .setPrompt("請對準條碼")//設置提醒標語
                        .setCameraId(0)//選擇相機鏡頭，前置或是後置鏡頭
                        .setBeepEnabled(false)//是否開啟聲音
                        .setBarcodeImageEnabled(true)//掃描後會產生圖片
                        .initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents()==null){
                Toast.makeText(this,"no result",Toast.LENGTH_LONG).show();
            }else {
                tvResult.setText(result.getContents().toString());
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private TextView tvResult;
    private Button btnScan;
    }

