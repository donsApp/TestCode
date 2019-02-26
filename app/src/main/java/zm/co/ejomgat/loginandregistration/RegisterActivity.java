package zm.co.ejomgat.loginandregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword;
    private EditText confirm_Password;
    private Button btnRegister;
    private TextView mSignUp;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mUsername = findViewById(R.id.edittext_username);
        mPassword = findViewById(R.id.edittext_password);
        confirm_Password = findViewById(R.id.edittext_cnf_password);
        btnRegister = findViewById(R.id.btnRegister);
        mSignUp = findViewById(R.id.textview_signUp);

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signinIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(signinIntent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = mUsername.getText().toString().trim();
                String pwd = mPassword.getText().toString().trim();
                String confirmPassword = confirm_Password.getText().toString().trim();

                if (pwd.equals(confirmPassword)){

                    long val = db.addUser(user, pwd);

                    if (val > 0){

                        Toast.makeText(RegisterActivity.this, "You have successfully registered", Toast.LENGTH_SHORT).show();

                        Intent goToSignInActivityIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(goToSignInActivityIntent);
                    }else {
                        Toast.makeText(RegisterActivity.this, "Registration Error", Toast.LENGTH_SHORT).show();
                    }

                }else{

                    Toast.makeText(RegisterActivity.this, "Your passwords did not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
