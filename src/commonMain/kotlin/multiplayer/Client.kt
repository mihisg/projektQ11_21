package multiplayer

import kotlinx.coroutines.*

expect class Client(ip : String,port:Int,scope: CoroutineScope):ClientServer {
    override fun create()
    override fun setGet(callback:(String)->(Unit))
    override fun send(data: MultiplayerData)
}