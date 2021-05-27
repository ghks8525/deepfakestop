package com.deepfakestop

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.deepfakestop.databinding.ActivityHomeBinding


class HomeActivity:AppCompatActivity() {
    private lateinit var mBinding:ActivityHomeBinding
    private val OPEN_GALLERY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        mBinding.listener = this

    }

    fun onClick(v: View){
        when(v.id){
            R.id.ah_btn_search_file -> {
                val uri: Uri = Uri.parse("content://media/external/images/media")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.action = Intent.ACTION_GET_CONTENT
                intent.type = "video/*"
                startActivityForResult(intent, OPEN_GALLERY)
            }

            R.id.ah_btn_upload -> {
                startActivity(Intent(applicationContext, ResultActivity::class.java))

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

            if(requestCode == OPEN_GALLERY){
                val loading = LoadingDialog()
                loading.show(supportFragmentManager,null)
                Handler().postDelayed(Runnable {
                    loading.dismiss()
                    mBinding.ahIvImg.visibility = View.VISIBLE
                },3000)
                var videoUrl = data?.data

                try{
                    Log.d("a", "${videoUrl.toString()}")
                    var bitmap = ThumbnailUtils.createVideoThumbnail(getFullPathFromUri(applicationContext,videoUrl!!)!!, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND)
                    mBinding.ahIvImg.setImageBitmap(bitmap)
                }catch (e:Exception){
                    e.printStackTrace()
                }

        }
    }

    fun getFullPathFromUri(ctx: Context, fileUri: Uri?): String? {
        var fullPath: String? = null
        val column = "_data"
        var cursor: Cursor? =
            fileUri?.let { ctx.getContentResolver().query(it, null, null, null, null) }
        if (cursor != null) {
            cursor.moveToFirst()
            var document_id: String = cursor.getString(0)
            if (document_id == null) {
                for (i in 0 until cursor.getColumnCount()) {
                    if (column.equals(cursor.getColumnName(i), ignoreCase = true)) {
                        fullPath = cursor.getString(i)
                        break
                    }
                }
            } else {
                document_id = document_id.substring(document_id.lastIndexOf(":") + 1)
                cursor.close()
                val projection = arrayOf(column)
                try {
                    cursor = ctx.getContentResolver().query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        projection,
                        MediaStore.Images.Media._ID + " = ? ",
                        arrayOf(document_id),
                        null
                    )
                    if (cursor != null) {
                        cursor.moveToFirst()
                        fullPath = cursor.getString(cursor.getColumnIndexOrThrow(column))
                    }
                } finally {
                    if (cursor != null) cursor.close()
                }
            }
        }
        return fullPath
    }


}