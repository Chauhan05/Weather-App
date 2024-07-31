package com.example.weatherapp.screens

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.api.NetworkResponse
import com.example.weatherapp.api.WeatherModel
import com.example.weatherapp.model.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel, modifier: Modifier = Modifier) {
    var city by remember {
        mutableStateOf("")
    }

    val weatherResult = viewModel.weatherResult.observeAsState()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                value = city,
                onValueChange = {
                    city = it
                },
                label = {
                    Text(text = "Location")
                },
                modifier = Modifier.weight(1f),
            )
            IconButton(onClick = {
                viewModel.getData(city)
                focusManager.clearFocus()
                hideKeyboard(context)
            }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search Icon",
                    modifier = Modifier.weight(1f)
                )
            }

        }
        when (val result = weatherResult.value) {
            is NetworkResponse.Error -> {
                Text(text = result.message)
            }

            NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }

            is NetworkResponse.Success -> {
//                Text(text = result.data.toString())
                SuccessData(result.data)
            }

            null -> {}
        }
    }
}

@Composable
fun SuccessData(data: WeatherModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(Icons.Default.LocationOn, "Location", modifier = Modifier.size(40.dp))
            Text(text = "${data.location.name},", fontSize = 28.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = data.location.country, fontSize = 22.sp, color = Color.DarkGray)
        }
        Spacer(modifier = Modifier.height(36.dp))
        Text(text = "${data.current.temp_c} ° c", fontSize = 44.sp)
        AsyncImage(
            modifier = Modifier.size(200.dp),
            model = "https:${data.current.condition.icon.replace("64x64", "128x128")}",
            contentDescription = "condition icon",
        )
        Text(text = data.current.condition.text)
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                RowCmp(
                    "Humidity",
                    data.current.humidity,
                    "Wind Speed",
                    data.current.wind_kph
                )
                Spacer(modifier = Modifier.height(16.dp))
                RowCmp(
                    "Feels Like",
                    data.current.feelslike_c,
                    "Precipitation",
                    data.current.precip_mm
                )
            }
        }
    }
}

@Composable
fun RowCmp(key1: String, value1: String, key2: String, value2: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Element(key = key1, value = value1)
        Element(key = key2, value = value2)
    }

}


@Composable
fun Element(key: String, value: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = value, fontSize = 24.sp)
        Text(text = key, fontSize = 20.sp)
    }
}
//@Composable
//fun RowCmp(key1: String, value1: String, key2: String, value2: String) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceAround
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally
//        ){
//            Text(text = "$value1", fontSize = 24.sp)
//            Text(text = "$key1", fontSize = 22.sp)
//        }
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = "$value2", fontSize = 24.sp)
//            Text(text = "$key2", fontSize = 22.sp)
//
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//    }
//}
private fun hideKeyboard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(null, 0)
}
@Preview(showSystemUi = true)
@Composable
private fun WeatherScreenPreview() {
    val viewmodel = WeatherViewModel()
    WeatherScreen(viewmodel)
}