package com.jzsoft.platform.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtils {
	public static byte[] serialize(Object obj) {  
		byte[] bytes = null;  
		ByteArrayOutputStream bo = null;  
		ObjectOutputStream oo = null;  
		try {  
			bo = new ByteArrayOutputStream(256);  
			oo = new ObjectOutputStream(bo);  
			// object to bytearray  
			oo.writeObject(obj);  
			oo.flush();
			bytes = bo.toByteArray();  
		}catch (Exception e) {  
			throw new RuntimeException("Cannot deserialize", e);
		}finally{
			CloseUtil.close(bo);
			CloseUtil.close(oo);
		}
		return bytes;  
	}  
	
	public static Object deserialize(byte[] bytes) {  
		Object obj = null;  
		ByteArrayInputStream bi = null;  
		ObjectInputStream oi = null;  
		try {  
			bi = new ByteArrayInputStream(bytes);  
			oi = new ObjectInputStream(bi);  
			// bytearray to object  
			obj = oi.readObject();  
		} catch (Exception e) {  
			throw new RuntimeException("Cannot serialize", e);
		} finally{
			CloseUtil.close(bi);
			CloseUtil.close(oi);
		}
		return obj;  
	}  
}
