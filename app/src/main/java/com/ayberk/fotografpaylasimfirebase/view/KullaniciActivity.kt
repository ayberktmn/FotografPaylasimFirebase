package com.ayberk.fotografpaylasimfirebase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.ayberk.fotografpaylasimfirebase.R
import com.ayberk.fotografpaylasimfirebase.databinding.ActivityFotografPaylasmaBinding
import com.ayberk.fotografpaylasimfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class KullaniciActivity : AppCompatActivity() {

     private lateinit var auth : FirebaseAuth
     private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

         auth = FirebaseAuth.getInstance()

        val guncelKullanici = auth.currentUser
        if(guncelKullanici != null) {
            val intent = Intent(this, HaberlerActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun girisYap(view: View) {

        if (binding.emailText.getText().toString().trim().equals("") || binding.passwordText.getText().toString().trim().equals("")){
            Toast.makeText(this@KullaniciActivity,"Email veya Şifre Boş Geçilemez",Toast.LENGTH_LONG).show()

        }
        else{
            auth.signInWithEmailAndPassword(binding.emailText.text.toString(), binding.passwordText.text.toString()).addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val guncelKullanici = auth.currentUser?.email.toString()
                    Toast.makeText(this, "Hoşgeldin ${guncelKullanici}", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, HaberlerActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()

            }

        }
    }


    fun kayıtOl(view: View){

        val email = binding.emailText.text.toString()
        val sifre= binding.passwordText.text.toString()

        auth.createUserWithEmailAndPassword(email,sifre).addOnCompleteListener { task ->

            if(task.isSuccessful){
                //Diğer aktiveye gidelim
                val intent = Intent(this, HaberlerActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener{ exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()


        }
    }
}