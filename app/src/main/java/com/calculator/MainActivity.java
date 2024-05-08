
package com.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvOutput;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = findViewById(R.id.tvInput);
        tvResult = findViewById(R.id.tvResult);


        Button addButton = findViewById(R.id.addButton);
        Button subtractButton = findViewById(R.id.subtractButton);
        Button multiplyButton = findViewById(R.id.multiplyButton);
        Button divideButton = findViewById(R.id.divideButton);
        Button moduloButton = findViewById(R.id.moduloButton);
        Button btnResult = findViewById(R.id.btnResult);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btn0 = findViewById(R.id.btn0);
        Button btnDelete = findViewById(R.id.btnDelete);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("+");
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("-");
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("*");
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("/");
            }
        });

        moduloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("%");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("9");
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("0");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDeleteOperation();
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }
    private void performOperation(String operation) {
        if (operation.equals("Clear")) {
            tvOutput.setText("");
        } else {
            String currentText = tvOutput.getText().toString();
            if (isOperator(operation)) {
                tvOutput.setText(currentText + " " + operation + " ");
            } else {
                tvOutput.setText(currentText + operation);
            }
        }
    }

    private void handleDeleteOperation() {
        String currentText = tvOutput.getText().toString();
        if (!currentText.isEmpty()) {
            tvOutput.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    private void calculateResult() {
        String expression = tvOutput.getText().toString().trim();
        if (expression.isEmpty()) {
            Toast.makeText(this, "Please enter an expression", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] parts = expression.split(" ");
        if (parts.length != 3) {
            Toast.makeText(this, "Invalid expression", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[2]);
            String operator = parts[1];
            int result = performCalculation(num1, num2, operator);
            tvResult.setText("Result = " + result);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid numbers in the expression", Toast.LENGTH_SHORT).show();
        } catch (ArithmeticException e) {
            Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
        }
    }

    private int performCalculation(int num1, int num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return num1 / num2;
            case "%":
                if (num2 == 0) {
                    throw new ArithmeticException("Cannot modulo by zero");
                }
                return num1 % num2;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }

    private boolean isOperator(String input) {
        return input.matches("[+\\-*/%]");
    }
}

