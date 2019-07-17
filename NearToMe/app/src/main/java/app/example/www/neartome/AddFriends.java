package app.example.www.neartome;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.google.android.gms.auth.api.signin.GoogleSignIn.getLastSignedInAccount;

public class AddFriends extends AppCompatActivity {

    EditText friendName;
    DatabaseReference databaseReference;
    GoogleSignInAccount acct;
    String personName;
    locations friendobj;
    int flag=0;
    Button addfrnd;
    Map<String,Object> frndlist = new HashMap<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<String> frnds = new ArrayList<>();
    String []array = new String[20];
    int k=0;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        friendName=findViewById(R.id.addfrndedittext);

        addfrnd=findViewById(R.id.addfriend);
        addfrnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference rref = database.getReference("users/"+personName+"/friends");
                rref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        System.out.println("geting the list in addfrnd activity");
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            frnds.add(d.getValue().toString());
                            k++;
                            flag++;
                            System.out.println(k);
                        }
                        for(String n:frnds){
                            System.out.println(n);
                        }
                        if(flag>0){
                            DatabaseReference ref = database.getReference("users/"+personName);
                            frnds.add(friendName.getText().toString());
                            for(String n:frnds){
                                System.out.println(n);
                            }
                            frndlist.put("friends",frnds);
                            ref.updateChildren(frndlist);
                            i=frndlist.size();
                            array[i-1]=friendName.getText().toString();}

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });


        acct = getLastSignedInAccount(AddFriends.this);
        if (acct != null) {
            personName = acct.getDisplayName();
        }


    }

}
