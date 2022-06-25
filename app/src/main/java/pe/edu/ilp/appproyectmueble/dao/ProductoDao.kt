package pe.edu.ilp.appproyectmueble.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pe.edu.ilp.appproyectmueble.entity.Producto

@Dao
interface ProductoDao {
    @Query("SELECT * FROM producto")
    fun getAll(): LiveData<List<Producto>>

    @Query("SELECT * FROM producto WHERE idproducto=:id")
    fun get(id:Int): LiveData<Producto>

    @Insert
    fun insertAll(vararg producto: Producto):List<Long>

    @Update
    fun update(producto: Producto)

    @Delete
    fun delete(producto: Producto)
}