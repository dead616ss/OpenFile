package com.myapplication.camera

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Bundle
import com.google.android.gms.vision.CameraSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_qr.*
import android.content.Intent
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.myapplication.R


//import com.globalkit.microapps.bus.core.domain.ResponseMessage
//import com.globalkit.microapps.bus.service.Bus
//import com.wokko.mobile.library.resourcedevice.periphery.service.camera.ocr.REQUEST_CAMERA_PERMISSION


class ActivityQR : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    var banderaShouldInit: Boolean = false
    var banderaDenied: Boolean = false
    var banderaShouldFinish: Boolean = false
    lateinit var ed_datos : EditText
    lateinit var linearScaner : LinearLayout
    lateinit var linearbutton : LinearLayout
    lateinit var linearTittle : LinearLayout
    lateinit var linearImgCamera : LinearLayout
    lateinit var imgCamera : ImageView
    lateinit var imgBack : ImageButton
    lateinit var imgClose : ImageButton
    lateinit var btn_enviar : Button
    private var disposable: Disposable? = null


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_qrfullscreen)
        linearbutton = findViewById(R.id.linear_button_capturar)
        linearScaner = findViewById(R.id.linear_qr)
        linearTittle = findViewById(R.id.linear_tittle)
        linearImgCamera = findViewById(R.id.linear_camera)
        imgCamera = findViewById(R.id.img_camera)
        ed_datos =findViewById(R.id.ed_datos)
        btn_enviar = findViewById(R.id.btn_enviar)
        imgBack =findViewById(R.id.btn_back)
        imgClose =findViewById(R.id.btn_close)

        ed_datos.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(s.length == 0)
                {
                    linearbutton.visibility = View.GONE
                    btn_enviar.visibility = View.INVISIBLE
                    linearScaner.visibility = View.VISIBLE
                    linearTittle.visibility = View.VISIBLE
                    linearImgCamera.visibility = View.GONE
                }
                else{
                    linearbutton.visibility = View.VISIBLE
                    btn_enviar.visibility = View.VISIBLE
                    linearScaner.visibility = View.GONE
                    linearTittle.visibility = View.GONE
                    linearImgCamera.visibility = View.VISIBLE

                }

            }
        })

        btn_enviar.setOnClickListener {
            var codigo = ed_datos.text.toString()

            println("#### WOKKO_RESOURCE_DEVICE 1 - $codigo")

            if (codigo.length >= 1){
                val intent = Intent()
                intent.putExtra("exito", codigo)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
        imgCamera.setImageResource(R.drawable.ic_cam)
        imgCamera.setOnClickListener {
            ed_datos.setText("");
        }
        imgBack.setOnClickListener {
            finish()
        }
        imgClose.setOnClickListener {
            finish()
        }
    }


/*    override fun onStart() {
        super.onStart()
        val permission = let { ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA) }
        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission()
        } else if (permission == PackageManager.PERMISSION_GRANTED) {
            openCamera()
        }
    }*/

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }

    fun detectPermission(){
        val permission = let { ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA) }

        if (permission == PackageManager.PERMISSION_DENIED
        ) {
            var i = Intent()
            i.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            i.data = Uri.parse("package: $packageName")
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // context!!.startActivity(i)
            startActivityForResult(i, 100)
        }
    }

    /*@TargetApi(Build.VERSION_CODES.M)
    private fun requestCameraPermission() {

        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) ==true) {
            Log.e("should ","shoul true" )
            banderaShouldInit = true
        } else {
            Log.e("should ","shoul false" )
            banderaShouldInit = false

        }
        requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
    }*/

    fun alertDialogPermissionCustom(){
        var builder = AlertDialog.Builder(this@ActivityQR);
        builder.setTitle("Permisos")
        builder.setMessage("Agrega permisos de cámara para poder utilizar la cámara")
        builder.setPositiveButton("aceptar", DialogInterface.OnClickListener { dialog, which ->
            detectPermission()
        }).setNegativeButton("salir", DialogInterface.OnClickListener { dialog, which ->
            finish();
        }).show()
        builder.create()
    }

  /*  @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            banderaDenied = false

            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) ==false) {
                banderaShouldFinish =false
                Log.e("should request ","shoul false" )
            }
            else{
                banderaShouldFinish =true
                Log.e("should request ","shoul true" )
            }
            if(!banderaShouldInit && !banderaDenied && !banderaShouldFinish) {
                val permission = let { ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA) }
                if (permission == PackageManager.PERMISSION_DENIED) {
                    alertDialogPermissionCustom()
                }

            }
            else if(!banderaShouldInit && !banderaDenied && banderaShouldFinish){
                val permission = let { ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA) }
                if (permission == PackageManager.PERMISSION_DENIED) {
                    this.finish()
                }
                else{
                    openCamera()
                }
            }
            else if(banderaShouldInit && !banderaDenied && banderaShouldFinish){
                val permission = let { ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA) }
                if (permission == PackageManager.PERMISSION_DENIED) {
                    this.finish()
                }
                else{
                    openCamera()
                }
            }
            else if(banderaShouldInit && !banderaDenied && !banderaShouldFinish){
                val permission = let { ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA) }
                if (permission == PackageManager.PERMISSION_DENIED) {
                    this.finish()
                }
                else
                {
                    openCamera()
                }
            }
            return
        }
        if (grantResults.size != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) { // iniciar voy el asyck
            banderaDenied = true
            return
        }

    }
*/
    fun openCamera(){
        disposable = barcodeView
            .drawOverlay()
            .setBeepSound(false)
            .setVibration(0)
            .setAutoFocus(true)
            .setFlash(false)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .getObservable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //Toast.makeText(this@ActivityQR, it.displayValue, Toast.LENGTH_SHORT).show()
                    val intent = Intent()


                    println("#### WOKKO_RESOURCE_DEVICE 2 - ${it.displayValue}")

                    intent.putExtra("exito", it.displayValue)
                    setResult(RESULT_OK, intent)
                    finish()
                },
                {
                    //Toast.makeText(this@ActivityQR, it.message, Toast.LENGTH_LONG).show()
                    val intent = Intent()
                    intent.putExtra("error", it.message)
                    setResult(Activity.RESULT_CANCELED, intent)
                    finish()
                })
    }

}