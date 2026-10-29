package com.example.kotlinejemplogridview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinejemplogridview.databinding.ActivityMainBinding

/**
 * Activity principal: muestra un GridView de frutas y unos botones
 * que cambian el aspecto de la cuadrícula mientras la app está funcionando.
 * - Botones 1, 2, 3 y 4: número de columnas (con 1 columna parece un ListView).
 * - Botón de espaciado: alterna entre celdas juntas y separadas.
 */
class MainActivity : AppCompatActivity() {

    // ViewBinding: clase generada automáticamente a partir de activity_main.xml
    private lateinit var binding: ActivityMainBinding

    // Estado actual de la cuadrícula (empieza igual que en el XML)
    private var columnas = 3
    private var separacionDp = 10

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
            val extra = dpAPx(16)
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
        binding.gridView.adapter = FrutaAdapter(this, frutas)

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

        // --- Botones de columnas: cada uno llama a la misma función con un número ---
        binding.btnUnaColumna.setOnClickListener { cambiarColumnas(1) }
        binding.btnDosColumnas.setOnClickListener { cambiarColumnas(2) }
        binding.btnTresColumnas.setOnClickListener { cambiarColumnas(3) }
        binding.btnCuatroColumnas.setOnClickListener { cambiarColumnas(4) }

        // --- Botón de espaciado: alterna entre celdas juntas (4dp) y separadas (24dp) ---
        binding.btnEspaciado.setOnClickListener {
            separacionDp = if (separacionDp == 24) 4 else 24
            binding.gridView.horizontalSpacing = dpAPx(separacionDp)
            binding.gridView.verticalSpacing = dpAPx(separacionDp)
            actualizarEstado()
        }

        // Mostramos el estado inicial
        actualizarEstado()
    }

    /**
     * Cambia el número de columnas del GridView desde código.
     * Es lo mismo que android:numColumns en el XML, pero en tiempo de ejecución.
     */
    private fun cambiarColumnas(numero: Int) {
        columnas = numero
        binding.gridView.numColumns = numero
        actualizarEstado()
    }

    /** Muestra encima de la cuadrícula cómo está configurada ahora mismo. */
    private fun actualizarEstado() {
        binding.tvEstado.text = getString(R.string.estado_grid, columnas, separacionDp)
    }

    /**
     * Convierte dp a píxeles. Los métodos de código (setPadding, spacing...)
     * trabajan en píxeles, pero en el diseño pensamos en dp.
     */
    private fun dpAPx(dp: Int): Int = (dp * resources.displayMetrics.density).toInt()
}
