package com.eldar.wallet.ui.generate_qr.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.eldar.wallet.R

@Composable
fun GenerateQrScreen(viewModel: GenerateQrViewModel) {
    viewModel.generateQrScreen();

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier) {
            BackButton(viewModel)
            Spacer(modifier = Modifier.padding(12.dp))
            qr(modifier = Modifier, viewModel)
        }

    }
}

@Composable
fun BackButton(viewModel: GenerateQrViewModel) {
    Icon(
        Icons.Rounded.ArrowBack,
        contentDescription = "Boton de volver hacia atras",
        Modifier.clickable { viewModel.goBack() }
    )
}

@Composable
fun qr(modifier: Modifier, viewModel: GenerateQrViewModel) {
    val urlQr: String by viewModel.urlQR.observeAsState(initial = "")

    Text(text = "Escanea el código", fontSize = 22.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.Center)
    
    Image(
        painter = rememberAsyncImagePainter(urlQr),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .height(128.dp)
            .width(128.dp)
    )
}

