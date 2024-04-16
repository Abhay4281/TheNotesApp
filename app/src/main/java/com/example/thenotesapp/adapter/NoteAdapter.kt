package com.example.thenotesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.thenotesapp.databinding.NoteLayoutBinding
import com.example.thenotesapp.fragments.HomeFragmentDirections
import com.example.thenotesapp.model.Note

class NoteAdapter:RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    //one recyclerview present in home for that create adapter and a view holder which basically attach data to respective ui

    //viewholder is used in attaching xml layout file here we use binding
    //whenever we want to access anything from notelayout we will write itembindg list of all ui componenet will be displayed
    /*itemcallback is used to determine the difference between twolist
    * areitemthesame is called to check whether the item represent the same object in node data class
    * compares id title desc of old item with new items
    * if properties are same it considers item as same
    * arecontentthesame is used to check if content is same in the note data class
    *AsyncListDiffer determine difference betweeen two list in a background thread which helps in smooth ui update
     */




    class NoteViewHolder(val itemBinding: NoteLayoutBinding):RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteDesc == newItem.noteDesc &&
                    oldItem.noteTitle == newItem.noteTitle
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem==newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallBack)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=differ.currentList[position]

        holder.itemBinding.noteTitle.text=currentNote.noteTitle
        holder.itemBinding.noteDesc.text=currentNote.noteDesc

        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(direction)
        }
    }


}