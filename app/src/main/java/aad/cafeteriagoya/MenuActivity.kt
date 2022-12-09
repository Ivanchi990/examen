package aad.cafeteriagoya

import aad.cafeteriagoya.DataProvider.Companion.listaProductos
import aad.cafeteriagoya.adapter.MenuAdaptador
import aad.cafeteriagoya.databinding.ActivityMainBinding
import aad.cafeteriagoya.databinding.ActivityMenuBinding
import aad.cafeteriagoya.entidades.Producto
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import android.media.midi.MidiDevice.MidiConnection
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MenuActivity : AppCompatActivity()
{
    var binding:ActivityMenuBinding? = null
    var base: MiBDOpenHelper? = null
    var cat = "Todas"
    private lateinit var adapterProductos: MenuAdaptador

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)

        base = MiBDOpenHelper(this, null)

        rellenarSpinner()

        iniciarRecicler()

        binding?.btFiltrar?.setOnClickListener{
            filtrar()
        }

        setContentView(binding!!.root)
    }

    fun rellenarSpinner()
    {
        val categorias = arrayOf("Todas", "pincho", "cafe", "refresco", "bocadillo")

        var adaptador: ArrayAdapter<String> =
        ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        binding!!.spinner.adapter = adaptador
    }

    fun iniciarRecicler()
    {
        val recyclerView = binding?.recyclerView
        recyclerView?.layoutManager = LinearLayoutManager(this)

        var productos:ArrayList<Producto> = ArrayList()

        if(cat.equals("Todas"))
        {
            productos = ArrayList(listaProductos)
        }
        else
        {
            for (producto in listaProductos)
            {
                if (producto.categoria.equals(cat))
                {
                    productos.add(producto)
                }
            }
        }

        adapterProductos = MenuAdaptador(
            onClickListener = { pos -> dameID(pos) }
        )

        adapterProductos.MenuAdaptador(this,  productos)

        recyclerView?.adapter = adapterProductos
    }

    fun filtrar()
    {
        cat = binding?.spinner?.selectedItem.toString()

        adapterProductos.notifyDataSetChanged()

        iniciarRecicler()
    }


    fun dameID(pos: Int)
    {
        base?.andirProducto(listaProductos[pos])
    }
}