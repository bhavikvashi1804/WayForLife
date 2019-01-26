package bhavik.exmple.com.wayforlife;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class discussions extends AppCompatActivity {

    private RecyclerView discussions_list;
    private DatabaseReference mdatabase;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussions);

        mdatabase =FirebaseDatabase.getInstance().getReference().child("discussions");
        mdatabase.keepSynced(true);

        discussions_list= findViewById(R.id.discussions_list);
        discussions_list.setLayoutManager(new LinearLayoutManager(this));
        fetch();
    }

    private void fetch(){
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("discussions")
                .limitToLast(50);

        FirebaseRecyclerOptions<discussions_model> options =
                new FirebaseRecyclerOptions.Builder<discussions_model>()
                        .setQuery(query, new SnapshotParser<discussions_model>() {

                            @NonNull

                            @Override

                            public discussions_model parseSnapshot(@NonNull DataSnapshot snapshot) {

                                return new discussions_model(

                                        snapshot.child("title").getValue().toString(),

                                        snapshot.child("discussion").getValue().toString());

                            }

                        })
                        .build();

        firebaseRecyclerAdapter
                =new FirebaseRecyclerAdapter<discussions_model, discussionsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull discussionsViewHolder holder, int position, @NonNull discussions_model model) {
                holder.setTitle(model.getTitle());
                holder.setDiscussion(model.getDiscussion());

            }

            @NonNull
            @Override
            public discussionsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.discussions_card, viewGroup, false);

                return new discussionsViewHolder(view);
            }
        };
        discussions_list.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }



    public  static class discussionsViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        public discussionsViewHolder(View itemView){

            super(itemView);
            view=itemView;
        }

        public void setTitle(String title){
            TextView post_title= view.findViewById(R.id.title);
            post_title.setText(title);
        }

        public void setDiscussion(String discussion){
            TextView post_discussion= view.findViewById(R.id.discussion);
            post_discussion.setText(discussion);
        }

    }
}
