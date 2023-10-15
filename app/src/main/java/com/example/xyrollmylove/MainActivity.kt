package com.example.xyrollmylove

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.xyrollmylove.ui.theme.XyrollMyLoveTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XyrollMyLoveTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //playAudio(context = this)
                    ImageLoading(this)
                }
            }
        }
    }
}

/*@Composable
fun playAudio(context: Context){

    val mp: MediaPlayer = MediaPlayer.create(context, R.raw.audio)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_play),
            contentDescription = "",
            modifier = Modifier
                .height(160.dp)
                .width(160.dp)
                .padding(32.dp)
                .background(Color.Black)
        )

        Row() {

            IconButton(onClick = { mp.start() }, modifier = Modifier.size(35.dp)) {
                Icon(painter = painterResource(id = R.drawable.ic_play), contentDescription = "")
            }

            IconButton(onClick = { mp.pause() }, modifier = Modifier.size(35.dp)) {
                Icon(painter = painterResource(id = R.drawable.ic_pause), contentDescription = "")
            }
        }
    }
}*/

@Composable
fun ImageLoading(context: Context){

    val images = listOf(
        "https://firebasestorage.googleapis.com/v0/b/xyrell-e6508.appspot.com/o/1.jpg?alt=media&token=0c9902d4-5fd1-4be6-aa15-98c09dfb25f8",
        "https://firebasestorage.googleapis.com/v0/b/xyrell-e6508.appspot.com/o/2.jpg?alt=media&token=1560f8f0-a6b1-4dcb-a968-91c049e85fde",
        "https://firebasestorage.googleapis.com/v0/b/xyrell-e6508.appspot.com/o/3.jpg?alt=media&token=7d22b09b-2bde-486a-ac50-a56f464f3275",
        "https://firebasestorage.googleapis.com/v0/b/xyrell-e6508.appspot.com/o/4.jpg?alt=media&token=6011ce72-e906-4a5b-b014-60bc572df949",
        "https://firebasestorage.googleapis.com/v0/b/xyrell-e6508.appspot.com/o/5.jpg?alt=media&token=e1530cc3-33f3-4075-a8a0-adf00c21ea41",
        "https://firebasestorage.googleapis.com/v0/b/xyrell-e6508.appspot.com/o/6.jpg?alt=media&token=f4628934-38f3-4b3d-9236-08bf37fb1b78",
        "https://firebasestorage.googleapis.com/v0/b/xyrell-e6508.appspot.com/o/7.jpg?alt=media&token=7b30f760-4793-4f3a-9aa6-766358a97fa2",
        "https://firebasestorage.googleapis.com/v0/b/xyrell-e6508.appspot.com/o/8.jpg?alt=media&token=735e76f0-0ff2-455c-a384-6628d0eb213a",
        "https://firebasestorage.googleapis.com/v0/b/xyrell-e6508.appspot.com/o/9.jpg?alt=media&token=02a53832-a71b-4097-b923-7c9c7a6fc1b1",
        "https://firebasestorage.googleapis.com/v0/b/xyrell-e6508.appspot.com/o/10.jpg?alt=media&token=f777dc20-ee2a-4472-b99a-50b494bb5dfe",

        "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-34544.appspot.com/o/11.jpg?alt=media&token=5979417f-3175-4527-9f3c-0f2bc999238a",
        "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-34544.appspot.com/o/12.jpg?alt=media&token=3b5107e8-c0df-4991-8881-2eb49decde06",
        "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-34544.appspot.com/o/13.jpg?alt=media&token=66f586e7-b672-440c-82e6-5e6193b589a1",
        "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-34544.appspot.com/o/14.jpg?alt=media&token=1f425915-97e6-45b0-b61d-71e66955661f",
        "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-34544.appspot.com/o/15.jpg?alt=media&token=7c27d63a-9574-429a-8d81-369c620a8423",
        "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-34544.appspot.com/o/16.jpg?alt=media&token=1f3921e3-4c7b-4c2a-858a-6f2f28a92bcb",
        "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-34544.appspot.com/o/17.jpg?alt=media&token=a9cb526f-2ad7-4681-874d-9a26914100c8",
        "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-34544.appspot.com/o/18.jpg?alt=media&token=ae1d25f3-3553-4960-919b-419dd417076d",
        "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-34544.appspot.com/o/19.jpg?alt=media&token=f477676b-2148-416b-9444-94f4cbe57ad0",
        "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-34544.appspot.com/o/20.jpg?alt=media&token=615409f9-64ab-4162-9870-47dd9a202e54",

        "https://firebasestorage.googleapis.com/v0/b/project-5580700583373527811.appspot.com/o/21.jpg?alt=media&token=95c96c9f-355e-4e14-b8c3-4da0c616c5ca",
        "https://firebasestorage.googleapis.com/v0/b/project-5580700583373527811.appspot.com/o/22.jpg?alt=media&token=d97c78db-67d0-4ac6-9b5a-81049dd99e55",
        "https://firebasestorage.googleapis.com/v0/b/project-5580700583373527811.appspot.com/o/23.jpg?alt=media&token=047fb397-88df-4ca6-ae94-a7aedfb76f34",
        "https://firebasestorage.googleapis.com/v0/b/project-5580700583373527811.appspot.com/o/24.jpg?alt=media&token=095e2ab5-cd8b-499c-9191-45f7c0ff305e",
        "https://firebasestorage.googleapis.com/v0/b/project-5580700583373527811.appspot.com/o/25.jpg?alt=media&token=152535cc-db88-4945-9034-7271f7482909",
        "https://firebasestorage.googleapis.com/v0/b/project-5580700583373527811.appspot.com/o/26.jpg?alt=media&token=fa08c5c3-ea02-4f9c-be3c-d574c2364282",
        "https://firebasestorage.googleapis.com/v0/b/project-5580700583373527811.appspot.com/o/27.jpg?alt=media&token=2619bbb6-8168-4995-8835-ed6989b6f1c3",
        "https://firebasestorage.googleapis.com/v0/b/project-5580700583373527811.appspot.com/o/28.jpg?alt=media&token=7bf33c56-116d-4c69-bfd5-214084c59119",
        "https://firebasestorage.googleapis.com/v0/b/project-5580700583373527811.appspot.com/o/29.jpg?alt=media&token=4b6408f2-0648-4bba-96d0-7ffbc844e330",
        "https://firebasestorage.googleapis.com/v0/b/project-5580700583373527811.appspot.com/o/30.jpg?alt=media&token=307dccc0-c76a-4f46-8f16-f853f42b1bc7",

        "https://firebasestorage.googleapis.com/v0/b/project-5232209485978911978.appspot.com/o/31.jpg?alt=media&token=abc80aac-d0ff-42dc-917f-794c4e45ce26",
        "https://firebasestorage.googleapis.com/v0/b/project-5232209485978911978.appspot.com/o/32.jpg?alt=media&token=bb2f30fc-a174-459d-934c-8ec57a97dd76",
        "https://firebasestorage.googleapis.com/v0/b/project-5232209485978911978.appspot.com/o/33.jpg?alt=media&token=82b5c074-2c5a-4491-9306-315b5ebd4722",
        "https://firebasestorage.googleapis.com/v0/b/project-5232209485978911978.appspot.com/o/34.jpg?alt=media&token=f2cbc046-42ab-4b36-a607-ec96629136cd",
        "https://firebasestorage.googleapis.com/v0/b/project-5232209485978911978.appspot.com/o/35.jpg?alt=media&token=c6243496-1597-40a9-8b1f-95b86853b669",
        "https://firebasestorage.googleapis.com/v0/b/project-5232209485978911978.appspot.com/o/36.jpg?alt=media&token=72bfbcef-6936-4d09-9f53-6df710bd09a0",
        "https://firebasestorage.googleapis.com/v0/b/project-5232209485978911978.appspot.com/o/37.jpg?alt=media&token=aebe0dc4-ff39-4820-b7ed-441df245a412",
        "https://firebasestorage.googleapis.com/v0/b/project-5232209485978911978.appspot.com/o/38.jpg?alt=media&token=19121cbe-d0c4-4eb4-b6a3-f45b410b343a",
        "https://firebasestorage.googleapis.com/v0/b/project-5232209485978911978.appspot.com/o/39.jpg?alt=media&token=b08943a9-2284-47ef-b7fd-e53650c0de2d",
        "https://firebasestorage.googleapis.com/v0/b/project-5232209485978911978.appspot.com/o/40.jpg?alt=media&token=e691e0d4-49e1-4276-a072-c5be000175e7",

        "https://firebasestorage.googleapis.com/v0/b/project-5856593301509719738.appspot.com/o/41.jpg?alt=media&token=31a35f19-cfd1-4ed7-9394-973da7dc9c75",
        "https://firebasestorage.googleapis.com/v0/b/project-5856593301509719738.appspot.com/o/42.jpg?alt=media&token=2dbd2ac1-d2fb-4677-9e70-bcbe3e8cc3d0",
        "https://firebasestorage.googleapis.com/v0/b/project-5856593301509719738.appspot.com/o/43.jpg?alt=media&token=90ece1b8-161b-43d6-a13e-71f5f3514eea",
        "https://firebasestorage.googleapis.com/v0/b/project-5856593301509719738.appspot.com/o/44.jpg?alt=media&token=90bd8d91-effc-408c-af1b-2149afb4325a",
        "https://firebasestorage.googleapis.com/v0/b/project-5856593301509719738.appspot.com/o/45.jpg?alt=media&token=36429116-b464-40e1-a6d1-d9a6ce0f42e6",
        "https://firebasestorage.googleapis.com/v0/b/project-5856593301509719738.appspot.com/o/46.jpg?alt=media&token=a49eaf88-78f9-4780-b48c-5253f6fef5bb",
        "https://firebasestorage.googleapis.com/v0/b/project-5856593301509719738.appspot.com/o/47.jpg?alt=media&token=516e21a0-a551-4702-ac95-00a31f6ac073",
        "https://firebasestorage.googleapis.com/v0/b/project-5856593301509719738.appspot.com/o/48.jpg?alt=media&token=cc6a7823-1a03-4016-a4ec-b8f3d3e203ea",
        "https://firebasestorage.googleapis.com/v0/b/project-5856593301509719738.appspot.com/o/49.jpg?alt=media&token=ab2c7804-1ee6-4507-ae91-3c7c85bd0d6f",
        "https://firebasestorage.googleapis.com/v0/b/project-5856593301509719738.appspot.com/o/50.jpg?alt=media&token=fefd8f8b-2a02-4be0-af52-3cc408988943",

        "https://firebasestorage.googleapis.com/v0/b/project-1084574711034271078.appspot.com/o/51.jpg?alt=media&token=eaa84450-f192-4a3f-9f3f-b80261fa8406",
        "https://firebasestorage.googleapis.com/v0/b/project-1084574711034271078.appspot.com/o/52.jpg?alt=media&token=df08c45e-860b-4498-a2f1-9c9c361bb545",
        "https://firebasestorage.googleapis.com/v0/b/project-1084574711034271078.appspot.com/o/53.jpg?alt=media&token=0f2caaae-7886-452f-a858-b2b784634248",
        "https://firebasestorage.googleapis.com/v0/b/project-1084574711034271078.appspot.com/o/54.jpg?alt=media&token=05659f0a-9b9b-49f2-94af-c20c9e51239d",
        "https://firebasestorage.googleapis.com/v0/b/project-1084574711034271078.appspot.com/o/55.jpg?alt=media&token=a0bd8b4b-844e-4311-b043-1fcccba798fa",
        "https://firebasestorage.googleapis.com/v0/b/project-1084574711034271078.appspot.com/o/56.jpg?alt=media&token=caba1762-5fde-45de-92f1-4d343d01664a",
        "https://firebasestorage.googleapis.com/v0/b/project-1084574711034271078.appspot.com/o/57.jpg?alt=media&token=6214b7c5-9ee9-4900-8584-b71c184614b5",
        "https://firebasestorage.googleapis.com/v0/b/project-1084574711034271078.appspot.com/o/58.jpg?alt=media&token=17ccbfb1-986d-4aba-a84a-5b30fbcc2859",
        "https://firebasestorage.googleapis.com/v0/b/project-1084574711034271078.appspot.com/o/59.jpg?alt=media&token=b42edba6-12e5-4c8f-b87c-c8c8b0387799",
        "https://firebasestorage.googleapis.com/v0/b/project-1084574711034271078.appspot.com/o/60.jpg?alt=media&token=0de4ff4d-3680-4eb7-ae6c-34066600ff0c",

        "https://firebasestorage.googleapis.com/v0/b/project-660660705034702971.appspot.com/o/61.jpg?alt=media&token=b04e20d4-f741-456c-ac61-f47bc537aa59",
        "https://firebasestorage.googleapis.com/v0/b/project-660660705034702971.appspot.com/o/62.jpg?alt=media&token=6d8e080d-2aab-4e71-bfae-f1631914a42b",
        "https://firebasestorage.googleapis.com/v0/b/project-660660705034702971.appspot.com/o/63.jpg?alt=media&token=a2c204ef-44b0-4063-a6c7-c160be749f06",
        "https://firebasestorage.googleapis.com/v0/b/project-660660705034702971.appspot.com/o/64.jpg?alt=media&token=59ba67eb-537d-4361-b1e0-8ea2acdee96d",
        "https://firebasestorage.googleapis.com/v0/b/project-660660705034702971.appspot.com/o/65.jpg?alt=media&token=40a08460-a30f-4a7c-9d16-6059ab4e9da7",
        "https://firebasestorage.googleapis.com/v0/b/project-660660705034702971.appspot.com/o/66.jpg?alt=media&token=36a1c29b-8c64-4cb5-81a6-d03d856309ed",
        "https://firebasestorage.googleapis.com/v0/b/project-660660705034702971.appspot.com/o/67.jpg?alt=media&token=fe051d00-251b-43e3-b6cb-672480a83934",
        "https://firebasestorage.googleapis.com/v0/b/project-660660705034702971.appspot.com/o/68.jpg?alt=media&token=6f97b662-662a-4092-b408-b74be90c7912",
        "https://firebasestorage.googleapis.com/v0/b/project-660660705034702971.appspot.com/o/69.jpg?alt=media&token=c4f58cb5-beae-4d70-90fb-6b81496e39d1",
        "https://firebasestorage.googleapis.com/v0/b/project-660660705034702971.appspot.com/o/70.jpg?alt=media&token=46b6e1ee-f28b-4c55-a8e3-44d9fa5f47fa",

        "https://firebasestorage.googleapis.com/v0/b/project-783663396527248497.appspot.com/o/71.jpg?alt=media&token=b2100566-111b-40df-a13a-69dbdc5249c3",
        "https://firebasestorage.googleapis.com/v0/b/project-783663396527248497.appspot.com/o/72.jpg?alt=media&token=0548fddd-a187-4f63-8989-b3b2fc30ae17",
        "https://firebasestorage.googleapis.com/v0/b/project-783663396527248497.appspot.com/o/73.jpg?alt=media&token=c599be33-1742-47f1-8068-7128f290a6b4",
        "https://firebasestorage.googleapis.com/v0/b/project-783663396527248497.appspot.com/o/74.jpg?alt=media&token=509824a4-8d0a-4004-b99a-84a315f5d4ee",
        "https://firebasestorage.googleapis.com/v0/b/project-783663396527248497.appspot.com/o/75.jpg?alt=media&token=24a4daf2-db33-4b85-b488-af1a88b7a860",
        "https://firebasestorage.googleapis.com/v0/b/project-783663396527248497.appspot.com/o/76.jpg?alt=media&token=9e6f3ced-98f9-4d82-8bea-c27b3c9143a6",
        "https://firebasestorage.googleapis.com/v0/b/project-783663396527248497.appspot.com/o/77.jpg?alt=media&token=c14c067b-1fc8-4e60-8d5c-4fd2f69c8a4c",
        "https://firebasestorage.googleapis.com/v0/b/project-783663396527248497.appspot.com/o/78.jpg?alt=media&token=461f062f-eb72-4a70-b61a-425068b7627b",
        "https://firebasestorage.googleapis.com/v0/b/project-783663396527248497.appspot.com/o/79.jpg?alt=media&token=1897458b-ebce-4e1a-803f-67eca05efd8b",
        "https://firebasestorage.googleapis.com/v0/b/project-783663396527248497.appspot.com/o/80.jpg?alt=media&token=fd4dfc6e-9926-40fc-8f35-8c69bf942dcb",

        "https://firebasestorage.googleapis.com/v0/b/project-2719815765446682093.appspot.com/o/81.jpg?alt=media&token=11d60f8c-cc91-4306-b1e5-3f4424c0d0c4",
        "https://firebasestorage.googleapis.com/v0/b/project-2719815765446682093.appspot.com/o/82.jpg?alt=media&token=e39c9fa7-6b42-4c21-86c0-7b3ff6dd4cd4",
        "https://firebasestorage.googleapis.com/v0/b/project-2719815765446682093.appspot.com/o/83.jpg?alt=media&token=12b14953-9590-4ac0-95ae-042141f73fda",
        "https://firebasestorage.googleapis.com/v0/b/project-2719815765446682093.appspot.com/o/84.jpg?alt=media&token=d6b105f7-522a-42d6-933d-546331d69441",
        "https://firebasestorage.googleapis.com/v0/b/project-2719815765446682093.appspot.com/o/85.jpg?alt=media&token=9e457270-c129-4073-aeb9-08045cf3fe69",
        "https://firebasestorage.googleapis.com/v0/b/project-2719815765446682093.appspot.com/o/86.jpg?alt=media&token=5d3d6df7-2cff-4bf6-b0e0-12eb100a3579",
        "https://firebasestorage.googleapis.com/v0/b/project-2719815765446682093.appspot.com/o/87.jpg?alt=media&token=0cc3e476-cc03-4298-b57e-8fc4a42b6a21",
        "https://firebasestorage.googleapis.com/v0/b/project-2719815765446682093.appspot.com/o/88.jpg?alt=media&token=0c41d907-dc2e-47ae-bcbd-1b71ec720ee0",
        "https://firebasestorage.googleapis.com/v0/b/project-2719815765446682093.appspot.com/o/89.jpg?alt=media&token=85b151f7-a5e4-49e3-bce0-a37cf02bbbc2",
        "https://firebasestorage.googleapis.com/v0/b/project-2719815765446682093.appspot.com/o/90.jpg?alt=media&token=e6be43a3-f1e9-447a-b528-eed739247ed2",

        "https://firebasestorage.googleapis.com/v0/b/project-4100278302621179448.appspot.com/o/91.jpg?alt=media&token=1cafe284-c32f-4919-8ac6-b9efa81552e5",
        "https://firebasestorage.googleapis.com/v0/b/project-4100278302621179448.appspot.com/o/92.jpg?alt=media&token=97c784f9-ff6e-4685-8416-4805ee49c24c",
        "https://firebasestorage.googleapis.com/v0/b/project-4100278302621179448.appspot.com/o/93.jpg?alt=media&token=97300532-a838-4806-996c-e635d5a06159",
        "https://firebasestorage.googleapis.com/v0/b/project-4100278302621179448.appspot.com/o/94.jpg?alt=media&token=7994f1cb-2786-4e9c-bd47-64bb9e68d337",
        "https://firebasestorage.googleapis.com/v0/b/project-4100278302621179448.appspot.com/o/95.jpg?alt=media&token=d09eecf7-eee8-4a93-a49e-f536ec83d5c0",
        "https://firebasestorage.googleapis.com/v0/b/project-4100278302621179448.appspot.com/o/96.jpg?alt=media&token=497a1383-d72d-4044-b806-68a1a9e009ac",
        "https://firebasestorage.googleapis.com/v0/b/project-4100278302621179448.appspot.com/o/97.jpg?alt=media&token=a05283ae-0827-4d76-bf81-6a97e848d9cf",
        "https://firebasestorage.googleapis.com/v0/b/project-4100278302621179448.appspot.com/o/98.jpg?alt=media&token=d221c71f-6654-4760-b166-e9214efae7e9",
        "https://firebasestorage.googleapis.com/v0/b/project-4100278302621179448.appspot.com/o/99.jpg?alt=media&token=ad24da37-e5e4-4672-9458-6217bbef30ba",
        "https://firebasestorage.googleapis.com/v0/b/project-4100278302621179448.appspot.com/o/100.jpg?alt=media&token=b7d69c84-f484-43be-8a5d-6dbfe67bf205",
    )

    ImageSlider(images, context)
}

