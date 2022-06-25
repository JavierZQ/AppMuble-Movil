package pe.edu.ilp.appproyectmueble.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "producto")
class Producto (
    val nombre:String,
    val precio:Double,
    val descripcion:String,
    val imagen:Int,
    @PrimaryKey(autoGenerate = true)
    var idproducto:Int=0

):Serializable