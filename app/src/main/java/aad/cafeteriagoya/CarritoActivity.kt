package aad.cafeteriagoya

import aad.cafeteriagoya.databinding.ActivityCarritoBinding
import aad.cafeteriagoya.databinding.ActivityMenuBinding
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CarritoActivity : AppCompatActivity()
{
    private var binding: ActivityCarritoBinding? = null
    private lateinit var hora:String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityCarritoBinding.inflate(layoutInflater)

        hora = intent.getStringExtra("hora").toString()

        mostrarCarrito()

        setContentView(binding!!.root)
    }


    fun mostrarCarrito()
    {
        var base = MiBDOpenHelper(this, null)

        var cursor = base.obtenerCarrito()

        cursor.moveToFirst()

        var con = ""

        while(!cursor.isAfterLast)
        {

        }
    }
}