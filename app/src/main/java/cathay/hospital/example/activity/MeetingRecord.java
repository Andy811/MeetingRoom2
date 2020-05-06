package cathay.hospital.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cathay.hospital.example.R;

public class MeetingRecord extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meeting_record,container,false);


        Button writeRecord = view.findViewById(R.id.btn_write_record);

        view.findViewById(R.id.btn_write_record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),UploadFile.class);
                startActivity(intent);
            }
        });






        return view;

    }

}

