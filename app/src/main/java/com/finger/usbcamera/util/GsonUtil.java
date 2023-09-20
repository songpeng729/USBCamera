package com.finger.usbcamera.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GsonUtil {

	/**
	 * 将字符串通过转义变成‘进行生成json数据
	 * 再通过转义变成“进行javaBean的反射
	 */
	private static String key = "\"";
//	private static String reValue = "'";
	private static String reValue = "\"";
	public static Gson createGson() {
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(java.util.Date.class, (JsonSerializer<Date>) (src, arg1, arg2) -> new JsonPrimitive(src.getTime()))
				.setDateFormat(DateFormat.LONG);
		gb.registerTypeAdapter(java.util.Date.class,
				new JsonDeserializer<Date>() {
					@Override
					public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
						return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
					}
				}).setDateFormat(DateFormat.LONG);
		Gson gson = gb.create();
		return gson;
	}
	/**
	 * 将Object转化成json
	 * 
	 * @param value
	 * @return
	 */
	public static String createJsonString(final Object value) {
		Gson gson = createGson();
		String str = gson.toJson(value);
		String newstr = str.replaceAll(key, reValue);
		return newstr;
	}

	/**
	 * 将Json数据转化为javaBean
	 * 
	 * @param jsonString
	 * @param cls
	 * @return
	 */
	public static <T> T getObject(String jsonString, Class<T> cls) {
		String newstr = jsonString.replaceAll(reValue, key);
		T t = null;
		try {
			Gson gson = createGson();
			t = gson.fromJson(newstr, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 将Json数据转化为javaBean
	 * 
	 * @param jsonString
	 * @param cls
	 * @return
	 */
	public static <T> T getObjectNormal(String jsonString, Class<T> cls) {
		T t = null;
		try {
			Gson gson = createGson();
			t = gson.fromJson(jsonString, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 将一个字符串转换成javaBean的集合
	 * 
	 * @return
	 */
	public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
		T[] arr = createGson().fromJson(s, clazz);
		return Arrays.asList(arr);
	}
}
