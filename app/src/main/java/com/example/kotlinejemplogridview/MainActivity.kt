package com.example.kotlinejemplogridview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinejemplogridview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // ViewBinding: clase generada automáticamente a partir de activity_main.xml
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // La app ocupa toda la pantalla, también detrás de las barras del sistema
        enableEdgeToEdge()

        // Inflamos el layout con ViewBinding y lo ponemos como contenido
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- Insets: evitamos que el contenido quede bajo la cámara o las barras ---
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { vista, insets ->
            // Espacio que ocupan las barras del sistema y el recorte de la cámara
            val barras = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            // 16dp extra de margen, convertidos a píxeles
            val extra = (16 * resources.displayMetrics.density).toInt()
            vista.setPadding(
                barras.left + extra,
                barras.top + extra,
                barras.right + extra,
                barras.bottom + extra
            )
            insets
        }

        // Datos de ejemplo: cada fruta con su nombre y su imagen de res/drawable
        val frutas = listOf(
            Fruta("Manzana", R.drawable.manzana),
            Fruta("Plátano", R.drawable.platano),
            Fruta("Cereza", R.drawable.cereza),
            Fruta("Pera", R.drawable.pera),
            Fruta("Fresa", R.drawable.fresa),
            Fruta("Uva", R.drawable.uva),
            Fruta("Calabaza", R.drawable.calabaza),
            Fruta("Limón", R.drawable.limon),
            Fruta("Piña", R.drawable.pina)
        )

        // Conectamos el GridView con el adaptador
        binding.gridView.adapter = FrutaAdapter(frutas)

        // Clic normal: position indica qué celda se ha pulsado (empieza en 0)
        binding.gridView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "Seleccionaste: ${frutas[position].nombre}", Toast.LENGTH_SHORT).show()
        }

        // Clic largo: devolvemos true para indicar que ya hemos tratado el evento
        // (si devolviéramos false, también se lanzaría el clic normal)
        binding.gridView.setOnItemLongClickListener { _, _, position, _ ->
            Toast.makeText(this, "Clic largo en: ${frutas[position].nombre}", Toast.LENGTH_LONG).show()
            true
        }
    }
}
