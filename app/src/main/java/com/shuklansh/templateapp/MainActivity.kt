package com.shuklansh.templateapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shuklansh.templateapp.ui.theme.SocketTestAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var textCount by remember { mutableStateOf("") }
            SocketTestAppTheme {
                // A surface container using the 'background' color from the theme

                SocketHandler.setSocket()

                val mSocket = SocketHandler.getSocket()
                mSocket.connect()
                LaunchedEffect(key1 = true ){
                    mSocket.connect()
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = textCount)
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(onClick = {
                            mSocket.emit("counterincr")
                            mSocket.on("value"){ args->
                                if(args[0]!=null){
                                    textCount = args[0].toString()

                                }

                            }
                        }) {
                            Text("Increase count")
                        }

                    }


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SocketTestAppTheme {
        Greeting("Android")
    }
}