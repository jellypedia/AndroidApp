package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Stack;

public class Calculator extends AppCompatActivity {
    Button btnOne, btnTwo, btnThree, btnFour, btnFive;
    Button btnSix, btnSeven, btnEight, btnNine, btnZero;
    Button btnAdd, btnMinus, btnMultiply, btnDivide, btnDecimal, btnEquals;
    TextView txtResult, txtEqua, txtTest;
    Stack<String> calcu = new Stack<String>();
    int equaLen, result; //equaLen len until sa op. 123+ is len: 4
    StringBuilder strEqua, strResult;
    boolean isOp = false;
    String op;

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
        txtTest = findViewById(R.id.txtTesting);

        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                strEqua = new StringBuilder(txtEqua.getText().toString());
                strResult = new StringBuilder(txtResult.getText().toString());

                if(!txtResult.getText().toString().contains(".")) { //walay decimal, igo ra-add
                    if(txtResult.getText().toString().isEmpty()) {
                        txtResult.setText(strResult.toString() + "0.");
                        txtEqua.setText(strEqua.toString() + "0.");
                    } else {
                        txtResult.setText(strResult.toString() + ".");
                        txtEqua.setText(strEqua.toString() + ".");
                    }
                } else if (txtResult.getText().toString().contains(".") && strEqua.charAt(strEqua.length() - 1) == '.') {
                    strEqua.deleteCharAt(strEqua.length()-1);
                    strResult.deleteCharAt(strResult.length()-1);

                    txtResult.setText(strResult.toString());
                    txtEqua.setText(strEqua.toString());
                }
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumAppend(1);
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumAppend(2);
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumAppend(3);
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumAppend(4);
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumAppend(5);
            }
        });

        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumAppend(6);
            }
        });

        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumAppend(7);
            }
        });

        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumAppend(8);
            }
        });

        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumAppend(9);
            }
        });

        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumAppend(0);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEqua = new StringBuilder(txtEqua.getText().toString());

                calcu.push(txtResult.getText().toString());

                operator(strEqua,'+');
                op = "+";
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEqua = new StringBuilder(txtEqua.getText().toString());
                calcu.push(txtResult.getText().toString());

                operator(strEqua,'-');

            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEqua = new StringBuilder(txtEqua.getText().toString());
                calcu.push(txtResult.getText().toString());

                operator(strEqua,'*');
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEqua = new StringBuilder(txtEqua.getText().toString());
                calcu.push(txtResult.getText().toString());

                operator(strEqua,'/');
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtResult.append(""+result);
            }
        });

    }

    public void operator(StringBuilder str, char op) {
        if(!Character.isDigit(str.charAt(str.length()-1)) && str.charAt(str.length()-1) != '.') {
            str.setCharAt(str.length()-1,op);
        } else {
            str.append(op);
        }
        txtResult.setText("");
        txtEqua.setText(str.toString());
        isOp = true;
    }

    public void btnNumAppend(int num) {
        txtResult.append(""+num);
        txtEqua.append(""+num);

        if(calcu.size() == 1) {
            calcuSequential(op);
        }
    }

    public void calcuSequential(String op) {

        String num1, num2;
        float result = 0;

        switch(op) {
            case "+":
                num2 = txtResult.getText().toString();
                num1 = calcu.pop();
                result = Float.parseFloat(num1) + Float.parseFloat(num2);
                break;
        }

        calcu.push(String.valueOf(result));
        System.out.println(result);
        txtResult.setText("");
        txtResult.setText(result+"");

    }
}