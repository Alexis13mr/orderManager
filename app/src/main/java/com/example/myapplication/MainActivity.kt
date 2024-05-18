package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btAddCli=findViewById<Button>(R.id.btn_add)
        val btAddItm=findViewById<Button>(R.id.btn_newItem)
        val btAddOrd=findViewById<Button>(R.id.btn_newOrder)
        val btOrder=findViewById<Button>(R.id.btn_orders)

        btAddCli.setOnClickListener{
            val int= Intent(this,ClienteActivity::class.java)
            startActivity(int)
        }
        btAddItm.setOnClickListener{
            val int= Intent(this,producto::class.java)
            startActivity(int)
        }
        btAddOrd.setOnClickListener{
            val int= Intent(this,Pedido::class.java)
            startActivity(int)
        }
        btOrder.setOnClickListener{
            val int= Intent(this,Vista::class.java)
            startActivity(int)
        }
    }
}