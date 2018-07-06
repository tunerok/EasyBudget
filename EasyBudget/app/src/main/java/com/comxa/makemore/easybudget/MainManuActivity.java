package com.comxa.makemore.easybudget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.TextView;

/**
 * Created by Artyom on 19.07.2016.
 */
public class MainManuActivity extends Activity {
    //String[] List_comp = {"Операции с текущим бюджетом","Сформировать..","Операции с бюджетами","Выйти"};
   // String[] List_1 = {"Переключиться на другой бюджет", "Создать новый бюджет", "Удалить текущий бюджет"};
    //String[] List_2 = {"Выписка по текущему месяцу", "Прогноз"};
    ListView List;
    final String LOG_TAGS ="myLog";
    Context ty = this;
    private int Session=0;
    TextView base_name;
    Button b_menu;
    String bd = "";

    @Override
    public void onBackPressed() {
        openQuitDialog();


    }

    private void openQuitDialog() {

        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                MainManuActivity.this);
        quitDialog.setTitle("Покинуть окно управления бюджетами?");

        quitDialog.setPositiveButton("Да", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.setNegativeButton("Нет", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        quitDialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        b_menu = (Button)findViewById(R.id.button5);
        List = (ListView)findViewById(R.id.listView);
        base_name = (TextView)findViewById(R.id.textView7);

        if (getIntent().getExtras().get("bd_name") != null)
            bd = getIntent().getExtras().getString("bd_name", "ERROR");
        base_name.setText(bd);
         final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ty,R.array.List_comp,R.layout.my_list_item);

        List.setAdapter(adapter);

        b_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Session == 0) {
                    onBackPressed();
                } else if (Session != 0) {
                    Session = 0;
                    List.setAdapter(adapter);
                }
            }
        });
        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if ((i == 2) && (Session == 0)) {
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(ty, R.array.List_1, R.layout.my_list_item);
                    List.setAdapter(adapter1);
                    Session = 1;
                } else if ((i == 1) && (Session == 0)) {
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(ty, R.array.List_2, R.layout.my_list_item);
                    List.setAdapter(adapter2);
                    Session = 2;
                }
                 else if ((i == 3) && (Session == 0)){
                    onBackPressed();
                }
                else if ((i == 0) && (Session == 0)){
                    Intent intent = new Intent(MainManuActivity.this, edit_bug.class);
                    startActivity(intent);
                }
            }


        });




    }
}
