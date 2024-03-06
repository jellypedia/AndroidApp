package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class Calculator extends AppCompatActivity {
    Button btnOne, btnTwo, btnThree, btnFour, btnFive;
    Button btnSix, btnSeven, btnEight, btnNine, btnZero;
    Button btnAdd, btnMinus, btnMultiply, btnDivide, btnDecimal, btnEquals;
    TextView txtResult, txtEqua;
    Stack<Object> calcu = new Stack<>();
    int num, result;
    StringBuilder str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnZero = findViewById(R.id.btnZero);
        btnAdd = findViewById(R.id.btnAdd);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnDecimal = findViewById(R.id.btnDecimal);
        btnEquals = findViewById(R.id.btnEquals);

        txtResult = findViewById(R.id.txtResult);
        txtEqua = findViewById(R.id.txtEqua);


        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtResult.append("1");
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtResult.append("2");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = Integer.parseInt(txtResult.getText().toString());
                str = new StringBuilder();
                if(str.charAt(str.length()-1) == '*' || str.charAt(str.length()-1) == '-' || str.charAt(str.length()-1) == '/') {
                    str = str.replace(str.length()-1,str.length(),"+");
                    txtEqua.setText(str.toString());
                }
//11+ = 3
//                txtEqua.append((num)+"+");
//                txtResult.setText("");
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = Integer.parseInt(txtResult.getText().toString());
//                calculate(num,"-");

                txtEqua.append((num)+"-");
                txtResult.setText("");
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtResult.append(""+result);
            }
        });

    }

    void calculate(int num, String op) {
        if(calcu.peek() instanceof String) { //operator sya
            switch(op) {
                case "+":
                    calcu.pop();
                    result = num+(Integer)calcu.peek();
                    break;
                case "-":
                    break;
            }
        }

        calcu.push(num);
        calcu.push(op);
    }

}