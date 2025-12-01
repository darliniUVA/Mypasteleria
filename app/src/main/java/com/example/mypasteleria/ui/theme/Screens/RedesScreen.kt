import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.mypasteleria.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RedesScreen() {
    val context = LocalContext.current

    TopAppBar(
        title = { Text("🎂 Pastelería Mil Sabores") },
        actions = {

            IconButton(onClick = { abrirUrl("https://www.facebook.com", context) }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_facebook),
                    contentDescription = "Facebook"
                )
            }
            IconButton(onClick = { abrirUrl("https://www.instagram.com", context) }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_instagram),
                    contentDescription = "Instagram"
                )
            }

            IconButton(onClick = { abrirUrl("https://www.tiktok.com", context) }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_tiktok),
                    contentDescription = "TikTok"
                )
            }
        }
    )
}

fun abrirUrl(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}
