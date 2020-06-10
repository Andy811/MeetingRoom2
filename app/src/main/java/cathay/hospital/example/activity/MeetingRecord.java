package cathay.hospital.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cathay.hospital.example.R;

public class MeetingRecord extends Fragment {
    ListView recordListView;
    DatabaseReference databaseReference;
    List<Upload> recordNameList;
    View view;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.meeting_record,container,false);



        Button writeRecord = view.findViewById(R.id.btn_write_record);

        view.findViewById(R.id.btn_write_record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),UploadFile.class);
                startActivity(intent);
            }
        });

        recordListView = view.findViewById(R.id.record_list);
        recordNameList = new ArrayList();


        viewAllRecordName();

        return view;


    }

    private void viewAllRecordName(){
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Upload upload = postSnapshot.getValue(cathay.hospital.example.activity.Upload.class);
                    recordNameList.add(upload);
                }
                String[] records = new String[recordNameList.size()];

                for (int i=0;i<records.length;i++){
                    records[i] = recordNameList.get(i).getMeetingName();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,records);
                recordListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}

