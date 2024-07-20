package com.example.noteappwithoutroom.presentation.add_note

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.noteappwithoutroom.R
import com.example.noteappwithoutroom.myapp.LocalNavController
import com.example.noteappwithoutroom.presentation.add_note.components.MyTextField
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun AddNoteScreen(
    noteId: Long,
    viewModel: AddNoteViewModel = koinViewModel()
) {
    val navController = LocalNavController.current
    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { imageUri ->
            imageUri?.let {
                viewModel.onImageChange(imageUri.toString())
            }
        }

    LaunchedEffect(noteId) {
        if (noteId.toInt() != -1) {
            viewModel.loadNoteById(noteId)
        } else {
            viewModel.onTitleChange("")
            viewModel.onDescriptionChange("")
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(
                    text = if (noteId.toInt() != -1) "Edit Note" else "Add Note",
                    style = MaterialTheme.typography.titleLarge,
                )
            }, navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GlideImage(
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)
                    .clickable {
                        launcher.launch("image/*")
                    },
                model = state.image,
                contentDescription = state.image,
                contentScale = ContentScale.Crop,
                failure = placeholder(R.drawable.dummy_user),
            )
            Spacer(modifier = Modifier.height(12.dp))
            MyTextField(value = state.title,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                hint = "Title...", onValueChange = {
                    viewModel.onTitleChange(it)
                })
            Spacer(modifier = Modifier.height(12.dp))
            MyTextField(
                value = state.description,
                hint = "Description...",
                modifier = Modifier
                    .height(150.dp)
                    .padding(horizontal = 8.dp),
                onValueChange = {
                    viewModel.onDescriptionChange(it)
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {
                    if (state.title.isBlank() or state.description.isBlank()) {
                        Toast.makeText(context, "Enter title and description", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        viewModel.saveOrUpdate()
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(5.dp)),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary)
            ) {
                Text(text = "Save", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}