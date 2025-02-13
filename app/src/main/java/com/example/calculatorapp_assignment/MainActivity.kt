package com.example.calculatorapp_assignment


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatorapp_assignment.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstNumber = findViewById<EditText>(R.id.f_no)
        val secondNumber = findViewById<EditText>(R.id.s_no)
        val resultText = findViewById<TextView>(R.id.oo)

        val addButton = findViewById<Button>(R.id.add)
        val subButton = findViewById<Button>(R.id.sub)
        val mulButton = findViewById<Button>(R.id.mul)
        val divButton = findViewById<Button>(R.id.div)
        val equalsButton = findViewById<Button>(R.id.equals)
        val acButton = findViewById<Button>(R.id.ac)
        val a="Error"

        fun calculate(operation: (Double, Double) -> Double) {
            val num1 = firstNumber.text.toString().toDoubleOrNull()
            val num2 = secondNumber.text.toString().toDoubleOrNull()

            if (num1 != null && num2 != null) {
                var result = operation(num1, num2).toString()

                // Limit result to 11 digits
                if (result.length > 11) {
                    result = result.substring(0, 11)
                }

                resultText.text = result
            } else {
                resultText.text = a
            }
        }

        addButton.setOnClickListener { calculate { a, b -> a + b } }
        subButton.setOnClickListener { calculate { a, b -> a - b } }
        mulButton.setOnClickListener { calculate { a, b -> a * b } }
        divButton.setOnClickListener { calculate { a, b -> if (b != 0.0) a / b else Double.NaN } }

        equalsButton.setOnClickListener {
            resultText.text = resultText.text.toString().take(11) // Ensure the limit is maintained
        }

        acButton.setOnClickListener {
            firstNumber.text.clear()
            secondNumber.text.clear()
            resultText.text = ""
        }
    }
}