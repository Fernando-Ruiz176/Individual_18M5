package cl.fernando.individual_18m5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cl.fernando.individual_18m5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mSharedPreferences = getSharedPreferences("cookie", Context.MODE_PRIVATE)
        binding.btnGuardar.setOnClickListener(View.OnClickListener {
            val entero = binding.inputEntero.text.toString().toInt()
            val texto = binding.inputTexto.text.toString()
            val decimal = binding.inputDecimal.text.toString().toFloat()
            val boleano = binding.switchA.isChecked

            guardarDatos(entero, texto, decimal, boleano)
        })

       binding.btnMostrarValores.setOnClickListener(View.OnClickListener {
            mostrarDatos()
        })


        binding.btnBorrar.setOnClickListener(View.OnClickListener {
            borrarDatos()
        })

    }

    private fun borrarDatos() {
        binding.txEntero.text = ""
        binding.txTexto.text = ""
        binding.txDecimal.text = ""
        binding.txSwitch.text = ""

        binding.inputEntero.text.clear()
        binding.inputTexto.text.clear()
        binding.inputDecimal.text.clear()
        binding.switchA.isChecked = false

        mSharedPreferences.edit().clear().apply()
    }

    private fun mostrarDatos() {
        val entero = mSharedPreferences.getInt("mi Entero",0)
        val text = mSharedPreferences.getString("miTexto","")
        val decimal = mSharedPreferences.getFloat("mi Double",0.0f)
        val boleano = mSharedPreferences.getBoolean("mi boleano",false)

        binding.txEntero.text = entero.toString()
        binding.txTexto.text = text
        binding.txDecimal.text = decimal.toString()
        binding.switchA.text = boleano.toString()

        binding.switchA.isChecked = boleano
    }


    private fun guardarDatos(entero: Int, texto: String, decimal: Float, boleano:Boolean) {
        mSharedPreferences.edit().putInt("mi Entero",entero).apply()
        mSharedPreferences.edit().putString("miTexto", texto).apply()
        mSharedPreferences.edit().putFloat("mi Double", decimal).apply()
        mSharedPreferences.edit().putBoolean("mi boleano", boleano).apply()
    }
}