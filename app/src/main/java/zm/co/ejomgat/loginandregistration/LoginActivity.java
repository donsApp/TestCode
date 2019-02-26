package zm.co.ejomgat.loginandregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mSignIn;
    private TextView mRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        mUsername = findViewById(R.id.edittext_username);
        mPassword = findViewById(R.id.edittext_password);
        mSignIn = findViewById(R.id.btnSignin);
        mRegister = findViewById(R.id.textview_register);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = mUsername.getText().toString();
                String pwd = mPassword.getText().toString();
                Boolean result = db.checkUser(user, pwd);

                //Toast.makeText(LoginActivity.this, "Logged In" + user +" " + pwd, Toast.LENGTH_SHORT).show();

                if (result == true){

                    Intent goToHomeActivity = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(goToHomeActivity);
                }else{

                    Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
