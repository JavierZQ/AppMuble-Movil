package pe.edu.ilp.appproyectmueble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginClienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_cliente)
        listaPro()
    }
    fun listaPro(){
        val btnIngresar=findViewById<android.view.View>(R.id.btnIngresar)
        btnIngresar.setOnClickListener {
            val intent=Intent(this,ListaActivity::class.java)
            startActivity(intent)
        }
    }
}