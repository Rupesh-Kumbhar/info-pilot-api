package com.practice.info_pilot_api.util;

import java.util.ArrayList;
import java.util.List;

public class ChunkUtil {

        private static final int CHUNK_SIZE = 1000;

        public static List<String> createChunks(String text) {

                List<String> chunks = new ArrayList<>();

                int start = 0;

                while (start < text.length()) {

                        int end = Math.min(start + CHUNK_SIZE,text.length());
                        chunks.add(text.substring(start,end));
                        start = end;
                }
                return chunks;
        }
}