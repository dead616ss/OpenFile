package com.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import android.util.Base64
import android.util.Base64OutputStream
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.io.*


class MainActivity : AppCompatActivity() {


    @SuppressLint("SdCardPath")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var open = findViewById<Button>(R.id.buttonopen)

        open.setOnClickListener{

            /*val typefile = "VID_20200506_141204.mp4"
            val selectedVideoFilePath = typefile  // 1
            val path = File(Environment.getExternalStorageDirectory().absolutePath,"/DCIM/Camera")
            val selectedVideoFile = File(path,selectedVideoFilePath)  // 2
            Log.i("Mensaje","Directorio: $selectedVideoFile")
            //val selectedVideoFile = File(Environment.getExternalStorageDirectory(),selectedVideoFilePath)  // 2
            //val selectedVideoFile = File(selectedVideoFilePath)  // 2
            val selectedVideoFileExtension : String = selectedVideoFile.extension  // 3
            val internalStorageVideoFileName : String = UUID.randomUUID().toString().plus(selectedVideoFileExtension)  // 4
            this.storeFileInInternalStorage(selectedVideoFile, internalStorageVideoFileName)
*/

            /***********************************************************************************/
            //Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            //availableMemorySD()
            //isExternalStorageWritable()
            //isExternalStorageReadable()
            /***************************************/
            /*val file = File(Environment.getExternalStorageDirectory().absolutePath + "/example.pdf")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.fromFile(file), "application/pdf")
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)*/
            /**************************************/
            /*val namefile = ""
            val recoverySD = Environment.getExternalStorageDirectory()
            val file = File(recoverySD.absolutePath,namefile)
            try {
                val inRecovery = FileInputStream(file)
                val  outRecovery = InputStreamReader(inRecovery)
            }catch (e : Exception){
                e.printStackTrace()
            }*/
            /******************************************/
            /*val selectedFilePath = "/storage/sdcard0/download/example.pdf"  // 1
            val selectedFile = File(selectedFilePath)  // 2
            val selectedFileExtension : String = selectedFile.extension  // 3
            val internalStorageFileName : String = UUID.randomUUID().toString().plus(selectedFileExtension)  // 4
            storeFileInInternalStorage(selectedFile, internalStorageFileName)  // 5*/
            /*******************************************/
           /* val path = Environment.getExternalStorageDirectory()
            val fileName = "getExternalStorageDirectory.txt"
            val contentData = "getExternalStorageDirectory() demo"
            this.fileDataToPathBase64(path, fileName*//*, contentData*//*)*/
            /*--------------------------------------------*/
            /*val path = Environment.getExternalStorageState()
            val fileName = "getExternalFilesDir.txt"
            val contentData = "getExternalFilesDir(Environment.DIRECTORY_MUSIC) demo"
            this.writeDataToPath(path, fileName, contentData);*/
            /*--------------------------------------------*/
            /*val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val fileName = "getExternalStoragePublicDirectory.txt"
            val contentData = "getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) demo"
            this.writeDataToPath(path, fileName, contentData);*/
            /*_________________________________________________*/

            savefile()
            //retrivefile()

            /******************************base64***********************/
            /*val path = File(Environment.getExternalStorageDirectory(),"temp")
            val fileName = "getExternalStorageDirectory.txt"
            this.fileDataToPathBase64(path, fileName)*/
        }

    }


    // Available Memory
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    fun availableMemorySD(): Long {
        val statFs = StatFs(Environment.getExternalStorageDirectory().absolutePath)
        val total = (statFs.blockCountLong * statFs.blockSizeLong)
        val free = (statFs.availableBlocksLong * statFs.blockSizeLong)
        val busy = total - free
        val available = total - busy
        println("Ocupada: $busy")
        println("Disponible: $available")
        return available
    }

    fun isExternalStorageWritable(): Boolean{
        val state = Environment.getExternalStorageState()
        if(Environment.MEDIA_MOUNTED == state){
            return true
        }
        return false
    }

    fun isExternalStorageReadable(): Boolean{
        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state){
            return true
        }
        return false
    }

    private fun storeFileInInternalStorage(selectedFile: File, internalStorageFileName: String) {
        val inputStream = FileInputStream(selectedFile) // 1
        try {
            val outputStream = application.openFileOutput(internalStorageFileName, Context.MODE_PRIVATE) //2
            val buffer = ByteArray(2048)
            inputStream.use {  // 3
                while (true) {
                    val byeCount = it.read(buffer)  // 4
                    if (byeCount < 0) break
                    outputStream.write(buffer, 0, byeCount)  // 5
                }
                //outputStream.close()  // 6
            }
        }catch (e: FileNotFoundException){
            e.printStackTrace()
            Log.i("Message","Failed: ${e.message} ")
        }finally {
            if (inputStream != null) {
                try {
                    Toast.makeText(this,"Write to <" + selectedFile.absolutePath + "> successfully!",Toast.LENGTH_LONG).show()
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(this, "Failed to Read!", Toast.LENGTH_LONG).show()
            }
        }

    }
    /*******************************Saved***************************************/
    private fun writeDataToPath(path: String, fileName: String) {
        val targetFilePath = File(path, fileName)
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(targetFilePath)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Failed: " + e.message, Toast.LENGTH_LONG).show()
        } finally {
            if (fos != null) {
                try {
                    Toast.makeText(this,"Write to <" + targetFilePath.absolutePath + "> successfully!",Toast.LENGTH_LONG).show()
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(this, "Failed to write!", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun savefile(){
        if (isExternalStorageWritable() && checkExternalStoragePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            val path = Environment.getExternalStorageDirectory().absolutePath + "/temp/app"
            val dir = File(path)
            if (!dir.exists()) {
                dir.mkdir()
            }
            val fileName = "getExternalStorageDirectory.txt"
            this.writeDataToPath(path, fileName)
        }else{
            Toast.makeText(this,"Cannot write to memory",Toast.LENGTH_SHORT).show()
        }
    }

    fun checkExternalStoragePermission(permission: String): Boolean {
        val permissionCheck = ContextCompat.checkSelfPermission(this, permission )
        return (permissionCheck == PackageManager.PERMISSION_GRANTED)
    }

    /*******************************Retrive**************************************/

    fun retriveDataPath(path: String, fileName: String){
        val targetFilePath = File(path, fileName)
        var fos: FileInputStream? = null
        try {
            fos = FileInputStream(targetFilePath)
            fos.close()
        }catch (e: IOException){
            e.printStackTrace()
            Toast.makeText(this, "Failed: " + e.message, Toast.LENGTH_LONG).show()
        }finally {
            if (fos != null) {
                try {
                    Toast.makeText(this,"Read to <" + targetFilePath.absolutePath + "> successfully!",Toast.LENGTH_LONG).show()
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(this, "Failed to Read!", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun retrivefile(){
        if (isExternalStorageWritable() && checkExternalStoragePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            val path = Environment.getExternalStorageDirectory().absolutePath + "/temp/"
            val fileName = "getExternalStorageDirectory.txt"
            this.retriveDataPath(path, fileName)
        }else{
            Toast.makeText(this,"Cannot read the file",Toast.LENGTH_SHORT).show()
        }
    }

    /**************************implementacion base64***************************/

    private fun fileDataToPathBase64(path: File, fileName: String): String {
   val targetFilePath = File(path, fileName)
   var inputStream: InputStream?
   var encodedFile = ""
   val lastVal: String

   try {
       inputStream = FileInputStream(targetFilePath)
       val buffer = ByteArray(1024)
       var bytesRead: Int
       val outPut = ByteArrayOutputStream()
       val outPut64 = Base64OutputStream(outPut, Base64.DEFAULT)
       while(inputStream.read(buffer).also { bytesRead = it } != -1) {
           outPut64.write(buffer,0,bytesRead)
       }
       outPut64.close()
       encodedFile = outPut.toString()
   } catch (e1: FileNotFoundException){
       e1.printStackTrace()
   }
   lastVal = encodedFile
        Log.i("Message","Conversion: $lastVal")
   return lastVal
}

}



