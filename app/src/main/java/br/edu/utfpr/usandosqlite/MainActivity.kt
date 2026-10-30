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
import br.edu.utfpr.usandosqlite.database.DatabaseHandler
import br.edu.utfpr.usandosqlite.databinding.ActivityMainBinding
import br.edu.utfpr.usandosqlite.entity.Cadastro

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var banco: DatabaseHandler

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

        banco = DatabaseHandler(this)

    } //fim do onCreate()

    private fun setButtonListeners() {
        binding.btIncluir.setOnClickListener {
            btIncluirOnClick()
        }
        binding.btAlterar.setOnClickListener {
            btAlterarOnClick()
        }
        binding.btExcluir.setOnClickListener {
            btExcluirOnClick()
        }
        binding.btPesquisar.setOnClickListener {
            btPesquisarOnClick()
        }
        binding.btListar.setOnClickListener {
            btListarOnClick()
        }
    }

    fun btIncluirOnClick() {

        val cadastro = Cadastro(
            0,
            binding.etNome.text.toString(),
            binding.etTelefone.text.toString()
        )

        banco.inserir(cadastro)

        Toast.makeText(this, "Sucesso!", Toast.LENGTH_SHORT).show()
    }

    fun btAlterarOnClick() {
        val cadastro = Cadastro(
            binding.etCod.text.toString().toInt(),
            binding.etNome.text.toString(),
            binding.etTelefone.text.toString()
        )

        banco.alterar(cadastro)

        Toast.makeText(this, "Sucesso!", Toast.LENGTH_SHORT).show()
    }

    fun btExcluirOnClick() {
        val cod = binding.etCod.text.toString().toInt()
        banco.excluir( cod )
        Toast.makeText(this, "Sucesso!", Toast.LENGTH_SHORT).show()
    }

    fun btPesquisarOnClick() {

        val registro = banco.pesquisar( binding.etCod.text.toString().toInt() )

        if( registro != null ) {
            binding.etNome.setText( registro.nome )
            binding.etTelefone.setText( registro.telefone )
        } else {
            Toast.makeText(this, "Registro não encontrado!",  Toast.LENGTH_SHORT).show()
        }
    }


    fun btListarOnClick() {

        val registros = banco.listar()

        val saida = StringBuilder()

        registros.forEach { registro ->
            saida.append( registro.id )
            saida.append( " - " )
            saida.append( registro.nome )
            saida.append( " - " )
            saida.append( registro.telefone )
            saida.append( "\n" )
        }

        Toast.makeText(this, saida.toString(), Toast.LENGTH_SHORT).show()

    }

    companion object {
        private const val ID = 0
        private const val NOME = 1
        private const val TELEFONE = 2
    }


} //fim da MainActivity