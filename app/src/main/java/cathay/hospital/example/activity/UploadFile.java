package cathay.hospital.example.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import cathay.hospital.example.R;

public class UploadFile extends AppCompatActivity {
    Button selectFile,upload;
    TextView selectedFile;
    Uri pdfUri;
    FirebaseDatabase database;
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);


        selectFile = findViewById(R.id.btn_select_file);
        upload = findViewById(R.id.btn_upload);
        selectedFile = findViewById(R.id.tv_selected_file);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

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

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();
        final String fileName = System.currentTimeMillis()+"";
        StorageReference storageReference=storage.getReference();

        storageReference.child("Uploads").child(fileName).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url = storageReference.getDownloadUrl().toString();
                        DatabaseReference reference = database.getReference();
                        reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(UploadFile.this, "上傳成功", Toast.LENGTH_LONG).show();
                                    //  progressDialog.dismiss();
                                } else {
                                    Toast.makeText(UploadFile.this, "上傳失敗", Toast.LENGTH_LONG).show();
                                    //  progressDialog.dismiss();
                                }
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UploadFile.this,"File not successfully upload",Toast.LENGTH_LONG).show();
                              //  progressDialog.dismiss();

                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                                int currentProgress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());

                                progressDialog.setProgress(currentProgress);
                            }
                        });
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
      //  intent.setType("image/jpeg");
        intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(intent,86);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //確認使用者是否有選擇檔案
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {
            pdfUri = data.getData();
            selectedFile.setText("已選擇檔案: "+data.getData().getLastPathSegment());   //還沒有requestCode的樣子，所以放在判斷式中不會出來
        }else{
            Toast.makeText(UploadFile.this,"請選擇檔案",Toast.LENGTH_SHORT).show();

        }
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
