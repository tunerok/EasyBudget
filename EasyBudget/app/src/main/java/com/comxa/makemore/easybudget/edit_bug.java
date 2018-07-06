package com.comxa.makemore.easybudget;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * Created by Artyom on 25.07.2016.
 */
public class edit_bug extends Activity {
    String lue = "ПРИЕМ! ПРИЕМ! КАК ОНО ТУТ ЖИВЕТСЯ?       ";
    TableLayout m;

    public void CTR(){

        TableRow tableRow = new TableRow(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        tableRow.setLayoutParams(params);
        m = (TableLayout)findViewById(R.id.quicklogTable);

        TableRow.LayoutParams param = new TableRow.LayoutParams();
        param.setMargins(0, 0, 0, 1);
        TextView r = new TextView(this);
        TextView r2 = new TextView(this);
        r.setText(lue);
        r2.setText(lue);
        r2.setTextSize(32);
        r.setLayoutParams(param);
        r.setTextColor(Color.rgb(245, 245, 220));
        r.setBackgroundColor(Color.rgb(0, 0, 0));

        tableRow.addView(r);
        tableRow.addView(r2);
        // tableRow.addView(r);
        //tableRow.addView(r);
        m.addView(tableRow);
        //m.addView(tableRow);
        //m.addView(tableRow);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_budget);

        CTR();
    }
}
