package io.github.yemyatthu1990.child.service

import android.app.Service
import android.content.Intent
import android.net.VpnService
import android.os.IBinder
import android.os.ParcelFileDescriptor
import android.util.Config

class ChildNetworkGuard : VpnService() {
    var vpnInterface: ParcelFileDescriptor? = null
    override fun onCreate() {
        super.onCreate()
        if (vpnInterface == null) {
            val builder = Builder()
            builder.addAddress("10.1.10.1", 32)
            builder.addRoute("0.0.0.0",0)
            vpnInterface = builder.establish()

        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}