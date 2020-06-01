package cathay.hospital.example.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;

import cathay.hospital.example.R;
import cathay.hospital.example.model.SharedPreferencesModel;
import cathay.hospital.example.model.bean.LoginData;
import cathay.hospital.example.util.UtilCommonVariable;
import cathay.hospital.example.util.UtilTools;
import cathay.hospital.example.viewModel.DataModel;
import cathay.hospital.example.viewModel.ViewModel_signin;

public class QRCodeScanner extends Fragment {
    TextView tvResult;
    ViewModel_signin viewModel_signin;
    String divNo;
    SharedPreferences defaultData;
    public QRCodeScanner(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.scanner,container,false);
        tvResult =  view.findViewById(R.id.tv_result);
        Button btnScan = view.findViewById(R.id.btn_scan);
        divNo = UtilCommonVariable.connectEnv;
        defaultData = getActivity().getSharedPreferences("session", Context.MODE_PRIVATE);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                IntentIntegrator.forSupportFragment(QRCodeScanner.this)
                        .setCaptureActivity(ScanningActivity.class)
                        .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)//掃條碼的類型
                        .setPrompt("請對準條碼")//設置提醒標語
                        .setCameraId(0)//選擇相機鏡頭，前置或是後置鏡頭
                        .setBeepEnabled(false)//是否開啟聲音
                        .setBarcodeImageEnabled(true)//掃描後會產生圖片
                        .initiateScan();

            }
        });
        return view;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents()==null){
                Toast.makeText(getContext(),"no result",Toast.LENGTH_LONG).show();  //結果不顯示可能跟getContext有關
            }else {
                tvResult.setText(result.getContents().toString());

                SharedPreferencesModel prf = new SharedPreferencesModel(getActivity());
                prf.getSharedPrefs();
                HashMap<String,String> dataMap = new HashMap<>();
                dataMap.put("userNo",prf.getSharedPrefs().getString("userNo",""));
                dataMap.put("meetingID",result.getContents());
                viewModel_signin = new ViewModel_signin(divNo, dataMap);//會導致閃退

                viewModel_signin.signin().observe(getActivity(),dataModel -> {
                    Boolean error = dataModel.getError();
                    if(error) {
                       Toast.makeText(getActivity(),"簽到失敗",Toast.LENGTH_SHORT).show();
                    } else {

                           Toast.makeText(getActivity(),"簽到成功",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

}
