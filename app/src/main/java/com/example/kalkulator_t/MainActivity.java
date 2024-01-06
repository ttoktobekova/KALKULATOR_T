package com.example.kalkulator_t;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Double firstOperand, secondOperand, result;
    private String currentOperator;
    private Boolean isFinishOperation;
    private Button resultButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);
        resultButton = findViewById(R.id.result_button);
        resultButton.setVisibility(View.GONE);

        resultButton.setOnClickListener(view -> {
            String resultText = textView.getText().toString();
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("result", resultText.toString());
            startActivity(intent);
            finish();
        });
    }


    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart: ");
    }

    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume: ");
    }

    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause: ");
    }

    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop: ");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy: ");
    }


    public void onNumberClick(View view) {
        String number = ((MaterialButton) view).getText().toString();

        if (view.getId() == R.id.btn_clear) {
            clearCalculator();
        } else if (textView.getText().toString().equals("0") || isFinishOperation) {
            textView.setText(number);
        } else {
            textView.append(number);
        }
        isFinishOperation = false;
        resultButton.setVisibility(View.GONE);
    }

    public void onOperationClick(View view) {
        String operator = ((MaterialButton) view).getText().toString();

        if (view.getId() == R.id.btn_equal) {
            handleEqualsClick();
        } else if (view.getId() == R.id.btn_plus_minus) {
            handlePlusMinusClick();
        } else if (view.getId() == R.id.percent) {
            handlePercentClick();
        } else {
            handleOperatorClick(operator);
        }
    }

    private void handleOperatorClick(String operator) {
        if (!isFinishOperation) {
            if (firstOperand == null) {
                firstOperand = Double.valueOf(textView.getText().toString());
            } else {
                handleEqualsClick();
            }

            textView.append(operator);
            currentOperator = operator;
            isFinishOperation = false;
            resultButton.setVisibility(View.GONE);
        }
    }

    private void handleEqualsClick() {
        if (firstOperand != null) {
            String[] numbers = textView.getText().toString().split("\\+|-|\\*|/|%");
            secondOperand = Double.valueOf(numbers[1]);

            switch (currentOperator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    result = firstOperand / secondOperand;
                    break;
                case "%":
                    result = firstOperand * (secondOperand / 100);
                    break;
            }

            textView.setText(result.toString());
            firstOperand = null;
            isFinishOperation = true;
            resultButton.setVisibility(View.VISIBLE);
        }
    }

    private void handlePlusMinusClick() {
        if (!isFinishOperation) {
            double currentValue = Double.parseDouble(textView.getText().toString());
            double negatedValue = -currentValue;
            textView.setText(Double.toString(negatedValue));
        }
        resultButton.setVisibility(View.GONE);
    }

    private void handlePercentClick() {
        if (!isFinishOperation) {
            double currentValue = Double.parseDouble(textView.getText().toString());
            double percentValue = currentValue / 100;
            textView.setText(Double.toString(percentValue));
            isFinishOperation = true;
            resultButton.setVisibility(View.GONE);
        }
    }

    private void clearCalculator() {
        textView.setText("0");
        firstOperand = null;
        secondOperand = null;
        result = null;
        isFinishOperation = false;
        resultButton.setVisibility(View.GONE);

    }


}
