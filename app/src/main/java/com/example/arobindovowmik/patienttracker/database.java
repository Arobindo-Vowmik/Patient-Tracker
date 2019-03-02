package com.example.arobindovowmik.patienttracker;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class database extends AppCompatActivity {
    DatabaseHelper MyDb;
    EditText z,b,c,d,y,x,r;
    Button bu;
    Button bo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        MyDb= new DatabaseHelper(this);
        //r=(EditText)findViewById(R.id.edit_text12);
        x=(EditText)findViewById(R.id.edit_text13);
        y=(EditText)findViewById(R.id.editText14);
        z=(EditText)findViewById(R.id.editText15);
        b=(EditText)findViewById(R.id.editText16);
        c=(EditText)findViewById(R.id.editText17);
        d=(EditText)findViewById(R.id.editText19);
        bu=(Button)findViewById(R.id.Button_add);
        bo=(Button)findViewById(R.id.Button_view);
        AddData();
        veiw();
    }
    public void AddData()
    {
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isinsert = MyDb.insertData(x.getText().toString(),y.getText().toString(),z.getText().toString(),b.getText().toString(),c.getText().toString(),d.getText().toString());
                if(isinsert=true)
                    Toast.makeText(database.this,"Data inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(database.this,"Data is not inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void veiw()
    {
        bo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = MyDb.getdata();
                if(res.getCount()==0) {
                    showmassage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer=  new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("PATIENT_NAME :" + res.getString(0)+"\n");
                    buffer.append("TELIPHONE_NAME :" + res.getString(1)+"\n");
                    buffer.append("EMAIL :" + res.getString(2)+"\n");
                    buffer.append("DATE_AT_ARRIVAL :" + res.getString(3)+"\n");
                    buffer.append("DISEASE :" + res.getString(4)+"\n");
                    buffer.append("COST :" + res.getString(5)+"\n\n");

                }
                showmassage("Data",buffer.toString());
            }
        });
    }
    public void showmassage(String title,String massage)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(massage);
        builder.show();
    }
}
