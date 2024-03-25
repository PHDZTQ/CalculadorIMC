package com.pht.calculadorimc.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.pht.calculadorimc.R
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 60
    private var currentHeight: Int = 120
    private var currentAge: Int = 20

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var txtHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnLessWeight: FloatingActionButton
    private lateinit var btnMoreWeight: FloatingActionButton
    private lateinit var txtWeight: TextView
    private lateinit var btnLessAge: FloatingActionButton
    private lateinit var btnMoreAge: FloatingActionButton
    private lateinit var txtAge: TextView
    private lateinit var btnCalculate: Button

    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        txtHeight = findViewById(R.id.txtHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnLessWeight = findViewById(R.id.btnLessWeight)
        btnMoreWeight = findViewById(R.id.btnMoreWeight)
        txtWeight = findViewById(R.id.txtWeight)
        btnLessAge = findViewById(R.id.btnLessAge)
        btnMoreAge = findViewById(R.id.btnMoreAge)
        txtAge = findViewById(R.id.txtAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            txtHeight.text = "$currentHeight cm"
        }
        btnMoreWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnLessWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }
        btnMoreAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        btnLessAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC():Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        return df.format(imc).toDouble()
    }

    private fun setWeight() {
        txtWeight.text = currentWeight.toString()
    }

    private fun setAge() {
        txtAge.text = currentAge.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }
}