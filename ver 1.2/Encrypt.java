class Encrypt{
    String encode(String str){
        String text = "";
        int key = 0;
        for(int counter = 0; counter < str.length(); counter++){
            if(counter%2==0){
                key=1;
            }
            else{
                key=-1;
            }
            text = text + (char)((int)str.charAt(counter) + key);
        }
         return text;
    }
}