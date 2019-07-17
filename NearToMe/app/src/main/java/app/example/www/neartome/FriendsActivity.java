package app.example.www.neartome;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.android.gms.auth.api.signin.GoogleSignIn.getLastSignedInAccount;

public class FriendsActivity extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String personName;
    GoogleSignInAccount acct;
    ArrayList<String> friendlist;
    FloatingActionButton addfrnd;
    String[] name;
    Map<String,Object> frndlist = new HashMap<>();
    int i = 0;
    int k = 0;

    int flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        addfrnd = findViewById(R.id.floatingActionButton);
        addfrnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FriendsActivity.this, AddFriends.class));
            }
        });
        acct = getLastSignedInAccount(FriendsActivity.this);
        if (acct != null) {
            personName = acct.getDisplayName();
        }
        listView = findViewById(R.id.listview);
        friendlist = new ArrayList<>();
        DatabaseReference ref = database.getReference("users/" + personName + "/friends");
        ValueEventListener valueEventListener = ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    friendlist.add(d.getValue().toString());
                    k++;

                    flag++;
                }

                if(flag>0){

                    getlist(k);


                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    public void getlist(final int size) {


        name = new String[size];

        for(int i=0;i<size;i++){
            name[i]=friendlist.get(i);
        }
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
        listView.setAdapter(arrayAdapter);
    }
}
