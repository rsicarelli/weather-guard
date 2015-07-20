
package br.com.rsicarelli.weatherguard.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherResponse {

    @Expose
    private String message;
    @Expose
    private String cod;
    @Expose
    private Integer count;
    @Expose
    @SerializedName("list")
    private java.util.List<ListResult> list = new ArrayList<ListResult>();

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * @param cod The cod
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     * @return The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return The list
     */
    public java.util.List<ListResult> getList() {
        return list;
    }

    /**
     * @param list The list
     */
    public void setList(java.util.List<ListResult> list) {
        this.list = list;
    }

}
