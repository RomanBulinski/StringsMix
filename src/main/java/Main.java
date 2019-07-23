import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Character.isLetter;

public class Main {

    public static String mix(String s1, String s2) {
        Map<Character, List<String>> mapFromS1 = getMapFromS1(s1);
        Map<Character, List<String>> mapS1addS2 = getmapS1addS2(mapFromS1, s2);
        Collection <List<String>> statisticOfLetter =  mapS1addS2.values();
        ArrayList<List<String>> listOfArrays = new ArrayList<>(statisticOfLetter);

        for(int i =listOfArrays.size()-1; i>=0;i--){
            List<String> tempArr = listOfArrays.get(i);
            if( tempArr.size()==1 && tempArr.get(0).length()==1 ){
                listOfArrays.remove(i);
            }
        }

        Comparator<String> compByThird = (aName, bName) -> aName.charAt(2) - bName.charAt(2);
        Comparator<String> compByFirst = (aName, bName) -> aName.charAt(0) - bName.charAt(0);
        Comparator<String> compByLength = (aName, bName) -> bName.length() - aName.length();

        String res = statisticOfLetter.stream()
                .map(m->listToString(m))
                .filter(s->s.length()>1)
                .sorted(compByThird)
                .sorted(compByFirst)
                .sorted(compByLength)
                .collect(Collectors.joining( "/" ));

        return res;
    }

    private static String listToString(List<String> listOfStrings){
        String result="";
        if(listOfStrings.size()==1 && listOfStrings.get(0).length()>3){
            result+= listOfStrings.get(0);
        }else if(listOfStrings.size()==2){
            String fromS1 = listOfStrings.get(0);
            String fromS2 = listOfStrings.get(1);
            if( fromS1.length()>fromS2.length() ){
                result+=fromS1;
            }else if(fromS1.length()<fromS2.length() ){
                result+=fromS2;
            }else if(fromS1.length()==fromS2.length() && fromS1.length()>3){
                result+="=:"+fromS2.substring(2);
            }
        }
        return result;
    }

    private static Map<Character, List<String>> getmapS1addS2(Map<Character, List<String>> mapFromS1, String s2) {
        Map<Character, List<String>> resultMap = mapFromS1;
        for (int i = 0; i < s2.length(); i++) {
            if (resultMap.containsKey(s2.charAt(i))) {
                List<String> temListString = resultMap.get(s2.charAt(i));
                if (temListString.size() == 1 &&  String.valueOf( temListString.get(0).charAt(0) ).equals("1") ) {
                    temListString.add("2:"+s2.charAt(i));
                } else if (temListString.size() == 2) {
                    String temS = temListString.get(1);
                    String temR = temS + s2.charAt(i);
                    temListString.remove(1);
                    temListString.add(temR);
                }else if(temListString.size() == 1 &&  String.valueOf( temListString.get(0).charAt(0) ).equals("2") ){
                    String temS = temListString.get(0);
                    String temR = temS + s2.charAt(i);
                    temListString.remove(0);
                    temListString.add(temR);
                }
                resultMap.put(s2.charAt(i), temListString );
            }else if(!resultMap.containsKey(s2.charAt(i)) && isLetter(s2.charAt(i))){
                List<String>  stringList = new ArrayList<>();
                stringList.add("2:"+s2.charAt(i));
                resultMap.put( s2.charAt(i), stringList);
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
                    temList.add("1:"+String.valueOf(temChar));
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


}
