package com.gw.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gw.chat.bean.Msg;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "ChatActivity";
    private List<Msg> msgList = new ArrayList<Msg>();

    private EditText etInput;
    private Button btnSend;
    private RecyclerView rvMsg;
    private MsgAdapter msgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initMsg();
        etInput = (EditText) findViewById(R.id.et_input);
        btnSend = (Button) findViewById(R.id.btn_send);
        rvMsg = (RecyclerView) findViewById(R.id.rv_msg);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvMsg.setLayoutManager(manager);
        msgAdapter = new MsgAdapter(msgList);
        rvMsg.setAdapter(msgAdapter);
        btnSend.setOnClickListener(this);

    }

    private void initMsg() {
        Msg msg1 = new Msg("Hello Hello !!! How are you?",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello Hello !!! What ?",Msg.TYPE_RECEIVED);
        msgList.add(msg2);
        Msg msg3 = new Msg("Hello Hello !!! Good Bye?",Msg.TYPE_RECEIVED);
        msgList.add(msg3);

    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick: "+v.getId());
        switch(v.getId()){
            case R.id.btn_send:
                Toast.makeText(this,"发送",Toast.LENGTH_SHORT);
                String content = etInput.getText().toString();
                if(!TextUtils.isEmpty(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SEND);
                    msgList.add(msg);
                    msgAdapter.notifyItemInserted(msgList.size() - 1);
                    rvMsg.scrollToPosition(msgList.size() - 1);
                    etInput.setText("");
                }
                break;
            default:
                break;
        }
    }
}
