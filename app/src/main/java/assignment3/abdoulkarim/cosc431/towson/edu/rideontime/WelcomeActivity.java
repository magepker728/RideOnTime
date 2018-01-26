package assignment3.abdoulkarim.cosc431.towson.edu.rideontime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    Button driverbtn;
    Button customerbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Var_Init();
    }

    void Var_Init(){
        customerbtn=(Button)findViewById(R.id.customer_btn);
        driverbtn=(Button)findViewById(R.id.driver_btn);
        driverbtn.setOnClickListener(this);
        customerbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.driver_btn:
                Intent ToDriverlogin = new Intent(getApplicationContext(),DriverLoging.class);
                startActivity(ToDriverlogin);
                break;

            case R.id.customer_btn:
                Intent ToCustomerlogin = new Intent(getApplicationContext(),CustomerLogin.class);
                startActivity(ToCustomerlogin);
                break;
        }
    }
}
