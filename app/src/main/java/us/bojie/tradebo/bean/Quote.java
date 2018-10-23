package us.bojie.tradebo.bean;

import com.google.gson.annotations.SerializedName;

public class Quote {

    /**
     * ask_price : 154.810000
     * ask_size : 200
     * bid_price : 154.740000
     * bid_size : 100
     * last_trade_price : 154.780000
     * last_extended_hours_trade_price : 154.750000
     * previous_close : 154.050000
     * adjusted_previous_close : 154.050000
     * previous_close_date : 2018-10-19
     * symbol : FB
     * trading_halted : false
     * has_traded : true
     * last_trade_price_source : consolidated
     * updated_at : 2018-10-22T23:54:36Z
     * instrument : https://api.robinhood.com/instruments/ebab2398-028d-4939-9f1d-13bf38f81c50/
     */

    @SerializedName("ask_price")
    private String askPrice;
    @SerializedName("ask_size")
    private int askSize;
    @SerializedName("bid_price")
    private String bidPrice;
    @SerializedName("bid_size")
    private int bidSize;
    @SerializedName("last_trade_price")
    private String lastTradePrice;
    @SerializedName("last_extended_hours_trade_price")
    private String lastExtendedHoursTradePrice;
    @SerializedName("previous_close")
    private String previousClose;
    @SerializedName("adjusted_previous_close")
    private String adjustedPreviousClose;
    @SerializedName("previous_close_date")
    private String previousCloseDate;
    @SerializedName("symbol")
    private String symbol;
    @SerializedName("trading_halted")
    private boolean tradingHalted;
    @SerializedName("has_traded")
    private boolean hasTraded;
    @SerializedName("last_trade_price_source")
    private String lastTradePriceSource;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("instrument")
    private String instrument;

    public String getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(String askPrice) {
        this.askPrice = askPrice;
    }

    public int getAskSize() {
        return askSize;
    }

    public void setAskSize(int askSize) {
        this.askSize = askSize;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public int getBidSize() {
        return bidSize;
    }

    public void setBidSize(int bidSize) {
        this.bidSize = bidSize;
    }

    public String getLastTradePrice() {
        return lastTradePrice;
    }

    public void setLastTradePrice(String lastTradePrice) {
        this.lastTradePrice = lastTradePrice;
    }

    public String getLastExtendedHoursTradePrice() {
        return lastExtendedHoursTradePrice;
    }

    public void setLastExtendedHoursTradePrice(String lastExtendedHoursTradePrice) {
        this.lastExtendedHoursTradePrice = lastExtendedHoursTradePrice;
    }

    public String getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(String previousClose) {
        this.previousClose = previousClose;
    }

    public String getAdjustedPreviousClose() {
        return adjustedPreviousClose;
    }

    public void setAdjustedPreviousClose(String adjustedPreviousClose) {
        this.adjustedPreviousClose = adjustedPreviousClose;
    }

    public String getPreviousCloseDate() {
        return previousCloseDate;
    }

    public void setPreviousCloseDate(String previousCloseDate) {
        this.previousCloseDate = previousCloseDate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isTradingHalted() {
        return tradingHalted;
    }

    public void setTradingHalted(boolean tradingHalted) {
        this.tradingHalted = tradingHalted;
    }

    public boolean isHasTraded() {
        return hasTraded;
    }

    public void setHasTraded(boolean hasTraded) {
        this.hasTraded = hasTraded;
    }

    public String getLastTradePriceSource() {
        return lastTradePriceSource;
    }

    public void setLastTradePriceSource(String lastTradePriceSource) {
        this.lastTradePriceSource = lastTradePriceSource;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
}
