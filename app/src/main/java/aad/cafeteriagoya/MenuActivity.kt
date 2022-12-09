package aad.cafeteriagoya

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu)
        rellenarSpinner()
    }

    fun rellenarSpinner() {
//        val categorias = arrayOf("Todas", "pincho", "cafe", "refresco", "bocadillo")
//
//        var adaptador: ArrayAdapter<String> =
//            ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
//        binding.spinner.adapter = adaptador
    }

}