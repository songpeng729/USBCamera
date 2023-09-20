package com.finger.usbcamera.activity.network;


import android.content.Context;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 网络链接的基础类
 */
@Deprecated
public class VolleyConnection {
    private RequestQueue mQueue;
    private final static String TYPE_UTF8_CHARSET = "charset=UTF-8";

    public VolleyConnection(Context context) {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(context);
        }
    }

    public RequestQueue getRequestQueue() {
        return mQueue;
    }

    /**
     * 使用HttpGet的方式进行网络的链接
     *
     * @param url
     * @param listener
     * @param errorListener
     */
    public void HttpGETByVolley(String url, Response.Listener<String> listener,
                                Response.ErrorListener errorListener) {
//		StringRequest request = new StringRequest(Request.Method.GET, url,
//				listener, errorListener);
        CommonRequest request = new CommonRequest(Request.Method.GET, url,
                listener, errorListener);
        try {
            request.getHeaders();
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }
        request.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(request);
    }
    /**
     * 使用HttpGet的方式进行网络的链接
     *
     * @param url
     * @param listener
     * @param errorListener
     */
    public void HttpDELETEByVolley(String url, Listener<String> listener,
                                   ErrorListener errorListener) {
        StringRequest request = new StringRequest(Request.Method.DELETE, url,
                listener, errorListener);
        request.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(request);
    }

    /**
     * 使用HTTPGet的方式，带有参数上传,进行网络链接
     *
     * @param url
     * @param listener
     * @param errorListener
     * @param map
     */
    public void HttpGETByVolley(String url, Listener<String> listener,
                                ErrorListener errorListener, final Map<String, String> map) {
//		StringRequest request = new StringRequest(Request.Method.GET, url,
//				listener, errorListener) {
        CommonRequest request = new CommonRequest(Request.Method.GET, url,
                listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        try {
            request.getHeaders();
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }
        request.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(request);
    }

    /**
     * 使用HttpPUT的方式，带有参数上传,进行网络链接
     *
     * @param url
     * @param listener
     * @param errorListener
     * @param map
     */
    public void HttpPUTByVolley(String url, Listener<String> listener,
                                ErrorListener errorListener, final Map<String, String> map) {
//		StringRequest request = new StringRequest(Request.Method.PUT, url,
//				listener, errorListener) {
        CommonRequest request = new CommonRequest(Request.Method.PUT, url,
                listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        try {
            request.getHeaders();
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }
        request.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(request);
    }

    /**
     * 使用HttpPOST的方式进行网络的链接
     *
     * @param url
     * @param successListener
     * @param errorListener
     * @param map
     */
    public void HttpPOSTByVolley(String url, Listener<String> successListener,
                                 ErrorListener errorListener, final Map<String, String> map) {
//		StringRequest request = new StringRequest(Request.Method.POST, url,
//				successListener, errorListener) {
        CommonRequest request = new CommonRequest(Request.Method.POST, url,
                successListener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // 在这里对map进行加密
                return map;
            }
        };
        try {
            request.getHeaders();
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }
        request.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(request);
    }

    /**
     * 使用HttpDelete的方式进行网络的链接
     *
     * @param url
     * @param successListener
     * @param errorListener
     * @param map
     */
    public void HttpDeleteByVolley(String url, Listener<String> successListener,
                                   ErrorListener errorListener, final Map<String, String> map) {
//		StringRequest request = new StringRequest(Request.Method.DELETE, url,
//				successListener, errorListener) {
        CommonRequest request = new CommonRequest(Request.Method.DELETE, url,
                successListener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // 在这里对map进行加密
                return map;
            }
        };
        try {
            request.getHeaders();
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }
        request.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(request);
    }
    /**
     * 使用POST的方式获取一段Json数据
     *
     * @param url
     * @param listener
     * @param errorListener
     */
    public void HttpJsonGETByVolley(String url, Listener<JSONObject> listener,
                                    ErrorListener errorListener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                listener, errorListener);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(jsonObjectRequest);
    }

    /**
     * 使用post方式进行返回json数据
     *
     * @param url
     * @param listener
     * @param errorListener
     * @param map
     */
    public void HttpJsonPOSTByVolley(String url, Listener<JSONObject> listener,
                                     ErrorListener errorListener, final Map<String, String> map) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(jsonObjectRequest);
    }

    class CommonRequest extends StringRequest{

        public CommonRequest(int method, String url, Listener<String> listener, ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        public CommonRequest(String url, Listener<String> listener, ErrorListener errorListener) {
            super(url, listener, errorListener);
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headers = super.getHeaders();

            if(headers == null || headers.equals(Collections.emptyMap())){
                headers = new HashMap<String, String>();
            }
            headers.put("appId","xzzd.bxsswsbhcxt.200");
            return headers;
        }

        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {
            Map<String, String> headers = response.headers;
            String parsed;
            try {
                parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            } catch (UnsupportedEncodingException e) {
                parsed = new String(response.data);
            }
            return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
        }
    }
}