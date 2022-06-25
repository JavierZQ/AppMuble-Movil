package pe.edu.ilp.appproyectmueble

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.ilp.appproyectmueble.controller.ImageController
import pe.edu.ilp.appproyectmueble.database.AppDatabse
import pe.edu.ilp.appproyectmueble.entity.Producto

class NuevoProductoActivity : AppCompatActivity() {
    private val SELECT_ACTIVITY = 50
    private var imagenUri:Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_producto)

        var idProducto:Int?=null

        if (intent.hasExtra("producto")){
            val producto=intent.extras?.getSerializable("producto")as Producto

            val nombre= findViewById<EditText>(R.id.editNombre)
            val precio =findViewById<EditText>(R.id.editPrecio)
            val descripcion=findViewById<EditText>(R.id.editDescripcion)
            nombre.setText(producto.nombre)
            precio.setText(producto.precio.toString())
            descripcion.setText(producto.descripcion)
            idProducto=producto.idproducto

            val imageUri=ImageController.getimagUri(this,idProducto.toLong())
            val imagen=findViewById<ImageView>(R.id.imageSelect_iv)
            imagen.setImageURI(imageUri)

        }
        val database= AppDatabse.getDatabase(this)

        val bnt=findViewById<android.view.View>(R.id.bntGuardar)

        //guardar
        bnt.setOnClickListener {
            val nombre= findViewById<EditText>(R.id.editNombre).text.toString()
            val precio =findViewById<EditText>(R.id.editPrecio).text.toString().toDouble()
            val descripcion=findViewById<EditText>(R.id.editDescripcion).text.toString()

            val producto= Producto(nombre,precio,descripcion,R.drawable.ic_launcher_background)


            if (idProducto!=null){
                CoroutineScope(Dispatchers.IO).launch {
                    producto.idproducto=idProducto
                    database.producto().update(producto)
                    //imagen
                    imagenUri?.let {
                        val intent=Intent()
                        intent.data=it
                        setResult(Activity.RESULT_OK,intent)
                        ImageController.saveImage(this@NuevoProductoActivity,idProducto.toLong(),it)
                    }
                    this@NuevoProductoActivity.finish()
                }
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    val id = database.producto().insertAll(producto)[0]
                    //imagen
                    imagenUri?.let {
                        ImageController.saveImage(this@NuevoProductoActivity,id,it)
                    }
                    this@NuevoProductoActivity.finish()
                }
            }

        }

///select imagen
        val imangeniv=findViewById<ImageView>(R.id.imageSelect_iv)
        imangeniv.setOnClickListener {
            ImageController.ImagePhotoFronGallery(this, SELECT_ACTIVITY)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when{
            requestCode==SELECT_ACTIVITY && resultCode==Activity.RESULT_OK ->{
                    imagenUri = data!!.data
                val ima=findViewById<ImageView>(R.id.imageSelect_iv)
                ima.setImageURI(imagenUri)
            }

        }
    }
}