package com.example.server.local

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.server.local.models.LocalServer
import com.example.server.local.models.assets.helpers.AssetsHelper

class LocalServerActivity : AppCompatActivity() {

    companion object {
        private val TAG = this::class.java.simpleName
        private const val PORT = 7777
    }

    //TODO move to the Service
    private val server = LocalServer(port = PORT)

    /**
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_server)

        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //TODO move to the Service
        server.apply {
            responses = AssetsHelper.getResponses(assets)
            start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //TODO move to the Service
        server.stop()
    }
}

