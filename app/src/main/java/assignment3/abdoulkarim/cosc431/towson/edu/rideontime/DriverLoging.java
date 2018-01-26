package assignment3.abdoulkarim.cosc431.towson.edu.rideontime;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverLoging extends AppCompatActivity implements View.OnClickListener {
Button driverLogin_btn;
Button driverRegistr_btn;
TextView noaccount_status;
EditText driver_password;
EditText driver_email;


FirebaseAuth mAuth;
String email, password;
private ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_loging);
        Var_Init();
        driverRegistr_btn.setVisibility(View.INVISIBLE);
        driverRegistr_btn.setEnabled(false);
    }

    void Var_Init(){

        mAuth= FirebaseAuth.getInstance();
        progressdialog= new ProgressDialog(this);


        driverLogin_btn=(Button)findViewById(R.id.driver_login_btn);
        driverRegistr_btn=(Button)findViewById(R.id.driver_register_btn);
        noaccount_status=(TextView)findViewById(R.id.driver_noaccount_status);
        driver_password=(EditText)findViewById(R.id.driver_password);
        driver_email=(EditText)findViewById(R.id.driver_email);
        driverRegistr_btn.setOnClickListener(this);
        driverLogin_btn.setOnClickListener(this);
        driverRegistr_btn.setOnClickListener(this);
        driverLogin_btn.setOnClickListener(this);
        noaccount_status.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id= view.getId();
        switch (id){
            case R.id.driver_noaccount_status:
                driverLogin_btn.setVisibility(View.INVISIBLE);
                noaccount_status.setVisibility(View.INVISIBLE);
                driverRegistr_btn.setVisibility(View.VISIBLE);
                driverRegistr_btn.setEnabled(true);
                break;

            case R.id.driver_register_btn:
                email= driver_email.getText().toString();
                password= driver_password.getText().toString();
                driver_Registration(email,password);
                break;

            case R.id.driver_login_btn:
                email= driver_email.getText().toString();
                password= driver_password.getText().toString();
                driver_Login(email,password);
                break;
        }

    }

    private void showMessage(String message)
    {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    void driver_Registration(String email, String password)
    {
        if(email.isEmpty())
        {
            showMessage("Please Enter an Email Address");
        }

        if(password.isEmpty())
        {
            showMessage("Please Enter a Password");
        }

        else
        {

            progressdialog.setTitle("Registration Status");
            progressdialog.setMessage("Data is Being Processed....");
            progressdialog.show();
            mAuth.createUserWithEmailAndPassword( email,password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete( @NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        showMessage("Registration Successfully Completed");
                        progressdialog.dismiss();
                    }
                    else
                    {
                        showMessage("Registration unsuccessful");
                        progressdialog.dismiss();
                    }
                }
            });
        }

    }

    void driver_Login(String email, String password){
        if(email.isEmpty())
        {
            showMessage("Please Enter an Email Address");
        }

        if(password.isEmpty())
        {
            showMessage("Please Enter a Password");
        }

        else
        {

            progressdialog.setTitle("Login Status : waiting for login");
            progressdialog.setMessage("Data is Being Processed....");
            progressdialog.show();
            mAuth.signInWithEmailAndPassword( email,password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete( @NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {

                        progressdialog.dismiss();
                        showMessage("Login Successfully ");
                        Intent TodriverLogin= new Intent(getApplicationContext(),DriverMaps.class);
                        startActivity(TodriverLogin);
                    }
                    else
                    {
                        showMessage("Login unsuccessful");
                        progressdialog.dismiss();
                    }
                }
            });
        }
    }

}
