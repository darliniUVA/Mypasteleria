
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mypasteleria.Data.Model.listaProductos
import com.example.mypasteleria.ViewModel.CarritoViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen(
    carritoViewModel: CarritoViewModel,
    onNavigate: (String) -> Unit
) {
    var filtro by remember { mutableStateOf("Todos") }

    val productosFiltrados =
        if (filtro == "Todos") listaProductos
        else listaProductos.filter { it.categoria == filtro }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Productos") },
                navigationIcon = {
                    IconButton(onClick = { onNavigate("home") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            DropdownMenuCategoria(filtro) { filtro = it }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(productosFiltrados) { producto ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {

                        Column(modifier = Modifier.padding(16.dp)) {

                            Image(
                                painter = painterResource(id = producto.image),
                                contentDescription = producto.nombre,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(producto.nombre, style = MaterialTheme.typography.titleLarge)
                            Text(producto.descripcion)
                            Text("Precio: $${producto.precio}")

                            Spacer(modifier = Modifier.height(12.dp))

                            Button(
                                onClick = {
                                    carritoViewModel.agregarProducto(producto)

                                    scope.launch {
                                        val res = snackbarHostState.showSnackbar(
                                            message = "Producto añadido",
                                            actionLabel = "Ir al carrito"
                                        )
                                        if (res == SnackbarResult.ActionPerformed) {
                                            onNavigate("carrito")
                                        }
                                    }
                                }
                            ) {
                                Text("Agregar al carrito")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DropdownMenuCategoria(
    seleccionActual: String,
    onSeleccionar: (String) -> Unit
) {
    var expandido by remember { mutableStateOf(false) }

    val categorias = listOf(
        "Todos",
        "Tortas Cuadradas",
        "Tortas Circulares",
        "Postres Individuales",
        "Sin Azúcar",
        "Sin Gluten",
        "Veganos"
    )

    Box {
        Button(onClick = { expandido = true }) {
            Text("Categoría: $seleccionActual")
        }

        DropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
            categorias.forEach { categoria ->
                DropdownMenuItem(
                    text = { Text(categoria) },
                    onClick = {
                        onSeleccionar(categoria)
                        expandido = false
                    }
                )
            }
        }
    }
}
