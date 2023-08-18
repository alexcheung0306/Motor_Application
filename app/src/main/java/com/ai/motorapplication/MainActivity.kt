package com.ai.motorapplication

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.ai.motorapplication.ui.theme.MotorApplicationTheme
import java.io.OutputStream
import java.util.UUID


class MainActivity : ComponentActivity() {
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private var bluetoothSocket: BluetoothSocket? = null
    private var outputStream: OutputStream? = null
    private val MY_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")  // Standard SerialPortService ID
    private val deviceAddress = "B0:B2:1C:A8:FA:40"  // replace with your ESP32's MAC address
    private val deviceAddress2 = "A0:B7:65:DD:7C:24"  // replace with your ESP32's MAC address

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MotorApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MotorizedBlindsApp()
                }
            }
        }

    }

}


@Preview
@Composable
fun MotorizedBlindsApp() {
    ControlButtons(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun ControlButtons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
//    val result by remember { mutableStateOf(1) }
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = {
                    Log.d("msg", "open button is clicked")
                },
            ) {
                Text(text = stringResource(R.string.open_curtain), fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                Log.d("msg", "close button is clicked")
            }) {
                Text(text = stringResource(id = R.string.close_curtain), fontSize = 24.sp)
            }
        }
    }
}


