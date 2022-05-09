package com.matteoputti.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matteoputti.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip()
    {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }
        val selectedId = binding.tipOptions.checkedRadioButtonId
        var tipPercentage = 0.00
        when(selectedId)
        {
            R.id.option_eighteen_percent -> tipPercentage = 0.18
            R.id.option_fifteen_percent -> tipPercentage = 0.15
            else -> tipPercentage = 0.25
        }

        var tip = cost * tipPercentage
        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp==true)
        {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}
