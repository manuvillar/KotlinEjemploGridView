package com.example.kotlinejemplogridview

import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Variables para el GridView y el adaptador
    private lateinit var gridView: GridView
    private lateinit var adapter: GridViewAdapter
    private lateinit var itemList: ArrayList<GridViewModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar el GridView desde el layout
        gridView = findViewById(R.id.gridView)

        // Crear la lista de elementos con datos de ejemplo
        itemList = ArrayList()

        // Agregar elementos a la lista
        // Asegúrate de tener estas imágenes en la carpeta res/drawable
        itemList.add(GridViewModal("Manzana", R.drawable.manzana))
        itemList.add(GridViewModal("Plátano", R.drawable.platano))
        itemList.add(GridViewModal("Cereza", R.drawable.cereza))
        itemList.add(GridViewModal("Pera", R.drawable.pera))
        itemList.add(GridViewModal("Fresa", R.drawable.fresa))
        itemList.add(GridViewModal("Uva", R.drawable.uva))
        itemList.add(GridViewModal("Calabaza", R.drawable.calabaza))
        itemList.add(GridViewModal("Limón", R.drawable.limon))
        itemList.add(GridViewModal("Piña", R.drawable.pina))

        // Crear el adaptador con el contexto y la lista de elementos
        adapter = GridViewAdapter(this, itemList)

        // Asignar el adaptador al GridView
        gridView.adapter = adapter

        // Configurar el evento de clic en los elementos del GridView
        // position: posición del elemento clickeado (comienza en 0)
        gridView.setOnItemClickListener { parent, view, position, id ->
            // Obtener el elemento seleccionado
            val selectedItem = itemList[position]

            // Mostrar un Toast con el nombre del elemento seleccionado
            Toast.makeText(
                this,
                "Seleccionaste: ${selectedItem.itemName}",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Configurar el evento de clic largo (opcional)
        gridView.setOnItemLongClickListener { parent, view, position, id ->
            val selectedItem = itemList[position]

            Toast.makeText(
                this,
                "Clic largo en: ${selectedItem.itemName}",
                Toast.LENGTH_LONG
            ).show()

            // Devolver true para indicar que el evento fue manejado
            true
        }
    }
}
