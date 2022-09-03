package com.example.uiwidget

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.uiwidget.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        disableEditTextContextMenu(binding.editText)        //禁用EditText上下文菜单（copy share paste）




        binding.buttonButton1.setOnClickListener(this)

    }


    /* 重写onClick，实现监测按键按下状态，实现对应响应 */
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button_Button1 -> {
                Toast.makeText(this, "You clicked Button 1", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        /* 屏蔽状态栏和导航栏单
        *  https://developer.android.com/develop/ui/views/layout/immersive */
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        // 配置隐藏的系统栏交互时的行为方式
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // 隐藏状态栏和导航栏
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

//        if (hasFocus) {
//            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION       //允许视图延伸到导航栏区域
//                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN            //允许试图延伸到状态栏区域
//                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION              //隐藏导航栏
//                    or View.SYSTEM_UI_FLAG_FULLSCREEN                   //隐藏状态栏
//                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
//        }
    }

    /* 禁用EditText上下文菜单（copy share paste）
    *  https://www.geeksforgeeks.org/how-to-disable-copy-functionality-in-edittext-in-android/ */
    fun disableEditTextContextMenu(EditText: EditText) {
        // EditText禁用复制功能
        EditText.customSelectionActionModeCallback = object : ActionMode.Callback {
            override fun onCreateActionMode(p0: ActionMode?, p1: Menu?): Boolean {
                return false
            }
            override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
                return false
            }
            override fun onActionItemClicked(p0: ActionMode?, p1: MenuItem?): Boolean {
                return false
            }
            override fun onDestroyActionMode(p0: ActionMode?) {
            }
        }
    }


}