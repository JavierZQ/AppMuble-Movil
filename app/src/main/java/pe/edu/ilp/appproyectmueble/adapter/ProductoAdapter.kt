package pe.edu.ilp.appproyectmueble.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import pe.edu.ilp.appproyectmueble.R
import pe.edu.ilp.appproyectmueble.controller.ImageController
import pe.edu.ilp.appproyectmueble.entity.Producto
import java.text.FieldPosition

class ProductoAdapter(private val mContexto:Context,private val listaProducto:List<Producto>):ArrayAdapter<Producto>(mContexto,0,listaProducto) {


    override fun getView(position: Int, converView:View?,parent: ViewGroup):View{
        val layout = LayoutInflater.from(mContexto).inflate(R.layout.item,parent,false)
        val producto=listaProducto[position]
        layout.findViewById<TextView>(R.id.txtnombre).text=producto.nombre
        layout.findViewById<TextView>(R.id.txtprecio).text="$${producto.precio}"

        val imageUri=ImageController.getimagUri(mContexto,producto.idproducto.toLong())
        layout.findViewById<ImageView>(R.id.imageView).setImageURI(imageUri)

        return layout
    }
}