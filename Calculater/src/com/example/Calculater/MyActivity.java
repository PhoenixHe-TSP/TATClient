package com.example.Calculater;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MyActivity extends Activity {
    private TextView input;
    private TextView output;
    private Button[] button;
    private String ask,answer;
    private MyCalculater calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = new Button[21];
        ask = answer = "";
        calc = new MyCalculater();
        findViews();
        register();


    }

    private void register(){
        button[1].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask = "";
                answer = "";
                input.setText(ask);
                output.setText(answer);
            }

        });

        button[2].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += "(";
                input.setText(ask);
            }

        });

        button[3].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += ")";
                input.setText(ask);
            }

        });

        button[4].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += "+";
                input.setText(ask);
            }

        });

        button[5].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += "7";
                input.setText(ask);
            }

        });

        button[6].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += "8";
                input.setText(ask);
            }

        });

        button[7].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += "9";
                input.setText(ask);
            }

        });

        button[8].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += "-";
                input.setText(ask);
            }

        });

        button[9].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += "4";
                input.setText(ask);
            }

        });

        button[10].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += "5";
                input.setText(ask);
            }

        });

        button[11].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += "6";
                input.setText(ask);
            }

        });

        button[12].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += "*";
                input.setText(ask);
            }

        });

        button[13].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += "1";
                input.setText(ask);
            }

        });

        button[14].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += "2";
                input.setText(ask);
            }

        });

        button[15].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += "3";
                input.setText(ask);
            }

        });

        button[16].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += "/";
                input.setText(ask);
            }

        });

        button[17].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += "0";
                input.setText(ask);
            }

        });

        button[18].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask += ".";
                input.setText(ask);
            }

        });

        button[19].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ask = ask.substring(0,input.length() - 1);
                input.setText(ask);
            }

        });

        button[20].setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                try{

                    answer = calc.calculate(ask);
                }catch(Exception e){
                    answer = "输入有误！";
                }
                output.setText(answer);
            }

        });
    }

    private void findViews(){
        input = (TextView)this.findViewById(R.id.textView1);
        output = (TextView)this.findViewById(R.id.textView);
        button[1] = (Button)this.findViewById(R.id.button1);
        button[2] = (Button)this.findViewById(R.id.button2);
        button[3] = (Button)this.findViewById(R.id.button3);
        button[4] = (Button)this.findViewById(R.id.button4);
        button[5] = (Button)this.findViewById(R.id.button5);
        button[6] = (Button)this.findViewById(R.id.button6);
        button[7] = (Button)this.findViewById(R.id.button7);
        button[8] = (Button)this.findViewById(R.id.button8);
        button[9] = (Button)this.findViewById(R.id.button9);
        button[10] = (Button)this.findViewById(R.id.button10);
        button[11] = (Button)this.findViewById(R.id.button11);
        button[12] = (Button)this.findViewById(R.id.button12);
        button[13] = (Button)this.findViewById(R.id.button13);
        button[14] = (Button)this.findViewById(R.id.button14);
        button[15] = (Button)this.findViewById(R.id.button15);
        button[16] = (Button)this.findViewById(R.id.button16);
        button[17] = (Button)this.findViewById(R.id.button17);
        button[18] = (Button)this.findViewById(R.id.button18);
        button[19] = (Button)this.findViewById(R.id.button19);
        button[20] = (Button)this.findViewById(R.id.button20);
    }



}
