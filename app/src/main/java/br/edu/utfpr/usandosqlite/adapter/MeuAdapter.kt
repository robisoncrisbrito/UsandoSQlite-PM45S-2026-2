package br.edu.utfpr.usandosqlite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.edu.utfpr.usandosqlite.R
import br.edu.utfpr.usandosqlite.entity.Cadastro

class MeuAdapter(val context: Context, val registros: MutableList<Cadastro>) : BaseAdapter() {
    override fun getCount(): Int {
        return registros.size
    }

    override fun getItem(pos: Int): Any? {
        val cadastro = registros.get( pos )
        return cadastro
    }

    override fun getItemId(pos: Int): Long {
        return registros.get(pos).id.toLong()
    }

    override fun getView(
        pos: Int,
        p1: View?,
        p2: ViewGroup?
    ): View? {

        val inflater = context.getSystemService( Context.LAYOUT_INFLATER_SERVICE ) as LayoutInflater
        val elementoLista = inflater.inflate( R.layout.elemento_lista, null )

        val tvNomeElementoLista = elementoLista.findViewById<TextView>( R.id.tvNomeElementoLista )
        val tvTelefoneElementoLista = elementoLista.findViewById<TextView>( R.id.tvTelefoneElementoLista )

        val cadastro = registros.get(pos)

        tvNomeElementoLista.text = cadastro.nome
        tvTelefoneElementoLista.text = cadastro.telefone

        return elementoLista
    }

    }

}