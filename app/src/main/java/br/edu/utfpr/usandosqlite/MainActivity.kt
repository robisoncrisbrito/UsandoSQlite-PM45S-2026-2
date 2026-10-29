package br.edu.utfpr.usandosqlite

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.utfpr.usandosqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var banco: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setButtonListeners()

        banco = SQLiteDatabase.openOrCreateDatabase(
            this.getDatabasePath("banco.db"),
            null
        )

        banco.execSQL(
            "CREATE TABLE IF NOT EXISTS cadastro " +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT, " +
                    "telefone TEXT) "
        )


    } //fim do onCreate()

    private fun setButtonListeners() {
        binding.btIncluir.setOnClickListener {  }
        binding.btAlterar.setOnClickListener {  }
        binding.btExcluir.setOnClickListener {  }
        binding.btPesquisar.setOnClickListener {  }
        binding.btListar.setOnClickListener {  }
    }

}