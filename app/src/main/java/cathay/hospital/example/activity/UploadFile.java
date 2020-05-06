package cathay.hospital.example.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.StorageReference;

import cathay.hospital.example.R;

public class UploadFile extends AppCompatActivity {
    Button selectFile,upload;
    TextView selectedFile;
    Uri pdfUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);


        selectFile = findViewById(R.id.btn_select_file);
        upload = findViewById(R.id.btn_upload);
        selectedFile = findViewById(R.id.tv_selected_file);

        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(UploadFile.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    selectPdf();
                }else{
                    ActivityCompat.requestPermissions(UploadFile.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pdfUri!=null){
                    uploadFile(pdfUri);
                }else{
                    //Toast.makeText(UploadFile.this,"請選擇檔案",Toast.LENGTH_SHORT).show();
                }
            }
        });
        setSpinner();//設定會議名稱選項
    }

    private void uploadFile(Uri pdfUri) {
        //StorageReference

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
           selectPdf();
        }else {
            Toast.makeText(UploadFile.this,"please provide permission",Toast.LENGTH_SHORT).show();

        }

    }

    private void selectPdf() {
        //提供使用者選擇檔案
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //確認使用者是否有選擇檔案
        super.onActivityResult(requestCode, resultCode, data);
        //if (requestCode == 86 && requestCode == RESULT_OK && data != null) {
            pdfUri = data.getData();
            selectedFile.setText("以選擇檔案: "+data.getData().getLastPathSegment());   //還沒有requestCode的樣子，所以放在判斷式中不會出來
     //   }else{
           // Toast.makeText(UploadFile.this,"請選擇檔案",Toast.LENGTH_SHORT).show();

      //  }
    }

    public void setSpinner(){
        Spinner spinner = findViewById(R.id.meeting_record_selector);

        String[] meetingName = {"第一個會議","第二個會議","第三個會議"};
        ArrayAdapter<String> meetingList = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,
                meetingName
        );
        spinner.setAdapter(meetingList);
    }

}
