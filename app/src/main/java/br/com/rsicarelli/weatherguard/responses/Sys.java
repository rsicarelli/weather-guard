
package br.com.rsicarelli.weatherguard.responses;

import com.google.gson.annotations.Expose;

public class Sys {

    @Expose
    private String country;

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

}
