package com.example.androidlabs;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class ChatRoomActivity extends AppCompatActivity {
    ListView lv;
    EditText editMessage;
    List<MessageModel> messageModelList2 = new ArrayList<>();
    Button btnSend;
    Button btnReceive;
    DBAdapter db;
    ChatAdapter mylistAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        lv = (ListView)findViewById(R.id.listView);
        editMessage = (EditText)findViewById(R.id.editMessage);
        btnSend = (Button)findViewById(R.id.btnSend);
        btnReceive = (Button)findViewById(R.id.btnReceive);
        mylistAdapter = new ChatAdapter(messageModelList2, getApplicationContext());
        db =  new DBAdapter(this);
        viewMessage();

        lv.setOnItemClickListener((parent, view, pos, id) -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setTitle("Do you want to delete this?")
                    //What is the message:
                    .setMessage("The selected row to delete is: " + (pos + 1))
                    //what the Yes button does:
                    .setNegativeButton("No", (click, arg) -> {
                    })
                    .setPositiveButton("Yes", (click, arg) -> {
                        messageModelList2.remove(pos);
                        mylistAdapter.notifyDataSetChanged();
                    })
                    .create().show();
        });


        btnSend.setOnClickListener(c -> {
            String message = editMessage.getText().toString();

//            MessageModel model = new MessageModel(message, true);
//            messageModelList2.add(model);

//            ChatAdapter adt = new ChatAdapter(messageModelList2, getApplicationContext());
//            lv.setAdapter(adt);
            db.insertMessage(message, true);
            editMessage.setText("");
            messageModelList2.clear();

            viewMessage();

        });

        btnReceive.setOnClickListener(c -> {
            String message = editMessage.getText().toString();
//            MessageModel model = new MessageModel(message, false);
//            messageModelList2.add(model);

//            ChatAdapter adt = new ChatAdapter(messageModelList2, getApplicationContext());
//            lv.setAdapter(adt);
            db.insertMessage(message, false);
            editMessage.setText("");
            messageModelList2.clear();

            viewMessage();

        });

        Log.e("ChatRoomActivity","onCreate");

    }

    private void viewMessage(){
        Cursor cursor = db.getAllMessages();
        //hien so message trong db
        Log.d("message",String.valueOf(cursor.getCount()));

        if (cursor.getCount() != 0){
            while (cursor.moveToNext()){
                MessageModel model = new MessageModel(cursor.getString(1), cursor.getInt(2)==0?true:false);
                messageModelList2.add(model);
                ChatAdapter adt = new ChatAdapter(messageModelList2, getApplicationContext());
                lv.setAdapter(adt);

            }
        }
    }


}

class MessageModel {
    public String message;
    public boolean isSend;

    public MessageModel(String message, boolean isSend) {
        this.message = message;
        this.isSend = isSend;
    }

    public MessageModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }
}
//test folk
class ChatAdapter extends BaseAdapter {
    private List<MessageModel> messageModelList;
    private Context context;
    private LayoutInflater inflater;

    public ChatAdapter(List<MessageModel> messageModels, Context context) {
        messageModelList = messageModels;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return messageModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null){
            if (messageModelList.get(position).isSend()){
                view = inflater.inflate(R.layout.activity_main_send, null);

            }else {
                view = inflater.inflate(R.layout.activity_main_receive, null);
            }
            TextView  messageText = (TextView)view.findViewById(R.id.messageText);
            messageText.setText(messageModelList.get(position).message);
        }
        return view;
    }
}