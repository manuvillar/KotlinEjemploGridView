package com.example.kotlinejemplogridview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

// Adaptador personalizado para el GridView
// BaseAdapter es la clase base para crear adaptadores personalizados
class GridViewAdapter(
    private val context: Context,              // Contexto de la aplicación
    private val itemList: List<GridViewModal>  // Lista de elementos a mostrar
) : BaseAdapter() {

    // Inflater para convertir XML en Views
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    // Devuelve el número total de elementos en la lista
    override fun getCount(): Int {
        return itemList.size
    }

    // Devuelve el elemento en la posición especificada
    override fun getItem(position: Int): Any {
        return itemList[position]
    }

    // Devuelve el ID del elemento en la posición especificada
    // En este caso, usamos la posición como ID
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Método principal que crea y configura la vista para cada elemento
    // convertView: vista reciclada para mejorar el rendimiento
    // position: posición del elemento en la lista
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // ViewHolder pattern para mejorar el rendimiento
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            // Si no hay vista reciclada, inflar una nueva desde el XML
            view = inflater.inflate(R.layout.grid_item, parent, false)

            // Crear un ViewHolder para guardar las referencias de las vistas
            viewHolder = ViewHolder(
                view.findViewById(R.id.itemImage),
                view.findViewById(R.id.itemName)
            )

            // Guardar el ViewHolder en la vista para reutilizarlo
            view.tag = viewHolder
        } else {
            // Si hay vista reciclada, reutilizarla
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        // Obtener el elemento actual de la lista
        val currentItem = itemList[position]

        // Configurar las vistas con los datos del elemento actual
        viewHolder.imageView.setImageResource(currentItem.itemImage)
        viewHolder.textView.text = currentItem.itemName

        return view
    }

    // Clase interna para el patrón ViewHolder
    // Almacena referencias a las vistas para evitar llamadas repetidas a findViewById
    private data class ViewHolder(
        val imageView: ImageView,
        val textView: TextView
    )
}
