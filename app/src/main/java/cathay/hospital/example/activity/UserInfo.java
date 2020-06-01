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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cathay.hospital.example.R;
import cathay.hospital.example.model.SharedPreferencesModel;
import cathay.hospital.example.util.AES;
import cathay.hospital.example.util.UtilTools;

public class UserInfo extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.user_info,container,false);

        TextView tv_userNo = view.findViewById(R.id.user_info_tv);
        TextView tv_password = view.findViewById(R.id.tv_password);
        Button btn_logout = view.findViewById(R.id.logout_btn);

        SharedPreferences set= this.getActivity().getSharedPreferences("session", Context.MODE_PRIVATE);

        tv_userNo.setText(set.getString("userNo",""));
        String password = AES.decrypt(set.getString("password","又失敗了"));
        tv_password.setText(password);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref =getActivity().getSharedPreferences("session", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit(); // commit changes
                UtilTools.goActivity(getActivity(),LoginActivity.class);
            }
        });
        return view;
    }
}
