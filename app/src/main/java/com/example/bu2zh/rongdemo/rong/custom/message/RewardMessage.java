package com.example.bu2zh.rongdemo.rong.custom.message;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

/**
 * Created by adadgz on 2018/3/17.
 */

@SuppressLint("ParcelCreator")
@MessageTag(value = "app:custom", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class RewardMessage extends MessageContent {

    private String TAG = RewardMessage.class.getSimpleName();
    private String content;

    /**
     * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
     */
    public static final Creator<RewardMessage> CREATOR = new Creator<RewardMessage>() {

        @Override
        public RewardMessage createFromParcel(Parcel source) {
            return new RewardMessage(source);
        }

        @Override
        public RewardMessage[] newArray(int size) {
            return new RewardMessage[size];
        }
    };


    public RewardMessage() {

    }

    public RewardMessage(String amount){
        this.content = amount;
    }

    public static RewardMessage obtain(String content){
        RewardMessage msg = new RewardMessage();
        msg.setContent(content);
        return msg;
    }

    public RewardMessage(byte[] data) {
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e1) {

        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);

            if (jsonObj.has("content"))
                this.setContent(jsonObj.optString("content"));


            if(jsonObj.has("user")) {
                this.setUserInfo(this.parseJsonToUserInfo(jsonObj.getJSONObject("user")));
            }

        } catch (JSONException e) {
            RLog.e(TAG, "JSONException", e);
        }
    }

    /**
     * 将消息属性封装成 json 串，再将 json 串转成 byte 数组
     *
     * @return
     */
    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            if(!TextUtils.isEmpty(this.getContent())) {
                jsonObj.put("message", this.getContent());
            }

            if(this.getJSONUserInfo() != null) {
                jsonObj.putOpt("user", this.getJSONUserInfo());
            }
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }

        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 描述了包含在 Parcelable 对象排列信息中的特殊对象的类型。
     *
     * @return 一个标志位，表明Parcelable对象特殊对象类型集合的排列。
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 将类的数据写入外部提供的 Parcel 中。
     * @param parcel
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        ParcelUtils.writeToParcel(parcel, this.getContent());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(parcel, this.getUserInfo());
    }

    public RewardMessage(Parcel in) {
        this.setContent(ParcelUtils.readFromParcel(in));
        this.setUserInfo(ParcelUtils.readFromParcel(in, UserInfo.class));

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
