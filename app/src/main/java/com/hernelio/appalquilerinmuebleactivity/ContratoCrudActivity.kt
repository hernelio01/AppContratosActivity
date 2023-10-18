package com.hernelio.appalquilerinmuebleactivity

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.hernelio.appalquilerinmuebleactivity.databinding.ActivityContratoCrudBinding


class ContratoCrudActivity : AppCompatActivity() {

    private lateinit var binding:ActivityContratoCrudBinding
    lateinit var ed1: EditText// Mostrar los resultados en   pantalla/Mapeo del campo de visualizaciòn
    lateinit var fechainicial: EditText
    lateinit var fechafinal: EditText
    lateinit var observaciones: EditText
    lateinit var mensaje: TextView
    lateinit var codigo: EditText
    private lateinit var spinnerInquilino: Spinner
    private lateinit var spinnerInmueble: Spinner
    private val idInquilinoList: MutableList<Int> = mutableListOf()
    private val idInmuebleList: MutableList<Int> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityContratoCrudBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_contrato_crud)

        fechainicial=binding.FechaInicial
        fechafinal=binding.FechaFinal
        codigo=binding.Codigo
        mensaje=binding.mensaje
        observaciones=binding.Observacioens
        spinnerInquilino=binding.spinnerInquilino
        spinnerInmueble=binding.spinnerInmueble

        // Crear adaptadores para los Spinners
        val inquilinoAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, idInquilinoList)
        val inmuebleAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, idInmuebleList)

        // Establecer el estilo de los adaptadores
        inquilinoAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        inmuebleAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        // Asignar adaptadores a los Spinners
        spinnerInquilino.adapter = inquilinoAdapter
        spinnerInmueble.adapter = inmuebleAdapter

        // Llenar las listas de IDs de inquilinos e inmuebles desde la base de datos
       val dbHelper = BaseDeDatos(this)
        val dbHelperI = BaseDeDatosInmueble(this)

        //val inquilinoRepository = InquilinoRepository(this)
        //val inmuebleRepository = InmuebleRepository(this)

        // Llenar idInquilinoList desde la base de datos
        val inquilinos = dbHelper.getAllInquilinos()
        idInquilinoList.addAll(inquilinos)

        val inmuebles = dbHelperI.getAllInmuebles()
        idInmuebleList.addAll(inmuebles)

        // Llenar idInmuebleList desde la base de datos
        //val inmuebles = inmuebleRepository.getAllInmuebles()
        //idInmuebleList.addAll(inmuebles.map { it.id })

        // Notificar a los adaptadores sobre el cambio de datos
        inquilinoAdapter.notifyDataSetChanged()
        inmuebleAdapter.notifyDataSetChanged()
        setContentView(binding.root)


    }

    fun crearDatosContrato(view: View) {
        //val idInquilino = (spinnerInquilino.selectedItem as Inquilino).id
        //val idInmueble = (spinnerInmueble.selectedItem as Inmueble).id

       // val fechaInicial = fechainicial.text.toString()
        //val fechaFinal = fechafinal.text.toString()
        //val observaciones = observaciones.text.toString()
        //Toast.makeText(this, "¡Por favor, ingrese las fechas!", Toast.LENGTH_SHORT).show()

        if(fechainicial.text.toString().length>0 && fechafinal.text.toString().length>0) {
           val contrato = Contrato(spinnerInquilino.selectedItem.toString().toInt(), spinnerInmueble.selectedItem.toString().toInt(), fechafinal.text.toString(), fechafinal.text.toString(), observaciones.text.toString())
            //val contrato = Contrato(idInquilino, idInmueble, fechaInicial, fechaFinal, observaciones )

            val dbHelperC = BaseDeDatosContrato(this)
            mensaje.setText(dbHelperC.insertarContrato(contrato))
            Toast.makeText(this, "¡Datos almacenados!", Toast.LENGTH_SHORT).show()
           // mensaje.setText(fechaInicial)
            //mensaje.setText(spinnerInmueble.selectedItem.toString())
            fechainicial.text.clear()
            fechafinal.text.clear()
            observaciones.text.clear()

            fechainicial.setText("")
            fechainicial.requestFocus()

            leerDatosContrato(view)


        } else{
            Toast.makeText(this, "¡Por favor, ingrese los Datos!", Toast.LENGTH_SHORT).show()
        }

    }

    fun leerDatosContrato(view:View){
        var db= BaseDeDatosContrato(this)
        var datos=db.traerDatos()


        if (datos.isEmpty()) {
            Toast.makeText(this, "¡No hay Datos para mostrar!", Toast.LENGTH_SHORT).show()


        } else {
            mensaje.text=""
            for(i in 0..datos.size-1){
                val contrato =datos.get(i)
                mensaje.append("id=> "+contrato.id +"--inquilino=> "+contrato.idinquilino+"--idInmueble=> "+contrato.idinmueble+"--fechainicial=> "+contrato.fechainicial+"--fechafinal=> "+contrato.fechafinal+"--observaciones=> "+contrato.observaciones+ "\n" )
            }
            db.close()
        }
    }
    fun borrarDatosContrato(view: View){

        var db=BaseDeDatosContrato(this)

        if (codigo.text.toString().length>0){
            db.borrar(codigo.text.toString())
            codigo.setText("")
            codigo.requestFocus()
            Toast.makeText(this, "¡DATOS BORRADOS EXITOSAMENTE", Toast.LENGTH_SHORT).show()
            leerDatosContrato(view)
        }else{
            Toast.makeText(this, "¡Ingrese codigo", Toast.LENGTH_SHORT).show()
            codigo.requestFocus()
        }

    }
    fun actualizarContrato(view: View){
        var db=BaseDeDatosContrato(this)
        if (codigo.text.toString().length>0){
            db.actualizar(codigo.text.toString(),spinnerInquilino.selectedItem.toString(), spinnerInmueble.selectedItem.toString(), fechafinal.text.toString(), fechafinal.text.toString(), observaciones.text.toString())
            Toast.makeText(this, "¡DATOS ACTUALIZADOS", Toast.LENGTH_SHORT).show()
            codigo.setText("")
            codigo.requestFocus()
            leerDatosContrato(view)

        }
        else{
            Toast.makeText(this, "¡Ingrese codigo", Toast.LENGTH_SHORT).show()
            codigo.requestFocus()
        }

    }

}