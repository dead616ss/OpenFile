package com.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import android.provider.ContactsContract
import android.util.Base64
import android.util.Base64OutputStream
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.io.*
import com.myapplication.camera.ActivityQR



class MainActivity : AppCompatActivity() {


    @SuppressLint("SdCardPath")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var open = findViewById<Button>(R.id.buttonopen)
        var search = findViewById<Button>(R.id.search)
        var nameSearch = findViewById<EditText>(R.id.editText)

        search.setOnClickListener(){
            val text = nameSearch.text.toString()
            Log.i("Search Name","$text")
            getSearchContact(text)


        }

        open.setOnClickListener{

           /* val typefile = "VID_20200506_222246.mp4" //6
            val path = File(Environment.getExternalStorageDirectory().absolutePath,"/DCIM/Camera/file")
            val selectedVideoFile = File(path, typefile)  // 2
            Log.i("Mensaje","Directorio: $selectedVideoFile")
            //val selectedVideoFile = File(Environment.getExternalStorageDirectory(),selectedVideoFilePath)  // 2
            //val selectedVideoFile = File(selectedVideoFilePath)  // 2
            val selectedVideoFileExtension : String = selectedVideoFile.extension  // 3
            val internalStorageVideoFileName : String = UUID.randomUUID().toString().plus(selectedVideoFileExtension)  // 4
            this.storeFileInInternalStorage(selectedVideoFile, internalStorageVideoFileName)*/

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

            //savefile()
            //retrivefile()

            /******************************base64***********************/
            /*val fileName = "getExternalStorageDirectory.txt"
            val contentData = "getExternalFilesDir(Environment.DIRECTORY_MUSIC) demo pruebas y cambios"

            //val path = File(Environment.getExternalStorageDirectory().path,"/temp")
            val dir = File(Environment.getExternalStorageDirectory().path)
            val path: String = File(dir,"/temp").toString()
            this.fileDataToPathBase64(path, fileName*//*,contentData *//*)*/

            /***********************allcontacts**************************/
           /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getAllContactsVcardUri()
            }*/
            /**************************contactos**************************/
            getContacts()
            //getSearchContact()
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
        val outputStream =  application.openFileOutput(internalStorageFileName, Context.MODE_PRIVATE) //2
        try {
            val buffer = ByteArray(2048)
            inputStream.use {  // 3
                while (true) {
                    val byeCount = it.read(buffer)  // 4
                    if (byeCount < 0) break
                    outputStream.write(buffer, 0, byeCount)  // 5
                }
                outputStream.close()  // 6
            }
        }catch (e: FileNotFoundException){
            e.printStackTrace()
            Log.i("Message","Failed: ${e.message} ")
        }finally {
            try {
                Toast.makeText(this,"Write to <" + selectedFile.absolutePath + "> successfully!",Toast.LENGTH_LONG).show()
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
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
            val path = Environment.getExternalStorageDirectory().absolutePath + "/temp"
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
        }catch (e: FileNotFoundException){
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
            val path = Environment.getExternalStorageDirectory().absolutePath + "/temp"
            val fileName = "getExternalStorageDirectory.txt"
            this.retriveDataPath(path, fileName)
        }else{
            Toast.makeText(this,"Cannot read the file",Toast.LENGTH_SHORT).show()
        }
    }

    /**************************implementacion base64***************************/

