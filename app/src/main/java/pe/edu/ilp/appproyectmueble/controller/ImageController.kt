package pe.edu.ilp.appproyectmueble.controller

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import pe.edu.ilp.appproyectmueble.ListaProductoActivity
import java.io.File

object ImageController {

    fun ImagePhotoFronGallery(activity: Activity,codigo:Int){
         var intent=Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        activity.startActivityForResult(intent,codigo)

    }
    //guardra
    fun saveImage(context:Context, id : Long, uri: Uri){
        val file=File(context.filesDir,id.toString())
        val bytes= context.contentResolver.openInputStream(uri)?.readBytes()!!
        file.writeBytes(bytes)
    }
///opctene imagen
    fun getimagUri(context: Context,id: Long):Uri{
        val file=File(context.filesDir,id.toString())

        return if(file.exists()) Uri.fromFile(file)
        else Uri.parse("android.resource://pe.edu.ilp.appproyectmueble/drawable/noimagen")
    }

    fun deleteImage(context: Context,id: Long){
        val file=File(context.filesDir,id.toString())
        file.delete()
    }

}