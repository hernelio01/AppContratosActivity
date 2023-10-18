package com.hernelio.appalquilerinmuebleactivity

class Contrato {
    var id:Int=0
    var idinquilino:Int=0
    var idinmueble:Int=0
    var fechainicial:String=""
    var fechafinal:String=""
    var observaciones:String=""
    constructor(idinquilino:Int,idinmueble:Int,fechainicial:String,fechafinal:String,observaciones:String){
        this.idinquilino=idinquilino
        this.idinmueble=idinmueble
        this.fechainicial=fechainicial
        this.fechafinal=fechafinal
        this.observaciones=observaciones
    }
    constructor()
}