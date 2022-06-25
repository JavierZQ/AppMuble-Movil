package pe.edu.ilp.appproyectmueble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast

class LoginAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_admin)

            incio()
    }
    fun incio(){
        val btnInicio=findViewById<android.view.View>(R.id.btnIngresar)
        btnInicio.setOnClickListener {
            val intent = Intent(this,ListaProductoActivity::class.java)
            startActivity(intent)
        }
    }
}