package com.example.kotlinejemplogridview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.kotlinejemplogridview.databinding.GridItemBinding

/**
 * Adaptador personalizado para mostrar objetos Fruta en un GridView.
 * Es igual que el adaptador de un ListView: ArrayAdapter ya sabe cuántos
 * elementos hay y cuál hay en cada posición; nosotros solo indicamos
 * cómo se dibuja cada celda.
 *
 * @param context Contexto (la Activity). ArrayAdapter lo guarda en su propiedad 'context'
 * @param frutas Lista de frutas a mostrar
 */
class FrutaAdapter(
    context: Context,
    private val frutas: List<Fruta>
) : ArrayAdapter<Fruta>(context, R.layout.grid_item, frutas) {

    /**
     * Se llama para cada celda visible del GridView.
     *
     * @param position Posición del elemento en la lista
     * @param convertView Celda reciclada (null si no hay ninguna para reutilizar)
     * @param parent GridView al que pertenece la celda
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Reciclaje de vistas: si no hay celda que reutilizar, inflamos una nueva;
        // si la hay, creamos el binding sobre ella (más eficiente que inflar siempre)
        val binding = if (convertView == null) {
            GridItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false // false: es el GridView quien añade la celda, no el inflater
            )
        } else {
            GridItemBinding.bind(convertView)
        }

        // Fruta que corresponde a esta celda
        val fruta = frutas[position]

        // Rellenamos la imagen y el nombre
        binding.itemImage.setImageResource(fruta.imagen)
        binding.itemName.text = fruta.nombre

        // Devolvemos la vista raíz de la celda
        return binding.root
    }
}
