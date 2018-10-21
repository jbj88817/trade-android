package us.bojie.tradebo.database.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import us.bojie.tradebo.database.converter.ExecutionsBeanConverter;

@Entity
public class RecentOrder {

    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("ref_id")
    private String refId;
    @SerializedName("time_in_force")
    private String timeInForce;
    private String cancel;
    private String id;
    @SerializedName("cumulative_quantity")
    private String cumulativeQuantity;
    @SerializedName("stop_price")
    private String stopPrice;
    @SerializedName("reject_reason")
    private String rejectReason;
    private String instrument;
    private String state;
    private String trigger;
    @SerializedName("override_dtbp_checks")
    private boolean overrideDtbpChecks;
    private String type;
    @SerializedName("last_transaction_at")
    private String lastTransactionAt;
    private String price;
    @SerializedName("extended_hours")
    private boolean extendedHours;
    private String account;
    @PrimaryKey
    private String url;
    @SerializedName("created_at")
    private String createdAt;
    private String side;
    @SerializedName("override_day_trade_checks")
    private boolean overrideDayTradeChecks;
    private String position;
    @SerializedName("average_price")
    private String averagePrice;
    private String quantity;
    @TypeConverters(ExecutionsBeanConverter.class)
    private List<ExecutionsBean> executions;

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCumulativeQuantity() {
        return cumulativeQuantity;
    }

    public void setCumulativeQuantity(String cumulativeQuantity) {
        this.cumulativeQuantity = cumulativeQuantity;
    }

    public String getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(String stopPrice) {
        this.stopPrice = stopPrice;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public boolean isOverrideDtbpChecks() {
        return overrideDtbpChecks;
    }

    public void setOverrideDtbpChecks(boolean overrideDtbpChecks) {
        this.overrideDtbpChecks = overrideDtbpChecks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastTransactionAt() {
        return lastTransactionAt;
    }

    public void setLastTransactionAt(String lastTransactionAt) {
        this.lastTransactionAt = lastTransactionAt;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isExtendedHours() {
        return extendedHours;
    }

    public void setExtendedHours(boolean extendedHours) {
        this.extendedHours = extendedHours;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public boolean isOverrideDayTradeChecks() {
        return overrideDayTradeChecks;
    }

    public void setOverrideDayTradeChecks(boolean overrideDayTradeChecks) {
        this.overrideDayTradeChecks = overrideDayTradeChecks;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public List<ExecutionsBean> getExecutions() {
        return executions;
    }

    public void setExecutions(List<ExecutionsBean> executions) {
        this.executions = executions;
    }

    public static class ExecutionsBean {
        /**
         * timestamp : 2018-10-11T17:29:05.547000Z
         * price : 69.68930000
         * settlement_date : 2018-10-15
         * id : 19bce5db-8bd1-4659-a6c4-934a4f8f5876
         * quantity : 7.00000
         */

        private String timestamp;
        private String price;
        @SerializedName("settlement_date")
        private String settlementDate;
        private String id;
        private String quantity;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSettlementDate() {
            return settlementDate;
        }

        public void setSettlementDate(String settlementDate) {
            this.settlementDate = settlementDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }
}
