import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mypasteleria.Data.Model.listaProductos

@Composable
fun ProductosDestacados() {
    val destacados = listaProductos.take(3)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Productos destacados",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(12.dp))

        destacados.forEach { producto ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                Row(modifier = Modifier.padding(10.dp)) {

                    Image(
                        painter = painterResource(id = producto.image),
                        contentDescription = producto.nombre,
                        modifier = Modifier.size(80.dp)
                    )

                    Spacer(Modifier.width(16.dp))

                    Column {
                        Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                        Text("${producto.precio} CLP")
                    }
                }
            }
        }
    }
}
