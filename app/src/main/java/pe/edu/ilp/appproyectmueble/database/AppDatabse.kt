package pe.edu.ilp.appproyectmueble.database

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.edu.ilp.appproyectmueble.dao.ProductoDao
import pe.edu.ilp.appproyectmueble.entity.Producto


@Database(entities = [Producto::class], version = 1)
abstract class AppDatabse:RoomDatabase() {
    abstract fun producto(): ProductoDao

    companion object{
        @Volatile
        private var INSTANCIA:AppDatabse?=null
        fun getDatabase(contexto: Context):AppDatabse{
            val tempInstancia= INSTANCIA
            if (tempInstancia!=null){
                return tempInstancia
            }
            synchronized(this){
                val instancia= Room.databaseBuilder(
                    contexto.applicationContext,
                    AppDatabse::class.java,
                    "app_mueble"
                ).build()
                INSTANCIA=instancia
                return instancia
            }
        }
    }
}