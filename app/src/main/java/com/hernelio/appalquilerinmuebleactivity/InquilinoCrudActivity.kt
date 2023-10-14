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
    lateinit var apellidos: EditText
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
        apellidos=binding.Apellido
        celular=binding.Celular
        correo=binding.Correo
        mensaje=binding.mensaje
        codigo=binding.Codigo
        cod=binding.Cod
        setContentView(binding.root)
    }
    fun crearDatos(view: View){
        if(nombre.text.toString().length>0 &&
            apellidos.text.toString().length>0){
            var inquilino=   Inquilino(cod.text.toString(),nombre.text.toString(),apellidos.text.toString(),celular.text.toString(),correo.text.toString())
            var Db= BaseDeDatos(this)
            mensaje.setText(Db.insertarDatos(inquilino))
            cod.text.clear()
            nombre.text.clear()
            apellidos.text.clear()
            celular.text.clear()
            correo.text.clear()
            cod.setText("")
            cod.requestFocus()
        }
        leerDatos(view)



    }
    fun leerDatos(view:View){
        var db= BaseDeDatos(this)
        var datos=db.traerDatos()
        mensaje.text=""
        for(i in 0..datos.size-1){
            val inquilino =datos.get(i)
            mensaje.append("id =>"+inquilino.id +"Codigo:"+inquilino.codigo+"Nombre:"+inquilino.nombre +"Apellidos:"+inquilino.apellido +"Celular:"+inquilino.celular + "Correo" +inquilino.correo+ "\n" )
        }
        db.close()
    }
    fun borrarDatos(view: View){
        var db=BaseDeDatos(this)
        db.borrar(codigo.text.toString())
        codigo.setText("")
        codigo.requestFocus()
        mensaje.setText("Datos borrados exitosamente")
        leerDatos(view)

    }
    fun actualizar(view: View){
        var db=BaseDeDatos(this)
        db.actualizar(codigo.text.toString(),cod.text.toString(),nombre.text.toString(),apellidos.text.toString(),celular.text.toString(),correo.text.toString())
    }
}