package com.example.myapplication

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bdsqlite

class ClienteActivity : AppCompatActivity() {
    lateinit var dbferr: bdsqlite
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.cliente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etCed=findViewById<EditText>(R.id.et_id)
        val etNom=findViewById<EditText>(R.id.et_name)
        val etTel=findViewById<EditText>(R.id.et_phone)
        val etDir=findViewById<EditText>(R.id.et_address)
        val btSave=findViewById<Button>(R.id.btn_save)
        val btSal=findViewById<Button>(R.id.btn_cancel)
        val tableLayout = findViewById<TableLayout>(R.id.tlTabla)

        dbferr=bdsqlite(this)
        fun clear(){
            etCed.text.clear()
            etNom.text.clear()
            etDir.text.clear()
            etTel.text.clear()
        }
        val headerRow = TableRow(this).apply {
            setBackgroundColor(resources.getColor(android.R.color.darker_gray))
            setPadding(8, 8, 8, 8)
        }


        fun showClients(){
            tableLayout.removeAllViews()

            val headerRow = TableRow(this).apply {
                setBackgroundColor(resources.getColor(android.R.color.darker_gray))
                setPadding(8, 8, 8, 8)
            }

            // Crear encabezados
            val headers = arrayOf("Nombre", "Cedula")
            headers.forEach { header ->
                val textView = TextView(this).apply {
                    text = header
                    setTypeface(null, Typeface.BOLD)
                    setPadding(8, 8, 8, 8)
                }
                headerRow.addView(textView)
            }
            val client=dbferr.getClient()


            tableLayout.addView(headerRow)

            for ((key, value) in client.valueSet()) {
                val tableRow = TableRow(this)
                val keyTextView = TextView(this).apply {
                    text = key
                    setPadding(8, 8, 8, 8)
                }
                val valueTextView = TextView(this).apply {
                    text = value.toString()
                    setPadding(8, 8, 8, 8)
                }
                tableRow.addView(keyTextView)
                tableRow.addView(valueTextView)
                tableLayout.addView(tableRow)
            }

        }
        showClients()
        btSave.setOnClickListener{

            if(etCed.text.isNotBlank() && etNom.text.isNotBlank() && etDir.text.isNotBlank() && etTel.text.isNotBlank()){
                val cedula:Int =etCed.text.toString().toInt()
                val nombre:String =etNom.text.toString()
                val telefono:String=etTel.text.toString()
                val direccion:String=etDir.text.toString()
                dbferr.addcamp( "Cliente",cedula,nombre,telefono,direccion,1.0,0)
                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show()
                clear()
                showClients()
            }else{
                Toast.makeText(this, "Hay campos sin llenar", Toast.LENGTH_LONG).show()
            }
        }

        btSal.setOnClickListener{
            finish()
        }
    }
}