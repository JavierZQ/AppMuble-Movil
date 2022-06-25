package pe.edu.ilp.appproyectmueble

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import pe.edu.ilp.appproyectmueble.controller.ImageController
import pe.edu.ilp.appproyectmueble.database.AppDatabse
import pe.edu.ilp.appproyectmueble.entity.Producto


class DetallePorductoActivity : AppCompatActivity() {
    private lateinit var database: AppDatabse
    private lateinit var producto:Producto
    private lateinit var productoLiveData: LiveData<Producto>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_porducto)
        database=AppDatabse.getDatabase(this)

        val idProducto=intent.getIntExtra("id",0)

        //imagen
        val imageUri=ImageController.getimagUri(this,idProducto.toLong())
        val image=findViewById<ImageView>(R.id.image_detalle)
        image.setImageURI(imageUri)

        productoLiveData=database.producto().get(idProducto)


        productoLiveData.observe(this, Observer {
            producto=it
            val nombre=findViewById<TextView>(R.id.nombre_producto)
            val precio = findViewById<TextView>(R.id.precio_producto)
            val detalle=findViewById<TextView>(R.id.detalle_producto)
            nombre.text=producto.nombre
            precio.text="$${producto.precio}"
            detalle.text=producto.descripcion


        })



    }
}