package com.practice.info_pilot_api.util;

public class EmbeddingParserUtil {

    private EmbeddingParserUtil() {}

    public static double[]
    parseEmbedding(
            String embedding) {

        String[] values =
                embedding.split(",");

        double[] result =
                new double[
                        values.length
                        ];

        for (
                int i = 0;
                i < values.length;
                i++
        ) {

            result[i] =
                    Double.parseDouble(
                            values[i]
                    );
        }

        return result;
    }
}