package com.example.noteappwithoutroom.presentation.note.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.noteappwithoutroom.R
import com.example.noteappwithoutroom.domain.model.NoteEntity

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NoteCard(note: NoteEntity, onItemClick: () -> Unit, onDelete: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onItemClick.invoke() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                GlideImage(
                    model = note.image,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(50.dp)
                        .clip(MaterialTheme.shapes.medium),
                    failure = placeholder(R.drawable.dummy_user),
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(text = note.title, style = MaterialTheme.typography.titleLarge)
                    Text(text = note.description, style = MaterialTheme.typography.titleLarge)
                }
            }
            IconButton(onClick = {
                onDelete.invoke()
            }) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete")
            }
        }
    }
}
