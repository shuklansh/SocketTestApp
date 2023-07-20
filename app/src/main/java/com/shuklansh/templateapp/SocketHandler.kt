package com.shuklansh.templateapp

import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {

    lateinit var msocket: Socket

    @Synchronized
    fun setSocket(){
        try{
            msocket = IO.socket("http://10.0.2.2:5000")
        } catch (e : URISyntaxException){

        }
    }

    @Synchronized
    fun getSocket(): Socket {
        return msocket
    }

}