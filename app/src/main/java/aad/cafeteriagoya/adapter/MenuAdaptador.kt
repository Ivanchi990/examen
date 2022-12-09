package aad.cafeteriagoya.adapter

import aad.cafeteriagoya.entidades.Producto
import android.content.Context
import aad.cafeteriagoya.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MenuAdaptador(
    private val onClickListener: (Int) -> Unit
): RecyclerView.Adapter<ProductoViewHolder>()
{
    private lateinit var context: Context
    private lateinit var productos: ArrayList<Producto>

    fun MenuAdaptador(context: Context, productos: ArrayList<Producto>)
    {
        this.context = context
        this.productos = productos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)

        return ProductoViewHolder(layoutInflater.inflate(R.layout.item_lista, parent, false))
    }

    //Renederizamos cada elemento de la lista
    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int)
    {
        holder.render(productos[position], onClickListener)
    }

    //Obtenemos el tamaño de la lista
    override fun getItemCount(): Int
    {
        return productos.size
    }
}