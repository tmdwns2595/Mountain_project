package org.jjcouple.termproject.fragment_display;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.os.Build;
import android.os.Bundle;
import org.jjcouple.termproject.Sign.LoginActivity;
import org.jjcouple.termproject.R;
import org.jjcouple.termproject.Sign.SignUp;
import org.jjcouple.termproject.main.MainActivity;

//public class FragmentSetting extends Fragment implements View.OnClickListener
public class FragmentSetting extends Fragment implements View.OnClickListener{

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private TextView textivewDelete;

    View view;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
//        View view = inflater.inflate(R.layout.profile, container, false);
        if (view == null) {
            view = inflater.inflate(R.layout.profile, container, false);
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        //initializing views
        textViewUserEmail = view.findViewById(R.id.textviewUserEmail);
        buttonLogout = view.findViewById(R.id.buttonLogout);
        textivewDelete = view.findViewById(R.id.textviewDelete);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        //????????? ????????? ?????? ?????? ???????????? null ???????????? ??? ??????????????? ???????????? ????????? ??????????????? ??????.
        if(firebaseAuth.getCurrentUser() == null) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }

        //????????? ?????????, null??? ????????? ?????? ??????
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //textViewUserEmail??? ????????? ????????? ??????.
        textViewUserEmail.setText("???????????????.\n"+ user.getEmail()+"?????? ????????? ???????????????.");

        //logout button event
        buttonLogout.setOnClickListener(this);
        textivewDelete.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogout) {
            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getActivity());
            alert_confirm.setMessage("?????? ??????????????? ??????????").setCancelable(false).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            firebaseAuth.signOut();
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        }
                    }
            );
            alert_confirm.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getActivity(), "??????", Toast.LENGTH_LONG).show();
                }
            });
            alert_confirm.show();
        }
        //??????????????? ???????????? ??????????????? ????????????. ???????????? ???????????? ?????? ???????????????.
        if(view == textivewDelete) {
            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getActivity());
            alert_confirm.setMessage("?????? ????????? ?????? ??????????").setCancelable(false).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getActivity(), "????????? ?????? ???????????????.", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getActivity(), SignUp.class));
                                        }
                                    });
                        }
                    }
            );
            alert_confirm.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getActivity(), "??????", Toast.LENGTH_LONG).show();
                }
            });
            alert_confirm.show();
        }
    }
}