fun Drawable.toBitmap(): Bitmap {
    if (this is BitmapDrawable) {
        return this.bitmap
    }
    val bitmap = Bitmap.createBitmap(
        intrinsicWidth.coerceAtLeast(1),
        intrinsicHeight.coerceAtLeast(1),
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)
    return bitmap
}


@Composable
fun NetworkImage(url: String, contentDescription: String?, width: Int, height: Int) {
    val painter: Painter = rememberImagePainter(url)
    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .width(width.dp)
            .height(height.dp)
            .padding(20.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageSlider(images: List<Any>, context: Context) {

    val mp: MediaPlayer = MediaPlayer.create(context, R.raw.audio)

    IconButton(onClick = { mp.start() }, modifier = Modifier.size(35.dp)) {
        Icon(painter = painterResource(id = R.drawable.ic_play), contentDescription = "")
    }

    var currentImageIndex by remember { mutableStateOf(0) }
    var isAnimating by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(currentImageIndex) {
        while (true) {
            delay(5000L)
            if (!isAnimating) {
                val nextIndex = (currentImageIndex + 1) % images.size
                currentImageIndex = nextIndex
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier
            .weight(1f)
            .height(100.dp)
            .fillMaxWidth(),
            //.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable._69536),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            // Scrollable Row of Cards
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(images) { index, image ->
                    Card(
                        modifier = Modifier
                            .width(300.dp)
                            .height(400.dp)
                            .clickable {
                                if (index != currentImageIndex && !isAnimating) {
                                    isAnimating = true
                                    coroutineScope.launch {
                                        val delayMillis = 500L
                                        // Wait for the card to change color before animating
                                        delay(delayMillis / 2)
                                        currentImageIndex = index
                                        delay(delayMillis)
                                        isAnimating = false
                                    }
                                }
                            },
                        elevation = CardDefaults.cardElevation()
                    ) {
                        Box(){
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(id = R.drawable.border),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds
                            )
                            NetworkImage(
                                contentDescription = "",
                                url = image as String,
                                width = 300,
                                height = 400
                            )
                        }

                    }
                }

            }

        }
        /* // Dots Indicator
         Row(
             modifier = Modifier
                 .align(Alignment.CenterHorizontally)
                 .padding(16.dp)
         ) {
             for (i in images.indices) {
                 Surface(
                     modifier = Modifier
                         .size(10.dp)
                         .padding(4.dp)
                         .clip(CircleShape)
                         .background(
                             if (currentImageIndex == i) MaterialTheme.colors.primary
                             else Color.LightGray
                         )
                         .clickable {
                             if (i != currentImageIndex && !isAnimating) {
                                 isAnimating = true
                                 coroutineScope.launch {
                                     val delayMillis = 500L
                                     // Wait for the dot to change color before animating
                                     delay(delayMillis / 2)
                                     currentImageIndex = i
                                     delay(delayMillis)
                                     isAnimating = false
                                 }
                             }
                         },
                     color = Color.Transparent
                 ) {
 
                 }
             }
         }*/
    }




    // Automatic Image Slider

}


