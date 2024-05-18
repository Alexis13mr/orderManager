import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class bdsqlite(context: Context) :SQLiteOpenHelper(context,"Ferreteria.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val crear="CREATE TABLE Cliente (_cedula INTEGER PRIMARY KEY,nombre TEXT,direccion TEXT, telefono TEXT)"
        val prod="CREATE TABLE Producto (_IDProducto INTEGER PRIMARY KEY AUTOINCREMENT,NombreProd TEXT,Valor REAL,Cantidad INTEGER)"
        db!!.execSQL(crear)
        db!!.execSQL(prod)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val delete= "DROP TABLE IF EXISTS Cliente"
        val delete1= "DROP TABLE IF EXISTS Cliente"

        db!!.execSQL(delete)
        db!!.execSQL(delete1)
        onCreate(db)
    }

    fun addcamp (tab:String,ced:Int,nom:String,dir:String,tel:String,valu:Double,cant:Int){
        val datos= ContentValues()


        if (tab=="Cliente"){
            datos.put("_cedula",ced)
            datos.put("nombre",nom)
            datos.put("direccion",dir)
            datos.put("telefono",tel)
        }else if (tab=="Producto"){
            datos.put("NombreProd",nom)
            datos.put("Valor",valu)
            datos.put("Cantidad",cant)
        }



        val db= this.writableDatabase
        db.insert(tab,null,datos)
        db.close()

    }

    fun getClient():ContentValues {
        var users=ContentValues()
        val db=this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM Cliente", null)

        if (cursor.moveToFirst()){
            do {
                val id=cursor.getInt(0)
                val nameCli=cursor.getString(1)
                users.put(nameCli,id)
            }while (cursor.moveToNext())
        }
        return users
    }

    fun getProds():List<Producto>{
        val prods = mutableListOf<Producto>()

        val db=this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM Producto", null)

        if (cursor.moveToFirst()){
            do {
                val id=cursor.getInt(0)
                val name=cursor.getString(1)
                val valu=cursor.getDouble(2)
                val cantidad=cursor.getInt(3)
                prods.add(Producto(id,name,valu,cantidad))
            }while (cursor.moveToNext())
        }
        return prods
    }

}

data class Producto(
    val id:Int,
    val nombre:String,
    val valor:Double,
    val cant: Int
)

