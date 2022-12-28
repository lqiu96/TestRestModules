package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TestLanguage.useGRPCTransport();
        TestLanguage.useRESTTransport();

        TestMediaTranslation.useGRPCTransport("src/main/resources/audio.m4a");
        TestMediaTranslation.useRESTTransport("src/main/resources/audio.m4a");
    }
}