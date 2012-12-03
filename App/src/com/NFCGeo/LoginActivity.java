package com.NFCGeo;
 
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast; 
import android.widget.Button;
 
public class LoginActivity extends Activity {
    
    private User mockUsers[];
    private Button loginButton;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       

        mockUsers = new User[3];

        mockUsers[0] = new User("Ryan");
        mockUsers[1] = new User("Kaitlin", UserType.MODERATOR);
        mockUsers[2] = new User("Alex", UserType.ADMIN);


        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.login_button);
        if(MainMenu.isLoggedIn)
        {
            loginButton.setText("Logoff");
        }
        else
        {
            loginButton.setText("Login");
        }
    }
    
    public void login(View viewParam)
    {
        EditText usernameEditText = (EditText) findViewById(R.id.txt_username);
        EditText passwordEditText = (EditText) findViewById(R.id.txt_password);
       
        String sUserName = usernameEditText.getText().toString();
        String sPassword = passwordEditText.getText().toString();
        
        if(!MainMenu.isLoggedIn)
        {
            for(int i = 0; i < 3; i++)
            {
                if(sUserName.equals(mockUsers[i].name()))
                {
                    MainMenu.login(mockUsers[i]);
                    CharSequence text = "Logged in successfully!"; 
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                    break;
                }
            }

            if(!MainMenu.isLoggedIn)
            {
                CharSequence text = "Incorrect username or password"; 
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else
        {
            MainMenu.logoff();
            CharSequence text = "You have been logged off"; 
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();

            loginButton.setText("Login");
        }
    }
}
