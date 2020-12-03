package com.example.atc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter <modll,myadapter.myViewholder>
{

        public myadapter(FirebaseRecyclerOptions<modll>options){
            super(options);
        }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull modll model) {

            holder.fName.setText(model.getfName());
            holder.email.setText(model.getEmail());

        Glide.with(holder.img.getContext()).load(model.getImageURL()).into(holder.img);





    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView fName,email;


        public myViewholder(@NonNull View itemView) {

            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.img1);
            fName = (TextView)itemView.findViewById(R.id.nametext);
            email = (TextView)itemView.findViewById(R.id.emailtext);
        }





        }
    }


