package se.malmo.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_textview, solution_textview;
    MaterialButton button_c, button_pow, button_percent, button_div, button_mul,
            button_add, button_sub, button_equals, button_dot, button_ac, button_1, button_2,
            button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_0;

    String input, output, newOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_textview = findViewById(R.id.result_textview);
        solution_textview = findViewById(R.id.solution_textview);

        assignId(button_0, R.id.button_0);
        assignId(button_1, R.id.button_1);
        assignId(button_2, R.id.button_2);
        assignId(button_3, R.id.button_3);
        assignId(button_4, R.id.button_4);
        assignId(button_5, R.id.button_5);
        assignId(button_6, R.id.button_6);
        assignId(button_7, R.id.button_7);
        assignId(button_8, R.id.button_8);
        assignId(button_9, R.id.button_9);
        assignId(button_c, R.id.button_c);
        assignId(button_ac, R.id.button_ac);
        assignId(button_pow, R.id.button_pow);
        assignId(button_dot, R.id.button_dot);
        assignId(button_add, R.id.button_add);
        assignId(button_sub, R.id.button_sub);
        assignId(button_mul, R.id.button_mul);
        assignId(button_div, R.id.button_div);
        assignId(button_percent, R.id.button_percent);
        assignId(button_equals, R.id.button_equals);


    }

    public void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        MaterialButton button = (MaterialButton) view;
        String data = button.getText().toString();

        switch (data){

            case "AC":
                input = "";
                output = "";
                newOutput = "";
                result_textview.setText("0");
                solution_textview.setText("");
                break;
            case "C":
                if (input.isEmpty()) {
                    solution_textview.setText("0");
                } else {
                    input = input.substring(0, input.length() - 1);
                    solution_textview.setText(input);
                }
                break;
            case "^":
                solve();
                input += "^";
                break;
            case "+":
                solve();
                input += "+";
                break;
            case "-":
                solve();
                input += "-";
                break;
            case "*":
                solve();
                input += "*";
                break;
            case "/":
                solve();
                input += "/";
                break;
            case "=":
                solve();
                input = "";
                break;
            case "%":
                Double number = Double.parseDouble(solution_textview.getText().toString());
                if (number > 1e10) {
                    result_textview.setText("Number is too big to convert to percentage");
                    solution_textview.setText("");
                } else {
                    input += "%";
                    Double x = number / 100;
                    result_textview.setText(String.valueOf(x));
                    input = "";
                }
                break;
            default:
                if(input == null){
                    input = "";
                }
                if(data.equals("+") || data.equals("-") || data.equals("*") || data.equals("/")){
                    solve();
                }
                input += data;

        }
        newOutput = removeDecimal(input);
        solution_textview.setText(newOutput);
        }

    private void solve() {
        if(input.split("\\*").length==2){
            String numbers[] = input.split(("\\*"));
            try {
                double x = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                output = Double.toString(x);
                newOutput = removeDecimal(output);
                result_textview.setText(newOutput);
                input = x + "";
            } catch (Exception e){
                result_textview.setError(e.getMessage().toString());
            }
        }
        if(input.split("\\/").length==2){
            String numbers[] = input.split(("\\/"));
            try {
                double dividend = Double.parseDouble(numbers[0]);
                double divisor = Double.parseDouble(numbers[1]);
                if (divisor == 0) {
                    output = "0";
                    newOutput = removeDecimal(output);
                    result_textview.setText(newOutput);
                    input = output;
                } else {
                    double x = dividend / divisor;
                    output = Double.toString(x);
                    newOutput = removeDecimal(output);
                    result_textview.setText(newOutput);
                    input = x + "";
                }
            } catch (Exception e){
                result_textview.setError(e.getMessage().toString());
            }
        }
        if(input.split("\\+").length==2){
            String numbers[] = input.split(("\\+"));
            try {
                double x = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                output = Double.toString(x);
                newOutput = removeDecimal(output);
                result_textview.setText(newOutput);
                input = x + "";
            } catch (Exception e){
                result_textview.setError(e.getMessage().toString());
            }
        }
        if(input.split("\\-").length==2){
            String numbers[] = input.split(("\\-"));
            try {
                if(Double.parseDouble(numbers[0]) < Double.parseDouble(numbers[1])){
                    double x = Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[0]);
                    output = Double.toString(x);
                    newOutput = removeDecimal(output);
                    result_textview.setText("-" + newOutput);
                    input = x + "";
                }
                else{
                    double x = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                    output = Double.toString(x);
                    newOutput = removeDecimal(output);
                    result_textview.setText(newOutput);
                    input = x + "";
                }

            } catch (Exception e){
                result_textview.setError(e.getMessage().toString());
            }
        }
        if(input.split("\\^").length==2){
            String numbers[] = input.split(("\\^"));
            try {
                double x = Math.pow(Double.parseDouble(numbers[0]) , Double.parseDouble(numbers[1]));
                output = Double.toString(x);
                newOutput = removeDecimal(output);
                result_textview.setText(newOutput);
                input = x + "";
            } catch (Exception e){
                result_textview.setError(e.getMessage().toString());
            }
        }

    }

    public String removeDecimal(String number){
        String n [] = number.split("\\.");
        if(n.length > 1){
            if(n[1].equals("0")){
                number = n[0];
            }
        }
        return number;
    }


}