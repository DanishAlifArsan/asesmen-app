package com.example.asesmenpaud.activity.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import com.example.asesmenpaud.R
import com.example.asesmenpaud.activity.PenilaianCreateActivity
import com.example.asesmenpaud.activity.PenilaianDetailActivity
import com.example.asesmenpaud.data.ListPenilaianItem
import com.example.asesmenpaud.databinding.PenilaianListBinding
import com.example.asesmenpaud.databinding.TanggalListBinding

class PenilaianAdapter(
    var ctx : Context, var dateList : List<String>, var penilaianList : Map<String, List<ListPenilaianItem>>
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return dateList.size
    }

    override fun getChildrenCount(p0: Int): Int {
        return penilaianList.get(dateList.get(p0))?.size as Int
    }

    override fun getGroup(p0: Int): Any {
        return dateList.get(p0)
    }

    override fun getChild(p0: Int, p1: Int): Any? {
        return penilaianList.get(dateList.get(p0))?.get(p1)
    }

    override fun getGroupId(p0: Int): Long {
       return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        var date: String = getGroup(p0).toString()

//        if (p2 == null) {
//            val inflater : LayoutInflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            p2 = inflater.inflate(R.layout.tanggal_list, null)



//        }
        val binding = TanggalListBinding.inflate(LayoutInflater.from(ctx), p3, false)

        binding.tanggal.text = date

        return binding.root
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        var penilaian : ListPenilaianItem = getChild(p0, p1) as ListPenilaianItem

//        if (p2 == null) {
//            val inflater : LayoutInflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            p2 = inflater.inflate(R.layout.tanggal_list, null)
//        }
        val binding = PenilaianListBinding.inflate(LayoutInflater.from(ctx), p4, false)

        binding.penilaianDesc.text = penilaian.desc?.substring(0, 10) +" ..." // di substring untuk 10 kata pertama

        binding.layout.setOnClickListener {
            val i = Intent(ctx, PenilaianDetailActivity::class.java)
            i.putExtra(PenilaianDetailActivity.PENILAIAN_KEY, penilaian)
            ctx.startActivity(i)
        }

        binding.btnSetting.setOnClickListener{
            showMenu(it, penilaian)
        }

        return binding.root
    }

    fun showMenu(v: View, penilaian : ListPenilaianItem) {
        PopupMenu(ctx, v).apply {
            setOnMenuItemClickListener{
                menuClick(it, penilaian)
            }
            inflate(R.menu.item_menu)
            show()
        }
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    fun menuClick(item: MenuItem, penilaian : ListPenilaianItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                val i = Intent(ctx, PenilaianCreateActivity::class.java)
                i.putExtra(PenilaianCreateActivity.EDIT_PENILAIAN_KEY, penilaian)
                ctx.startActivity(i)
                true
            }
            R.id.action_delete -> {
                AlertDialog.Builder(ctx).apply {
                    setTitle(ctx.getString(R.string.hapus))
                    setMessage(ctx.getString(R.string.konfirmasi_hapus))
                    setPositiveButton(ctx.getString(R.string.ya)) { _, _ ->
//                        viewModel.logout()
                    }
                    setNegativeButton(ctx.getString(R.string.tidak)) { _, _ ->
                        setCancelable(true)
                    }
                    create()
                    show()
                }
                true
            }
            else -> false
        }
    }
}