package com.ntgclarity.calculator

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),viewInterface {
    private var tvResult: TextView? = null
    lateinit var calculatorPrenseter :CalculatorPrenseter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculatorPrenseter = CalculatorPrenseter(this)
        
        calculatorPrenseter.showResult?.observe(this) { myresult ->
            showResultWithOld(myresult)
        }
        tvResult = findViewById<TextView>(R.id.tv_result)
        val btnOne = findViewById<Button>(R.id.btn_one)
        val btnTwo = findViewById<Button>(R.id.btn_two)
        val btnPlus = findViewById<Button>(R.id.btn_plus)

        btnOne.setOnClickListener {
          calculatorPrenseter.handleDigitClick(1)
        }
        btnTwo.setOnClickListener {
            calculatorPrenseter.handleDigitClick(2)
        }
        btnPlus.setOnClickListener {
            calculatorPrenseter.onBtnPlusClicked(tvResult?.text.toString().toDouble())
        }
    }
    fun sums() {
        val calculator = Calculator()
        val numbers = listOf(12.0, 20.0, 40.0)

        numbers.forEach {
            calculator.operand = it
            val result = calculator.execute("+")
            println("result: ${result}")
        }
    }

    override fun showResultAsItIs(resultAsString : String) {
        tvResult?.visibility = View.VISIBLE
        tvResult?.text = resultAsString
    }

    override fun showResultWithOld(resultAsString: String) {
        tvResult?.visibility = View.VISIBLE
        tvResult?.text = "${tvResult?.text}${resultAsString}"
    }

    override fun showLoading() {
        // find
        val btnOne = findViewById<Button>(R.id.btn_one)
        btnOne.visibility = View.VISIBLE
    }
}


//M model    View    P  Presenter
interface  viewInterface {
    fun showResultAsItIs(resultAsString : String)
    fun showResultWithOld(resultAsString : String)
    fun showLoading()
//    fun hideLoading()
}

interface myPresenterInterface {
    fun handleDigitClick(digit: Int)
    fun onBtnPlusClicked(toDouble: Double)
    //fun loginBtnClicked(username:String , pass:String)
}
//Interfaces