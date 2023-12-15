package com.example.resto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun RatingBar(initialRating: Float, maxRating: Int, onRatingChanged: (Float) -> Unit)
{
    var rating by remember { mutableStateOf(initialRating) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(maxRating) { index ->
            Icon(
                painter = painterResource(id = R.drawable.star1),
                contentDescription = null,
                tint = if (index < rating) Color.Red else Color.Gray,
                modifier = Modifier
                    .clickable { onRatingChanged(index + 1f) }
                    .size(15.dp)
            )
        }
    }
}

@Composable
fun LazyRow1(items: List<String>)
{
    var selectedItem by remember { mutableStateOf<String?>(null) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                val isSelected = selectedItem == item
                Box(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .border(
                            2.dp,
                            if (isSelected) Color.Red else Color(0xFFCECECE)
                        )
                        .clickable {
                            selectedItem = if (isSelected) null else item
                        }
                        .width(150.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = item,
                        color = if (isSelected) Color.Black else Color(0xFF9D9D9D),
                        modifier = Modifier
                            .padding(14.dp),
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}

@Composable
fun Options(){
    val items1 = listOf("Small  450da", "Medium  550da", "Large  900da")
    val items2 = listOf("Standard ", "Garlic Roasted  Free", "Cheese Burst  Free")
    val items3 = listOf("Standard", "Cheese  150da", "Spice Free")

    Spacer(modifier = Modifier.height(8.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
    ) {
        Text(text = "Sizes")
        LazyRow1(items = items1)

        Text(text = "Crust")
        LazyRow1(items = items2)

        Text(text = "Toppings")
        LazyRow1(items = items3)
    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
){
    var isLikeClicked by remember { mutableStateOf(false) }
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
                .clip(RoundedCornerShape(18.dp))
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopStart
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "back"
                    )
                }
                IconButton(
                    onClick = { isLikeClicked = !isLikeClicked },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = painterResource(
                            if (isLikeClicked) R.drawable.likefull else R.drawable.likeborder
                        ),
                        contentDescription = "like"
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                title, style = TextStyle(color = Color.Black, fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            RatingBar(
                initialRating = 4f,
                maxRating = 5,
                onRatingChanged = { newRating ->
                }
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "For a vegetarian looking for a BIG treat that goes easy on the spices, this one's got it all..The onions, the capsicum, those delectable mushrooms-with paneer and golden corn to top it all",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 10.sp
                )
            )
        }
    }
}

@Composable
fun App(){
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(5.dp)
    ){
        ImageCard(
            painterResource(id = R.drawable.vegpizza),
            "veg",
            "Veg Pizza",
            modifier = Modifier.weight(1f)
        )
        Options()
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(Color.Red),
            modifier = Modifier
                .fillMaxWidth()) {
            Text(text = "Add to cart")
        }
    }
}



