package pe.edu.ilp.appproyectmueble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        admin()
        cliente()

    }
    fun admin(){
        val btnadmin=findViewById<android.view.View>(R.id.btnAdmin)
        btnadmin.setOnClickListener {
            val intent=Intent(this,LoginAdminActivity::class.java)
            startActivity(intent)
        }
    }

    fun cliente(){
        val btncliente=findViewById<android.view.View>(R.id.btnCliente)
        btncliente.setOnClickListener {
            val intent=Intent(this,LoginClienteActivity::class.java)
            startActivity(intent)
        }
    }
}