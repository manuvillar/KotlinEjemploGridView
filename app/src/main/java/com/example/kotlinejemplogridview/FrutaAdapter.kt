package com.example.kotlinejemplogridview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.kotlinejemplogridview.databinding.GridItemBinding

// Adaptador personalizado: es el "puente" entre la lista de datos y el GridView.
// El GridView le pregunta cuántos elementos hay y cómo dibujar cada uno.
class FrutaAdapter(
    private val frutas: List<Fruta>   // Datos que vamos a mostrar
) : BaseAdapter() {

    // ¿Cuántas celdas tiene el GridView?
    override fun getCount(): Int = frutas.size

    // ¿Qué dato hay en la posición indicada?
    override fun getItem(position: Int): Fruta = frutas[position]

    // Identificador de cada elemento. Usamos la propia posición.
    override fun getItemId(position: Int): Long = position.toLong()

    // Método principal: construye la vista de UNA celda.
    // convertView: celda que ya no se ve en pantalla y Android nos deja reutilizar.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: GridItemBinding

        if (convertView == null) {
            // No hay celda para reciclar: inflamos grid_item.xml con ViewBinding.
            // GridItemBinding se genera automáticamente a partir de grid_item.xml.
            binding = GridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            // Guardamos el binding dentro de la vista para recuperarlo al reciclarla
            binding.root.tag = binding
        } else {
            // Hay celda reciclada: recuperamos su binding, sin volver a inflar el XML
            binding = convertView.tag as GridItemBinding
        }

        // Rellenamos la celda con los datos de la fruta de esta posición
        val fruta = frutas[position]
        binding.itemImage.setImageResource(fruta.imagen)
        binding.itemName.text = fruta.nombre

        return binding.root
    }
}
