package org.example;

import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.LanguageServiceSettings;

import java.io.IOException;

public class TestLanguage {
    public static void useGRPCTransport() throws IOException {
        System.out.println("Calling useGRPCTransport() in TestLanguage");
        try (LanguageServiceClient languageServiceClient = LanguageServiceClient.create()) {
            Document document = Document.newBuilder().setContent("Hello World!").setType(Document.Type.PLAIN_TEXT).build();
            AnalyzeSentimentResponse response = languageServiceClient.analyzeSentiment(document);
            System.out.println("The Language is: " + response.getLanguage());
        }
        System.out.println("Done useGRPCTransport() in TestLanguage");
    }

    public static void useRESTTransport() throws IOException {
        System.out.println("Calling useRESTTransport() in TestLanguage");
        LanguageServiceSettings languageServiceSettings = LanguageServiceSettings.newHttpJsonBuilder().build();
        try (LanguageServiceClient languageServiceClient = LanguageServiceClient.create(languageServiceSettings)) {
            Document document = Document.newBuilder().setContent("Hello World!").setType(Document.Type.PLAIN_TEXT).build();
            AnalyzeSentimentResponse response = languageServiceClient.analyzeSentiment(document);
            System.out.println("The Language is: " + response.getLanguage());
        }
        System.out.println("Done useRESTTransport() in TestLanguage");
    }
}
