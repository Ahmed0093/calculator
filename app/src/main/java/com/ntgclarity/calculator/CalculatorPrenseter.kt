package com.ntgclarity.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

//MVVM   Model View  --> VIEWMODEL
//Binding / observer Pattern
//Androi
//CalcViewModel
//ViewModel  MVVM
// databinding binding    . Observer
//MVI  [JetpackCompose]  --- VIPER
class CalculatorPrenseter(private val myCalcView : viewInterface) : myPresenterInterface {
    var isTypingNumber :Boolean = true
    val calculator = Calculator()
    val showResult :MutableLiveData<String>? = null
// LiveData
// Rx-java
    override fun handleDigitClick(digit: Int) {
        if (isTypingNumber) {
            myCalcView.showResultWithOld(digit.toString())
          //  showResult?.postValue(digit.toString())
        } else {
            myCalcView.showResultAsItIs(digit.toString())

           // showResult?.postValue(digit.toString())
            isTypingNumber = true
        }
    }

    override fun onBtnPlusClicked(toDouble: Double) {
        if (isTypingNumber) {
            isTypingNumber = false
        }
        calculator.operand = toDouble

        val result = calculator.execute("+")
        myCalcView.showResultAsItIs(result.toString())
       // showResult?.postValue(result.toString())
    }
}