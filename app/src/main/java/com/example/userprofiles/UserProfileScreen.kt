package com.example.userprofiles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserProfileScreen(viewModel: UserViewModel = viewModel()) {
    val todo by viewModel.todo.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            loading -> {
                CircularProgressIndicator()
            }

            error != null -> {
                Text(text = error!!, fontSize = 16.sp, color = Color.Red)
                Button(onClick = { viewModel.fetchRandomTodo() }) {
                    Text(text = "Load New Todo")
                }
            }

            todo != null -> {
                Text(
                    text = todo!!.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = if (todo!!.completed) "Completed" else "Incomplete",
                    fontSize = 20.sp,
                    color = Color.Blue
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.fetchRandomTodo() }) {
                    Text(text = "Load New Todo")
                }
            }
        }
    }
}
