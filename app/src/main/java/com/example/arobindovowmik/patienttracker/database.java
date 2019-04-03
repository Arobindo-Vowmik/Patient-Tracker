package com.example.arobindovowmik.patienttracker;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class database extends AppCompatActivity {
    DatabaseHelper MyDb;
    EditText z,b,c,d,y,x,r;
    Button bu;
    Button update;
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
        update=(Button)findViewById(R.id.Button_update);
        AddData();
        veiw();
        update_Data();
    }
    public void update_Data()
    {
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       Boolean uPdata_data= MyDb.updata_data(x.getText().toString(),y.getText().toString(),z.getText().toString()
                               ,b.getText().toString(),c.getText().toString(),d.getText().toString());
                       if(uPdata_data==true)
                           Toast.makeText(database.this,"Data is Updated",Toast.LENGTH_LONG).show();
                       else
                           Toast.makeText(database.this,"Data is not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void AddData()
    {
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = x.getText().toString().trim();
                String vaue = y.getText().toString().trim();
                String st = z.getText().toString().trim();
                String s = b.getText().toString().trim();
                String str = c.getText().toString().trim();
                String strr = d.getText().toString().trim();
                int VALUE=0;
                int A=value.length();
                int B=vaue.length();
                int C=st.length();
                int D=s.length();
                int E=str.length();
                int F=strr.length();
                if (A==0)
                {
                    x.setError("Please insert PATIENT_NAME !");
                    //Toast.makeText(database.this,"Please insert PATIENT_NAME !",Toast.LENGTH_LONG).show();
                    VALUE=1;
                }
                if (B==0)
                {
                    //Toast.makeText(database.this,"AAAAAAAAAAAAAAAAAAAAAAAA !",Toast.LENGTH_LONG).show();
                    y.setError("Please insert TELIPHONE_NAME !");
                    VALUE=1;
                }
                if (C==0)
                {
                    z.setError("Please insert EMAIL !");
                    //Toast.makeText(database.this,"Please insert EMAIL !",Toast.LENGTH_LONG).show();
                    VALUE=1;
                }
                if (D==0)
                {
                    b.setError("Please insert DATE_AT_ARRIVAL !");
                    //Toast.makeText(database.this,"Please insert DATE_AT_ARRIVAL !",Toast.LENGTH_LONG).show();
                    VALUE=1;
                }
                if (E==0)
                {
                    c.setError("Please insert DISEASE !");
                    //Toast.makeText(database.this,"Please insert DISEASE !",Toast.LENGTH_LONG).show();
                    VALUE=1;
                }
                if (F==0)
                {
                    d.setError("Please insert COST !");
                    //Toast.makeText(database.this,"Please insert COST !",Toast.LENGTH_LONG).show();
                    VALUE=1;
                }
                if(VALUE==0)
                {
                    Boolean bte=MyDb.insertData(x.getText().toString(),y.getText().toString(),z.getText().toString(),b.getText().toString(),c.getText().toString(),d.getText().toString());
                    Toast.makeText(database.this,"Data is inserted !",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(database.this,"Data is not inserted !",Toast.LENGTH_LONG).show();
                }
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
