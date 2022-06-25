package pe.edu.ilp.appproyectmueble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.lifecycle.Observer
import pe.edu.ilp.appproyectmueble.adapter.ProductoAdapter
import pe.edu.ilp.appproyectmueble.database.AppDatabse
import pe.edu.ilp.appproyectmueble.entity.Producto

class ListaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        val listas=findViewById<ListView>(R.id.listaCompra)
        //listado
        var listaProductos= emptyList<Producto>()
        //datbse
        val database=AppDatabse.getDatabase(this)

        database.producto().getAll().observe(this, Observer {
            listaProductos=it
            val adapter= ProductoAdapter(this,listaProductos)

            listas.adapter=adapter
        })

        listas.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this,DetallePorductoActivity::class.java)
            intent.putExtra("id",listaProductos[position].idproducto)
            startActivity(intent)

        }

    }
}