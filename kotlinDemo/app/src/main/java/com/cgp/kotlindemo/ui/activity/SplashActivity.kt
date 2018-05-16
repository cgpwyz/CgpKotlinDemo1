package com.cgp.kotlindemo.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import android.widget.Toast
import com.cgp.kotlindemo.MyApplication
import com.cgp.kotlindemo.R
import kotlinx.android.synthetic.main.activity_splash.*
import me.weyye.hipermission.HiPermission
import me.weyye.hipermission.PermissionCallback
import me.weyye.hipermission.PermissionItem

class SplashActivity : AppCompatActivity() {
    private var mScaleAnimation: ScaleAnimation? = null//改变量可以为空
    private var mAnimationSet: AnimationSet? = null//改变量可以为空

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startAnimation()
    }

    /**
     * 播放动画
     */
    fun startAnimation() {
        //渐变动画
        val mAlphaAnimation = AlphaAnimation(0f, 1.0f)//val必须初始化并且不可被改变
        mAlphaAnimation.duration = 2000
        //拉伸动画
        mScaleAnimation = ScaleAnimation(0f, 1f, 0f, 1f)
        mScaleAnimation?.duration = 2000
        //动画组合
        mAnimationSet = AnimationSet(true)
        mAnimationSet?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })
        //添加动画
        mAnimationSet?.addAnimation(mScaleAnimation)//如果变量为空就不调用方法
        mAnimationSet?.addAnimation(mAlphaAnimation)
        checkPermission()
    }

    /**
     * 运行时权限
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
    private fun checkPermission() {
        Toast.makeText(this@SplashActivity, "初始化完毕开始动画" + MyApplication.context, Toast.LENGTH_SHORT)
        val permissionItems = ArrayList<PermissionItem>()
        permissionItems.add(PermissionItem(android.Manifest.permission.READ_PHONE_STATE, "手机状态", R.drawable.permission_ic_phone))
        permissionItems.add(PermissionItem(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储空间", R.drawable.permission_ic_storage))
        HiPermission.create(this)
                .title("亲爱的大佬")
                .msg("为了能装13，请开启这些权限")
                .permissions(permissionItems)
                .style(R.style.PermissionDefaultBlueStyle)
                .animStyle(R.style.PermissionAnimScale)
                .checkMutiPermission(object : PermissionCallback {
                    override fun onFinish() {
                        //让view去播放动画
                        layout_splash.startAnimation(mAnimationSet)
                    }

                    override fun onDeny(permission: String?, position: Int) {
                    }

                    override fun onGuarantee(permission: String?, position: Int) {
                    }

                    override fun onClose() {
                    }

                })
    }
}
