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

class Producto : AppCompatActivity() {
    lateinit var dbferr: bdsqlite
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_producto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etId=findViewById<EditText>(R.id.et_id)
        val etName=findViewById<EditText>(R.id.et_name)
        val etValue=findViewById<EditText>(R.id.et_valor)
        val etCant=findViewById<EditText>(R.id.et_cantidad)
        val btSave=findViewById<Button>(R.id.btn_save)
        val btSal=findViewById<Button>(R.id.btn_cancel)
        val tableLayout=findViewById<TableLayout>(R.id.tlTabla1)
        dbferr=bdsqlite(this)
        fun clear(){
            etId.text.clear()
            etName.text.clear()
            etCant.text.clear()
            etValue.text.clear()
        }
        etId.isEnabled=false



        fun showProds(){
            tableLayout.removeAllViews()
            val prods=dbferr.getProds()

            val headerRow = TableRow(this).apply {
                setBackgroundColor(resources.getColor(android.R.color.darker_gray))
                setPadding(8, 8, 8, 8)
            }

            // Crear encabezados
            val headers = arrayOf("ID", "Producto","Valor und","Cantidad")
            headers.forEach { header ->
                val textView = TextView(this).apply {
                    text = header
                    setTypeface(null, Typeface.BOLD)
                    setPadding(8, 8, 8, 8)
                }
                headerRow.addView(textView)
            }
            tableLayout.addView(headerRow)

            for (prod in prods) {
                val tableRow = TableRow(this)
                val idTextView = TextView(this).apply {
                    text = prod.id.toString()
                    setPadding(8, 8, 8, 8)
                }
                val nameTextView = TextView(this).apply {
                    text = prod.nombre
                    setPadding(8, 8, 8, 8)
                }
                val valTextView = TextView(this).apply {
                    text = prod.valor.toString()
                    setPadding(8, 8, 8, 8)
                }
                val cantTextView = TextView(this).apply {
                    text = prod.cant.toString()
                    setPadding(8, 8, 8, 8)
                }

                tableRow.addView(idTextView)
                tableRow.addView(nameTextView)
                tableRow.addView(valTextView)
                tableRow.addView(cantTextView)
                tableLayout.addView(tableRow)
            }

        }



        showProds()
        btSave.setOnClickListener{

            if( etName.text.isNotBlank() && etCant.text.isNotBlank() && etValue.text.isNotBlank()){
//                val IDP:Int =etId.text.toString().toInt()
                val nombre:String =etName.text.toString()
                val valor:Double=etValue.text.toString().toDouble()
                val cant:Int=etCant.text.toString().toInt()
                dbferr.addcamp( "Producto",0,nombre,"","",valor,cant)
                Toast.makeText(this, "Producto guardado", Toast.LENGTH_SHORT).show()
                clear()
                showProds()

            }else{
                Toast.makeText(this, "El producto no fue guardado", Toast.LENGTH_LONG).show()
            }
        }

        btSal.setOnClickListener{
            finish()
        }
    }
}