package cathay.hospital.example.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cathay.hospital.example.R;

public class Notification extends Fragment {


        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.notification,container,false);


            RecyclerView recyclerView = view.findViewById(R.id.rv_notification);

        //    ArrayList<NotificationMessage> data = new ArrayList<>();

       //     data.add(new NotificationMessage("等等要開會喔哭哭"));
            ListAdapter adapter = new ListAdapter();
            recyclerView.setAdapter(adapter);
            RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getActivity());

            recyclerView.setLayoutManager(layoutmanager);
            return view;




    }



    //RecyclerView的adapter
    /*
    public class NotifivationAdapter extends RecyclerView.Adapter {

        private Context mcontext;
        private ArrayList<NotificationMessage> mData;
        public NotifivationAdapter(Context context,ArrayList<NotificationMessage> data){
           // mcontext = context;
          //  mData = data;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           View view = LayoutInflater
                   .from(mcontext)
                   .inflate(R.layout.cell_message,parent,false);
            ViewHolder holder = new ViewHolder(view);
            holder.tvMessage = view.findViewById(R.id.tv_message);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NotificationMessage notiMessage = mData.get(position);

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
        class ViewHolder extends RecyclerView.ViewHolder {

           private TextView tvMessage;



            public ViewHolder(View itemView) {
                super(itemView);
                tvMessage = itemView.findViewById(R.id.tv_message);
                itemView.setOnClickListener((View.OnClickListener) this);

            }
            public void bindView(int position){
                tvMessage.setText(MessageData.message[position]);

            }
            public void onClick(View view){

            }

        }



    }*/
}
