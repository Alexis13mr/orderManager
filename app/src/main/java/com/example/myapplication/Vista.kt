package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bdsqlite

class Vista : AppCompatActivity() {
    lateinit var dbferr: bdsqlite
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vista)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tvLClie=findViewById<TextView>(R.id.tvIdCli)
        val btnUpdat=findViewById<Button>(R.id.btnUpdate)
        dbferr=bdsqlite(this)

        btnUpdat.setOnClickListener {
            val client=dbferr.getClient().toString()
            tvLClie.text=client

        }
    }
}