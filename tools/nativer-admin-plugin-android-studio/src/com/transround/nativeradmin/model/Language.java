package com.transround.nativeradmin.model;

public enum Language {

    AB("ab","Abkhaz","аҧсуа бызшәа"),
    AA("aa","Afar","Afaraf"),
    AF("af","Afrikaans","Afrikaans"),
    AK("ak","Akan","Akan"),
    SQ("sq","Albanian","gjuha shqipe"),
    AM("am","Amharic","አማርኛ"),
    AR("ar","Arabic","العربية"),
    AN("an","Aragonese","aragonés"),
    HY("hy","Armenian","Հայերեն"),
    AS("as","Assamese","অসমীয়া"),
    AV("av","Avaric","магӀарул мацӀ"),
    AE("ae","Avestan","avesta"),
    AY("ay","Aymara","aymar aru"),
    AZ("az","Azerbaijani","azərbaycan dili"),
    BE("be","Belarusian","беларуская мова"),
    BN("bn","Bengali","বাংলা"),
    BS("bs","Bosnian","bosanski jezik"),
    BG("bg","Bulgarian","български език"),
    CE("ce","Chechen","нохчийн мотт"),
    ZH("zh","Chinese","中文"),
    HR("hr","Croatian","hrvatski jezik"),
    CZ("cz","Czech","čeština"),
    DA("da","Danish","dansk"),
    NL("nl","Dutch","Vlaams"),
    EN("en","English","English"),
    EO("eo","Esperanto","Esperanto"),
    ET("et","Estonian","eesti"),
    FI("fi","Finnish","suomi"),
    FR("fr","French","français"),
    KA("ka","Georgian","ქართული"),
    DE("de","German","Deutsch"),
    EL("el","Greek","ελληνικά"),
    HI("hi","Hindi","हिन्दी"),
    HU("hu","Hungarian","Magyar"),
    GA("ga","Irish","Gaeilge"),
    IS("is","Icelandic","Íslenska"),
    IT("it","Italian","italiano"),
    JA("ja","Japanese","日本語"),
    KK("kk","Kazakh","қазақ тілі"),
    KM("km","Khmer","ខេមរភាសា"),
    KY("ky","Kyrgyz","Кыргызча"),
    KO("ko","Korean","한국어"),
    KU("ku","Kurdish","كوردی‎"),
    LA("la","Latin","lingua latina"),
    LT("lt","Lithuanian","lietuvių kalba"),
    LV("lv","Latvian","latviešu valoda"),
    MK("mk","Macedonian","македонски јазик"),
    NE("ne","Nepali","नेपाली"),
    NO("no","Norwegian","Norsk"),
    FA("fa","Persian","فارسی"),
    PL("pl","Polish","polszczyzna"),
    PT("pt","Portuguese","português"),
    RU("ru","Russian","русский язык"),
    SA("sa","Sanskrit","संस्कृतम्"),
    SR("sr","Serbian","српски језик"),
    SK("sk","Slovak","slovenčina"),
    SL("sl","Slovene","slovenščina"),
    ES("es","Spanish","español"),
    SV("sv","Swedish","Svenska"),
    TA("ta","Tamil","தமிழ்"),
    TH("th","Thai","ไทย"),
    TR("tr","Turkish","Türkçe"),
    UK("uk","Ukrainian","українська мова"),
    UR("ur","Urdu","اردو"),
    UZ("uz","Uzbek","Ўзбек"),
    VI("vi","Vietnamese","Tiếng Việt"),
    ZU("zu","Zulu","isiZulu"),
    CA("ca","Catalan","català"),
    CS("cs","Czech","čeština"),
    NB("nb","Norwegian","norsk"),
    RO("ro","Romanian","română");


    private String code;
    private String englishName;
    private String nativeName;

    Language( String code, String englishName, String nativeName ) {
        this.code = code;
        this.englishName = englishName;
        this.nativeName = nativeName;
    }

    public final String getCode() {
        return code;
    }

    public static Language getByCode(String code){
        for(Language l : values()){
            if(code.equals(l.getCode())){
                return l;
            }
        }
        throw new IllegalArgumentException("No enum found with code: " + code);
    }

    public final String getEnglishName() {
        return englishName;
    }

    public final String getNativeName() {
        return nativeName;
    }

    @Override
    public String toString() {
        return String.format("%1$s (%2$s)", nativeName, englishName);
    }

    public String getCombinedString() {
        return String.format("[%1$s] %2$s/%3$s", code, englishName, nativeName);
    }

}