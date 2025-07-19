package com.vharya.aktifitas7_2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var inputUsername: EditText
    private lateinit var inputPassword: EditText

    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: Button
    private lateinit var buttonExit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inputUsername = findViewById(R.id.input_username)
        inputPassword = findViewById(R.id.input_password)

        buttonLogin = findViewById(R.id.button_login)
        buttonRegister = findViewById(R.id.button_register)
        buttonExit = findViewById(R.id.button_exit)

        buttonExit.setOnClickListener {
            finish()
        }

        buttonRegister.setOnClickListener {
            val username = inputUsername.text.toString()
            val password = inputPassword.text.toString()

            try {
                val dbUser = DatabaseUserAdapter(this@MainActivity)
                val result = dbUser.register(username, password)

                if (result > 0) {
                    Toast.makeText(this@MainActivity, "Berhasil Daftar", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
            }
        }

        buttonLogin.setOnClickListener {
            val username = inputUsername.text.toString()
            val password = inputPassword.text.toString()

            try {
                val dbUser = DatabaseUserAdapter(this)
                val result = dbUser.login(username, password)

                if (result) {
                    Toast.makeText(this@MainActivity, "Berhasil Masuk", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@MainActivity, AdminActivity::class.java)
                    startActivity(intent)
                }
            } catch (e: Exception) {
//                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                Log.e("ERROR", "${e.message}")
                Log.e("ERROR", "${e.stackTrace}")
            }
        }
    }
}