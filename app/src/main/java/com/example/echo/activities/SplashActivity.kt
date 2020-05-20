package com.example.echo

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.echo.activities.MainActivity

class SplashActivity : AppCompatActivity() {

    var permissionsString = arrayOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.PROCESS_OUTGOING_CALLS,
        android.Manifest.permission.READ_PHONE_STATE,
        android.Manifest.permission.MODIFY_AUDIO_SETTINGS,
        android.Manifest.permission.RECORD_AUDIO
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (!hasPermissions(this@SplashActivity, *permissionsString)) run {
            ActivityCompat.requestPermissions(this@SplashActivity, permissionsString, 131)
        }
        else {
            Handler().postDelayed(
                {
                    val startAct = Intent(
                        this@SplashActivity,
                        MainActivity::class.java
                    )
                    startActivity(startAct)
                    this.finish()
                }, 1000
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            131 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED
                    && grantResults[1] == PackageManager.PERMISSION_DENIED
                    && grantResults[2] == PackageManager.PERMISSION_DENIED
                    && grantResults[3] == PackageManager.PERMISSION_DENIED
                    && grantResults[4] == PackageManager.PERMISSION_DENIED
                ) {
                    Handler().postDelayed(
                        {
                            val startAct = Intent(
                                this@SplashActivity,
                                MainActivity::class.java
                            )
                            startActivity(startAct)
                            this.finish()
                        }, 1000
                    )

                } else {
                    Toast.makeText(
                        this@SplashActivity, "Please grant all the permission to Continue"
                        , Toast.LENGTH_SHORT
                    ).show()
                    this.finish()
                }
                return
            }
            else -> {
                Toast.makeText(this@SplashActivity, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
                this.finish()
                return
            }
        }
    }

    fun hasPermissions(context: Context, vararg permissions: String): Boolean {
        var hasAllPermissions = true
        for (permission in permissions) {
            val res = context.checkCallingOrSelfPermission(permission)
            if (res != PackageManager.PERMISSION_GRANTED) {
                hasAllPermissions = false
            }
        }
        return hasAllPermissions
    }
}
