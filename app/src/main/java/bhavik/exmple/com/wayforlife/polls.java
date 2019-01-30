package bhavik.exmple.com.wayforlife;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class polls extends AppCompatActivity {
    private RecyclerView polls_list;
    private DatabaseReference mdatabase;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    RadioButton radioButton;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polls);
        mdatabase =FirebaseDatabase.getInstance().getReference().child("polls");
        mdatabase.keepSynced(true);

        polls_list= findViewById(R.id.polls_list);
        polls_list.setLayoutManager(new LinearLayoutManager(this));

        fetch();
    }

    private void fetch(){
        final Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("polls");

        FirebaseRecyclerOptions<polls_model> options =
                new FirebaseRecyclerOptions.Builder<polls_model>()
                        .setQuery(query, new SnapshotParser<polls_model>() {

                            @NonNull

                            @Override

                            public polls_model parseSnapshot(@NonNull DataSnapshot snapshot) {

                                return new polls_model(

                                        snapshot.child("question").getValue().toString());

                            }

                        })
                        .build();

        firebaseRecyclerAdapter
                =new FirebaseRecyclerAdapter<polls_model, polls.pollsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull polls.pollsViewHolder holder, final int position, @NonNull final polls_model model) {
                holder.setQuestion(model.getQuestion());
                holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId){
                            case R.id.Yes:
                                String mGroupId = getRef(position).getKey();
                                final DatabaseReference mDatabase1 = FirebaseDatabase.getInstance().getReference("polls").child(mGroupId).child("yes");
                                mDatabase1.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Long noOfyes = (long) dataSnapshot.getValue();
                                        mDatabase1.setValue(noOfyes + 1);

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                            case R.id.No:
                                String mGroupId1 = getRef(position).getKey();
                                final DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference("polls").child(mGroupId1).child("no");
                                mDatabase2.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Long noOfNo = (long) dataSnapshot.getValue();
                                        mDatabase2.setValue(noOfNo + 1);

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                        }
                    }
                });

            }

            @NonNull
            @Override
            public polls.pollsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.polls_card, viewGroup, false);

                return new polls.pollsViewHolder(view);
            }
        };
        polls_list.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }




    public  static class pollsViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        RadioGroup radioGroup;
        public pollsViewHolder(View itemView){

            super(itemView);
            view=itemView;
            radioGroup = view.findViewById(R.id.radiogroup);


        }

        public void setQuestion(String question){
            TextView post_question= view.findViewById(R.id.poll_question);
            post_question.setText(question);
        }

    }
}
