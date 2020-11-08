
package com.example.androidlabs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class ChatRoomActivity extends AppCompatActivity {
    private ArrayList<Message> messagesList = new ArrayList<>();
    private MyListAdapter myListAdapter;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        Button sendBtn = findViewById(R.id.btnSend);
        Button receiveBtn = findViewById(R.id.btnReceive);
        ListView myList = findViewById(R.id.listView);
        EditText msgEditText = findViewById(R.id.editMessage);

        loadDataFromDatabase();
        myList.setAdapter(myListAdapter = new MyListAdapter());

        sendBtn.setOnClickListener(click -> {
            String text = msgEditText.getText().toString();
            if (text.length() > 0) {
                ContentValues newRowValues = new ContentValues();

                newRowValues.put(MyOpener.COL_MSG, text);
                newRowValues.put(MyOpener.COL_SENT, 0);

                long newId = db.insert(MyOpener.TABLE_NAME, null, newRowValues);


                Message message = new Message(newId, text, true);
                messagesList.add(message);
                myListAdapter.notifyDataSetChanged();
                msgEditText.setText("");
            }

        });

        receiveBtn.setOnClickListener(click -> {
            String text = msgEditText.getText().toString();
            if (text.length() > 0) {
                ContentValues newRowValues = new ContentValues();

                newRowValues.put(MyOpener.COL_MSG, text);
                newRowValues.put(MyOpener.COL_SENT, 1);

                long newId = db.insert(MyOpener.TABLE_NAME, null, newRowValues);


                Message message = new Message(newId, text, false);
                messagesList.add(message);
                myListAdapter.notifyDataSetChanged();
                msgEditText.setText("");
            }

        });

        myList.setOnItemClickListener((parent, view, pos, id) -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Do you want to delete this?")
                  //  .setMessage("The selected row is: " + pos + ", The database id is: " + id)
                    .setPositiveButton("yes", (click, arg) -> {
                        deleteMsg(id);
                        messagesList.remove(pos);
                        myListAdapter.notifyDataSetChanged();

                    })
                    .setNegativeButton("No", (click, arg) -> {
                    })
                    .create().show();

        });
        String[] columns = {MyOpener.COL_ID, MyOpener.COL_MSG, MyOpener.COL_SENT};
        Cursor results = db.query(false, MyOpener.TABLE_NAME, columns, null, null, null, null, null, null);
        printCursor(results, db.getVersion());

    }

    protected void loadDataFromDatabase() {
        MyOpener dbOpener = new MyOpener(this);
        db = dbOpener.getWritableDatabase();

        String[] columns = {MyOpener.COL_ID, MyOpener.COL_MSG, MyOpener.COL_SENT};
        Cursor results = db.query(false, MyOpener.TABLE_NAME, columns, null, null, null, null, null, null);

        int msgColumnIndex = results.getColumnIndex(MyOpener.COL_MSG);
        int isSendColumnIndex = results.getColumnIndex(MyOpener.COL_SENT);
        int idColumnIndex = results.getColumnIndex(MyOpener.COL_ID);

        while (results.moveToNext()) {
            String msg = results.getString(msgColumnIndex);
            long isSendLong = results.getLong(isSendColumnIndex);
            boolean isSend;
            if (isSendLong == 0) {
                isSend = true;
            } else {
                isSend = false;
            }
            long id = results.getLong(idColumnIndex);
            messagesList.add(new Message(id, msg, isSend));
        }


    }

    protected void deleteMsg(long id) {
        db.delete(MyOpener.TABLE_NAME, MyOpener.COL_ID + "= ?", new String[]{Long.toString(id)});
    }

    protected void printCursor(Cursor c, int version) {
        Log.e("Cursor_Info", "database version: " + version);
        Log.e("Cursor_Info", "number of columns: " + c.getColumnCount());
        Log.e("Cursor_Info", "name of columns: " + Arrays.toString(c.getColumnNames()));
        Log.e("Cursor_Info", "name of rows: " + c.getCount());
        if (c != null && c.moveToFirst()) {

            do {
                Log.e("Cursor_Info", "row: " + c.getPosition() + "{ id: " + c.getInt(c.getColumnIndex(MyOpener.COL_ID)) + " msg: " + c.getString(c.getColumnIndex(MyOpener.COL_MSG)) + " isSend: " + c.getInt(c.getColumnIndex(MyOpener.COL_SENT)) + " }");

            } while (c.moveToNext());

        }
    }


    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return messagesList.size();
        }

        @Override
        public Object getItem(int position) {
            return messagesList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) messagesList.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Message message = (Message) getItem(position);
            LayoutInflater inflater = getLayoutInflater();


            View extraView = inflater.inflate(
                    (message.isSent() ? R.layout.activity_main_receive : R.layout.activity_main_send)
                    , parent, false);

            TextView textView = extraView.findViewById(R.id.messageText);
            textView.setText(message.getMessage());

            return extraView;
        }
    }


}