    private fun fileDataToPathBase64(path: String, fileName: String/*, data:String*/): String {
   val targetFilePath = File(path, fileName)
   val inputStream: InputStream?
   var encodedFile = ""
   val lastVal: String

   try {
       /*val fos =  FileOutputStream(targetFilePath)
       fos.write(data.toByteArray())*/
       inputStream = FileInputStream(targetFilePath)
       Log.i("Message","Ruta: $targetFilePath")
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

    /*************************************************************************************************************/

    /************************contactos*************************/


   /* @SuppressLint("Recycle")
    fun getContacts():List<Map<String,String>> {
        val contacts = ArrayList<Map<String,String>>()
        val uniqueValues = HashSet<String>()

        val uri =  ContactsContract.CommonDataKinds.Phone.CONTENT_URI

        val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE)

        //val people = mainActivity.baseContext.applicationContext.contentResolver.query(uri, projection, null, null, null)
        val people = contentResolver.query(uri, projection, null, null, null)

        println("----people=${people}---------")
        val indexName = people!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val indexNumber = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        val indexPhoneType = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE)


        people.moveToFirst()
        do {
            if (!uniqueValues.contains(people.getString(indexNumber))) {
                val contactsModel = HashMap<String,String>()
                contactsModel["displayName"] = people.getString(indexName)
                contactsModel["mobileNumber"] = people.getString(indexNumber)
                contactsModel["phoneType"] = people.getString(indexPhoneType)

                uniqueValues.add(people.getString(indexNumber))
                contacts.add(contactsModel)
                println("---Step 2.n---contactsModel=${contactsModel}")
            }
        } while (people.moveToNext())
        println("---Step 3---contacts=${contacts}")
        return contacts
    }*/

    @SuppressLint("Recycle")
    fun getContacts():List<Map<String,String>> {
        val contacts = ArrayList<Map<String,String>>()
        val uniqueValues = HashSet<String>()

        val uri =  ContactsContract.CommonDataKinds.Phone.CONTENT_URI

        val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE)

        //val people = mainActivity.baseContext.applicationContext.contentResolver.query(uri, projection, null, null, null)
        val people = contentResolver.query(uri, projection, null, null, null)

        println("----people=${people}---------")
        val indexName = people!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val indexNumber = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        val indexPhoneType = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE)


        people.moveToFirst()
        do {
            if (!uniqueValues.contains(people.getString(indexNumber))) {
                val contactsModel = HashMap<String,String>()
                contactsModel["displayName"] = people.getString(indexName)
                contactsModel["mobileNumber"] = people.getString(indexNumber)
                contactsModel["phoneType"] = people.getString(indexPhoneType)

                uniqueValues.add(people.getString(indexNumber))
                contacts.add(contactsModel)
                println("---Step 2.n---contactsModel=${contactsModel}")
            }
        } while (people.moveToNext())
        println("---Step 3---contacts=${contacts}")
        return contacts
    }

    /*********************************************************************************************************/
    /*************************************************all contacts********************************************/

    /*@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getAllContactsVcardUri(): Uri? {
        val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
            arrayOf(ContactsContract.Contacts.LOOKUP_KEY),null,null,null)

        if (cursor == null){
            return null
        }
        try {
            val uriListBuilder = StringBuilder()
            var index = 0
            while (cursor.moveToNext()){
                if (index != 0)
                    uriListBuilder.append(':')
                index++
            }
            Log.i("Messega","contacts: $cursor")
            return Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_MULTI_VCARD_URI,Uri.encode(uriListBuilder.toString()))
        }finally {
            cursor.close()
        }
    }*/

    /*****************************search*********************************/

   /* @SuppressLint("Recycle")
    fun getSearchContact(): ArrayList<Map<String, String>> {
        val contacts = ArrayList<Map<String,String>>()
        //val contacts = ArrayList<String>()
        val uniqueValues = HashSet<String>()

        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

        // Sets the columns to retrieve for the user contacts
       val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        // Retrieves the contact from the Contacts Provider
        val profileCursor = contentResolver.query(uri, projection,null,null,null)



        profileCursor!!.moveToFirst()
        do{
            if (!uniqueValues.contains(profileCursor.getString(1))){
                val contactsModel = HashMap<String,String>()
                contactsModel["displaName"] = profileCursor.getString(1)
                contactsModel["mobileNumber"] = profileCursor.getString(2)
                uniqueValues.add(profileCursor.getString(1))
                contacts.add(contactsModel)
                println("ContactArray: ${profileCursor.getString(1)} " + " ${profileCursor.getString(2)}")
            }
        }while (profileCursor.moveToNext())
        return contacts

    }*/

    /*******************************************************************************************************************/

    @SuppressLint("Recycle")
    fun getSearchContact(nameSearch : String): ArrayList<Map<String, String>> {
        val contacts = ArrayList<Map<String,String>>()

        val uniqueValues = HashSet<String>()

        val uri = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI,Uri.encode(nameSearch))

        // Sets the columns to retrieve for the user contacts
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER)

        // Retrieves the contact from the Contacts Provider
        val profileCursor = contentResolver.query(uri, projection, null,null,"DISPLAY_NAME ASC")
        profileCursor!!.moveToFirst()

       do{
            if (profileCursor.count > 0){
                val contactsModel = HashMap<String,String>()
                if(!uniqueValues.contains(profileCursor.getString(1))){
                contactsModel["idName"] = profileCursor.getString(0)
                contactsModel["displayName"] = profileCursor.getString(1)
                contactsModel["mobileNumber"] = profileCursor.getString(2)
                uniqueValues.add(profileCursor.getString(1))
                contacts.add(contactsModel)
                println("ContactArray: ${profileCursor.getString(1)} " + " ${profileCursor.getString(2)}")
                }
            }else{
                Log.i("Message","Contact does not exist")
            }
       }
           while (profileCursor.moveToNext())
            profileCursor.close()
        return contacts
    }

    /*****************************************scanfunctionalityservice******************************************/


}






