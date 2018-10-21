package us.bojie.tradebo.database.entity;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class OwnedStock {

    @SerializedName("shares_held_for_stock_grants")
    private String sharesHeldForStockGrants;
    @SerializedName("account")
    private String account;
    @SerializedName("pending_average_buy_price")
    private String pendingAverageBuyPrice;
    @SerializedName("shares_held_for_options_events")
    private String sharesHeldForOptionsEvents;
    @SerializedName("intraday_average_buy_price")
    private String intradayAverageBuyPrice;
    @SerializedName("url")
    @PrimaryKey
    @NonNull
    private String url;
    @SerializedName("shares_held_for_options_collateral")
    private String sharesHeldForOptionsCollateral;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("shares_held_for_buys")
    private String sharesHeldForBuys;
    @SerializedName("average_buy_price")
    private String averageBuyPrice;
    @SerializedName("instrument")
    private String instrument;
    @SerializedName("intraday_quantity")
    private String intradayQuantity;
    @SerializedName("shares_held_for_sells")
    private String sharesHeldForSells;
    @SerializedName("shares_pending_from_options_events")
    private String sharesPendingFromOptionsEvents;
    @SerializedName("quantity")
    private String quantity;

    public OwnedStock() {
    }

    public String getSharesHeldForStockGrants() {
        return sharesHeldForStockGrants;
    }

    public void setSharesHeldForStockGrants(String sharesHeldForStockGrants) {
        this.sharesHeldForStockGrants = sharesHeldForStockGrants;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPendingAverageBuyPrice() {
        return pendingAverageBuyPrice;
    }

    public void setPendingAverageBuyPrice(String pendingAverageBuyPrice) {
        this.pendingAverageBuyPrice = pendingAverageBuyPrice;
    }

    public String getSharesHeldForOptionsEvents() {
        return sharesHeldForOptionsEvents;
    }

    public void setSharesHeldForOptionsEvents(String sharesHeldForOptionsEvents) {
        this.sharesHeldForOptionsEvents = sharesHeldForOptionsEvents;
    }

    public String getIntradayAverageBuyPrice() {
        return intradayAverageBuyPrice;
    }

    public void setIntradayAverageBuyPrice(String intradayAverageBuyPrice) {
        this.intradayAverageBuyPrice = intradayAverageBuyPrice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSharesHeldForOptionsCollateral() {
        return sharesHeldForOptionsCollateral;
    }

    public void setSharesHeldForOptionsCollateral(String sharesHeldForOptionsCollateral) {
        this.sharesHeldForOptionsCollateral = sharesHeldForOptionsCollateral;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSharesHeldForBuys() {
        return sharesHeldForBuys;
    }

    public void setSharesHeldForBuys(String sharesHeldForBuys) {
        this.sharesHeldForBuys = sharesHeldForBuys;
    }

    public String getAverageBuyPrice() {
        return averageBuyPrice;
    }

    public void setAverageBuyPrice(String averageBuyPrice) {
        this.averageBuyPrice = averageBuyPrice;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getIntradayQuantity() {
        return intradayQuantity;
    }

    public void setIntradayQuantity(String intradayQuantity) {
        this.intradayQuantity = intradayQuantity;
    }

    public String getSharesHeldForSells() {
        return sharesHeldForSells;
    }

    public void setSharesHeldForSells(String sharesHeldForSells) {
        this.sharesHeldForSells = sharesHeldForSells;
    }

    public String getSharesPendingFromOptionsEvents() {
        return sharesPendingFromOptionsEvents;
    }

    public void setSharesPendingFromOptionsEvents(String sharesPendingFromOptionsEvents) {
        this.sharesPendingFromOptionsEvents = sharesPendingFromOptionsEvents;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
