package br.edu.utfpr.usandosqlite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.utfpr.usandosqlite.databinding.ActivityListarBinding

class ListarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}