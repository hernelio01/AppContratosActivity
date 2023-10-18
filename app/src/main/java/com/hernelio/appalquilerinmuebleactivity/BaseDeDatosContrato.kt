package com.hernelio.appalquilerinmuebleactivity

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.view.View

val DbC="DbContrato"

val tablaC="contrato"
val idC="id"
val idinquilino="idinquilino"
val idinmueble="idinmueble"
val fechainicial="fechainicial"
val fechafinal="fechafinal"
val observaciones= "observaciones"
class BaseDeDatosContrato (context: Context) : SQLiteOpenHelper(context, DbC, null,1){
    override fun onCreate(p0: SQLiteDatabase?) {
        //TODO("Not yet implemented")
        val crearTablaContrato = """CREATE TABLE contrato (id INTEGER PRIMARY KEY AUTOINCREMENT, idinquilino INTEGER,  idinmueble INTEGER, fechainicial VARCHAR(300), fechafinal VARCHAR(300), observaciones VARCHAR(300), FOREIGN KEY(idinquilino) REFERENCES Inquilino(id), FOREIGN KEY(idinmueble) REFERENCES Inmueble(id))""".trimIndent()
        //val crearTablaContrato = """CREATE TABLE contrato (id INTEGER PRIMARY KEY AUTOINCREMENT, idinquilino INTEGER,  idinmueble INTEGER, fechainicial VARCHAR(300), fechafinal VARCHAR(300), observaciones VARCHAR(300), FOREIGN KEY(idinquilino) REFERENCES Inquilino(id), FOREIGN KEY(idinmueble) REFERENCES Inmueble(id))""".trimIndent()

        p0?.execSQL(crearTablaContrato)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //TODO("Not yet implemented")
    }

    fun insertarContrato(contrato: Contrato):String {
        val p0 = this.writableDatabase
        var contenedordeValores = ContentValues();
        contenedordeValores.put("idinquilino",contrato.idinquilino)
        contenedordeValores.put("idinmueble",contrato.idinmueble)
        contenedordeValores.put("fechainicial",contrato.fechainicial)
        contenedordeValores.put("fechafinal",contrato.fechafinal)
        contenedordeValores.put("observaciones",contrato.observaciones)
        var resultado = p0.insert("contrato",null,contenedordeValores)
        if(resultado==-1.toLong()){
            return "Fallas en la inserción"
        }else{
            return "Datos insertados OK"
        }
    }
    fun traerDatos():MutableList<Contrato>{
        var lista:MutableList<Contrato> = ArrayList()
        val db= this.readableDatabase
        val sql="select * from contrato"
        val resultado= db.rawQuery(sql,null)
        var i=0
        if(resultado.moveToFirst()){
            do{
                var contrato= Contrato()
                contrato.id= resultado.getString(0).toInt()
                contrato.idinquilino=resultado.getInt(resultado.getColumnIndex("idinquilino").toInt()).toInt()
                contrato.idinmueble=resultado.getInt(resultado.getColumnIndex("idinmueble").toInt()).toInt()
                contrato.fechafinal=resultado.getString(resultado.getColumnIndex("fechafinal").toInt())
                contrato.fechainicial=resultado.getString(resultado.getColumnIndex("fechainicial").toInt())
                contrato.observaciones=resultado.getString(resultado.getColumnIndex("observaciones").toInt())
                lista.add(contrato)
            }while(resultado.moveToNext())
        }
        resultado.close()
        db.close()
        return lista
    }
    fun borrar(codigo:String){
        if(codigo.length>0){
            val db=writableDatabase
            db.delete("contrato","id=?", arrayOf(codigo))
            db.close()
        }

    }
    fun actualizar(id:String,idinquilino:String,idinmueble:String,fechaIncial:String,fechaFinal:String,observaciones:String){
        val db=this.writableDatabase
        val contenedor= ContentValues()
        contenedor.put("idinquilino",idinquilino)
        contenedor.put("idinmueble",idinmueble)
        contenedor.put("fechainicial",fechaIncial)
        contenedor.put("fechafinal",fechaFinal)
        contenedor.put("observaciones",fechaFinal)
        db.update("contrato",contenedor,"id=?", arrayOf(id))
    }
}