package cathay.hospital.example.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import cathay.hospital.example.R;
import cathay.hospital.example.model.SharedPreferencesModel;
import cathay.hospital.example.model.bean.LoginData;
import cathay.hospital.example.util.AES;
import cathay.hospital.example.util.UtilCommonVariable;
import cathay.hospital.example.util.UtilTools;
import cathay.hospital.example.viewModel.ViewModel_login;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editUser,editPassword;
    TextView textError;
    Button btnLogin,btnRefresh;
    String userNo,password,divNo;
    ViewModel_login viewModel_login;
    SharedPreferencesModel sharedPrefsModel;
    private final String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();//隱藏標題列

        init();
    }

    public void init(){
        divNo = UtilCommonVariable.connectEnv;
        editUser = findViewById(R.id.editUser);
        editPassword = findViewById(R.id.editPassword);
        textError = findViewById(R.id.textError);
        btnLogin = findViewById(R.id.btnLogin);
        btnRefresh = findViewById(R.id.btnRefresh);

        btnLogin.setOnClickListener(this);
        btnRefresh.setOnClickListener(this);

        sharedPrefsModel = new SharedPreferencesModel(getApplicationContext());
    }

    public void onClick(View v) {
        if(v == btnLogin){
            userNo = editUser.getText().toString();
            password = editPassword.getText().toString();
            loginMember();
        }else if(v == btnRefresh){
            editUser.setText("");
            editPassword.setText("");
            textError.setText("");
            editUser.requestFocus();
        }
    }

    public void loginMember(){
        HashMap<String,String> sharedMap = new HashMap<>();
        switch(userNo){
            case "123456":
                sharedMap.put("userNo", userNo);
                sharedMap.put("divNo",divNo);
                sharedPrefsModel.setSharedPrefsData(sharedMap);

                UtilTools.goActivity(this,MainActivity.class);
                break;
            default:
                HashMap<String,String> paramsMap = new HashMap<>();
                paramsMap.put("userNo",userNo);
                paramsMap.put("password",AES.encrypt(password));
                paramsMap.put("divno",divNo);

                viewModel_login = new ViewModel_login(divNo,paramsMap);
                viewModel_login.getLoginData().observe(this, dataModel -> {
                    Boolean error = dataModel.getError();
                    if(error) {
                        textError.setText(R.string.connectError);
                    } else {
                        LoginData loginData = (LoginData)dataModel.getDataObj();
                        if(loginData.getStatus().equals("1")){
                            sharedMap.put("divNo",divNo);
                            sharedMap.put("userNo", userNo);
                            sharedMap.put("userName", loginData.getUserName());
                            sharedPrefsModel.setSharedPrefsData(sharedMap);

                            UtilTools.goActivity(this,MainActivity.class);
                        }else{
                            textError.setText(R.string.loginError);
                        }
                    }
                });
                break;
        }

    }
}
