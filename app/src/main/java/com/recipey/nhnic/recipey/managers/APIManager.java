package com.recipey.nhnic.recipey.managers;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.recipey.nhnic.recipey.network.JsonObjectRequestCustomHeader;
import com.recipey.nhnic.recipey.network.JsonObjectRequestCustomHeaderDelete;
import com.recipey.nhnic.recipey.network.JsonObjectRequestCustomHeaderGet;
import com.recipey.nhnic.recipey.network.JsonObjectRequestCustomHeaderPatch;
import com.recipey.nhnic.recipey.network.VolleySingleton;

import org.json.JSONObject;

public class APIManager {
    final int TIME_OUT = 30000;

    protected interface APIListener {
        public void success(JSONObject response);
        public void failed(VolleyError error);
    }

    public void makeAPICall(String url, JSONObject bodyParams, int method, Context context, final APIListener listener) {
//        if (bodyParams != null) {
//            try {
//                bodyParams.put("session_token", ProfileManager.getInstance(context).getSessionToken());
//            } catch (Exception e) {
//            }
//        }

        switch (method) {
            case Request.Method.GET:
                makeCustomHeaderGet(url, bodyParams, context, listener);
                break;
            case Request.Method.PATCH:
                makeAPICallPatch(url, bodyParams, context, listener);
                break;
            case Request.Method.DELETE:
                makeAPICallDelete(url, bodyParams, context, listener);
                break;
            default:
                makeAPICallNormal(url, bodyParams, method, context, listener);
                break;
        }

//        RequestQueue queue = VolleySingleton.getInstance(context)
//                .getRequestQueue();
//        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
//                method, url, bodyParams,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        if (listener != null) {
//                            listener.success(response);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        if (listener != null) {
//                            listener.failed(error);
//                        }
//                    }
//                }
//        );
//        int socketTimeout = TIME_OUT;//30 seconds - change to what you want
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        jsObjRequest.setRetryPolicy(policy);
//        queue.add(jsObjRequest);
    }

    public void makeAPICallNormal(String url, JSONObject bodyParams, int method, Context context, final APIListener listener) {
        RequestQueue queue = VolleySingleton.getInstance(context)
                .getRequestQueue();
        JsonObjectRequestCustomHeader jsObjRequest = new JsonObjectRequestCustomHeader(
                context, method, url, bodyParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (listener != null) {
                            listener.success(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (listener != null) {
                            listener.failed(error);
                        }
                    }
                }
        );
        int socketTimeout = TIME_OUT;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        queue.add(jsObjRequest);
    }

    public void makeCustomHeaderGet(String url, JSONObject bodyParams, Context context, final APIListener listener) {
        RequestQueue queue = VolleySingleton.getInstance(context)
                .getRequestQueue();
        JsonObjectRequestCustomHeaderGet jsObjRequest = new JsonObjectRequestCustomHeaderGet(
                context, Request.Method.GET, url, bodyParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (listener != null) {
                            listener.success(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (listener != null) {
                            listener.failed(error);
                        }
                    }
                }
        );

        int socketTimeout = TIME_OUT;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        queue.add(jsObjRequest);
    }

    public void makeAPICallGet(String url, JSONObject bodyParams, Context context, final APIListener listener) {
        RequestQueue queue = VolleySingleton.getInstance(context)
                .getRequestQueue();
//        JsonObjectRequestCustomHeaderGet jsObjRequest = new JsonObjectRequestCustomHeaderGet(
//                context, Request.Method.GET, url, bodyParams,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        if (listener != null) {
//                            listener.success(response);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        if (listener != null) {
//                            listener.failed(error);
//                        }
//                    }
//                }
//        );

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET, url, bodyParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (listener != null) {
                            listener.success(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (listener != null) {
                            listener.failed(error);
                        }
                    }
                }
        );

        int socketTimeout = TIME_OUT;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        queue.add(jsObjRequest);
    }

    public void makeAPICallPatch(String url, JSONObject bodyParams, Context context, final APIListener listener) {
        RequestQueue queue = VolleySingleton.getInstance(context)
                .getRequestQueue();
        JsonObjectRequestCustomHeaderPatch jsObjRequest = new JsonObjectRequestCustomHeaderPatch(
                context, Request.Method.POST, url, bodyParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (listener != null) {
                            listener.success(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (listener != null) {
                            listener.failed(error);
                        }
                    }
                }
        );
        int socketTimeout = TIME_OUT;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        queue.add(jsObjRequest);
    }

    public void makeAPICallDelete(String url, JSONObject bodyParams, Context context, final APIListener listener) {
        RequestQueue queue = VolleySingleton.getInstance(context)
                .getRequestQueue();
        JsonObjectRequestCustomHeaderDelete jsObjRequest = new JsonObjectRequestCustomHeaderDelete(
                context,  Request.Method.POST, url, bodyParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (listener != null) {
                            listener.success(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (listener != null) {
                            listener.failed(error);
                        }
                    }
                }
        );
        int socketTimeout = TIME_OUT;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        queue.add(jsObjRequest);
    }
}