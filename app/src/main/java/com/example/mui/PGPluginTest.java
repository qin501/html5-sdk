package com.example.mui;

import android.content.Context;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;
import io.dcloud.common.util.JSUtil;

public class PGPluginTest extends StandardFeature {
    @Override
    public void onStart(Context context, Bundle bundle, String[] strings) {
        //如果需要在应用启动时进行初始化，可以继承这个方法，并在properties.xml文件的service节点添加扩展插件的注册即可触发Onstart方法
        super.onStart(context, bundle, strings);
    }
    public void PluginTestFunction(IWebview pWebview, JSONArray array){
        //原生代码中获取js层传递的参数
        //参数的获取顺序与js层传递的顺序一致
        String CallBackID = array.optString(0);
        JSONArray newArray=new JSONArray();
        newArray.put(array.optString(1));
        newArray.put(array.optString(2));
        newArray.put(array.optString(3));
        newArray.put(array.optString(4));
        //调用方法将原生代码的执行结果返回给js层并触发相应的js层回调函数
        JSUtil.execCallback(pWebview,CallBackID,newArray,JSUtil.OK,false);
    }
    public void PluginTestFunctionArrayArgu(IWebview pWebview,JSONArray array){
        String ReturnString=null;
        String CallBackID=array.optString(0);
        JSONArray newArray=null;
        try{
            newArray=new JSONArray(array.optString(1));
            String inValue1=newArray.getString(0);
            String inValue2=newArray.getString(1);
            String inValue3=newArray.getString(2);
            String inValue4=newArray.getString(3);
            ReturnString=inValue1+"-"+inValue2+"-"+inValue3+"-"+inValue4;
        }catch (Exception e){
            e.printStackTrace();
        }
        JSUtil.execCallback(pWebview,CallBackID,ReturnString,JSUtil.OK,false);
    }
    public String PluginTestFunctionSyncArrayArgu(IWebview pWebview,JSONArray array){
        JSONArray newArray=null;
        JSONObject retJSONObj=null;
        try{
            newArray=array.optJSONArray(0);
            String inValue1=newArray.getString(0);
            String inValue2=newArray.getString(1);
            String inValue3=newArray.getString(2);
            String inValue4=newArray.getString(3);
            retJSONObj=new JSONObject();
            retJSONObj.putOpt("RetArgu1", inValue1);
            retJSONObj.putOpt("RetArgu2", inValue2);
            retJSONObj.putOpt("RetArgu3", inValue3);
            retJSONObj.putOpt("RetArgu4", inValue4);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSUtil.wrapJsVar(retJSONObj);
    }
    public String PluginTestFunctionSync(IWebview pWebview, JSONArray array){
        String inValue1=array.optString(0);
        String inValue2=array.optString(1);
        String inValue3=array.optString(2);
        String inValue4=array.optString(3);
        String ReturnValue = inValue1 + "-" + inValue2 + "-" + inValue3 + "-" + inValue4;
        return JSUtil.wrapJsVar(ReturnValue,true);
    }

}
