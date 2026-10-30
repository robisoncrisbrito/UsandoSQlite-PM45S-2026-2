package br.edu.utfpr.usandosqlite.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.edu.utfpr.usandosqlite.entity.Cadastro

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, "banco.db", null, 1) {

    override fun onCreate(banco: SQLiteDatabase?) {
        banco?.execSQL(
            "CREATE TABLE IF NOT EXISTS cadastro " +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT, " +
                    "telefone TEXT) "
        )
    }

    override fun onUpgrade(
        banco: SQLiteDatabase?,
        p1: Int,
        p2: Int
    ) {
        banco?.execSQL("DROP TABLE IF EXISTS cadastro")
        onCreate(banco)
    }

    fun inserir(cadastro: Cadastro) {

        val banco = this.writableDatabase

        val registro = ContentValues()
        registro.put("nome", cadastro.nome)
        registro.put("telefone", cadastro.telefone)

        banco.insert("cadastro", null, registro)

    }

    fun alterar(cadastro: Cadastro) {

        val banco = this.writableDatabase

        val registro = ContentValues()
        registro.put("nome", cadastro.nome)
        registro.put("telefone", cadastro.telefone)

        banco.update(
            "cadastro",
            registro,
            "_id = ${cadastro.id}",
            null
        )
    }

    fun excluir(id: Int) {
        val banco = this.writableDatabase

        banco.delete(
            "cadastro",
            "_id = $id",
            null
        )
    }

    fun pesquisar(id: Int): Cadastro? {
        val banco = this.writableDatabase

        val registros: Cursor = banco.query(
            "cadastro",
            null,
            "_id = $id",
            null,
            null,
            null,
            null
        )

        if (registros.moveToNext()) {
            val cadastro = Cadastro(
                id,
                registros.getString(NOME),
                registros.getString(TELEFONE)
            )
            return cadastro
        } else {
            return null
        }
    }

    fun listar(): MutableList<Cadastro> {
        val banco = this.writableDatabase

        val registros: Cursor = banco.query(
            "cadastro",
            null,
            null,
            null,
            null,
            null,
            null
        )

        var saida = mutableListOf<Cadastro>()

        while (registros.moveToNext()) {

            val cadastro = Cadastro(
                registros.getInt(ID),
                registros.getString(NOME),
                registros.getString(TELEFONE)
            )

            saida.add(cadastro)
        }

        return saida

    }

    companion object {
        private const val ID = 0
        private const val NOME = 1
        private const val TELEFONE = 2
    }

} //fim do DatabaseHandler