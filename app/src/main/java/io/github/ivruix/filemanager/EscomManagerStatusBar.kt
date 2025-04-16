package io.github.ivruix.filemanager

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class EscoManagerStatusBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val logoImageView: ImageView
    private val appNameTextView: TextView
    private val optionsButton: ImageButton

    // Esta función se llamará cuando se seleccione un elemento del menú
    var onMenuItemSelectedListener: ((Int) -> Boolean)? = null

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_status_bar, this, true)

        logoImageView = view.findViewById(R.id.iv_logo)
        appNameTextView = view.findViewById(R.id.tv_app_name)
        optionsButton = view.findViewById(R.id.btn_options)

        // Configurar el botón de opciones para mostrar el menú
        optionsButton.setOnClickListener { v ->
            showOptionsMenu(v)
        }

        // Configuración inicial
        setAppName("EscoManager")
    }

    /**
     * Muestra el menú de opciones cuando se hace clic en el botón
     */
    private fun showOptionsMenu(view: View) {
        val popup = PopupMenu(context, view)
        popup.menuInflater.inflate(R.menu.menu_main, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            // Llamar al listener y devolver su resultado
            onMenuItemSelectedListener?.invoke(menuItem.itemId) ?: false
        }

        popup.show()
    }

    /**
     * Establece el nombre de la aplicación en la barra de estado
     */
    fun setAppName(appName: String) {
        appNameTextView.text = appName
    }

    /**
     * Establece la ruta actual (si deseas mostrarla)
     */
    fun setCurrentPath(path: String) {
        // Si quieres mostrar la ruta actual
        // appNameTextView.text = path
    }
}
