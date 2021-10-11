package com.hkg.GraphicEngine.core.utils;

import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.lwjgl.system.MemoryUtil;

/**
 *
 * @author kagan.guvenc
 */
public class Utils {

    public static FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data).flip();
        return buffer;
    }

    public static IntBuffer storeDataInIntBuffer(int[] data) {
        IntBuffer buffer = MemoryUtil.memAllocInt(data.length);
        buffer.put(data).flip();
        return buffer;
    }

    public static String loadResources(String fileName) throws Exception {
        String result = "";
        InputStream in = Utils.class.getResourceAsStream(fileName);
        Scanner scanner = new Scanner(in, StandardCharsets.UTF_8);
        result = scanner.useDelimiter("\\A").next();
        return result;
    }

}
