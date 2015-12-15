package com.zhy.http.okhttp.callback;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class ResultCallback<T>
{
    public Type mType;

    public ResultCallback()
    {
        mType = getSuperclassTypeParameter(getClass());
    }

    /**
     * 获得超类的泛型参数的实际类型
     * http://blog.csdn.net/hello__zero/article/details/44201033
     * http://blog.sina.com.cn/s/blog_4a4f9fb50101g7z9.html
     * @param subclass
     * @return
     */
    static Type getSuperclassTypeParameter(Class<?> subclass)
    {
//        getClass().getGenericSuperclass()返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class)
        {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);//获取第一个参数
    }

    public void onBefore(Request request)
    {
    }

    public void onAfter()
    {
    }

    public void inProgress(float progress)
    {

    }

    public abstract void onError(Request request, Exception e);

    public abstract void onResponse(T response);


    public static final ResultCallback<String> DEFAULT_RESULT_CALLBACK = new ResultCallback<String>()
    {
        @Override
        public void onError(Request request, Exception e)
        {

        }

        @Override
        public void onResponse(String response)
        {

        }
    };
}