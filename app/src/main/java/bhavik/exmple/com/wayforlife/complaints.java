package bhavik.exmple.com.wayforlife;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class complaints extends AppCompatActivity {

    private RecyclerView complaints_list;
    private DatabaseReference mdatabase;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    Button user_complaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        user_complaint= findViewById(R.id.user_complaint);
        user_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),user_complaint.class);
                startActivity(intent);
            }
        });

        mdatabase =FirebaseDatabase.getInstance().getReference().child("complaints");
        mdatabase.keepSynced(true);

        complaints_list= findViewById(R.id.complaints_list);
        complaints_list.setLayoutManager(new LinearLayoutManager(this));
        fetch();
    }

    private void fetch(){
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("complaints")
                .limitToLast(50);

        FirebaseRecyclerOptions<complaints_model> options =
                new FirebaseRecyclerOptions.Builder<complaints_model>()
                        .setQuery(query, new SnapshotParser<complaints_model>() {

                            @NonNull

                            @Override

                            public complaints_model parseSnapshot(@NonNull DataSnapshot snapshot) {

                                return new complaints_model(

                                        snapshot.child("title").getValue().toString(),

                                        snapshot.child("complaint").getValue().toString());

                            }

                        })
                        .build();

        firebaseRecyclerAdapter
                =new FirebaseRecyclerAdapter<complaints_model, complaintsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull complaintsViewHolder holder, int position, @NonNull complaints_model model) {
                holder.setTitle(model.getTitle());
                holder.setComplaint(model.getComplaint());

            }

            @NonNull
            @Override
            public complaintsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.complaints_card, viewGroup, false);

                return new complaintsViewHolder(view);
            }
        };
        complaints_list.setAdapter(firebaseRecyclerAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }



    public  static class complaintsViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        public complaintsViewHolder(View itemView){

            super(itemView);
            view=itemView;
        }

        public void setTitle(String title){
            TextView post_title= view.findViewById(R.id.title);
            post_title.setText(title);
        }

        public void setComplaint(String complaint){
            TextView post_complaint= view.findViewById(R.id.complaint);
            post_complaint.setText(complaint);
        }

    }
}
