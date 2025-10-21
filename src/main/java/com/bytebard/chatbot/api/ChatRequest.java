package com.bytebard.chatbot.api;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.errors.OpenAIException;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.finetuning.jobs.FineTuningJob;
import com.openai.models.finetuning.jobs.JobListPage;

import java.util.*;

public final class ChatRequest {
//    private static String MODEL;
    private static OpenAIClient CLIENT = null;

//    public ChatRequest(String apiKey, String modelName, Map<String, Object> result) throws OpenAIException {
//        System.out.println("Start ChatRequest Initialize");
//        try{
//            if(modelName == null || modelName.isEmpty()) throw new OpenAIException("modelName is null or empty");
//            if(apiKey == null || apiKey.isEmpty()) throw new OpenAIException("apiKey is null or empty");
//
//            MODEL = modelName;
//            CLIENT = new OpenAIOkHttpClient.Builder().apiKey(apiKey).fromEnv().build();
//
//            result.put("model", MODEL);
//            result.put("status", "success");
//            System.out.println("ChatRequest Initialize Success");
//        } catch (OpenAIException e) {
//            result.put("status", "error");
//            result.put("errorMessage", e.getMessage());
//            System.out.println("ChatRequest Initialize Error");
//        }
//
//        return;
//    }
    public static Map<String, Object> initChatRequest(String apiKey) throws OpenAIException {
        System.out.println("Start ChatRequest Initialize");
        Map<String, Object> result = new HashMap<>();
        try{
//            if(modelName == null || modelName.isEmpty()) throw new OpenAIException("modelName is null or empty");
            if(apiKey == null || apiKey.isEmpty()) throw new OpenAIException("apiKey is null or empty");

//            MODEL = modelName;
            CLIENT = new OpenAIOkHttpClient.Builder().apiKey(apiKey).fromEnv().build();

            result.put("model", "");
            result.put("status", "success");
            System.out.println("ChatRequest Initialize Success");
        } catch (OpenAIException e) {
            result.put("status", "error");
            result.put("errorMessage", e.getMessage());
            System.out.println("ChatRequest Initialize Error");
        }

        return result;
    }

    public static Map<String, Object> getTuneModels(){
        JobListPage joblist = CLIENT.fineTuning().jobs().list();
        List<Map<String, Object>> result = new ArrayList<>();

        for (FineTuningJob job : joblist.data()) {
            Map<String, Object> jobInfo = new HashMap<>();
            jobInfo.put("id", job.id());
            jobInfo.put("model", job.model());
            jobInfo.put("fineTuneModel", job.fineTunedModel());
            jobInfo.put("fineTuneModelToStr", job.fineTunedModel().orElse(null)); //API호출 시 모델 설정값에 넣는 정보
            jobInfo.put("status", job.status());
            jobInfo.put("createdAt", job.createdAt());
            jobInfo.put("finishedAt", job.finishedAt());
            jobInfo.put("error", job.error());
            jobInfo.put("trainedTokens", job.trainedTokens());
            jobInfo.put("validationFile", job.validationFile());
            jobInfo.put("integrations", job.integrations());
            jobInfo.put("metadata", job.metadata());
            jobInfo.put("method", job.method());
            result.add(jobInfo);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("total", result.size());
        response.put("data", result);
        return response;
    }

    public static Map<String, Object> questionMessage(String questionMessage, String fineTuneModel) throws OpenAIException {
        Map<String, Object> result = new HashMap<>();
        try {
            ChatCompletion chat = CLIENT.chat().completions().create(
                ChatCompletionCreateParams.builder()
                    .model(fineTuneModel).addUserMessage(questionMessage).build()
            );

            result.put("status", "success");
            result.put("chatId", chat.id());
            result.put("answerMessage", chat.choices().get(0).message().content());
            result.put("role",chat.choices().get(0).message()._role());
            result.put("size",chat.choices().size());
//            result.put("data", chat);
        } catch (OpenAIException e) {
            result.put("status", "error");
            result.put("errorMessage", e.getMessage());
            throw new OpenAIException();
        }

        return result;
    }
}
