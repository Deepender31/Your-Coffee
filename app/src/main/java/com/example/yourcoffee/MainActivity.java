package com.example.yourcoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ETD1;
    Button Bplus ,Bminus,Border,Breset,Bemail;
    TextView Tq,Tordersummary;
    CheckBox Cb1,cb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ETD1=findViewById(R.id.Edt1);
        Bplus=findViewById(R.id.Bplus);
        Bminus=findViewById(R.id.Bminus);
        Bemail=findViewById(R.id.BEMAIL);
        Breset=findViewById(R.id.BRESET);
        Border=findViewById(R.id.BORDER);
        Cb1=findViewById(R.id.Cb1);
        cb2=findViewById(R.id.Cb2);
        Tq=findViewById(R.id.TQ);
        Tordersummary=findViewById(R.id.TV3);
        Bplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=Integer.parseInt(Tq.getText().toString());
                if(a>=0)
                    a++;
                Tq.setText(""+a);
            }
        });
        Bminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=Integer.parseInt(Tq.getText().toString());
                if(a>0)
                    a--;
                Tq.setText(""+a);
            }
        });
        Breset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ETD1.setText("");
                Tq.setText("0");
                Cb1.setChecked(false);
                cb2.setChecked(false);
                Tordersummary.setText("");
            }
        });
        Border.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=ETD1.getText().toString();
                String p="Sir your Coffee contains";
                int price=0;
                int quantity=Integer.parseInt(Tq.getText().toString());
                if(Cb1.isChecked()){
                       price =price +20;
                       p=p+" Wripped Cream";
            }
                if(cb2.isChecked()){
                    price =price+20;
                    p=p+" Chocolate";
                }
                if(Cb1.isChecked()&&cb2.isChecked()){
                    p="Sir your Coffee Contain Wripped Cream and Chocolate";
                }
                price =price *quantity;
                Tordersummary.setText("Hello  "+a +"\n");
                Tordersummary.append(p+"\n");
                Tordersummary.append("sir You ordered Total "+quantity+" Coffee.\n");
                Tordersummary.append("Sir you need to pay just $"+price+"\n"+"Thank you Sir");


            }
        });
        Bemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=ETD1.getText().toString();
                String p="Sir your Coffee contains";
                int price=0;
                int quantity=Integer.parseInt(Tq.getText().toString());
                if(Cb1.isChecked()){
                    price =price +20;
                    p=p+" Wripped Cream";
                }
                if(cb2.isChecked()){
                    price =price+20;
                    p=p+" Chocolate";
                }
                if(Cb1.isChecked()&&cb2.isChecked()){
                    p="Sir your Coffee Contain Wripped Cream and Chocolate";
                }
                price =price *quantity;
                String Body="Hello  "+a +"\n"+p+"\n"+"sir You ordered Total "+quantity+" Coffee.\n"+"Sir you need to pay just $"+price+"\n"+"Thank you Sir";
                Intent intent=new Intent(Intent.ACTION_SENDTO);
                intent.putExtra(Intent.EXTRA_SUBJECT,"ORDER SUMMARY");
                intent.putExtra(Intent.EXTRA_TEXT,Body);
                intent.setData(Uri.parse("mailto:"));
                if(intent.resolveActivity(getPackageManager()) !=null){
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this,"There is No application that support this action",Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(savedInstanceState !=null){
            Tq.setText(""+savedInstanceState.getInt("quantity"));
            ETD1.setText(savedInstanceState.getString("name"));
            Tordersummary.setText(savedInstanceState.getString("orderdetails"));
            Cb1.setSelected(savedInstanceState.getBoolean("check1"));
            cb2.setSelected(savedInstanceState.getBoolean("check2"));
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("quantity",Integer.parseInt(Tq.getText().toString()));
        outState.putBoolean("check1",Cb1.isChecked());
        outState.putBoolean("check2",cb2.isChecked());
        outState.putString("orderdetails",Tordersummary.getText().toString());
        outState.putString("name",ETD1.getText().toString());


    }

}