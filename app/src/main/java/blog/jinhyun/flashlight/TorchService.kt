package blog.jinhyun.flashlight

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TorchService : Service() {

    private val torch by lazy {
        Torch(this)
    }

    private var isRunning = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            // 앱에서 작동할 때
            "on" -> torch.flashOn()
            "off" -> torch.flashOff()
            // 서비스에서 작동할 때
            else -> {
                isRunning = !isRunning
                if (isRunning) {
                    torch.flashOn()
                } else {
                    torch.flashOff()
                }
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}