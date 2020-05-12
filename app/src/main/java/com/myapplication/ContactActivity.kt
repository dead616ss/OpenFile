package com.myapplication

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.Toast


class ContactActivity :Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var open = findViewById<Button>(R.id.buttonopen)

        open.setOnClickListener(){
            findDetail()
        }
    }

    fun findDetail() {

        var result: Array<String>? = null

        // Find a contact using a partial name match
        val searchName = "P"
        val lookupUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, searchName)

        // Create a projection of the required column names.
        var projection = arrayOf<String>(ContactsContract.Contacts._ID)

        // Get a Cursor that will return the ID(s) of the matched name.
        val idCursor = contentResolver.query(lookupUri, projection, null, null, null)

        // Extract the first matching ID if it exists.
        var id: String? = null
        if (idCursor!!.moveToFirst())
        {
            val idIdx = idCursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID)
            id = idCursor.getString(idIdx)
        }
        // Close that Cursor.
        idCursor.close()

        // Create a new Cursor searching for the data associated with the
        // returned Contact ID.
        if (id != null)
        {
            // Return all the PHONE data for the contact.
            val where = (ContactsContract.Data.CONTACT_ID + " = " + id
                    + "AND" + ContactsContract.Data.MIMETYPE + " = ‘"
                    + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                    + "’")
            projection = arrayOf<String>(ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER)

            val dataCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, projection, where, null, null)

            // Get the indexes of the required columns.
            val nameIdx = dataCursor!!.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME)
            val phoneIdx = dataCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)
            //result = arrayOfNulls<String>(dataCursor.count())
                result = arrayOf(arrayOfNulls<String>(dataCursor.count).toString())
            while (dataCursor.moveToNext())
            {
                // Extract the name.
                val name = dataCursor.getString(nameIdx)

                // Extract the phone number.
                val number = dataCursor.getString(phoneIdx)
                result?.set(dataCursor.position, "$name($number)")
                Toast.makeText(this, "$name($number)", Toast.LENGTH_SHORT).show()
            }
            dataCursor.close()
        }
    }

}