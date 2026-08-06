package com.practice.info_pilot_api;

import com.practice.info_pilot_api.util.SimilarityUtil;

public class SimilarityTest {

    public static void main(
            String[] args) {

        double[] a = {
                1.0,
                2.0,
                3.0
        };

        double[] b = {
                1.0,
                2.0,
                3.0
        };

        System.out.println(
                SimilarityUtil
                        .cosineSimilarity(
                                a,
                                b
                        )
        );
    }
}