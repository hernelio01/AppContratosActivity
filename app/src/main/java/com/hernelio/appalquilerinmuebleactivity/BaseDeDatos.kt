package com.hernelio.appalquilerinmuebleactivity

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


val Db="DbInquilino"
val tabla="inquilino"
val id="id"
val codigo="codigo"
val nombre="nombre"
val apellido="apellido"
val celular="celular"
val correo="correo"

class BaseDeDatos (context: Context) : SQLiteOpenHelper(context, Db, null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
       // TODO("Not yet implemented")
        val crearTabla="CREATE TABLE inquilino(id INTEGER PRIMARY KEY AUTOINCREMENT,"+ "codigo VARCHAR(10),nombre VARCHAR(300), apellido VARCHAR(50), celular VARCHAR(20), correo VARCHAR(20))"
        p0?.execSQL(crearTabla)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //TODO("Not yet implemented")
    }
    fun insertarDatos(inquilino: Inquilino):String{
        val p0 = this.writableDatabase
        var contenedordeValores = ContentValues();
        contenedordeValores.put("codigo",inquilino.codigo)
        contenedordeValores.put("nombre",inquilino.nombre)
        contenedordeValores.put("apellido",inquilino.apellido)
        contenedordeValores.put("celular",inquilino.celular)
        contenedordeValores.put("correo",inquilino.correo)
        var resultado = p0.insert("inquilino",null,contenedordeValores)
        if(resultado==-1.toLong()){
            return "Fallas en la inserción"
        }else{
            return "Datos insertados OK"
        }
    }
    fun traerDatos():MutableList<Inquilino>{
        var lista:MutableList<Inquilino> = ArrayList()
        val db= this.readableDatabase
        val sql="select * from inquilino"
        val resultado= db.rawQuery(sql,null)
        var i=0
        if(resultado.moveToFirst()){
            do{
                var inquilino= Inquilino()
                inquilino.id= resultado.getString(0).toInt()
                inquilino.codigo=resultado.getString(resultado.getColumnIndex("codigo").toInt())
                inquilino.nombre=resultado.getString(resultado.getColumnIndex("nombre").toInt())
                inquilino.apellido=resultado.getString(resultado.getColumnIndex("apellido").toInt())
                inquilino.celular=resultado.getString(resultado.getColumnIndex("celular").toInt())
                inquilino.correo=resultado.getString(resultado.getColumnIndex("correo").toInt())
                lista.add(inquilino)
            }while(resultado.moveToNext())
        }
        resultado.close()
        db.close()
        return lista
    }
    fun borrar(codigo:String){
        if(codigo.length>0){
            val db=writableDatabase
            db.delete("inquilino","id=?", arrayOf(codigo))
            db.close()
        }

    }
    fun actualizar(id:String,cod:String,nombre:String,apellido:String,celular:String,correo:String){
        val db=this.writableDatabase
        val contenedor= ContentValues()
        contenedor.put("codigo",cod)
        contenedor.put("nombre",nombre)
        contenedor.put("apellido",apellido)
        contenedor.put("celular",celular)
        contenedor.put("correo",correo)
        db.update("inquilino",contenedor,"id=?", arrayOf(id))
    }


}