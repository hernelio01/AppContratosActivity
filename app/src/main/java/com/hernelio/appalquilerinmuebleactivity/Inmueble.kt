package com.hernelio.appalquilerinmuebleactivity

class Inmueble {
    var id:Int=0
    var nombre:String=""
    var tipo:String=""
    var direccion:String=""
    constructor(nombre:String,tipo:String,direccion:String){
        this.nombre=nombre
        this.tipo=tipo
        this.direccion=direccion
    }
    constructor()
}