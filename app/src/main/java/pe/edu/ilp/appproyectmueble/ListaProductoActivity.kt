package pe.edu.ilp.appproyectmueble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pe.edu.ilp.appproyectmueble.adapter.ProductoAdapter
import pe.edu.ilp.appproyectmueble.database.AppDatabse
import pe.edu.ilp.appproyectmueble.entity.Producto

class ListaProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_producto)

        val lista=findViewById<ListView>(R.id.lista)

        var listaProductos= emptyList<Producto>()
        //instacia ala database
        val database=AppDatabse.getDatabase(this)

        database.producto().getAll().observe(this, androidx.lifecycle.Observer {
            listaProductos=it
            //adaptador
            val adapter= ProductoAdapter(this,listaProductos)
            lista.adapter=adapter
        })


//clink mostrar el producto
        lista.setOnItemClickListener { parent, view, position, id ->
            val intent=Intent(this,ProductoActivity::class.java)
            intent.putExtra("id",listaProductos[position].idproducto)
            startActivity(intent)
        }
         //nuevo producto
        val btn=findViewById<FloatingActionButton>(R.id.floatingActionButton)
        btn.setOnClickListener{
            val intent=Intent(this,NuevoProductoActivity::class.java)
            startActivity(intent)
        }


    }
}