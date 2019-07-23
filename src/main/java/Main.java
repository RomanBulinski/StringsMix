import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static String mix(String s1, String s2) {

        Map<Character, List<String>> mapFromS1 = getMapFromS1(s1);
        Map<Character, List<String>> mapS1addS2 = getmapS1addS2(mapFromS1, s2);
        Collection<List<String>> sttisticOfLetter =  mapS1addS2.values();

        String res = sttisticOfLetter.stream()
                .filter(n -> (n.get(0)+n.get(0)).length() > 2 )
                .map(m->listToString(m))
                .collect(Collectors.joining( "/" ));

        return res;
    }


    private static String listToString(List<String> listOfStrings){
        String result="";
        String fromS1 = listOfStrings.get(0);
        String fromS2 = listOfStrings.get(1);
        if( fromS1.length()>fromS2.length() ){
            result+="1:"+fromS1;
        }else if(fromS1.length()<fromS2.length() ){
            result+="2:"+fromS2;
        }
        return result;
    }

    private static Map<Character, List<String>> getmapS1addS2(Map<Character, List<String>> mapFromS1, String s2) {

        Map<Character, List<String>> resultMap = mapFromS1;

        for (int i = 0; i < s2.length(); i++) {
            if (resultMap.containsKey(s2.charAt(i))) {
                List<String> temListString = resultMap.get(s2.charAt(i));
                if (temListString.size() == 1) {
                    temListString.add(String.valueOf(s2.charAt(i)));
                } else if (temListString.size() == 2) {
                    String temS = temListString.get(1);
                    String temR = temS + s2.charAt(i);
                    temListString.remove(1);
                    temListString.add(temR);
                }
                resultMap.put(s2.charAt(i), temListString );
            }
        }
        return resultMap;
    }


    private static Map<Character, List<String>> getMapFromS1(String s1) {
        Map<Character, List<String>> charactersMap = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char temChar = s1.charAt(i);
            List<String> temList = new ArrayList<>();
            if (Character.isLowerCase(temChar)) {
                if (!charactersMap.containsKey(temChar)) {
                    temList.add(String.valueOf(temChar));
                    charactersMap.put(temChar, temList);
                } else if (charactersMap.containsKey(temChar)) {
                    List<String> stringList = charactersMap.get(temChar);
                    String temp = stringList.get(0);
                    temp = temp + temChar;
                    stringList.remove(0);
                    stringList.add(temp);
                    charactersMap.put(temChar, stringList);
                }
            }
        }
        return charactersMap;
    }






    private static String toPartStream(List<Object> inputList) {
        StringBuilder temSB = new StringBuilder();
        temSB.append(inputList.get(2) + ":");
        for (int j = 0; j < (int) inputList.get(0); j++) {
            temSB.append(inputList.get(1));
        }
        return temSB.append("/").toString();
    }

    private static Map<Character, List<Object>> getCharacterIntegerMap(String s, int id) {

        int stringId = 0;
        if (id == 1) {
            stringId = 1;
        } else if (id == 2) {
            stringId = 2;
        }

        Map<Character, List<Object>> mapForString = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char temChar = s.charAt(i);
            if (Character.isLowerCase(temChar)) {
                if (!mapForString.keySet().contains(temChar)) {
                    List<Object> tempList = new ArrayList<>();
                    tempList.add(1);
                    tempList.add(temChar);
                    tempList.add(stringId);
                    mapForString.put(temChar, tempList);
                } else if (mapForString.keySet().contains(temChar)) {
                    List<Object> tempList = mapForString.get(temChar);
                    int tempAmount = (int) tempList.get(0);
                    tempList.remove(0);
                    tempList.add(0, tempAmount + 1);
                    mapForString.put(temChar, tempList);
                }
            }
        }
        return mapForString;
    }


}
