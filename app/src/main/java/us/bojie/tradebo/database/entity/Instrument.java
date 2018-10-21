package us.bojie.tradebo.database.entity;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Instrument {

    /**
     * margin_initial_ratio : 0.5000
     * rhs_tradability : tradable
     * id : 54db869e-f7d5-45fb-88f1-8d7072d4c8b2
     * market : https://api.robinhood.com/markets/XNAS/
     * simple_name : Alphabet
     * min_tick_size : null
     * maintenance_ratio : 0.2500
     * tradability : tradable
     * state : active
     * type : stock
     * tradeable : true
     * fundamentals : https://api.robinhood.com/fundamentals/GOOGL/
     * quote : https://api.robinhood.com/quotes/GOOGL/
     * symbol : GOOGL
     * day_trade_ratio : 0.2500
     * name : Alphabet Inc. Class A Common Stock
     * tradable_chain_id : 9f75b6b7-ef7e-4942-99b9-be7d81db8e6e
     * splits : https://api.robinhood.com/instruments/54db869e-f7d5-45fb-88f1-8d7072d4c8b2/splits/
     * url : https://api.robinhood.com/instruments/54db869e-f7d5-45fb-88f1-8d7072d4c8b2/
     * country : US
     * bloomberg_unique : EQ0000000044666717
     * list_date : 2004-08-19
     */

    @SerializedName("margin_initial_ratio")
    private String marginInitialRatio;
    @SerializedName("rhs_tradability")
    private String rhsTradability;
    @SerializedName("id")
    @PrimaryKey
    @NonNull
    private String id;
    @SerializedName("market")
    private String market;
    @SerializedName("simple_name")
    private String simpleName;
    @SerializedName("min_tick_size")
    private String minTickSize;
    @SerializedName("maintenance_ratio")
    private String maintenanceRatio;
    @SerializedName("tradability")
    private String tradability;
    @SerializedName("state")
    private String state;
    @SerializedName("type")
    private String type;
    @SerializedName("tradeable")
    private boolean tradeable;
    @SerializedName("fundamentals")
    private String fundamentals;
    @SerializedName("quote")
    private String quote;
    @SerializedName("symbol")
    private String symbol;
    @SerializedName("day_trade_ratio")
    private String dayTradeRatio;
    @SerializedName("name")
    private String name;
    @SerializedName("tradable_chain_id")
    private String tradableChainId;
    @SerializedName("splits")
    private String splits;
    @SerializedName("url")
    private String url;
    @SerializedName("country")
    private String country;
    @SerializedName("bloomberg_unique")
    private String bloombergUnique;
    @SerializedName("list_date")
    private String listDate;

    public Instrument() {
    }

    public String getMarginInitialRatio() {
        return marginInitialRatio;
    }

    public void setMarginInitialRatio(String marginInitialRatio) {
        this.marginInitialRatio = marginInitialRatio;
    }

    public String getRhsTradability() {
        return rhsTradability;
    }

    public void setRhsTradability(String rhsTradability) {
        this.rhsTradability = rhsTradability;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getMinTickSize() {
        return minTickSize;
    }

    public void setMinTickSize(String minTickSize) {
        this.minTickSize = minTickSize;
    }

    public String getMaintenanceRatio() {
        return maintenanceRatio;
    }

    public void setMaintenanceRatio(String maintenanceRatio) {
        this.maintenanceRatio = maintenanceRatio;
    }

    public String getTradability() {
        return tradability;
    }

    public void setTradability(String tradability) {
        this.tradability = tradability;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isTradeable() {
        return tradeable;
    }

    public void setTradeable(boolean tradeable) {
        this.tradeable = tradeable;
    }

    public String getFundamentals() {
        return fundamentals;
    }

    public void setFundamentals(String fundamentals) {
        this.fundamentals = fundamentals;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDayTradeRatio() {
        return dayTradeRatio;
    }

    public void setDayTradeRatio(String dayTradeRatio) {
        this.dayTradeRatio = dayTradeRatio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTradableChainId() {
        return tradableChainId;
    }

    public void setTradableChainId(String tradableChainId) {
        this.tradableChainId = tradableChainId;
    }

    public String getSplits() {
        return splits;
    }

    public void setSplits(String splits) {
        this.splits = splits;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBloombergUnique() {
        return bloombergUnique;
    }

    public void setBloombergUnique(String bloombergUnique) {
        this.bloombergUnique = bloombergUnique;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }
}
