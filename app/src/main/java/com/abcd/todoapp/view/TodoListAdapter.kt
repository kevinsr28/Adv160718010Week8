package com.abcd.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.abcd.todoapp.R
import com.abcd.todoapp.databinding.TodoItemLayoutBinding
import com.abcd.todoapp.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todolist: ArrayList<Todo>, val adapterOnClick:(Any) -> Unit):RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>(),
    TodoCheckedChangeListener,TodoEditClickListener {
    class TodoListViewHolder(var view: TodoItemLayoutBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.todo_item_layout,parent,false)
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater,
            R.layout.todo_item_layout,parent,false)
        return TodoListViewHolder(view)
    }

    fun updateTodoList(newTodoList:List<Todo>){
        todolist.clear()
        todolist.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.view.todo = todolist[position]
        holder.view.listener = this
        holder.view.editlistener = this
//        holder.view.checkTask.text = todolist[position].title + " " + todolist[position].priority
//
//        holder.view.imgEdit.setOnClickListener{
//            val action = TodoListFragmentDirections.actionEditTodoFragment(todolist[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        holder.view.checkTask.setOnCheckedChangeListener{
//            compoundButton, isChecked->
//            if(isChecked){
//                adapterOnClick(todolist[position].uuid)
//            }
//        }
    }

    override fun getItemCount(): Int {
        return todolist.size
    }

    override fun onTodoCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked){
                adapterOnClick(obj.uuid)
        }
    }

    override fun onTodoEditClick(v: View) {
        val action = TodoListFragmentDirections.actionEditTodoFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

}