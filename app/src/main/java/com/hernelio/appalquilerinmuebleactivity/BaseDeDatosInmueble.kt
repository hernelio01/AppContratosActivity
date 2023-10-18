package com.hernelio.appalquilerinmuebleactivity

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


val DbI="DbInmueble"

val tablaI="inmueble"
val idI="id"
val nombreI="nombre"
val tipo="tipo"
val direccion="direccion"

class BaseDeDatosInmueble (context: Context) : SQLiteOpenHelper(context, DbI, null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        //TODO("Not yet implemented")
        val crearTabla="CREATE TABLE inmueble(id INTEGER PRIMARY KEY AUTOINCREMENT,"+ "nombre VARCHAR(300), tipo VARCHAR(300), direccion VARCHAR(300))"
        p0?.execSQL(crearTabla)
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //TODO("Not yet implemented")
    }
    fun insertarDatos(inmueble: Inmueble):String{
        val p0 = this.writableDatabase
        var contenedordeValores = ContentValues();
        contenedordeValores.put("nombre",inmueble.nombre)
        contenedordeValores.put("tipo",inmueble.tipo)
        contenedordeValores.put("direccion",inmueble.direccion)
        var resultado = p0.insert("inmueble",null,contenedordeValores)
        if(resultado==-1.toLong()){
            return "Fallas en la inserción"
        }else{
            return "Datos insertados OK"
        }
    }
    fun traerDatos():MutableList<Inmueble>{
        var lista:MutableList<Inmueble> = ArrayList()
        val db= this.readableDatabase
        val sql="select * from inmueble"
        val resultado= db.rawQuery(sql,null)
        var i=0
        if(resultado.moveToFirst()){
            do{
                var inmueble= Inmueble()
                inmueble.id= resultado.getString(0).toInt()
                inmueble.nombre=resultado.getString(resultado.getColumnIndex("nombre").toInt())
                inmueble.tipo=resultado.getString(resultado.getColumnIndex("tipo").toInt())
                inmueble.direccion=resultado.getString(resultado.getColumnIndex("direccion").toInt())
                lista.add(inmueble)
            }while(resultado.moveToNext())
        }
        resultado.close()
        db.close()
        return lista
    }

    @SuppressLint("Range")
    fun validarContrato(): List<Int> {
        val contratosIds = mutableListOf<Int>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT id FROM contrato", null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(idC))
            contratosIds.add(id)
        }

        cursor.close()
        db.close()
        return contratosIds
    }
    fun borrar(codigo:String){
        if(codigo.length>0){
            val db=writableDatabase
            db.delete("inmueble","id=?", arrayOf(codigo))
            db.close()
        }

    }
    fun actualizar(id:String,nombreI:String,tipo:String,direccion:String){
        val db=this.writableDatabase
        val contenedor= ContentValues()
        contenedor.put("nombre",nombreI)
        contenedor.put("tipo",tipo)
        contenedor.put("direccion",direccion)
        db.update("inmueble",contenedor,"id=?", arrayOf(id))
    }
    @SuppressLint("Range")
    fun getAllInmuebles(): List<Int> {
        val inmuebleIds = mutableListOf<Int>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT id FROM inmueble", null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(idI))
            inmuebleIds.add(id)
        }

        cursor.close()
        db.close()
        return inmuebleIds
    }
}