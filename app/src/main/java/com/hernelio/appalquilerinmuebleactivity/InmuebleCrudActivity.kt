package com.hernelio.appalquilerinmuebleactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

import com.hernelio.appalquilerinmuebleactivity.databinding.ActivityInmuebleCrudBinding



class InmuebleCrudActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInmuebleCrudBinding
    lateinit var ed1: EditText// Mostrar los resultados en   pantalla/Mapeo del campo de visualizaciòn
    lateinit var nombreI: EditText
    lateinit var tipo: EditText
    lateinit var direccion: EditText
    lateinit var mensaje: TextView
    lateinit var codigo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_inmueble_crud)
        var binding = ActivityInmuebleCrudBinding.inflate(layoutInflater)
        nombreI=binding.NombreInmueble
        mensaje=binding.mensaje
        codigo=binding.Codigo
        tipo=binding.TipoInmueble
        direccion=binding.DireccionInmueble

        setContentView(binding.root)
    }
    fun crearDatosInmueble(view: View){
        if(nombreI.text.toString().length>0 &&
            tipo.text.toString().length>0){
            var inmueble= Inmueble(nombreI.text.toString(),tipo.text.toString(),direccion.text.toString())
            var Db= BaseDeDatosInmueble(this)
            mensaje.setText(Db.insertarDatos(inmueble))

            nombreI.text.clear()
            tipo.text.clear()
            direccion.text.clear()

            nombreI.setText("")
            nombreI.requestFocus()
            leerDatosInmueble(view)
        }else{
            mensaje.setText("¡¡¡¡¡INGRESAR DATOS DEL INMUEBLE¡")
            nombreI.requestFocus()
        }
    }
    fun leerDatosInmueble(view:View){
        var db= BaseDeDatosInmueble(this)
        var datos=db.traerDatos()
        if (datos.isEmpty()) {
            mensaje.setText("NO HAY DATOS PARA MOSTRAR => ingrese datos¡")
            nombreI.requestFocus()
        } else {
            mensaje.text=""
            for(i in 0..datos.size-1){
                val inmueble =datos.get(i)
                mensaje.append("id =>"+inmueble.id +"Nombre:"+inmueble.nombre +"Tipo:"+inmueble.tipo +"Direccion:"+inmueble.direccion + "\n" )
            }
            db.close()
        }


    }
    fun borrarDatosInmueble(view: View){

        var db=BaseDeDatosInmueble(this)

        db.borrar(codigo.text.toString())
        codigo.setText("")
        codigo.requestFocus()
        mensaje.setText("Datos borrados exitosamente")
        leerDatosInmueble(view)
    }
    fun actualizarInmueble(view: View){
        var db=BaseDeDatosInmueble(this)
        db.actualizar(codigo.text.toString(),nombreI.text.toString(),tipo.text.toString(),direccion.text.toString())
        codigo.setText("")
        leerDatosInmueble(view)
    }
}