package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Calculator extends AppCompatActivity {
    Button btnOne, btnTwo, btnThree, btnFour, btnFive;
    Button btnSix, btnSeven, btnEight, btnNine, btnZero;
    Button btnAdd, btnMinus, btnMultiply, btnDivide, btnDecimal, btnEquals;
    TextView txtResult, txtEqua;
    Stack<String> calcuSeq = new Stack<String>();
    int equaLen;
    BigDecimal result; //equaLen len until sa op. 123+ is len: 4
    StringBuilder strEqua, strResult;
    char op;

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

        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                strEqua = new StringBuilder(txtEqua.getText().toString());
                strResult = new StringBuilder(txtResult.getText().toString());
                String newTerm = txtEqua.getText().toString().substring(equaLen);

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
                } else if (newTerm.length() == 0) {
                    txtEqua.setText(strEqua.toString() + "0.");
                } else if (!newTerm.contains(".")) {
                    txtEqua.setText(strEqua.toString() + ".");
                }
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumber(1);
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumber(2);
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumber(3);
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumber(4);
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumber(5);
            }
        });

        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumber(6);
            }
        });

        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumber(7);
            }
        });

        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumber(8);
            }
        });

        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumber(9);
            }
        });

        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNumber(0);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEqua = new StringBuilder(txtEqua.getText().toString());

                operator(strEqua,'+');
                op = '+';
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEqua = new StringBuilder(txtEqua.getText().toString());

                operator(strEqua,'-');
                op = '-';
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEqua = new StringBuilder(txtEqua.getText().toString());

                operator(strEqua,'*');
                op = '*';
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEqua = new StringBuilder(txtEqua.getText().toString());

                operator(strEqua,'/');
                op = '/';
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInfix();
            }
        });
    }

    public void operator(StringBuilder str, char op) {
        if(calcuSeq.size() == 1) {
            calcuSeq.pop();
            calcuSeq.push(String.valueOf(result));
        } else {
            calcuSeq.push(txtResult.getText().toString());
        }

        if(!Character.isDigit(str.charAt(str.length()-1)) && str.charAt(str.length()-1) != '.') {
            str.setCharAt(str.length()-1,op);
        } else {
            str.append(op);
        }
        txtEqua.setText(str.toString());

        equaLen = txtEqua.length();
    }

    public void btnNumber(int num) {
        txtResult.append(""+num);
        txtEqua.append(""+num);

        if(calcuSeq.size() == 1) {
            calcuSequential(op);
        }
    }

    public void calcuSequential(char op) {
        String newTerm = txtEqua.getText().toString().substring(equaLen);

        BigDecimal testNum1 = new BigDecimal(calcuSeq.peek());
        BigDecimal testNum2 = new BigDecimal(newTerm);

        switch(op) {
            case '+':
                result = testNum1.add(testNum2);
                break;
            case '-':
                result = testNum1.subtract(testNum2);
                break;
            case '*':
                result = testNum1.multiply(testNum2);
                break;
            case '/':
                try {
                    result = testNum1.divide(testNum2);
                } catch (ArithmeticException e) {
                    if(!Objects.requireNonNull(e.getMessage()).contains("by zero")) {
                        result = testNum1.divide(testNum2, 11, RoundingMode.HALF_EVEN);
                    } else {
                        result = BigDecimal.valueOf(0);
                    }
                }
                break;
        }

        txtResult.setText("");
        txtResult.setText(String.valueOf(result));
    }

    Stack<String> MDASnum = new Stack<>();
    Stack<String> MDASop = new Stack<>();
    @SuppressLint("SetTextI18n")
    public void getInfix() {
        // 4-7*9+1/2

        String[] ambotTEST = strEqua.toString().split("(?=[+\\-*/])|(?<=[+\\-*/])");
        String[] realKapoy = new String[ambotTEST.length+1];

        for(int i = 0; i < ambotTEST.length; i++) {
            realKapoy[i] = ambotTEST[i];
        }

        realKapoy[ambotTEST.length] = txtEqua.getText().toString().substring(equaLen);
        String tempOP;
        BigDecimal num1, num2;
        BigDecimal MDASresult;
        // 9,-,3,*,5,+,1

        for(int i = 0; i < ambotTEST.length; i++) {
            if(ambotTEST[i].matches("[+\\-*/]")) {
                MDASnum.add(realKapoy[i]);
                // 9,3,5
            } else if(realKapoy[i] == "+" || realKapoy[i] == "-" && MDASop.peek() == "*" || MDASop.peek() == "/") { // if u try to stack -/+ on top of * / /
                    tempOP = realKapoy[i];
                    while(MDASnum.size() != 1) { //goal is result nalay nabilin sa stack
                        switch(MDASop.peek()) {
                            case "+":
                                num1 = new BigDecimal(MDASnum.pop());
                                num2 = new BigDecimal(MDASnum.pop());

                                MDASresult = num1.add(num2);
                                MDASnum.push(MDASresult.toString());
                                break;
                            case "-":
                                num1 = new BigDecimal(MDASnum.pop());
                                num2 = new BigDecimal(MDASnum.pop());

                                MDASresult = num2.subtract(num1);
                                MDASnum.push(MDASresult.toString());
                                break;
                            case "*":
                                num1 = new BigDecimal(MDASnum.pop());
                                num2 = new BigDecimal(MDASnum.pop());

                                MDASresult = num1.multiply(num2);
                                MDASnum.push(MDASresult.toString());
                                break;
                            case "/":
                                num1 = new BigDecimal(MDASnum.pop());
                                num2 = new BigDecimal(MDASnum.pop());

                                try {
                                    MDASresult = num1.divide(num2);
                                } catch (ArithmeticException e) {
                                    if(!Objects.requireNonNull(e.getMessage()).contains("by zero")) {
                                        MDASresult = num1.divide(num2, 11, RoundingMode.HALF_EVEN);
                                        MDASnum.push(MDASresult.toString());
                                    } else {
                                        MDASresult = BigDecimal.valueOf(0);
                                        MDASnum.push(MDASresult.toString());
                                    }
                                }
                                break;
                        }
                    }
                    MDASop.push(tempOP);
                } else {
                    MDASop.add(realKapoy[i]);
                    // -,*,    +
                }
            }
        }

    public void calcuMDAS() {
        // 9 6 3
        // - +

    }

    public enum precedence {
        MultDiv, AddMin;
    }
}