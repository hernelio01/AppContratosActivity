package com.hernelio.appalquilerinmuebleactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hernelio.appalquilerinmuebleactivity.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding.btnOso.setOnClickListener {

            val name =  "Oso" //etName.text.toString()
            // if (name.isNotEmpty()){
            val intent = Intent(this, InquilinoCrudActivity::class.java)
           // intent.putExtra("EXTRA_NAME", name)
            startActivity(intent)
            // }



        }
        binding.btnPerro.setOnClickListener {

            val name =  "Perro" //etName.text.toString()
            // if (name.isNotEmpty()){
            val intent = Intent(this, InmuebleCrudActivity::class.java)
            // intent.putExtra("EXTRA_NAME", name)
            startActivity(intent)
            // }



        }
    }
}