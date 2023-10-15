package com.hernelio.appalquilerinmuebleactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.hernelio.appalquilerinmuebleactivity.databinding.ActivityInquilinoCrudBinding

class InquilinoCrudActivity : AppCompatActivity() {

    lateinit var ed1: EditText// Mostrar los resultados en   pantalla/Mapeo del campo de visualizaciòn
    lateinit var nombre: EditText
    lateinit var apellido: EditText
    lateinit var celular: EditText
    lateinit var correo: EditText
    lateinit var mensaje: TextView
    lateinit var codigo: EditText
    lateinit var cod: EditText



    private lateinit var binding: ActivityInquilinoCrudBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityInquilinoCrudBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_inquilino_crud)

        nombre=binding.Nombre
        apellido=binding.Apellido
        celular=binding.Celular
        correo=binding.Correo
        mensaje=binding.mensaje
        codigo=binding.Codigo
        cod=binding.Cod
        setContentView(binding.root)
    }
    fun crearDatos(view: View){
        if(nombre.text.toString().length>0 &&
            apellido.text.toString().length>0){
            var inquilino=   Inquilino(cod.text.toString(),nombre.text.toString(),apellido.text.toString(),celular.text.toString(),correo.text.toString())
            var Db= BaseDeDatos(this)
            mensaje.setText(Db.insertarDatos(inquilino))
            cod.text.clear()
            nombre.text.clear()
            apellido.text.clear()
            celular.text.clear()
            correo.text.clear()
            cod.setText("")
            cod.requestFocus()
            leerDatos(view)
        }else{
            mensaje.setText("¡¡¡¡¡INGRESAR DATOS DEL INQUILINO¡")
            cod.requestFocus()
        }




    }
    fun leerDatos(view:View){
        var db= BaseDeDatos(this)
        var datos=db.traerDatos()
        if (datos.isEmpty()) {
            mensaje.setText("NO HAY DATOS PARA MOSTRAR => ingrese datos¡")
            cod.requestFocus()
        } else {
            mensaje.text=""
            for(i in 0..datos.size-1){
                val inquilino =datos.get(i)
                mensaje.append("id =>"+inquilino.id +"Codigo:"+inquilino.codigo+"Nombre:"+inquilino.nombre +"Apellidos:"+inquilino.apellido +"Celular:"+inquilino.celular + "Correo" +inquilino.correo+ "\n" )
            }
            db.close()
        }


    }
    fun borrarDatos(view: View){
        // val codigoAEliminar = codigo.text.toString()
        var db=BaseDeDatos(this)
         // Verificar si el código está presente en la base de datos
        //val datos = db.traerDatos()
        //var codigoEncontrado = false
        //for (inquilino in datos) {
            // if (inquilino.codigo == codigoAEliminar) {
                //   codigoEncontrado = true
                //     break
                // }
            //}
        //if (codigoEncontrado) {
            // El código está presente en la base de datos, borrar el registro
            //db.borrar(codigoAEliminar)
            //mensaje.text = "Datos borrados exitosamente"
            //leerDatos(view)
            // } else {
            // El código no se encontró en la base de datos
            //mensaje.text = "Código no encontrado en la base de datos"
            // }

        // Limpiar el campo de texto y establecer el foco en él
        //codigo.setText("")
        //codigo.requestFocus()

        db.borrar(codigo.text.toString())
        codigo.setText("")
        codigo.requestFocus()
        mensaje.setText("Datos borrados exitosamente")
        leerDatos(view)




}
    fun actualizar(view: View){
        var db=BaseDeDatos(this)
        db.actualizar(codigo.text.toString(),cod.text.toString(),nombre.text.toString(),apellido.text.toString(),celular.text.toString(),correo.text.toString())
        codigo.setText("")
        leerDatos(view)
    }
}
