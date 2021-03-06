package com.jzsoft.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SerializeUtil {
	 public static byte[] serialize(Object object) {  
	        ObjectOutputStream oos = null;  
	        ByteArrayOutputStream baos = null;  
	        try {  
	            // 序列化  
	            baos = new ByteArrayOutputStream();  
	            oos = new ObjectOutputStream(baos);  
	            oos.writeObject(object);  
	            byte[] bytes = baos.toByteArray();  
	            return bytes;  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            try {  
	                if (oos != null) {  
	                    oos.close();  
	                }  
	                if (baos != null) {  
	                    baos.close();  
	                }  
	            } catch (Exception e2) {  
	                e2.printStackTrace();  
	            }  
	        }  
	        return null;  
	    }  
	  
	    public static Object unserialize(byte[] bytes) {  
	        ByteArrayInputStream bais = null;  
	        try {  
	            // 反序列化  
	            bais = new ByteArrayInputStream(bytes);  
	            ObjectInputStream ois = new ObjectInputStream(bais);  
	            return ois.readObject();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }  
}
