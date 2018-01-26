package assignment3.abdoulkarim.cosc431.towson.edu.rideontime;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerLogin extends AppCompatActivity implements View.OnClickListener {
    Button customerLogin_btn;
    Button customerRegistr_btn;
    TextView noaccount_status;
    EditText customer_password;
    EditText customer_email;
    FirebaseAuth mAuth;
    String email, password;
    private ProgressDialog progressdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        Var_Init();
        customerRegistr_btn.setVisibility(View.INVISIBLE);
        customerRegistr_btn.setEnabled(false);
    }

    void Var_Init(){
        mAuth= FirebaseAuth.getInstance();
        progressdialog= new ProgressDialog(this);

        customerLogin_btn=(Button)findViewById(R.id.customer_login_btn);
        customerRegistr_btn=(Button)findViewById(R.id.customer_register_btn);
        noaccount_status=(TextView)findViewById(R.id.customer_noaccount_status);
        customer_password=(EditText)findViewById(R.id.customer_password);
        customer_email=(EditText)findViewById(R.id.customer_email);
        customerRegistr_btn.setOnClickListener(this);
        customerLogin_btn.setOnClickListener(this);
        noaccount_status.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id= view.getId();
        switch (id)
        {
            case R.id.customer_noaccount_status:
                customerLogin_btn.setVisibility(View.INVISIBLE);
                noaccount_status.setVisibility(View.INVISIBLE);
                customerRegistr_btn.setVisibility(View.VISIBLE);
                customerRegistr_btn.setEnabled(true);
                break;
            case R.id.customer_register_btn:
                email= customer_email.getText().toString();
                password= customer_password.getText().toString();
                customer_Registration(email,password);
                break;

            case R.id.customer_login_btn:
                email= customer_email.getText().toString();
                password= customer_password.getText().toString();
                customer_Login(email,password);
                break;

                }


        }



    private void showMessage(String message)
    {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    void customer_Registration(String email, String password)
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

    void customer_Login(String email, String password){
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
                        Intent TodcustomerLogin= new Intent(getApplicationContext(),CustomerMaps.class);
                        startActivity(TodcustomerLogin);
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
