package com.example.kharazmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {

        when (view.id) {

            R.id.btn0 -> addNumber("0")
            R.id.btn1 -> addNumber("1")
            R.id.btn2 -> addNumber("2")
            R.id.btn3 -> addNumber("3")
            R.id.btn4 -> addNumber("4")
            R.id.btn5 -> addNumber("5")
            R.id.btn6 -> addNumber("6")
            R.id.btn7 -> addNumber("7")
            R.id.btn8 -> addNumber("8")
            R.id.btn9 -> addNumber("9")
            R.id.clear -> clear()
            R.id.btnMin -> operator("-")
            R.id.btnMulti -> operator("*")
            R.id.btnDiv -> operator("/")
            R.id.btnPlus -> operator("+")
            R.id.btnEqual -> calculate()

        }

    }

    lateinit var textViewNumber: TextView
    lateinit var textViewOperator: TextView
    lateinit var textViewPrevious: TextView
    var operator = ""
    var firstNumber = ""
    var secondNumber = ""

    private fun addNumber(number: String) {
        textViewNumber = findViewById(R.id.textShow)
        textViewNumber.append(number)
    }


    private fun operator(getOperator: String) {
        textViewNumber = findViewById(R.id.textShow)
        textViewOperator = findViewById(R.id.textOperator)
        textViewPrevious = findViewById(R.id.textPrevious)
        if (textViewNumber.text.isEmpty() && firstNumber.isEmpty()) {
            return
        }
        if (operator.isEmpty()) {
            operator = getOperator
            firstNumber = textViewNumber.text.toString()
            textViewPrevious.text = firstNumber
            textViewNumber.text = ""
            textViewOperator.text = getOperator
            secondNumber = textViewNumber.text.toString()
        } else {

            if (firstNumber.isNotEmpty() && textViewNumber.text.isEmpty()) {
                operator = getOperator
                textViewOperator.text = getOperator
            } else {

                secondNumber = textViewNumber.text.toString()
                operator = getOperator
                textViewOperator.text = getOperator
                calculate()
            }
        }

    }


    private fun clear() {

        textViewNumber.text = ""
        textViewPrevious.text = ""
        textViewOperator.text = ""
        operator = ""
        firstNumber = ""
        secondNumber = ""
    }

    private fun calculate() {
        if (operator.isEmpty() || firstNumber.isEmpty())
        else {
            secondNumber = textViewNumber.text.toString()
            textViewNumber = findViewById(R.id.textShow)
            textViewOperator = findViewById(R.id.textOperator)
            textViewPrevious = findViewById(R.id.textPrevious)
            var intFirstNumber = firstNumber.toInt()
            var intSecondNumber = secondNumber.toInt()
            var result = ""
            when (operator) {

                "+" -> result = "${intFirstNumber + intSecondNumber}"
                "-" -> result = "${intFirstNumber - intSecondNumber}"
                "/" -> result = "${(intFirstNumber / intSecondNumber).toFloat()}"
                "*" -> result = "${intFirstNumber * intSecondNumber}"

            }

            textViewPrevious.text = "$firstNumber $operator $secondNumber = "
            textViewNumber.text = result
            operator = ""
            textViewOperator.text = ""
            firstNumber = ""
            secondNumber = ""


        }
    }
    }




