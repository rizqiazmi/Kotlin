import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hellocompose.R

@Composable
fun Welcome(navController: NavController) {
    val imageModifier = Modifier
        .size(1080.dp, 1920.dp)

    val imageModifier2 = Modifier
        .size(75.dp)
        .offset(x = 170.dp, y = 530.dp)

    Image(
        painter = painterResource(id = R.drawable.splashtext),
        contentDescription = "image description",
        contentScale = ContentScale.Crop,
        modifier = imageModifier
    )
    Image(
        painter = painterResource(id = R.drawable.arrow),
        contentDescription = "image description",
        contentScale = ContentScale.Crop,
        modifier = imageModifier2
        .clickable { navController.navigate("greeting") }
    )
}

