package pe.edu.ilp.appproyectmueble

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.ilp.appproyectmueble.controller.ImageController
import pe.edu.ilp.appproyectmueble.database.AppDatabse
import pe.edu.ilp.appproyectmueble.entity.Producto

class ProductoActivity : AppCompatActivity() {
    private lateinit var database:AppDatabse
    private lateinit var producto:Producto
    private lateinit var productoLiveData: LiveData<Producto>
    private val EDIT_ACTIVITY = 49
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

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
            ///buton editar
        val btnEditar=findViewById<ImageButton>(R.id.carrito)
        btnEditar.setOnClickListener{
            val intent=Intent(this,NuevoProductoActivity::class.java)
            intent.putExtra("producto",producto)
            startActivityForResult(intent,EDIT_ACTIVITY)
        }
        ///buton eliminar
        val btnEliminar=findViewById<ImageButton>(R.id.Eliminar)
        btnEliminar.setOnClickListener{
            productoLiveData.removeObservers(this)
            CoroutineScope(Dispatchers.IO).launch {
                database.producto().delete(producto)
                ImageController.deleteImage(this@ProductoActivity,producto.idproducto.toLong())
                this@ProductoActivity.finish()
            }


        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when{
            resultCode == EDIT_ACTIVITY && resultCode == Activity.RESULT_OK->{

                val image=findViewById<ImageView>(R.id.image_detalle)
                image.setImageURI(data!!.data)
            }
        }
        }
}