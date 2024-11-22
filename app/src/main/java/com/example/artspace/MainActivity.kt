package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // Set the content of the activity to the ArtSpaceLayout composable
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {

    // Artwork resources and states for dynamic UI updates
    val firstArtwork = R.drawable.denji_face
    val secondArtwork = R.drawable.zero_two_face
    val thirdArtwork = R.drawable.sanji_face
    val fourthArtwork = R.drawable.naruto_face

    // State for the title of the artwork
    var title by remember { mutableStateOf(R.string.denji) }
    // State for the year of the artwork
    var year by remember { mutableStateOf(R.string.denji_year) }
    // State for the current artwork image resource
    var currentArtwork by remember { mutableStateOf(firstArtwork) }

    Column(
        modifier = modifier
            .fillMaxSize() // Make the column fill the entire screen
            .wrapContentHeight(Alignment.CenterVertically), // Center content vertically
        horizontalAlignment = Alignment.CenterHorizontally, // Align content to the center horizontally
    ) {
        // Artwork display box
        Box(
            modifier = modifier
                .padding(25.dp) // Padding around the box
                .height(400.dp) // Fixed height for the artwork display
                .border(
                    width = 5.dp, // Border thickness
                    color = Color.Gray, // Border color
                    shape = RectangleShape // Shape of the border
                ),
        ) {
            Row(modifier = modifier.padding(25.dp)) {
                // Display the current artwork
                ArtWork(currentArtwork = currentArtwork)
            }
        }

        // Display artwork title and year in a styled text field
        Row(
            modifier = modifier.padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically, // Align text vertically in the center
        ) {
            TextField(title = title, year = year)
        }

        // Navigation buttons for cycling through artworks
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly, // Distribute buttons evenly
            modifier = Modifier
                .fillMaxWidth() // Make the row span the full width
                .padding(top = 50.dp)
        ) {
            // Previous button
            Button(
                onClick = {
                    // Logic to update the artwork and its metadata for "Previous"
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = fourthArtwork
                            title = R.string.naruto
                            year = R.string.naruto_year
                        }
                        secondArtwork -> {
                            currentArtwork = firstArtwork
                            title = R.string.denji
                            year = R.string.denji_year
                        }
                        thirdArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.zero_two
                            year = R.string.zero_two_year
                        }
                        else -> {
                            currentArtwork = thirdArtwork
                            title = R.string.sanji
                            year = R.string.sanji_year
                        }
                    }
                },
                shape = RoundedCornerShape(50.dp), // Rounded button shape
                border = BorderStroke(2.dp, Color.Gray), // Add a border to the button
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 20.dp), // Add elevation for a shadow effect
                modifier = Modifier
                    .weight(1f) // Make buttons equally sized
                    .padding(horizontal = 20.dp) // Add spacing around the button
            ) {
                Text(text = "Previous") // Button label
            }

            // Next button
            Button(
                onClick = {
                    // Logic to update the artwork and its metadata for "Next"
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.zero_two
                            year = R.string.zero_two_year
                        }
                        secondArtwork -> {
                            currentArtwork = thirdArtwork
                            title = R.string.sanji
                            year = R.string.sanji_year
                        }
                        thirdArtwork -> {
                            currentArtwork = fourthArtwork
                            title = R.string.naruto
                            year = R.string.naruto_year
                        }
                        else -> {
                            currentArtwork = firstArtwork
                            title = R.string.denji
                            year = R.string.denji_year
                        }
                    }
                },
                shape = RoundedCornerShape(50.dp), // Rounded button shape
                border = BorderStroke(2.dp, Color.Gray), // Add a border to the button
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 20.dp), // Add elevation for a shadow effect
                modifier = Modifier
                    .weight(1f) // Make buttons equally sized
                    .padding(horizontal = 20.dp) // Add spacing around the button
            ) {
                Text(text = "Next") // Button label
            }
        }
    }
}

@Composable
fun ArtWork(
    modifier: Modifier = Modifier,
    @DrawableRes currentArtwork: Int // Artwork image resource ID
) {
    Image(
        painter = painterResource(currentArtwork), // Load the image resource
        contentDescription = stringResource(id = R.string.denji), // Accessibility description
        modifier = modifier.fillMaxWidth(), // Make the image span the full width
        contentScale = ContentScale.FillWidth // Scale the image to fill the width
    )
}

@Composable
fun TextField(
    @StringRes title: Int, // Resource ID for the artwork title
    @StringRes year: Int // Resource ID for the artwork year
) {
    Column(
        modifier = Modifier
            .background(color = Color.LightGray) // Background color for the text field
            .padding(16.dp), // Padding around the text content
        verticalArrangement = Arrangement.Center, // Align text vertically
        horizontalAlignment = Alignment.CenterHorizontally // Align text horizontally
    ) {
        Text(
            text = stringResource(id = title) // Display the title
        )
        Text(
            text = "— ${stringResource(id = year)} —", // Display the year with decorative dashes
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceLayoutPreview() {
    ArtSpaceTheme {
        // Preview of the ArtSpace layout in the design editor
        ArtSpaceLayout()
    }
}
