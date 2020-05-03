package cathay.hospital.example.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cathay.hospital.example.R;

public class ListAdapter extends RecyclerView.Adapter{


        private Context mcontext;
        private ArrayList<NotificationMessage> mData;
      /*  public ListAdapter(Context context,ArrayList<NotificationMessage> data){
            // mcontext = context;
            //  mData = data;
        }*/

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.cell_message,parent,false);
            ViewHolder holder = new ViewHolder(view);
            //holder.tvMessage = view.findViewById(R.id.tv_message);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            //NotificationMessage notiMessage = mData.get(position);
            ((ViewHolder) holder).bindView(position);

        }

        @Override
        public int getItemCount() {
            return MessageData.message.length;
        }
        private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

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



    }

