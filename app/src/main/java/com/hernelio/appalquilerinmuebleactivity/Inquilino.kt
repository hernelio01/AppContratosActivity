package com.hernelio.appalquilerinmuebleactivity

class Inquilino {
    var id:Int=0
    var codigo:String=""
    var nombre:String=""
    var apellido:String=""
    var celular:String=""
    var correo:String=""
    constructor(codigo:String, nombre:String,apellido:String,celular:String,correo:String){
        this.codigo=codigo
        this.nombre=nombre
        this.apellido=apellido
        this.celular=celular
        this.correo=correo
    }
    constructor()
}



