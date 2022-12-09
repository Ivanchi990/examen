package aad.cafeteriagoya.adapter

import aad.cafeteriagoya.R
import aad.cafeteriagoya.entidades.Producto
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.io.File

class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val imagenView = itemView.findViewById<ImageView>(R.id.imgProducto)
    var textViewProducto = itemView.findViewById<TextView>(R.id.textoProducto)
    var textViewPrecio = itemView.findViewById<TextView>(R.id.precioProducto)
    var button = itemView.findViewById<Button>(R.id.buttonAnadir)

    fun render(producto: Producto,
               onClickListener: (Int) -> Unit)
    {
        if(producto.categoria.equals("bocadillo"))
        {
            imagenView.setImageResource(R.drawable.bocadillo)
        }
        else if(producto.categoria.equals("cafe"))
        {
            imagenView.setImageResource(R.drawable.cafe)
        }
        else if(producto.categoria.equals("pincho"))
        {
            imagenView.setImageResource(R.drawable.pincho)
        }
        else if(producto.categoria.equals("refresco"))
        {
            imagenView.setImageResource(R.drawable.refresco)
        }

        textViewProducto.text = producto.nombre
        textViewPrecio.text = producto.precio.toString() + "€"

        button.setOnClickListener {
            onClickListener(producto.id)
        }
    }
}