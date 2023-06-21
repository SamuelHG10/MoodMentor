package com.example.chatgpt

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatgpt.adapter.UserAdapter
import com.example.chatgpt.model.user


class UsersActivity : AppCompatActivity() {
    var userList = ArrayList<user>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        //userRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


        val userList = ArrayList<user>()

        userList.add(user("farchan","https://cdn.pixabay.com/photo/2014/11/30/14/11/cat-551554_640.jpg"))
        userList.add(user("farchan","https://cdn.pixabay.com/photo/2014/11/30/14/11/cat-551554_640.jpg"))
        userList.add(user("farchan","https://cdn.pixabay.com/photo/2014/11/30/14/11/cat-551554_640.jpg"))
        userList.add(user("farchan","https://cdn.pixabay.com/photo/2014/11/30/14/11/cat-551554_640.jpg"))
        userList.add(user("farchan","https://cdn.pixabay.com/photo/2014/11/30/14/11/cat-551554_640.jpg"))
        userList.add(user("farchan","https://cdn.pixabay.com/photo/2014/11/30/14/11/cat-551554_640.jpg"))
        userList.add(user("farchan","https://cdn.pixabay.com/photo/2014/11/30/14/11/cat-551554_640.jpg"))

        var userAdapter = UserAdapter(this, userList)

        //userRecyclerView.adapter = UserAdapter
    }
}