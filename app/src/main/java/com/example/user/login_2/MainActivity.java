package com.example.user.login_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    Button button, button_2;
    String login="LOGIN";
    String password="PASSWORD";
    TextView textView1, textView2;
    private SharedPreferences preferences;

    public static String APP_PREFERENCES_LOGIN ;
    public static String APP_PREFERENCES_PASSWORD ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.log);
        editText2 = findViewById(R.id.pass);
        button = findViewById(R.id.logon);
        textView1 = findViewById(R.id.login);
        textView2 = findViewById(R.id.password);
        button_2 = findViewById(R.id.seesome);


        preferences = getSharedPreferences(getString(R.string.preferences_fiel_key), Context.MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                APP_PREFERENCES_LOGIN = editText1.getText().toString();
                APP_PREFERENCES_PASSWORD = editText2.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(login, APP_PREFERENCES_LOGIN);
                editor.putString(password, APP_PREFERENCES_PASSWORD);
                editor.apply();
            }
        });

        button_2.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {

                textView1.setText(preferences.getString(login, "default"));
                textView2.setText(preferences.getString(password, "default"));
            }
        });
    }
}


