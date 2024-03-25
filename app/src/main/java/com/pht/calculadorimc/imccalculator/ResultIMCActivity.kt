package com.pht.calculadorimc.imccalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pht.calculadorimc.R
import com.pht.calculadorimc.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var txtResult: TextView
    private lateinit var txtImc: TextView
    private lateinit var txtDescription: TextView
    private lateinit var btnRecalculate: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)
        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initUI(result: Double) {
        txtImc.text = result.toString()
        when (result) {
            in 0.00..18.50 -> { //PESO BAJO
                txtResult.text = getString(R.string.title_bajo_peso)
                txtResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                txtDescription.text = getString(R.string.description_bajo_peso)
            }

            in 18.51..24.99 -> { //PESO NORMAL
                txtResult.text = getString(R.string.title_normal_peso)
                txtResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                txtDescription.text = getString(R.string.description_normal_peso)
            }

            in 25.00..29.99 -> { //SOBREPESO
                txtResult.text = getString(R.string.title_sobrepeso)
                txtResult.setTextColor(ContextCompat.getColor(this, R.color.sobrepeso))
                txtDescription.text = getString(R.string.description_sobrepeso)
            }

            in 30.00..99.99 -> { //OBESIDAD
                txtResult.text = getString(R.string.title_Obesidad)
                txtResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                txtDescription.text = getString(R.string.description_obesidad)
            }

            else -> { //ERROR
                txtImc.text = getString(R.string.error)
                txtResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                txtResult.text = getString(R.string.error)
                txtDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponents() {
        txtResult = findViewById(R.id.txtResult)
        txtImc = findViewById(R.id.txtImc)
        txtDescription = findViewById(R.id.txtDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }
}