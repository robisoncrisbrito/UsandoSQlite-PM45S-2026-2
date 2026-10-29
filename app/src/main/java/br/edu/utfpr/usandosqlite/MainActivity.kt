package br.edu.utfpr.usandosqlite

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
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
        binding.btIncluir.setOnClickListener {
            btIncluirOnClick()
        }
        binding.btAlterar.setOnClickListener {  }
        binding.btExcluir.setOnClickListener {  }
        binding.btPesquisar.setOnClickListener {  }
        binding.btListar.setOnClickListener {
            btListarOnClick()
        }
    }

    fun btIncluirOnClick() {
        val nome = binding.etNome.text.toString()
        val telefone = binding.etTelefone.text.toString()

        val registro = ContentValues()
        registro.put("nome", nome)
        registro.put("telefone", telefone)

        banco.insert( "cadastro", null, registro)

        Toast.makeText(this, "Sucesso!", Toast.LENGTH_SHORT).show()
    }

    fun btListarOnClick() {

        val registros: Cursor = banco.query(
            "cadastro",
            null,
            null,
            null,
            null,
            null,
            null
        )

        val saida = StringBuilder()

        while( registros.moveToNext() ) {
            saida.append( registros.getInt( 0 ) )
            saida.append( " - " )
            saida.append( registros.getString( 1 ) )
            saida.append( " - " )
            saida.append( registros.getString( 2 ) )
            saida.append( "\n" )
        }

        Toast.makeText(this, saida.toString(), Toast.LENGTH_SHORT).show()

    }

}