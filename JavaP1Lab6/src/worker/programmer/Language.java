package worker.programmer;

public enum Language
{
    Java, Ruby, C, Assembler;
    private int languageExperience;
    public int getLanguageExperience(){
        return languageExperience;
    }
    public void setLanguageExperience(int ages){
        languageExperience = ages;
    }
}