package com.example.simpledictionary;

import java.util.ArrayList;

public class DictionaryManagement {
    public static ArrayList<String> dictionarySearchAdvanced(String inputWord) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < Dictionary.wordsAdvanced.size(); i++) {
            String strTarget = Dictionary.wordsAdvanced.get(i).getWord_target();
            if (strTarget.length() >= inputWord.length()) {
                if (inputWord.equals(strTarget.substring(0, inputWord.length()))) {
                    result.add(strTarget);
                }
            }
        }
        return result;
    }

    public static ArrayList<String> AdvancedExplain(String str) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < Dictionary.wordsAdvanced.size(); i++) {
            if (Dictionary.wordsAdvanced.get(i).getWord_target().equals(str)) {
                result.add(Dictionary.wordsAdvanced.get(i).getWord_explain());
                result.add(Dictionary.wordsAdvanced.get(i).getWord_pronun());
                return result;
            }
        }
        result.add("Not found");
        result.add("Not found");
        return result;
    }

    public static boolean checkDuplicateWord(String word) {
        //duplicate favorite word
        return false;
    }

//    public static String translateAPI(String text) {
//        try {
//            String urlStr = "https://script.google.com/" +
//                    "macros/s/AKfycbySwcqSyUqlrrss0qvl8Mncg4IRCUq2OLl-S_HIlLY8bhq9xhbv/exec"
//                    + "?q=" + URLEncoder.encode(text, "UTF-8") + "&target=vi&source=en";
//            URL url = new URL(urlStr);
//            StringBuilder response = new StringBuilder();
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//            return response.toString();
//        } catch (IOException exception) {
//            return null;
//        }
//    }
//
//    public static void speakWord(String word) {
//        System.setProperty("freetts.voices",
//                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
//        VoiceManager freettsVM = VoiceManager.getInstance();
//        Voice freettsVoice = freettsVM.getVoice("kevin16");
//        freettsVoice.allocate();
//        freettsVoice.speak(word);
//    }
//
//    public static void speakPara(String sentence) {
//        System.setProperty("freetts.voices",
//                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
//        VoiceManager freettsVM = VoiceManager.getInstance();
//        Voice freettsVoice = freettsVM.getVoice("kevin16");
//        freettsVoice.allocate();
//        freettsVoice.speak(sentence);
//    }
}
