package com.pavithbuddhima.myschedule;

/**
 * This class could be used to store all the information about a synonym and when needed could
 * be used to retrieve them as well.
 */
public class Synonym {

    private String category;
    private String synonyms;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    @Override
    public String toString() {
        return "Synonym [Category=" + category + ", Synonyms = " + synonyms + "]";
    }
}
