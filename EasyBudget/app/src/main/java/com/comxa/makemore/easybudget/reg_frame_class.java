package com.comxa.makemore.easybudget;

import android.app.Activity;
import android.app.Notification;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;

/**
 * Created by Artyom on 19.07.2016.
 */


public class reg_frame_class extends Activity {

    EditText login,pass1,pass2;
    Button Next,Cancel;
    TextView hi,pass22;
    Switch show_p;
    Boolean check = false, br=true;
    int def_type;
    DB_helper db;
    String name = "", pass= "",s_pass="";
    private Toast toast;
    long RowId = 0;
    SQLiteDatabase open_db;
    Cursor c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_frame);
        hi = (TextView)findViewById(R.id.textView4);
        pass22 = (TextView)findViewById(R.id.textView5);
        login = (EditText)findViewById(R.id.editText);
        pass1 = (EditText)findViewById(R.id.editText2);
        pass2 = (EditText)findViewById(R.id.editText3);
        Next = (Button)findViewById(R.id.button3);
        Cancel = (Button)findViewById(R.id.button4);
        show_p = (Switch)findViewById(R.id.switch1);
        if (getIntent().getExtras().get("log") != null)
            check = getIntent().getExtras().getBoolean("log",false);
        def_type = pass1.getInputType();
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);


        hi.setText("Регистрация нового аккаунта.");
        pass22.setVisibility(View.VISIBLE);
        pass2.setVisibility(View.VISIBLE);
        db = new DB_helper(this);


        try {
            open_db = db.getWritableDatabase();

        } catch (Exception e) {
            toast.setText("Ошибка доступа базы данных");
            toast.show();

        }

        if (check){

            pass2.setVisibility(View.INVISIBLE);
            pass22.setVisibility(View.INVISIBLE);
            hi.setText("Добро пожаловать!");

        }



        show_p.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (show_p.isChecked()) {
                    pass1.setInputType(1);
                    pass2.setInputType(1);
                } else {
                    pass1.setInputType(def_type);
                    pass2.setInputType(def_type);
                }
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                if (!(check)) {     //testing login%passwords registration
                    try {
                        br = false;
                        name = login.getText().toString();
                        c = open_db.query("users", null, null, null, null, null, null);
                        //c.moveToFirst();
                        if (c.moveToFirst()) {
                            // определяем номера столбцов по имени
                            int nameColIndex = c.getColumnIndex("login");
                            do {

                                if (!(name.equals(c.getString(nameColIndex)))) {
                                    br = true;
                                } else {

                                    toast.setText("Данный пользователь уже существует");
                                    toast.show();
                                    br = false;
                                    break;
                                }
                                //следующей нет, то выходим из цикла
                            } while (c.moveToNext());
                        } else {//empty table
                            br = true;
                        }

                        if (br) {  // check for copy

                            try {
                                pass = pass1.getText().toString();
                                s_pass = pass2.getText().toString();
                            } catch (Exception e) {
                                br = false;
                                toast.setText("Поле ввода пароля имеет недопустимое значение");
                                toast.show();
                            }


                            if (pass.equals(s_pass)) {

                                cv.put("login", name);
                                cv.put("pass", pass);
                                RowId = open_db.insert("users", null, cv);

                            } else {
                                br = false;
                                toast.setText("Введенные пароли не совпадают");
                                toast.show();
                            }
                        }
                    } catch (Exception e) {
                        br = false;
                        toast.setText("Ошибка ввода, проверьте заполненные поля");
                        toast.show();
                    }

                } else {//login


                    try {
                        br = false;
                        name = login.getText().toString();
                        pass = pass1.getText().toString();

                    } catch (Exception e) {
                        toast.setText("Поля ввода имеют недопустимые значение");
                        toast.show();
                    }

                    c = open_db.query("users", null, null, null, null, null, null);

                    if (c.moveToFirst()) {
                        // определяем номера столбцов по имени
                        int nameColIndex = c.getColumnIndex("login");
                        int passColIndex = c.getColumnIndex("pass");
                        do {

                            if (name.equals(c.getString(nameColIndex))) {
                                if (pass.equals(c.getString(passColIndex))) {
                                    br = true;
                                } else {
                                    toast.setText("Пароль не подходит");
                                    toast.show();
                                    br = false;
                                    break;
                                }
                            }
                            //следующей нет, то выходим из цикла
                        } while (c.moveToNext());

                    } else {
                        toast.setText("База данных пуста");
                        toast.show();
                    }


                }
                if (br) {  // check for copy
                    Intent intent = new Intent(reg_frame_class.this, MainManuActivity.class);
                    intent.putExtra("bd_name", name);
                    startActivity(intent);
                }

            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(reg_frame_class.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
